package framework.report;

import java.time.LocalDateTime;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    
    private static ExtentReports extent;
    
    public static ExtentReports getInstance(String suiteName) {
    	if (extent == null)
    		createInstance(suiteName);
    	
        return extent;
    }
    
    
    public static ExtentReports createInstance(String suiteName) {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        String date = String.format("%d-%02d-%02d(%02d.%02d)", year, month, day, hour, minute);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/AutomationReport_"+ date +".html");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(suiteName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(suiteName);
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        return extent;
    }
}