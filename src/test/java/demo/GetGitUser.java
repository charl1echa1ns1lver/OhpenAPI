package demo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import framework.base.FrameworkProperties;
import framework.report.Log;
import framework.test.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetGitUser extends TestBase {
	
	@DataProvider (name = "data-provider2")
	 public Object[][] dpMethod2(){
	 return new Object[][] {{"charl1echa1ns1lver"}};
	 }
	
    @Test( 	groups = {"demo"},
    		dataProvider = "data-provider2",
            priority = 1,
            testName="TC01_GetGitUser",
            description="Get all git user information" )
    public void testGetUserInfo(String username) throws InstantiationException, IllegalAccessException{
    	RestAssured.baseURI = FrameworkProperties.getBaseUrl() + "/users/" + username;
    	Log.logger.info("Validate that service response is OK with status code 200");
    	Response response = RestAssured.given().auth().preemptive().oauth2(FrameworkProperties.getOAuth()).get();
		int statusCode = response.getStatusCode();
    	Assert.assertTrue( response.getStatusCode() == 200, "Service response was " + statusCode);
    	String user = response.body().jsonPath().getString("login");
    	Log.logger.info("Validate that service response contains user " + username);
    	Assert.assertEquals(user, username, "username validation failed");
    	response.body().prettyPeek();
    }
}
