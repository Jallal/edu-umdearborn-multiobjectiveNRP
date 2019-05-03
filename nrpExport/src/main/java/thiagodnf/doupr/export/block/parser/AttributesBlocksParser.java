package thiagodnf.doupr.export.block.parser;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.util.StringUtils;
import thiagodnf.doupr.export.ExportParser;

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
