package framework.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import framework.report.Log;

public class FrameworkProperties {
	
	public static Properties props = FrameworkProperties.GetConfig();

	// region Initialize Properties

	/**
	 * Singleton to initialize config.properties
	 * @return the mapped properties included in the file
	 * @author carlos.cadena
	 */
	private static Properties GetConfig()
	{
		if(props == null)
		{
			props = initializeProperties("config.properties");			
	}
		return props;
		
	}	
	/**
	 * Method to initialize property files located in default properties location
	 * @param fileName
	 * @return the properties object
	 * @author carlos.cadena
	 */
	private static Properties initializeProperties(String fileName)
	{
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("src/test/resources/" + fileName);
			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			Log.logger.fatal("Loading properties file '" +  fileName  + "' failed, check configuration file location");
	}
		return prop;
	}

	 /**
     * Return BASE_URL environment variable, and if null 'base.url' property value is returned
     * @return the property value
     * @author carlos.cadena
     */
    public static String getBaseUrl() {
        if(System.getProperty("BASE_URL") == null)
        {
            return props.getProperty("base.url");
        }
        return System.getProperty("BASE_URL");
    }

      
	/**
	 * Return OAUTHTOKEN variable, and if null 'oauth' property value is returned
	 * @return the property value
	 * @author carlos.cadena
	 */
	public static String getOAuth() {
		if(System.getProperty("OAUTH") == null)
		{
			return props.getProperty("oauth");
		}
		return System.getProperty("OAUTH");
	}
	
	/**
	 * Return PARALLEL variable, and if null 'parallel' property value is returned
	 * @return the property value
	 * @author carlos.cadena
	 */
	public static String getParallel() {
		if(System.getProperty("PARALLEL") == null)
		{
			return props.getProperty("parallel");
		}
		return System.getProperty("PARALLEL");
	}
	
	/**
	 * Return PARALLEL_TYPE variable, and if null 'parallel.type' property value is returned
	 * @return the property value
	 * @author carlos.cadena
	 */
	public static String getParallelType() {
		if(System.getProperty("PARALLEL_TYPE") == null)
		{
			return props.getProperty("parallel.type");
		}
		return System.getProperty("PARALLEL_TYPE");
	}



	

	}
