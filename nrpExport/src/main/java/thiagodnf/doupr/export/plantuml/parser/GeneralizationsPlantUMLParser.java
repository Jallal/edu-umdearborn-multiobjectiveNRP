package thiagodnf.doupr.export.plantuml.parser;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.export.ExportParser;

import java.util.List;

public class GeneralizationsPlantUMLParser extends ExportParser {

    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

        if (cls.hasSuperClass()) {
            builder.append(cls.getName());
            builder.append(" <|-- ");
            builder.append(cls.getSuperClass());
            builder.append("\n");
        }

        return builder.toString();
    }
}
