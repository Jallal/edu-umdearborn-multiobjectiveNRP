package edu.umich.ISELab.core.util;

import java.util.UUID;

public class UUIDUtils {

    private static int SEQUENTIAL_ID = 1;

    public static String randomUUID() {
//		return UUID.randomUUID().toString();
        return UUID.randomUUID().toString().split("-")[0];
    }

    public static String sequencialUUID() {
        return String.valueOf(SEQUENTIAL_ID++);
    }

    public static void restart() {
        SEQUENTIAL_ID = 1;
    }

    public static int next() {
        return SEQUENTIAL_ID;
    }
}
