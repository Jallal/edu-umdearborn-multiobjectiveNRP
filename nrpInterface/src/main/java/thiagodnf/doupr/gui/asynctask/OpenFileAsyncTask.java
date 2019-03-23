package thiagodnf.doupr.gui.asynctask;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.util.FileReaderUtils;
import thiagodnf.doupr.evaluation.util.DesignMetricsUtil;
import thiagodnf.doupr.gui.util.MessageBox;

import java.io.File;
import java.io.IOException;

public class OpenFileAsyncTask extends AsyncTask<ProjectObject> {

    protected File selectedFile;

    public OpenFileAsyncTask(File selectedFile) {
        super("Opening file...", 2);

        this.selectedFile = selectedFile;
    }

    @Override
    public ProjectObject doInBackground() {

        try {

            ProjectObject project = FileReaderUtils.read(selectedFile);

            publish(progress++);

            project.setDesignMetrics(DesignMetricsUtil.calculate(project));

            publish(progress++);

            return project;
        } catch (IOException ex) {
            MessageBox.error(ex);
        }

        return null;
    }
}
