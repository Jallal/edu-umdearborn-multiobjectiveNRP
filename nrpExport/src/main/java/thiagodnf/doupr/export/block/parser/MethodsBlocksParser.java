package thiagodnf.doupr.export.block.parser;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.export.ExportParser;

import java.util.List;

public class MethodsBlocksParser extends ExportParser {

    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < cls.getMethods().size(); i++) {

            MethodObject method = cls.getMethods().get(i);

            builder.append("Method(");
            builder.append(method.getName());
            builder.append(",");
            builder.append(method.getReturnType());
            builder.append(",");
            builder.append(method.getVisibility());
            builder.append(",");
            builder.append(method.isStatic() ? "Y" : "N");
            builder.append(",");
            builder.append(method.isAbstract() ? "Y" : "N");

            builder.append(");").append("\n");
        }

        return builder.toString();
    }
}
