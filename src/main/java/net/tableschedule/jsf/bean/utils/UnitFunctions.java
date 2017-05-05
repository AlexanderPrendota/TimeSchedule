package net.tableschedule.jsf.bean.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by aleksandrprendota on 05.05.17.
 */
public class UnitFunctions {

    private static final Logger LOG = Logger.getLogger(UnitFunctions.class);

    public static String getProperty(String name){

        Properties prop = new Properties();
        InputStream input;
        String filename = "application.properties";
        input = UnitFunctions.class.getClassLoader().getResourceAsStream(filename);
        if(input == null){
            LOG.error("Sorry, unable to find " + filename);
        }
        try {
            prop.load(input);
        } catch (IOException e) {
            LOG.error("Error with parsing application property");
        }
        return prop.getProperty(name);

    }
}
