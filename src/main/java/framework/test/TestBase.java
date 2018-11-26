package framework.test;

import com.applitools.eyes.selenium.Eyes;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import framework.base.FrameworkProperties;
import framework.base.WebDriverFacade;
import framework.report.Log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public abstract class TestBase {
	
	private Eyes eyes;

	public static ThreadLocal<ExtentTest> report = new ThreadLocal<ExtentTest>();
	private static final String screenshots = new File(System.getProperty("user.dir")).getAbsolutePath() + File.separator + "screenshots" + File.separator; 

	public static void setReport(ExtentTest rep) {
		report.set(rep);
	}

	public static ExtentTest getReport() {
		return report.get();
	}
	
	public Eyes getEyes() {
		if(eyes == null) {
			eyes = new Eyes();
		}
		return eyes;
	}

	/**
	 * Driver Creation
	 * @throws URISyntaxException 
	 */
	@BeforeSuite
	public void setUp(ITestContext context) throws IOException, URISyntaxException{
	    getEyes().setApiKey(FrameworkProperties.getApplitoolsApiKey());
		Path screenshotsPath = new File(screenshots).toPath();
		if(!Files.exists(screenshotsPath))
		{
			Files.createDirectory(screenshotsPath);
		}
		System.setProperty("log4j.configurationFile", "log4j2-config.xml");
		System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
	}

	@BeforeMethod
	public void setUpPage(ITestContext context, Method method) {
        ThreadContext.put("threadName", context.getName());
        Log.logger = LogManager.getLogger(getClass());
		WebDriverFacade.createDriver("FULL");
        Log.testStart(context.getName());
        Log.testDescription(method.getAnnotation(Test.class).description());
	}

	@AfterMethod(alwaysRun = true)
	public void closeApp(ITestResult result, ITestContext context, Method method) {
		String imageTitle = context.getName() + "_image_error";
		String imagePath = screenshots + imageTitle + ".png";
		try {
		if (result.getStatus() == ITestResult.FAILURE) {
			if ((Log.failReason != null && !Log.failReason.isEmpty())) Log.testFailReason();
			Log.testFail(context.getName());
			WebDriverFacade.takeScreenshot(imagePath);
			getReport().fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(new String(Base64.getEncoder().encode(Files.readAllBytes(new File(imagePath).toPath())))).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			Log.testSkipped(context.getName());
			WebDriverFacade.takeScreenshot(imagePath);
			getReport().skip(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(new String(Base64.getEncoder().encode(Files.readAllBytes(new File(imagePath).toPath())))).build());
		}
	     } catch (IOException e) {
			e.printStackTrace();
			getReport().info("There was an error capturing screenshot > " + e.getMessage() + " caused by > " + e.getCause().getMessage());
		}
			WebDriverFacade.shutdown();
			Log.testEnd(context.getName());
	}
	
	@AfterSuite(alwaysRun = true)
	public void endSuite()
	{
		if (Files.exists(new File(screenshots).toPath())) {
			File screenshotFolder = new File(screenshots);
			File[] files = screenshotFolder.listFiles();
			for (File f : files) {
				f.deleteOnExit();
			}
		}
		if(eyes.getIsOpen()){
			eyes.close(false);
		}
			
	}
}
