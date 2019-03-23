package thiagodnf.doupr.export.block.parser;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.export.ExportParser;

import java.util.List;

public class CallInsBlocksParser extends ExportParser {

    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

//		for (CallObject call : cls.getCallIns()) {
//
//			builder.append("CallIn(");
//			builder.append(call.getSourceClass());
//			builder.append(",");
//			builder.append(call.getSourceMethod());
//			builder.append(" --> ");
//			builder.append(call.getTargetMethod());
//			builder.append(",");
//			builder.append(call.getTargetClass());
//			builder.append(");").append("\n");
//		}

        return builder.toString();
    }
}
