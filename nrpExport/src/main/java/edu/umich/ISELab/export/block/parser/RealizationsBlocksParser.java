package edu.umich.ISELab.export.block.parser;

import edu.umich.ISELab.export.ExportParser;

import java.util.List;

public class RealizationsBlocksParser extends ExportParser {

    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

        if (cls.hasInterface()) {

            for (String inter : cls.getInterfaces()) {

                builder.append("Realization(");
                builder.append(inter);
                builder.append(");").append("\n");
            }
        }

        return builder.toString();
    }
}
