package thiagodnf.doupr.export.dot.generator;

import thiagodnf.doupr.export.dot.parser.CompositionDotParser;
import thiagodnf.doupr.export.dot.parser.GeneralizationDotParser;
import thiagodnf.doupr.export.dot.parser.RealizationDotParser;

import java.util.List;

public class DotClassDiagramGenerator extends DotAbstractGenerator {

    public DotClassDiagramGenerator(String exportFilename) {
        super(exportFilename);
    }

    @Override
    public String getContent(Object parent, ProjectObject project, ClassObject cls) {

        List<ClassObject> classes = project.getClasses();

        StringBuilder builder = new StringBuilder();

        builder.append(getHeader());
        builder.append(getInformationAboutNodes(classes));
        builder.append("\n");

        for (ClassObject c : classes) {
            builder.append(new GeneralizationDotParser().parse(classes, c));
            builder.append(new RealizationDotParser().parse(classes, c));
            builder.append(new CompositionDotParser().parse(classes, c));
            builder.append("\n");
        }

        builder.append(getFooter());

        return builder.toString();
    }

    @Override
    protected String getSuffix() {
        return ".cd.dot";
    }

    @Override
    public String getContent(Object parent, ProjectObject project) {
        // TODO Auto-generated method stub
        return null;
    }
}
