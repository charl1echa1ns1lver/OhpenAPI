package framework.base;

import framework.report.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
	 * Return TIMEOUT environment variable, and if null 'timeout' property value is returned
	 * @return the property value
	 * @author carlos.cadena
	 */
	public static String getTimeout() {
		if(System.getProperty("TIMEOUT") == null)
		{
			return props.getProperty("timeout");
		}
		return System.getProperty("TIMEOUT");
	}

    /**
     * Return LOCAL environment variable, and if null 'local' property value is returned
     * @return the property value
     * @author carlos.cadena
     */
    public static String getLocal() {
        if(System.getProperty("LOCAL") == null)
        {
            return props.getProperty("local");
        }
        return System.getProperty("LOCAL");
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
     * Return BROWSER_VERSION environment variable, and if null 'browser.version' property value is returned
     * @return the property value
     * @author carlos.cadena
     */
    public static String getBrowserVersion() {
        if(System.getProperty("BROWSER_VERSION") == null)
        {
            return props.getProperty("browser.version");
        }
        return System.getProperty("BROWSER_VERSION");
    }
    
	/**
	 * Return BROWSER variable, and if null 'browser' property value is returned
	 * @return the property value
	 * @author carlos.cadena
	 */
	public static String getBrowser() {
		if(System.getProperty("BROWSER") == null)
		{
			return props.getProperty("browser");
		}
		return System.getProperty("BROWSER");
	}
	
	/**
	 * Return APPLITOOLS_APIKEY variable, and if null 'applitools.apikey' property value is returned
	 * @return the property value
	 * @author carlos.cadena
	 */
	public static String getApplitoolsApiKey() {
		if(System.getProperty("APPLITOOLS_APIKEY") == null)
		{
			return props.getProperty("applitools.apikey");
		}
		return System.getProperty("APPLITOOLS_APIKEY");
	}



	}
