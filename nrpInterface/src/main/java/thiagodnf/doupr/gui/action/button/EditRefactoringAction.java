package thiagodnf.doupr.gui.action.button;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.RefactoringUtils;
import thiagodnf.doupr.evaluation.util.DesignMetricsUtil;
import thiagodnf.doupr.gui.subwindow.ViewSolutionSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.optimization.problem.RefactoringProblem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditRefactoringAction extends AddRefactoringAction implements ActionListener {

    public EditRefactoringAction(ViewSolutionSubWindow window) {
        super(window);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (LOGGER.isInfoEnabled()) LOGGER.info("The user opened the pane for editing a refactorig");

        JTable table = window.getRefactoringsPanel().getTable();

        int[] selectedRows = table.getSelectedRows();

        for (int i = 0; i < selectedRows.length; i++) {
            selectedRows[i] = table.convertRowIndexToModel(selectedRows[i]);
        }

        if (selectedRows.length != 1) {
            MessageBox.warning("You must select just a refactoring before edit it");
            return;
        }

        Refactoring selectedRefactoring = window.getRefactorings().get(selectedRows[0]);

        List<Refactoring> refactoringsAppliedBefore = new ArrayList<>();

        for (int i = 0; i < selectedRows[0]; i++) {
            refactoringsAppliedBefore.add(window.getRefactorings().get(i));
        }

        ProjectObject copy = ProjectObjectUtils.copy(((RefactoringProblem) window.getProblem()).getProject());

        try {
            ProjectObject refactored = RefactoringUtils.apply(copy, refactoringsAppliedBefore);
            refactored.setDesignMetrics(DesignMetricsUtil.calculate(refactored));

            if (showDialog(refactored, selectedRefactoring) == JOptionPane.OK_OPTION) {
                window.editRefactoring(selectedRows[0], panel.getRefactoring());
            }
        } catch (Exception ex) {
            MessageBox.error(ex);
        }
    }
}
