package utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.GitUser;
import framework.report.Log;

/**
 * The Class Utils.
 */
public class TestUtils {
	
	/**
	 * Gets the random element from list.
	 *
	 * @param <T> the generic type
	 * @param list the list
	 * @return the random element from list
	 */
	public static <T> T getRandomElementFromList(List<T> list) {
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}
	
	/**
	 * Random between a min an a max number converted to String.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the string
	 */
	public static String randomBetween(int min, int max) {
		Random rand = new Random();
		return String.valueOf(rand.ints(min, (max + 1)).limit(1).findFirst().getAsInt());
	}
	
	/**
	 * Random date.
	 *
	 * @param start the start inclusive
	 * @param end the end exclusive
	 * @return the date
	 */
	public static LocalDate randomDate(LocalDate start, LocalDate end) {
		long startEpochDay = start.toEpochDay();
		long endEpochDay = end.toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

		return LocalDate.ofEpochDay(randomDay);
	}
	
	/**
	 * Creates the json test file.
	 *
	 * @param borrower the borrower
	 * @throws JsonProcessingException 
	 */
	public static <T> void printBodyResponse(String testName, T data) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Log.logger.info("Service Response Body was" + mapper.writeValueAsString(data));
		
	}

}
