package thiagodnf.doupr.export;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public abstract class ExportGenerator {

    protected String filename;

    public ExportGenerator(String filename) {
        this.filename = filename;
    }

    public void generate(Object parent, ProjectObject project, ClassObject cls) throws IOException {
        FileUtils.write(new File(filename + getSuffix()), getContent(parent, project, cls));
    }

    public void generate(Object parent, ProjectObject project) throws IOException {
        FileUtils.write(new File(filename + getSuffix()), getContent(parent, project));
    }


    protected abstract String getSuffix();

    public abstract String getContent(Object parent, ProjectObject project, ClassObject cls) throws IOException;

    public abstract String getContent(Object parent, ProjectObject project) throws IOException;
}
