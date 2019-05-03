package thiagodnf.doupr.export.plantuml.parser;

import thiagodnf.doupr.export.ExportParser;

import java.util.List;

public class MethodsPlantUMLParser extends ExportParser {

    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < cls.getMethods().size(); i++) {

            MethodObject method = cls.getMethods().get(i);

            builder.append(cls.getName()).append(" : ");

            if (method.isPrivate()) {
                builder.append("-");
            } else if (method.isProtected()) {
                builder.append("#");
            } else if (method.isPublic()) {
                builder.append("+");
            } else {
                builder.append("~");
            }

            builder.append(method.getName());
            builder.append("()");
            builder.append(":");
            builder.append(method.getReturnType());

            builder.append("\n");
        }

        return builder.toString();
    }
}
