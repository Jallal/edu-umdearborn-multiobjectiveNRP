package thiagodnf.doupr.gui.action.button;

import org.apache.log4j.Logger;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import thiagodnf.doupr.gui.component.JExportAsChooser;
import thiagodnf.doupr.gui.component.JExportAsChooser.JExportAsChooserListener;
import thiagodnf.doupr.gui.subwindow.ViewSolutionSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.optimization.solution.Solution;
import thiagodnf.doupr.optimization.variables.RefactoringVariable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class is responsible for exporting a list of refactoring for a file. To
 * do it, a FileChooser is showed to the user and s(he) must type the filename
 * and choose the file extension before do it. Before save it, it is
 * important the list of refactoring does not be empty
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-08-12
 */
public class ExportRefactoringsAction implements ActionListener, JExportAsChooserListener {

    protected static final Logger LOGGER = Logger.getLogger(ExportRefactoringsAction.class);

    protected ViewSolutionSubWindow window;

    public ExportRefactoringsAction(ViewSolutionSubWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Exporting the refactorings");

        Solution solution = window.getSolution();

        if (((RefactoringVariable) solution.getVariableValue(0)).getRefactorings().isEmpty()) {
            MessageBox.warning("The refactoring list cannot be empty. You must add some refactorings before save them");
            return;
        }

        JExportAsChooser chooser = new JExportAsChooser();

        // The possible formats that the user can choose
        chooser.addFileFilter("Blocks File (.blocks)", "blocks", this);
        chooser.addFileFilter("List of Refactorings (.refactorings)", "refactorings", this);

        // Show the dialog to user until s(he) return a valid output file
        chooser.showDialog();
    }

    @Override
    public void save(String extension, File output) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Saving the file");

        try {
            if (extension.equalsIgnoreCase("refactorings")) {
                exportRefactoringList(output, window.getSolution());
            } else if (extension.equalsIgnoreCase("blocks")) {

            }

            MessageBox.info("The file was successfully exported");

        } catch (Exception ex) {
            MessageBox.error(ex);
        }
    }

    public void exportRefactoringList(File path, Solution solution) throws IOException {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Exporting the list of refactorings");

        new SolutionListOutput(Arrays.asList(solution))
                .setSeparator("\t")
                .printVariablesToFile(path.getAbsolutePath());
    }

}
