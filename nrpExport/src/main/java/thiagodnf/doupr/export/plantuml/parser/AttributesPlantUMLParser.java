package thiagodnf.doupr.export.plantuml.parser;

import thiagodnf.doupr.export.ExportParser;

import java.util.List;

public class AttributesPlantUMLParser extends ExportParser {

    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < cls.getAttributes().size(); i++) {

            AttributeObject attribute = cls.getAttributes().get(i);

            builder.append(cls.getName()).append(" : ");

            if (attribute.isPrivate()) {
                builder.append("-");
            } else if (attribute.isProtected()) {
                builder.append("#");
            } else if (attribute.isPublic()) {
                builder.append("+");
            } else {
                builder.append("~");
            }

            builder.append(attribute.getName());
//			builder.append("()");
//			builder.append(":");

//			builder.append("Attribute(");
//			builder.append(attribute.getName());
//			builder.append(",");
//			builder.append(attribute.getType());
//			builder.append(",");
//			builder.append(attribute.getVisibility());
//			builder.append(",");
//			builder.append(attribute.isStatic() ? "Y" : "N");
//			builder.append(",");
//			builder.append(attribute.isFinal() ? "Y" : "N");
//			builder.append(",");

//			if (!attribute.getTemplates().isEmpty()) {
//				builder.append(StringUtils.join(attribute.getTemplates(), ";"));
//			}

            builder.append("\n");
        }

        return builder.toString();
    }
}
