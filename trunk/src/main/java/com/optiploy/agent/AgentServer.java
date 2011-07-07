package com.optiploy.agent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
			
			Agent agent = null;
			
			try
			{				
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
			
			createInstances(agent.getId());	
			
			try
	        {
				ServerSocket server = new ServerSocket(agent.getPort());
				Socket socket;
				
				ObjectInputStream in;
				ObjectOutputStream out;
				Packet request;
				Packet response;            			

	            while (!shutdown)
	            {
	            	logger.info("Server waiting for connection");
	            	
	                socket = server.accept();
	                logger.info("Connection made to agent...");
	                
	                in = new ObjectInputStream(socket.getInputStream());
	                out = new ObjectOutputStream(socket.getOutputStream());
	                
	                request = (Packet) in.readObject();	 
	                response = new Packet();	                
	               
	                if(request.getRequestType().equals(Constants.AGENT_START_INSTANCES))
	                {
	                	createInstances(agent.getId());
	                	response.setParameter(Constants.AGENT_STATUS_RUNNING, Boolean.TRUE);
	                }
	                else if(request.getRequestType().equals(Constants.AGENT_STOP_INSTANCES))
	                {
	                	destroyInstances();
	                	response.setParameter(Constants.AGENT_STATUS_INSTANCES_DOWN, Boolean.TRUE);
	                }
	                else if(request.getRequestType().equals(Constants.AGENT_RESTART_INSTANCES))
	                {
	                	destroyInstances();
	                	createInstances(agent.getId());
	                	response.setParameter(Constants.AGENT_STATUS_RUNNING, Boolean.TRUE);
	                }
	                else if(request.getRequestType().equals(Constants.AGENT_HARD_STOP))
	                {
	                	destroyInstances();
	                	shutdownAgent();
	                	response.setParameter(Constants.AGENT_STATUS_DOWN, Boolean.TRUE);
	                }	                
	                
	                response.setRequestType(request.getRequestType());
	                
	                logger.debug("Sending response packet: " + request.getRequestType());
	                out.writeObject(response);
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
	
	private static void createInstances(int agentId)
	{
		Agent agent = agentService.findById(agentId);
		
		String priority = optiployProperties.getPropertyValue("Priority");
		
		try
		{			
			instanceService.deleteAllInstances(agent.getId(), agent.getName());
						
			int ports[] = GeneralUtil.openPorts((agent.getPort()+1),(agent.getPort()+100),agent.getInstances());
			
			
			for(int i = 0; i < agent.getInstances(); i++)
			{
				Instance instance = new Instance();
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
	}
	
	private static void shutdownAgent()
	{
		logger.error("Shutting down agent");
		
		System.exit(0);
	}
	
}
