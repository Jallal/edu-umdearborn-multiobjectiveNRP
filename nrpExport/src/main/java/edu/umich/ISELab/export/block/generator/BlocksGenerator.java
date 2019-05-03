package edu.umich.ISELab.export.block.generator;

import edu.umich.ISELab.core.util.UUIDUtils;
import edu.umich.ISELab.export.ExportGenerator;
import edu.umich.ISELab.export.block.parser.AttributesBlocksParser;
import edu.umich.ISELab.export.block.parser.CallInsBlocksParser;
import edu.umich.ISELab.export.block.parser.CallOutsBlocksParser;
import edu.umich.ISELab.export.block.parser.ClassBlocksParser;
import edu.umich.ISELab.export.block.parser.GeneralizationsBlocksParser;
import edu.umich.ISELab.export.block.parser.MethodsBlocksParser;
import edu.umich.ISELab.export.block.parser.ParametersBlocksParser;
import edu.umich.ISELab.export.block.parser.RealizationsBlocksParser;

import java.util.List;

public class BlocksGenerator extends ExportGenerator {

    public BlocksGenerator(String exportFilename) {
        super(exportFilename);
    }

    @Override
    public String getContent(Object parent, ProjectObject project, ClassObject cls) {

        List<ClassObject> classes = project.getClasses();

        StringBuilder builder = new StringBuilder();

        for (ClassObject c : classes) {

            String id = UUIDUtils.randomUUID();

            builder.append("StartClass ").append(id).append("\n");
            builder.append(new ClassBlocksParser().parse(classes, c));
            builder.append(new AttributesBlocksParser().parse(classes, c));
            builder.append(new ParametersBlocksParser().parse(classes, c));
            builder.append(new MethodsBlocksParser().parse(classes, c));
            builder.append(new GeneralizationsBlocksParser().parse(classes, c));
            builder.append(new RealizationsBlocksParser().parse(classes, c));
            builder.append(new CallInsBlocksParser().parse(classes, c));
            builder.append(new CallOutsBlocksParser().parse(classes, c));
            builder.append("EndClass ").append(id).append("\n");
        }

        return builder.toString();
    }

    @Override
    protected String getSuffix() {
        return ".blocks";
    }

    @Override
    public String getContent(Object parent, ProjectObject project) {
        // TODO Auto-generated method stub
        return null;
    }
}
