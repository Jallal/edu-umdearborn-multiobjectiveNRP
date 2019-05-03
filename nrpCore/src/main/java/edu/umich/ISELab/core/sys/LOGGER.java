package edu.umich.ISELab.core.sys;

import org.apache.log4j.Logger;

import java.util.List;

public class LOGGER {

    public static void info(Object cls, String msg) {
        info(Logger.getLogger(cls.getClass()), msg);
    }

    public static void info(Logger LOGGER, String msg) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(msg);
        }
    }

    public static void info(Object o, List<String> msgs) {
        info(o, null, msgs);
    }

    public static void info(Object o, String title, List<?> msgs) {
        if (title != null) {
            info(o, title);
        }

        for (Object msg : msgs) {
            info(o, "    " + msg.toString());
        }
    }
}
