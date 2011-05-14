package com.optiploy.property;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

public class OptiployProperties
{
	private static Logger logger = Logger.getLogger(OptiployProperties.class);

	private static Properties properties = null;
		
	public OptiployProperties()
	{
		if (properties == null) 
		{
			try 
			{
				properties = new Properties();

				FileInputStream fFIS = new FileInputStream(	"/Optiploy/conf/optiploy.properties");
				properties.load(fFIS);
				fFIS.close();

				logger.info("Successfully loaded the opti.properties from ../conf/optiploy.properties");
			}

			catch (Exception e) 
			{
				logger.error("There was a problem the properties file from ../conf/optiploy.properties");

				try 
				{
					URL url = OptiployProperties.class.getResource("optiploy.properties");

					if (url != null) 
					{
						InputStream in = url.openStream();
						properties = new Properties();
						properties.load(in);
						in.close();

						logger.info("Successfully loaded the optiploy.properties from inside the jar file");
					}
					else 
					{
						logger.fatal("Could not find optiploy.properties inside the application!");
					}
				}
				catch (Exception e2) 
				{
					logger.fatal("Fatal problem occured trying to load properties from inside the jar file",e2);
				}
			}
		}
	}
	public String getPropertyValue(String propertyName)
	{
		return properties.getProperty(propertyName);
	}	
	
}
