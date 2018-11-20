package framework.base;

import com.applitools.eyes.selenium.Eyes;

/**
 * Interface that allow a page class to implement visual tests
 * @author carlos.cadena
 *
 */
public interface Visualizable {
	
	public void performVisualTest(Eyes eyes);

}
