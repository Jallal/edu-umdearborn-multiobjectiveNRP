package edu.umich.ISELab.export.dot.parser;

import edu.umich.ISELab.export.ExportParser;

import java.util.List;

public class GeneralizationDotParser extends ExportParser {

    @Override
    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

        String superClass = cls.getSuperClass();

        // To create this connection it is necessary to have at least a
        // generalization
        if (!cls.hasSuperClass()) {
            return builder.toString();
        }

        ClassObject superCls = ClassObjectUtils.findByName(classes, superClass);

        builder.append(superCls.getSimpleName().replaceAll("\\$", "__"));
        builder.append(" -> ");
        builder.append(cls.getSimpleName().replaceAll("\\$", "__"));
        builder.append("\n");

        return builder.toString();
    }

}
