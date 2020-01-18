package utils;

import io.restassured.RestAssured;

public class ApiUtils {
	
	private final static String getRandomZipCode = "https://api.postcodes.io/random/postcodes";
	private final static String validateZipCode = "https://api.postcodes.io/postcodes/%s/validate";
	
	/**
	 * Gets the random zip code.
	 *
	 * @return the random zip code
	 */
	public static String getRandomZipCode() {
		RestAssured.baseURI = getRandomZipCode;
		return RestAssured.get().body().jsonPath().getString("result.postcode");
	}
	
	public static boolean validateZipCode(String zipcode) {
		RestAssured.baseURI = String.format(validateZipCode, zipcode);
		return RestAssured.get().body().jsonPath().getBoolean("result");
	}

}
