package edu.umich.ISELab.export.block.parser;

import edu.umich.ISELab.export.ExportParser;
import thiagodnf.doupr.core.base.ClassObject;
import edu.umich.ISELab.core.util.StringUtils;

import java.util.List;

public class AttributesBlocksParser extends ExportParser {

    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < cls.getAttributes().size(); i++) {

            AttributeObject attribute = cls.getAttributes().get(i);

            builder.append("Attribute(");
            builder.append(attribute.getName());
            builder.append(",");
            builder.append(attribute.getType());
            builder.append(",");
            builder.append(attribute.getVisibility());
            builder.append(",");
            builder.append(attribute.isStatic() ? "Y" : "N");
            builder.append(",");
            builder.append(attribute.isFinal() ? "Y" : "N");
            builder.append(",");

            if (!attribute.getTemplates().isEmpty()) {
                builder.append(StringUtils.join(attribute.getTemplates(), ";"));
            }

            builder.append(");").append("\n");
        }

        return builder.toString();
    }
}
