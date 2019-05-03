package edu.umich.ISELab.export.dot.generator;

import edu.umich.ISELab.export.dot.parser.DependencyDotParser;

import java.util.List;

public class DotDependencyGraphGenenerator extends DotAbstractGenerator {

    public DotDependencyGraphGenenerator(String exportFilename) {
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
            builder.append(new DependencyDotParser().parse(classes, c));
        }

        builder.append(getFooter());

        return builder.toString().replaceAll("\\$", "");
    }

    @Override
    protected String getSuffix() {
        return ".dp.dot";
    }

    @Override
    public String getContent(Object parent, ProjectObject project) {
        // TODO Auto-generated method stub
        return null;
    }
}
