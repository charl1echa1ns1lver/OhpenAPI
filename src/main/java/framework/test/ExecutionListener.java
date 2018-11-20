package framework.test;

import framework.base.FrameworkProperties;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExecutionListener implements IAlterSuiteListener {

	@Override
	public void alter(List<XmlSuite> suites) {
		XmlSuite suite = suites.get(0);
		this.alterParallel(suite);

	}
	
	/**
	 * Alter parallel execution parameters for suite
	 * @param suite
	 * @author carlos.cadena
	 */
	private void alterParallel(XmlSuite suite)
	{
		if (System.getProperty("parallel") != null && System.getProperty("threadCount") != null) {
			suite.setParallel(ParallelMode.getValidParallel(System.getProperty("parallel")));
			suite.setThreadCount(Integer.valueOf(System.getProperty("threadCount")).intValue());
		}

		else {
			suite.setParallel(ParallelMode.getValidParallel(FrameworkProperties.props.getProperty("parallel.type")));
			suite.setThreadCount(Integer.valueOf(FrameworkProperties.props.getProperty("parallel.devices")));
		}
	}
}
