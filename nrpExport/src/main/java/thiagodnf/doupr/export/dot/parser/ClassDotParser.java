package thiagodnf.doupr.export.dot.parser;

import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ParameterObject;
import thiagodnf.doupr.core.util.StringUtils;
import thiagodnf.doupr.export.ExportParser;

import java.util.List;

public class ClassDotParser extends ExportParser {

    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

        builder.append(cls.getSimpleName().replaceAll("\\$", "__"));
        builder.append("[label = \"{");

        if (cls.isInterface()) {
            builder.append("\\<\\<interface\\>\\>").append("\\n");
        } else if (cls.isAbstract()) {
            builder.append("\\<\\<abstract\\>\\>").append("\\n");
        }

        builder.append("").append(cls.getSimpleName()).append("");
        builder.append("|");
        builder.append(parseAttributes(cls.getAttributes()));
        builder.append("|");
        builder.append(parseMethods(cls.getMethods()));
        builder.append("}\"]");

        return builder.toString();
    }

    public String parseAttributes(List<AttributeObject> attributes) {
        StringBuilder builder = new StringBuilder();

        if (attributes.isEmpty()) {
            builder.append("...");
        } else {
            for (AttributeObject attribute : attributes) {
                // Visibility
                if (attribute.isPublic()) {
                    builder.append("+ ");
                } else if (attribute.isProtected()) {
                    builder.append("# ");
                } else if (attribute.isPrivate()) {
                    builder.append("- ");
                } else {
                    builder.append("+ ");
                }

                // Name
                builder.append(attribute.getName());

                // Type
                builder.append(" : ").append(attribute.getTypeWithTemplate());
                builder.append("\\l");
            }
        }

        return builder.toString();
    }

    public String parseMethods(List<MethodObject> methods) {
        StringBuilder builder = new StringBuilder();

        if (methods.isEmpty()) {
            builder.append("...");
        } else {
            for (MethodObject method : methods) {

                // Visibility
                if (method.isPublic()) {
                    builder.append("+ ");
                } else if (method.isProtected()) {
                    builder.append("# ");
                } else if (method.isPrivate()) {
                    builder.append("- ");
                }
                // Name
                if (method.isContructor()) {
                    builder.append("Constructor");
                } else {
                    builder.append(method.getSimpleName());
                }

                builder.append("(");
                builder.append(parseParameters(method.getParameters()));
                builder.append(")");

                // Type
                builder.append(" : ").append(StringUtils.getSimpleName(method.getReturnType()));

                builder.append("\\l");
            }
        }

        return builder.toString();
    }

    public String parseParameters(List<ParameterObject> parameters) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < parameters.size(); i++) {
            builder.append(parameters.get(i).getTypeWithTemplate());

            if (i + 1 != parameters.size()) {
                builder.append(",");
            }
        }

        return builder.toString();
    }
}
