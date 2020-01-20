package framework.report;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import framework.base.FrameworkProperties;
import framework.test.TestBase;

public class ReportListener implements ITestListener, ISuiteListener {

	private ExtentReports extent;
	private static ExtentTest parentTest;
	
	/**
	 * On this method a test section is added in the report for each executed test in the suite
	 * @author carlos.cadena
	 */
	@Override
	public synchronized void onStart(ITestContext context) {
		ExtentTest child = parentTest.createNode(context.getName());
		TestBase.setReport(child);
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {

	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		TestBase.getReport().pass("Test passed");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
	}

	/**
	 * On this method the report instance is created for the suite and the environment information is retrieved
	 * @author carlos.cadena
	 */
	@Override
	public void onStart(ISuite suite) {
		extent = ExtentManager.getInstance(suite.getName());
		extent.setSystemInfo("Browser", FrameworkProperties.getBrowser());
		parentTest = extent.createTest(suite.getName());
	}

	/**
	 * On this method the logging area in the report gets populated with the info collected on 
	 * the .log files generated for each test
	 * @author carlos.cadena
	 */
	@Override
	public void onFinish(ISuite suite) {
		String results = "";
		CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
		decoder.onMalformedInput(CodingErrorAction.IGNORE);
		try {
			File dir = new File(".");
			File[] files = dir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".log");
				}
			});
			for (File file : files) {
				FileInputStream input = new FileInputStream(file);
				InputStreamReader reader = new InputStreamReader(input, decoder);
				BufferedReader bufferedreader = new BufferedReader(reader);
				String line = null;
				while ((line = bufferedreader.readLine()) != null) {
					results += line;
					results += System.getProperty("line.separator") + "<br>";
				}
				bufferedreader.close();
				file.deleteOnExit();
			}

		} catch (IOException x) {
            x.printStackTrace();
		}

		extent.setTestRunnerOutput(results);

		extent.flush();
		
		this.modifyHtmlImageNames();
    }

	/**
	 * This private method modifies the label "base-64" on report since MediaEntityBuilder class on Extent Reports
	 * does not have a function to do so when you want to encode in base64 the screenshot image
	 * @author carlos.cadena
	 */
	private void modifyHtmlImageNames() {
		try {
			ExtentHtmlReporter htmlReporter = (ExtentHtmlReporter) extent.getStartedReporters().get(0);
			String path = htmlReporter.config().getFilePath();
			File reportFile = new File(path);
			Document doc = Jsoup.parse(reportFile, "UTF-8", "");
			Elements imageLabels = doc.select("span[class*='label grey white-text']");
			for (Element x : imageLabels) {
				x.text("image");
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			writer.write(doc.outerHtml());
			writer.close();

		} catch (IOException x) {
            x.printStackTrace();
		}

	}
}
