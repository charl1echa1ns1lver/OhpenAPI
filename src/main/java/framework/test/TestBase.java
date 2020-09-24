package framework.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import dto.GitUser;
import framework.report.Log;

public abstract class TestBase {
	
	public static ThreadLocal<ExtentTest> report = new ThreadLocal<ExtentTest>();
	private ThreadLocal<GitUser> user;

	public static void setReport(ExtentTest rep) {
		report.set(rep);
	}

	public static ExtentTest getReport() {
		return report.get();
	}
	
	public GitUser getBorrower() {
		return user.get();
	}
	
	public void setBorrower(GitUser borrower) {
		user.set(borrower);
	}
	
	/**
	 * Driver Creation
	 * @throws URISyntaxException 
	 */
	@BeforeSuite
	public void setUp(ITestContext context) throws IOException, URISyntaxException{
		System.setProperty("log4j.configurationFile", "log4j2-config.xml");
		System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
	}

	@BeforeMethod
	public void setUpPage(ITestContext context, Method method) throws MalformedURLException {
        ThreadContext.put("threadName", context.getName());
        Log.logger = LogManager.getLogger(getClass());
		Log.testStart(context.getName());
		Log.testDescription(method.getAnnotation(Test.class).description());
	}

	@AfterMethod(alwaysRun = true)
	public void closeApp(ITestResult result, ITestContext context, Method method) {
		if (result.getStatus() == ITestResult.FAILURE) {
			if ((Log.failReason != null && !Log.failReason.isEmpty())) Log.testFailReason();
			Log.testFail(context.getName());
			getReport().fail(result.getThrowable().getMessage());
		} else if (result.getStatus() == ITestResult.SKIP) {
			Log.testSkipped(context.getName());
			getReport().skip(result.getThrowable().getMessage());
		}
			Log.testEnd(context.getName());
	}
	
	@AfterSuite(alwaysRun = true)
	public void endSuite()
	{
		
	}
}
