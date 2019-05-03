package thiagodnf.doupr.export.html.generator;

import thiagodnf.doupr.export.json.generator.JSONInteractiveForceLayoutGenerator;

import java.io.IOException;

public class HTMLInteractiveForceLayout extends HTMLAbstractGenerator {

    public HTMLInteractiveForceLayout(String exportFilename) {
        super(exportFilename);
    }

    @Override
    protected String getSuffix() {
        return ".ifl.html";
    }

    @Override
    public String getContent(Object parent, ProjectObject project) throws IOException {

        String content = new JSONInteractiveForceLayoutGenerator("").getContent(parent, project);

        content = "var data = " + content;

        StringBuilder builder = new StringBuilder();

        String template = getTemplate(parent, "HTMLInteractiveForceLayout.html");

        template = template.replace("%%TITLE%%", "Interactive Force Layout");
        template = template.replace("%%JAVASCRIPT%%", content);

        builder.append(template);

        return builder.toString();
    }

    @Override
    public String getContent(Object parent, ProjectObject project, ClassObject cls) throws IOException {

        String content = new JSONInteractiveForceLayoutGenerator("").getContent(parent, project, cls);

        content = "var data = " + content;

        StringBuilder builder = new StringBuilder();

        String template = getTemplate(parent, "HTMLInteractiveForceLayout.html");

        template = template.replace("%%TITLE%%", "Interactive Force Layout");
        template = template.replace("%%JAVASCRIPT%%", content);

        builder.append(template);

        return builder.toString();
    }
}
