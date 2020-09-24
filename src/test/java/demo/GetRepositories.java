package demo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import framework.base.FrameworkProperties;
import framework.report.Log;
import framework.test.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRepositories extends TestBase {
	
	@DataProvider (name = "data-provider")
	 public Object[][] dpMethod(){
	 return new Object[][] {{"charl1echa1ns1lver", "OhpenAPI"}};
	 }
	
    @Test( 	groups = {"demo"},
    		dataProvider = "data-provider",
            priority = 1,
            testName="TC01_GetGitUser",
            description="Get all git user information" )
    public void testGetUserInfo(String username, String expectedRepo) throws InstantiationException, IllegalAccessException{
    	RestAssured.baseURI = FrameworkProperties.getBaseUrl() + "/users/" + username + "/repos";
    	Log.logger.info("Validate that service response is OK with status code 200");
    	Response response = RestAssured.given().auth().preemptive().oauth2(FrameworkProperties.getOAuth()).get();
		int statusCode = response.getStatusCode();
    	Assert.assertTrue( response.getStatusCode() == 200, "Service response was " + statusCode);
    	List<String> repos = response.body().jsonPath().getList("name");
    	Log.logger.info("Validate that service response contains repo expected  " + expectedRepo);
    	Assert.assertTrue(repos.stream().anyMatch(x -> x.equals(expectedRepo)), "Service response does not contain repo " + expectedRepo);
    	response.body().prettyPeek();
    }
}
