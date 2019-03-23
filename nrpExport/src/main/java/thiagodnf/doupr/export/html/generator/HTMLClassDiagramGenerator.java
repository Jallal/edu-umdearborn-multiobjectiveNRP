package thiagodnf.doupr.export.html.generator;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.export.json.generator.JSONClassDiagramGenerator;

import java.io.IOException;

public class HTMLClassDiagramGenerator extends HTMLAbstractGenerator {

    public HTMLClassDiagramGenerator(String exportFilename) {
        super(exportFilename);
    }

    @Override
    protected String getSuffix() {
        return ".cd.html";
    }

    @Override
    public String getContent(Object parent, ProjectObject project) throws IOException {

        String content = new JSONClassDiagramGenerator("").getContent(parent, project);

        StringBuilder builder = new StringBuilder();

        String template = getTemplate(parent, "HTMLClassDiagram.html");

        template = template.replace("%%TITLE%%", "Class Diagram");
        template = template.replace("%%JAVASCRIPT%%", content);

        builder.append(template);

        return builder.toString();
    }

    @Override
    public String getContent(Object parent, ProjectObject project, ClassObject cls) throws IOException {

        String content = new JSONClassDiagramGenerator("").getContent(parent, project, cls);

        StringBuilder builder = new StringBuilder();

        String template = getTemplate(parent, "HTMLClassDiagram.html");

        template = template.replace("%%TITLE%%", "Class Diagram");
        template = template.replace("%%JAVASCRIPT%%", content);

        builder.append(template);

        return builder.toString();
    }
}
