package framework.test;

import java.util.List;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;

import framework.base.FrameworkProperties;

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
			suite.setParallel(ParallelMode.getValidParallel(FrameworkProperties.getParallelType()));
			suite.setThreadCount(Integer.valueOf(FrameworkProperties.getParallel()).intValue());
			suite.setDataProviderThreadCount(2);
	}
}
