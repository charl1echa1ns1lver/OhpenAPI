# AlphaFwkDemo

Prerequisites:

Maven 3.5.4 or higher
Java 1.8.1 or higher


To execute follow the next steps:

1. clone project using https > https://github.com/charl1echa1ns1lver/AlphaFwk.git

On pom.xml :

			<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>2.22.0</version>
				<configuration>
					<suiteXmlFiles>
						<suiteXmlFile>${suiteXmlFile}</suiteXmlFile>
					</suiteXmlFiles>
				</configuration>

suiteXmlFile variable could be any suite you define in the fwk, for the sake of simplicity the phptravels xml has been hardcoded and does not need to be provided in the command line (phptravels.xml):

<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<testng.version>6.10</testng.version>
		<appium.version>6.0.0</appium.version>
		<suiteXmlFile>./src/test/java/suites/phptravels.xml</suiteXmlFile>
	</properties>

2. To execute the tests you have two ways:

Alternative 1:

- Using a command line navigate to the root folder where your project was cloned and execute this maven command:

First time:

mvn clean install -DPARALLEL=2 -DBROWSER=Edge

If install command was run the first time then you just simply execute for next runs:

mvn clean install -DPARALLEL=2 -DBROWSER=Edge

PARALLEL option could be any number you desired for parallel execution (2 by default)
BROWSER option could be Edge,Chrome or Firefox (case could be upper, lower or title)

There are other parameters that can be provided like SUITEXMLFILE as mentioned above,  TIMEOUT (if you want a custom timeout to be set for the explicit waits, 60 s by default) or BASE_URL (https://www.phptravels.net by default)

Note: 

Alternative 2 (Using eclipse)

- After cloning the project, imported and configure the Java Facet if needed (sometimes this does not happen automatically). When imported Eclipse should be able to recognize this as a Maven Project

- Go to ./src/test/java/suites/phptravels.xml > right click > Run as TestNG Suite

A Report generated using Extent Reports is created after each execution that can be found in:

./test-output with the name AutomationReport_YYYY-MM-DD(hh.mm)

NOTE about alternative 1:

On pom.xml a WebDriverManager dependency was included and when a driver gets initialized it recognizes the chrome, firefox or edge version you have installed in your machine. If you are working with the latest Chrome version in your machine you might experience this new bug

https://github.com/SeleniumHQ/selenium/issues/6317

For the latest version of Firefox the Select method selectByVisibleText was throwing Element Not Interactable exception
