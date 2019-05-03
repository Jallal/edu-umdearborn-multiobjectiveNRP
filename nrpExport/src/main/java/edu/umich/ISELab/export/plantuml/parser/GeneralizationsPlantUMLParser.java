package edu.umich.ISELab.export.plantuml.parser;

import edu.umich.ISELab.export.ExportParser;

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
