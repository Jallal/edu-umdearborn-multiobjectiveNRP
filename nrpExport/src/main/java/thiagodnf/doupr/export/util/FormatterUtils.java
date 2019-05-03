package thiagodnf.doupr.export.util;

import thiagodnf.doupr.core.util.StringUtils;

import java.util.regex.Matcher;

public class FormatterUtils {

    public static String format(String template, AttributeObject attr) {

        String result = template;

        String type = attr.getType();

        if (type.contains("$")) {
            type = Matcher.quoteReplacement(type);
        }

        String name = attr.getName();

        if (name.contains("$")) {
            name = Matcher.quoteReplacement(name);
        }

        result = result.replaceAll("@NAME@", name);
        result = result.replaceAll("@TYPE@", type);
        result = result.replaceAll("@SIMPLETYPE@", StringUtils.getSimpleName(type));
        result = result.replaceAll("@VISIBILITY@", attr.getVisibility().toString().toLowerCase());

        return result;
    }

    public static String format(String template, ParameterObject p) {

        String result = template;

        String type = p.getType();

        if (type.contains("$")) {
            type = Matcher.quoteReplacement(type);
        }

        result = result.replaceAll("@TYPE@", type);
        result = result.replaceAll("@SIMPLETYPE@", StringUtils.getSimpleName(type));

        return result;
    }

    public static String format(String template, MethodObject m) {

        String result = template;

        String returnType = m.getReturnType();

        if (returnType.contains("$")) {
            returnType = Matcher.quoteReplacement(returnType);
        }

        String name = m.getName();

        if (name.contains("$")) {
            name = Matcher.quoteReplacement(name);
        }

        result = result.replaceAll("@NAME@", name);
        result = result.replaceAll("@RETURNTYPE@", returnType);
        result = result.replaceAll("@SIMPLERETURNTYPE@", StringUtils.getSimpleName(returnType));
        result = result.replaceAll("@VISIBILITY@", m.getVisibility().toString().toLowerCase());
        //result = result.replaceAll("@PARAMETERS@", format(template, attr));

        return result;
    }

}
