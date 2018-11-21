package framework.report;

import org.apache.logging.log4j.Logger;

public class Log {

    public static Logger logger;
    public static String failReason;

    public static void testStart(String testName){
        logger.info("*************** " + testName + " Inicio ***************");
    }

    public static void testDescription(String testDescription){
        logger.info("----------- " + testDescription + " -----------");
    }

    public static void testFail(String testName){
        logger.error("xxxxxxxxxxxxxxx " + testName + " Fallo xxxxxxxxxxxxxxx");
    }

    public static void testSkipped(String testName){
        logger.error("!!!!!!!!!!!! " + testName + " No Ejecutado !!!!!!!!!!!!");
    }

    public static void testEnd(String testName){
        logger.info("*************** " + testName + " Final ***************\n");
    }

    public static void testStep(String message){
        logger.info(message);
    }

    public static void testStep(String message, String parameter){
        logger.info(String.format(message,parameter));
    }

    public static void validationFail(String message){failReason = message;}

    public static void testFailReason(){logger.error("xxxxxx " + failReason + " xxxxxx");}
}
