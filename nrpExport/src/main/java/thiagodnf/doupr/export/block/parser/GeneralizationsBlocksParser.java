package thiagodnf.doupr.export.block.parser;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.export.ExportParser;

import java.util.List;

public class GeneralizationsBlocksParser extends ExportParser {

    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

        if (cls.hasSuperClass()) {
            builder.append("Generalization(");
            builder.append(cls.getSuperClass());
            builder.append(");").append("\n");
        }

        return builder.toString();
    }
}
