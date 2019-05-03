package edu.umich.ISELab.export.dot.parser;

import edu.umich.ISELab.export.ExportParser;

import java.util.List;

public class RealizationDotParser extends ExportParser {

    @Override
    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

        // To create this connection it is necessary to have at least a realization
        if (!cls.hasInterface()) {
            return builder.toString();
        }

        for (String inter : cls.getInterfaces()) {
            builder.append(ClassObjectUtils.findByName(classes, inter).getSimpleName().replaceAll("\\$", "__"));
            builder.append(" -> ");
            builder.append(cls.getSimpleName().replaceAll("\\$", "__"));
            builder.append("[arrowtail=onormal, style=dashed]");
            builder.append("\n");
        }

        return builder.toString();
    }

}
