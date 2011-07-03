package com.optiploy.agent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.optiploy.constants.Constants;
import com.optiploy.exception.OptiployException;
import com.optiploy.model.Agent;
import com.optiploy.model.Instance;
import com.optiploy.model.Property;
import com.optiploy.packet.Packet;
import com.optiploy.property.OptiployProperties;
import com.optiploy.service.AgentService;
import com.optiploy.service.InstanceService;
import com.optiploy.service.PropertyService;
import com.optiploy.util.GeneralUtil;


public class AgentServer
{
	private static Logger logger = Logger.getLogger(AgentServer.class);
	
	public static boolean shutdown = false;
	
	private static Agent agent;
	private static Instance instance;
	
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-agent.xml");
	
	private static AgentService agentService = (AgentService)applicationContext.getBean("agentService");
	private static InstanceService instanceService = (InstanceService)applicationContext.getBean("instanceService");
	private static PropertyService propertyService = (PropertyService)applicationContext.getBean("propertyService");
	
	private static Property propertyServerVersion = propertyService.getPropertyByPropertyName(Constants.VERSION);
	private static Property propertyLocalTest = propertyService.getPropertyByPropertyName(Constants.ADMIN_LOCAL_TEST);
	
	private static OptiployProperties optiployProperties = (OptiployProperties)applicationContext.getBean("optiployProperties");
	
	private static List<BuildInstance> instances = new ArrayList<BuildInstance>();
	private static BuildInstance buildInstance;
	
	
	
		
	
	public static void main(String[] args) throws OptiployException
    {	
		try
		{		
			String host = InetAddress.getLocalHost().getHostName();
			String address = InetAddress.getLocalHost().getHostAddress();				
			
			try
			{
				agentService.getAgentByAgentName(host);
				
				logger.debug("Agent already in the database.  Updating information: " + host);
				
				agent = agentService.getAgentByAgentName(host);
				agent.setDescription(optiployProperties.getPropertyValue(Constants.AGENT_DECRIPTION));
				agent.setAddress(address);
				agent.setPort(Integer.parseInt(optiployProperties.getPropertyValue(Constants.AGENT_PORT)));
				agent.setVersion(optiployProperties.getPropertyValue(Constants.VERSION));
				agent.setStatus(Constants.AGENT_STATUS_RUNNING);
				agentService.update(agent);	
				
				logger.debug("Existing agent updated: " + host);
			}
			catch(IndexOutOfBoundsException e)
			{	
				logger.debug("Adding new agent: " + host);
				
				agent = new Agent();
				
				agent.setName(host);
				agent.setDescription(optiployProperties.getPropertyValue(Constants.AGENT_DECRIPTION));
				agent.setAddress(address);
				agent.setPort(Integer.parseInt(optiployProperties.getPropertyValue(Constants.AGENT_PORT)));
				agent.setVersion(optiployProperties.getPropertyValue(Constants.VERSION));
				agent.setPriority(1);
				agent.setInstances(1);
				agent.setStatus(Constants.AGENT_STATUS_RUNNING);
				
				agent = agentService.merge(agent);
				
				logger.debug("New agent added: " + host);				
			}	
			
			if (!propertyServerVersion.getValue().equals(agent.getVersion()))
			{
				agent.setStatus(Constants.AGENT_STATUS_INCOMPATIBLE);
				agentService.update(agent);
				
				logger.error("Agent is incompatable with server");
				logger.error("Server version: " + propertyServerVersion.getValue());
				logger.error("Agent version: " + agent.getVersion());
				logger.error("Shutting down agent");
				
				System.exit(0);
			}		
			
			createInstances();	
			
			try
	        {
				ServerSocket server = new ServerSocket(agent.getPort());
				Socket socket;
				
				ObjectInputStream in;
				Packet request;
				Packet response;
		            
	            logger.info("Server waiting for connection");			

	            while (!shutdown)
	            {
	                socket = server.accept();
	                logger.info("Connection made to agent...");
	                
	                in = new ObjectInputStream(socket.getInputStream());
	                request = (Packet) in.readObject();
	                
	                if(request.getRequestType().equals(Constants.AGENT_HARD_STOP))
	                {
	                	destroyInstances();
	                }
	                else if(request.getRequestType().equals(Constants.AGENT_START_INSTANCES))
	                {
	                	destroyInstances();
	                	createInstances();
	                }
	                
	                response = new Packet();
	                response.setRequestType(request.getRequestType());
	            }		
	        }
	        catch(Exception e)
	        {
	        	logger.error("Error in Agent Server",e);
	        }
			
		} 
		catch (UnknownHostException e)
		{
			logger.error("Can't get localhost name: " + e);
		}	
    }
	
	private static void createInstances()
	{
		String priority = optiployProperties.getPropertyValue("Priority");
		
		try
		{			
			instanceService.deleteAllInstances(agent.getId(), agent.getName());
						
			int ports[] = GeneralUtil.nextOpenPort((agent.getPort()+1),(agent.getPort()+100),agent.getInstances());
			
			
			for(int i = 0; i < agent.getInstances(); i++)
			{
				instance = new Instance();
				instance.setAgentId(agent.getId());
				instance.setPort(ports[i]);
				instance.setStatus(Constants.INSTANCE_STATUS_READY);
				
				if(priority.equalsIgnoreCase("Order"))
					instance.setPriority(i + 1);
				if(priority.equalsIgnoreCase("Secondary"))
					instance.setPriority(i + 2);
				else if(priority.equalsIgnoreCase("High"))	
					instance.setPriority(1);
				else if(priority.equalsIgnoreCase("Medium"))	
					instance.setPriority(3);	
				else if(priority.equalsIgnoreCase("Low"))	
					instance.setPriority(5);
				else
					instance.setPriority(10);
				
				instance = instanceService.merge(instance);
				
				buildInstance = new BuildInstance(instance, agent);
				
				buildInstance.start();
				
				instances.add(buildInstance);
			}	
			
		}
		catch(Exception e)
		{
			logger.error("Problem creating instances for this agent",e);
		}
	}
	
	private static void destroyInstances()
	{
			Iterator<BuildInstance> itr = instances.iterator();
			
			while(itr.hasNext())
			{
				itr.next().interrupt();
				logger.debug("Destroyed instance");				
			}
			
			instances.clear();
			
			
			
		/*
		try
		{
			List<Instance> instances = instanceService.getReadyInstances();
			
			Iterator it = instances.iterator();
			
			while(it.hasNext())
			{
				Instance instance = (Instance)it.next();
				
				Socket socket;
				
				if(propertyLocalTest.getValue().equals("true"))
					socket = new Socket("127.0.0.1", instance.getPort());
            	else
            		socket = new Socket(agent.getAddress(), instance.getPort());
				
				Packet request = new Packet();            	
	        	
	        	request.setRequestType(Constants.REQUEST_TYPE_SHUTDOWN);
	        	request.setParameter(Constants.AGENT_HARD_STOP, "true");
	        	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

	        	System.out.println("Sending instance shutdown packet: " + request.getRequestType());
	            out.writeObject(request);

	            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
	            Packet response = (Packet) in.readObject();
	            System.out.println("Received response packet: ");
			}
			
			for(int i = 0; it.hasNext(); i++)
			{
				
			}
			
			logger.info("All instances shutdown");
		} 
		catch (Exception e)
		{
			logger.error("Error shutting down threads",e);
		}
		
		*/
		
	}
	
}
