package thiagodnf.doupr.gui.action.button;

import org.apache.log4j.Logger;
import edu.umich.ISELab.core.grooming.NrpBase;
import thiagodnf.doupr.gui.subwindow.ViewSolutionSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RemoveRefactoringAction implements ActionListener {

    protected static final Logger LOGGER = Logger.getLogger(RemoveRefactoringAction.class);

    protected ViewSolutionSubWindow window;

    public RemoveRefactoringAction(ViewSolutionSubWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JTable table = window.getRefactoringsPanel().getTable();

        int[] selectedRows = table.getSelectedRows();

        for (int i = 0; i < selectedRows.length; i++) {
            selectedRows[i] = table.convertRowIndexToModel(selectedRows[i]);
        }

        if (selectedRows.length == 0) {
            MessageBox.warning("You must select at least a grooming before remove it");
            return;
        }

        List<NrpBase> refactorings = window.getRefactorings();

        if (!MessageBox.confirm("Warning", "Do you want to delete " + selectedRows.length + " refactorings?")) {
            return;
        }

        List<NrpBase> refactoringsToRemove = new ArrayList<>();

        for (int i = 0; i < selectedRows.length; i++) {
            refactoringsToRemove.add(refactorings.get(selectedRows[i]));
        }

        if (LOGGER.isInfoEnabled()) {

            LOGGER.info("Refactorings removed");

            for (NrpBase r : refactoringsToRemove) {
                LOGGER.info(r);
            }
        }

        window.removeRefactoring(refactoringsToRemove);
    }
}
