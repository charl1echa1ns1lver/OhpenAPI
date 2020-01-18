package utils;

import java.util.List;
import java.util.Random;

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

}
