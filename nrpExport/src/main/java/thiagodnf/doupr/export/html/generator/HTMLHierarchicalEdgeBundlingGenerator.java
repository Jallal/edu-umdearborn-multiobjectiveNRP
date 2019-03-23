package thiagodnf.doupr.export.html.generator;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.export.json.generator.JSONHierarchicalEdgeBundlingGenerator;

import java.io.IOException;

public class HTMLHierarchicalEdgeBundlingGenerator extends HTMLAbstractGenerator {

    public HTMLHierarchicalEdgeBundlingGenerator(String exportFilename) {
        super(exportFilename);
    }

    @Override
    protected String getSuffix() {
        return ".heb.html";
    }

    @Override
    public String getContent(Object parent, ProjectObject project) throws IOException {

        String content = new JSONHierarchicalEdgeBundlingGenerator("").getContent(parent, project);

        content = "var data = " + content;

        StringBuilder builder = new StringBuilder();

        String template = getTemplate(parent, "HTMLHierarchicalEdgeBundling.html");

        template = template.replace("%%TITLE%%", "Dependency Graph");
        template = template.replace("%%JAVASCRIPT%%", content);

        builder.append(template);

        return builder.toString();
    }

    @Override
    public String getContent(Object parent, ProjectObject project, ClassObject cls) throws IOException {

        String content = new JSONHierarchicalEdgeBundlingGenerator("").getContent(parent, project, cls);

        content = "var data = " + content;

        StringBuilder builder = new StringBuilder();

        String template = getTemplate(parent, "HTMLHierarchicalEdgeBundling.html");

        template = template.replace("%%TITLE%%", "Dependency Graph");
        template = template.replace("%%JAVASCRIPT%%", content);

        builder.append(template);

        return builder.toString();
    }
}
