package thiagodnf.doupr.gui.action.button;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.NrpBase;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.gui.panel.FormRefactoringPanel;
import thiagodnf.doupr.gui.subwindow.ViewSolutionSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddRefactoringAction implements ActionListener {

    protected static final Logger LOGGER = Logger.getLogger(AddRefactoringAction.class);

    protected FormRefactoringPanel panel;

    protected ViewSolutionSubWindow window;

    public AddRefactoringAction(ViewSolutionSubWindow window) {
        this.window = window;
    }

    protected int showDialog(ProjectObject refactored, NrpBase ref) {

        this.panel = new FormRefactoringPanel(refactored, ref);

        int optionType = JOptionPane.OK_CANCEL_OPTION;
        int messageType = JOptionPane.PLAIN_MESSAGE;

        while (true) {

            if (LOGGER.isInfoEnabled()) LOGGER.info("Showing the confirm dialog called 'Refactoring' to user");

            int result = JOptionPane.showConfirmDialog(MainWindow.getIntance(), panel, "Refactoring", optionType, messageType);

            if (result == JOptionPane.CANCEL_OPTION) {

                if (LOGGER.isInfoEnabled()) LOGGER.info("The user cancelled the dialog");

                break;
            }

            if (result == JOptionPane.CLOSED_OPTION) {

                if (LOGGER.isInfoEnabled()) LOGGER.info("The user close the dialog");

                break;
            }

            if (LOGGER.isInfoEnabled()) LOGGER.info("The user pushed the OK button. Validaing the refactoring");

            NrpBase refactoring = panel.getRefactoring();

            if (LOGGER.isInfoEnabled()) LOGGER.info(refactoring);

            refactoring.loadActors(window.getRefactored());

            List<Condition> conditions = refactoring.getPreConditions(window.getRefactored());

            List<Boolean> results = new ArrayList<>();

            for (Condition condition : conditions) {

                boolean isValid = condition.validate(window.getRefactored());

                if (!isValid) {
                    MessageBox.error(MainWindow.getIntance(), condition.getError(), null);
                    break;
                }

                results.add(isValid);
            }

            if (results.size() == conditions.size() && !results.contains(false)) {

                if (LOGGER.isInfoEnabled()) LOGGER.info("The new refactoring is valid");

                return JOptionPane.OK_OPTION;
            }
        }

        return JOptionPane.CANCEL_OPTION;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (LOGGER.isInfoEnabled()) LOGGER.info("The user opened the pane for adding new refactorig");

        if (showDialog(window.getRefactored(), null) == JOptionPane.OK_OPTION) {
            window.addRefactoring(panel.getRefactoring());
        }
    }
}
