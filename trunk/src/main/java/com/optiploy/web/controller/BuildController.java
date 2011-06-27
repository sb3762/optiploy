package com.optiploy.web.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.optiploy.application.BuildApplication;
import com.optiploy.exception.DataNotFoundException;
import com.optiploy.exception.OptiployException;
import com.optiploy.model.Agent;
import com.optiploy.model.Build;
import com.optiploy.model.Job;
import com.optiploy.model.Log;
import com.optiploy.service.AgentService;
import com.optiploy.service.JobService;
import com.optiploy.web.listener.StartupListener;


public class BuildController extends AbstractController
{

private static Logger logger = Logger.getLogger(BuildController.class);
	
	private BuildApplication buildApplication;
	private AgentService agentService;
	private JobService jobService;	
	private Agent agent;
	private Job job;
	private Build build; 
	private Log log;
	
	private Locale locale;
	
	@SuppressWarnings("unchecked")
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)throws UsernameNotFoundException
	{	
		StartupListener.setupContext(request.getSession().getServletContext());	
		
		locale = request.getLocale();
				
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		
		Enumeration<String> enumeration = request.getParameterNames();
		
		while(enumeration.hasMoreElements())
		{
			String key = (String)enumeration.nextElement();
			String value = request.getParameter(key);
			
			parameterMap.put(key, value);			
		}
		
		if(parameterMap.containsKey("jobId"))
		{
			int jobId = Integer.parseInt((String) parameterMap.get("jobId"));
			
			parameterMap.remove("jobId");
						
			try
			{
				log = buildApplication.startBuild(jobId, parameterMap);					
				job = jobService.findById(jobId);
				
				String agentName = "N/A";
				String startTimestamp = "N/A";
				String completeTimeStamp = "N/A";
				
				if(log.getAgentId() != null)
				{
					agent = agentService.findById(log.getAgentId());
					agentName = agent.getName();
				}
					
				if(log.getStart() != null && log.getComplete()!= null)
				{
					startTimestamp = log.getStart().toString();
					completeTimeStamp = log.getComplete().toString();
				}
				
				build = new Build();
				
				build.setJobId(jobId);
				build.setJobName(job.getName());
				build.setJobDecription(job.getDescription());
				build.setProcessingAgent(agentName);
				build.setStartTimestamp(startTimestamp);
				build.setCompleteTimestamp(completeTimeStamp);
				build.setBuildMessages(log.getBuildMessage());
				build.setSuccess(log.isSuccess());
				
			} 
			catch (DataNotFoundException e)
			{
				logger.error("DataNotFoundException: " + e);
			} 
			catch (OptiployException e)
			{
				logger.error("OptiployException: " + e);
			}
		}
		else
		{
			// To do..
		}		
			return new ModelAndView("buildComplete","build",build);					
	}
		
	@Required
	public void setBuildApplication(BuildApplication buildApplication)
	{
		this.buildApplication = buildApplication;
	}
	@Required
	public void setJobService(JobService jobService)
	{
		this.jobService = jobService;
	}
	@Required
	public void setAgentService(AgentService agentService)
	{
		this.agentService = agentService;
	}
}
