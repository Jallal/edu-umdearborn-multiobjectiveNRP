package edu.umich.ISELab.export.block.parser;

import edu.umich.ISELab.core.util.StringUtils;
import edu.umich.ISELab.export.ExportParser;

import java.util.List;

public class ParametersBlocksParser extends ExportParser {

    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < cls.getMethods().size(); i++) {

            MethodObject method = cls.getMethods().get(i);

            for (int j = 0; j < method.getParameters().size(); j++) {

                ParameterObject parameter = method.getParameters().get(j);

                builder.append("Parameter(");
                builder.append(method.getName());
                builder.append(",");
                builder.append(parameter.getType());
                builder.append(",");

                if (!parameter.getTemplates().isEmpty()) {
                    builder.append(StringUtils.join(parameter.getTemplates(), ";"));
                }

                builder.append(");").append("\n");
            }
        }

        return builder.toString();
    }
}
