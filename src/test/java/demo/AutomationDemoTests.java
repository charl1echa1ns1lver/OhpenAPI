package demo;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.base.FrameworkProperties;
import framework.base.WebDriverFacade;
import framework.test.TestBase;

public class AutomationDemoTests extends TestBase {
	

    @Parameters({"article"})
    @Test( 	groups = {"demo"},
            priority = 1,
            testName="Purchase Article Test",
            description=" Purchase article test with different articles" )
    public void testArticlePurchase(String article){
    	WebDriverFacade.navigateTo("google.com");
    	WebDriverFacade.navigateTo(FrameworkProperties.getBaseUrl());
    }

}
