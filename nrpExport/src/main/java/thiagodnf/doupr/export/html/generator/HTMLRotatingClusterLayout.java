package thiagodnf.doupr.export.html.generator;

import thiagodnf.doupr.export.json.generator.JSONRotatingClusterLayoutGenerator;

import java.io.IOException;

public class HTMLRotatingClusterLayout extends HTMLAbstractGenerator {

    public HTMLRotatingClusterLayout(String exportFilename) {
        super(exportFilename);
    }

    @Override
    protected String getSuffix() {
        return ".rcl.html";
    }

    @Override
    public String getContent(Object parent, ProjectObject project) throws IOException {

        String content = new JSONRotatingClusterLayoutGenerator("").getContent(parent, project);

        content = "var data = " + content;

        StringBuilder builder = new StringBuilder();

        String template = getTemplate(parent, "HTMLRotatingClusterLayout.html");

        template = template.replace("%%TITLE%%", "Rotating Cluster Layout");
        template = template.replace("%%JAVASCRIPT%%", content);

        builder.append(template);

        return builder.toString();
    }

    @Override
    public String getContent(Object parent, ProjectObject project, ClassObject cls) {
        return null;
    }
}
