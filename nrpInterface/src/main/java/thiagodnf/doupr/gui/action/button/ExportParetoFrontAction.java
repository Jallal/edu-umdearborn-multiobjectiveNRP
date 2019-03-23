package thiagodnf.doupr.gui.action.button;

import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import thiagodnf.doupr.core.sys.LOGGER;
import thiagodnf.doupr.gui.component.JExportAsChooser;
import thiagodnf.doupr.gui.component.JExportAsChooser.JExportAsChooserListener;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * This class is responsible for exporting the Pareto-front for a file. To
 * do it, a FileChooser is showed to the user and s(he) must type the filename
 * and choose the file extension before do it.
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-08-12
 */
public class ExportParetoFrontAction implements ActionListener, JExportAsChooserListener {

    protected ViewParetoFrontSubWindow window;

    public ExportParetoFrontAction(ViewParetoFrontSubWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LOGGER.info(this, "Exporting the Pareto-front");

        JExportAsChooser chooser = new JExportAsChooser();

        // The possible formats that the user can choose
        chooser.addFileFilter("Objective Values (.fun)", "fun", this);
        chooser.addFileFilter("Variable Values (.var)", "var", this);

        // Show the dialog to user until s(he) return a valid output file
        chooser.showDialog();
    }

    @Override
    public void save(String extension, File output) {

        LOGGER.info(this, "Saving the file");

        try {

            if (extension.equalsIgnoreCase("fun")) {
                exportToFUNFiles(output);
            } else if (extension.equalsIgnoreCase("var")) {
                exportToVARFiles(output);
            }

            MessageBox.info("The file was successfully exported");

        } catch (Exception ex) {
            MessageBox.error(ex);
        }
    }

    public void exportToFUNFiles(File path) throws IOException {

        LOGGER.info(this, "Exporting the FUN files");

        new SolutionListOutput(window.getParetoFront())
                .setSeparator("\t")
                .printObjectivesToFile(path.getAbsolutePath());
    }

    public void exportToVARFiles(File path) throws IOException {

        LOGGER.info(this, "Exporting the VAR files");

        new SolutionListOutput(window.getParetoFront())
                .setSeparator("\t")
                .printVariablesToFile(path.getAbsolutePath());
    }
}
