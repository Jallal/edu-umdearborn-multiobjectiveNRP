package thiagodnf.doupr.export.block.generator;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.util.UUIDUtils;
import thiagodnf.doupr.export.ExportGenerator;
import thiagodnf.doupr.export.block.parser.AttributesBlocksParser;
import thiagodnf.doupr.export.block.parser.CallInsBlocksParser;
import thiagodnf.doupr.export.block.parser.CallOutsBlocksParser;
import thiagodnf.doupr.export.block.parser.ClassBlocksParser;
import thiagodnf.doupr.export.block.parser.GeneralizationsBlocksParser;
import thiagodnf.doupr.export.block.parser.MethodsBlocksParser;
import thiagodnf.doupr.export.block.parser.ParametersBlocksParser;
import thiagodnf.doupr.export.block.parser.RealizationsBlocksParser;

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
