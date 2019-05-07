package edu.umich.ISELab.core.util;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

    public static String join(List<?> list, String separator) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {

            builder.append(list.get(i));

            if (i + 1 != list.size()) {
                builder.append(separator);
            }
        }

        return builder.toString();
    }

    public static List<String> getSimpleNames(List<String> classes) {
        return classes.stream().map(e -> StringUtils.getSimpleName(e)).collect(Collectors.toList());
    }

    public static String getSimpleName(String name) {

        if (name == null || name.isEmpty()) {
            return name;
        }

        String[] split = name.split("\\.");

        return split[split.length - 1];
    }

    public static String getPackageName(String name) {

        int lastIndex = name.lastIndexOf(".");

        // The classname does not have the package one
        if (lastIndex == -1) {
            return "";
        }

        return name.substring(0, lastIndex);
    }
}
