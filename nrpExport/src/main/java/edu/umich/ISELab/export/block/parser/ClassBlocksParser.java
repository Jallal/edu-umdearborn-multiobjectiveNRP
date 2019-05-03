package edu.umich.ISELab.export.block.parser;

import edu.umich.ISELab.export.ExportParser;

import java.util.List;

public class ClassBlocksParser extends ExportParser {

    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

        builder.append("Class(");
        builder.append(cls.getName());
        builder.append(",");
        builder.append(cls.isInterface() ? "Y" : "N");
        builder.append(",");
        builder.append(cls.isAbstract() ? "Y" : "N");
        builder.append(",");
        builder.append(cls.getVisibility());
        builder.append(");").append("\n");

        return builder.toString();
    }
}
