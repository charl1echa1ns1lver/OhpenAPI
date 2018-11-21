package demo;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AutomationDemoTests {
	

    @Test( 	groups = {"demo"},
            priority = 1,
            testName="Purchase article test",
            description=" Purchase article test with different articles " )
    @Parameters(value = {"environment","language"})
    public void testArticlePurchase(String articleName, String language){
    	
    }

}
