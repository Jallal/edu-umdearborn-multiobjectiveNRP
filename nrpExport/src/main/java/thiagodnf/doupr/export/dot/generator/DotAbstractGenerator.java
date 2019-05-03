package thiagodnf.doupr.export.dot.generator;

import thiagodnf.doupr.export.ExportGenerator;
import thiagodnf.doupr.export.dot.parser.ClassDotParser;

import java.util.List;

public abstract class DotAbstractGenerator extends ExportGenerator {

    public DotAbstractGenerator(String exportFilename) {
        super(exportFilename);
    }

    /**
     * This method returns information about the header file
     *
     * @return a content for the header file
     */
    public String getHeader() {

        StringBuilder builder = new StringBuilder();

        builder.append("digraph hierarchy {").append("\n");
        builder.append("\t").append("size=\"5,5\"").append("\n");
        builder.append("\t").append("node[shape=record,style=filled,fillcolor=\"#ffffcc\",color=\"#990033\"]")
                .append("\n");
        builder.append("\t").append("edge[dir=back, arrowtail=empty, color=\"#990033\"]").append("\n");
        builder.append("\n");

        return builder.toString();
    }

    /**
     * This method returns information about the footer file
     *
     * @return a content for the footer file
     */
    public String getFooter() {
        return "}";
    }

    public String getInformationAboutNodes(List<ClassObject> classes) {
        StringBuilder builder = new StringBuilder();

        for (ClassObject cls : classes) {
            builder.append("\t").append(new ClassDotParser().parse(classes, cls)).append("\n");
        }

        return builder.toString();
    }
}
