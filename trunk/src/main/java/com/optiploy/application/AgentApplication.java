package com.optiploy.application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.optiploy.constants.Constants;
import com.optiploy.model.Agent;
import com.optiploy.packet.Packet;
import com.optiploy.service.AgentService;

public class AgentApplication
{	
	private static Logger logger = Logger.getLogger(AgentApplication.class);
	
	private AgentService agentService;	
	private Agent agent;
	
	public AgentApplication()
	{
		
	}
	
	public void restartInstances(int agentId)
	{
		Socket socket = null;
		
		try
		{
			agent = (Agent) agentService.findById(agentId);
			
        	socket = new Socket(agent.getAddress(),agent.getPort());
        	
        	Packet request = new Packet();            	
        	
        	request.setRequestType(Constants.AGENT_RESTART_INSTANCES);
        	        	
        	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            logger.debug("Sending request packet: " + request.getRequestType());
            out.writeObject(request);
            
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Packet response = (Packet) in.readObject();
            
            logger.debug("Received response packet | Running: " + response.getParameter(Constants.AGENT_STATUS_RUNNING));
		}
		catch (IOException e)
        {
        	agent.setStatus(Constants.AGENT_STATUS_DOWN);        	
        } 
		catch (ClassNotFoundException e)
		{
			agent.setStatus(Constants.AGENT_STATUS_INCOMPATIBLE);
		}
		finally
		{
			try
			{
				if (socket != null)
                    socket.close();
			}
			catch(IOException e)
			{
				logger.error("SERVER: IOException caught trying to close connection to Agent ID "
                        + agent.getId()
                        + ": "
                        + e.getMessage());
			}			
		}
    }	
	
	@Required
	public void setAgentService(AgentService agentService)
	{
		this.agentService = agentService;
	}
}
