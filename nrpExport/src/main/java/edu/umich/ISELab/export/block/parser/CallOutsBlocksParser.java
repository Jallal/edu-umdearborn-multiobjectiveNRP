package edu.umich.ISELab.export.block.parser;

import edu.umich.ISELab.export.ExportParser;

import java.util.List;

public class CallOutsBlocksParser extends ExportParser {

    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

//		for (CallObject call : cls.getCallOuts()) {
//
//			builder.append("CallOut(");
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
