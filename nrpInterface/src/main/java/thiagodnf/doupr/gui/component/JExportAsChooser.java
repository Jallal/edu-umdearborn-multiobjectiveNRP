package thiagodnf.doupr.gui.component;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JExportAsChooser extends JFileChooser {

    protected static final Logger LOGGER = Logger.getLogger(JExportAsChooser.class);
    private static final long serialVersionUID = 579532904906303152L;
    protected Map<String, JExportAsChooserListener> listeners;

    public JExportAsChooser() {

        this.listeners = new HashMap<>();

        // Show only the files that have the same extension filter
        setAcceptAllFileFilterUsed(false);
        setCurrentDirectory(new File(System.getProperty("user.dir")));
        setDialogTitle("Exporting as");
    }

    public void addFileFilter(String description, String extension, JExportAsChooserListener listener) {
        setFileFilter(new FileNameExtensionFilter(description, extension));
        this.listeners.put(extension, listener);
    }

    public void showDialog() {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Showing the dialog");

        if (showDialog(MainWindow.getIntance(), "Export") != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = getSelectedFile();

        String absolutePath = file.getAbsolutePath();

        // The user typed the filename. Now we have to export it
        String filename = FilenameUtils.getBaseName(file.getAbsolutePath());
        String path = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
        String extension = ((FileNameExtensionFilter) getFileFilter()).getExtensions()[0];

        File output = new File(path + File.separator + filename + "." + extension);

        if (output.exists()) {

            if (MessageBox.confirm("Warning", "The file already exists. Do you want to override it?")) {
                listeners.get(extension).save(extension, output);
            } else {
                showDialog();
            }
        } else {
            listeners.get(extension).save(extension, output);
        }
    }

    public interface JExportAsChooserListener {
        public void save(String extension, File output);
    }
}

