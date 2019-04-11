package thiagodnf.doupr.gui.action.table;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.refactoring.NrpBase;
import thiagodnf.doupr.gui.component.JHorizontalSlider;
import thiagodnf.doupr.gui.subwindow.ViewSolutionSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.gui.util.MessageBox.MessageBoxListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EvaluateAction extends MouseAdapter implements ActionListener {

    protected static final Logger LOGGER = Logger.getLogger(EvaluateAction.class);

    protected ViewSolutionSubWindow window;

    protected JHorizontalSlider slider;

    public EvaluateAction(ViewSolutionSubWindow window) {
        this.window = window;
        this.slider = new JHorizontalSlider(-1.0, 1.0);
    }

    protected JPanel getPanel() {

        JPanel panel = new JPanel();

        panel.add(slider);

        return panel;
    }

    @Override
    public void mousePressed(MouseEvent me) {

        if (me.getClickCount() == 2) {

            JTable table = (JTable) me.getSource();

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                return;
            }

            NrpBase refactoring = window.getRefactorings().get(selectedRow);

            slider.setNormalizedValue(refactoring.getUserFeedback());

            showTheUserFeedbackPanelToUser(new int[]{selectedRow});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JTable table = window.getRefactoringsPanel().getTable();

        int[] selectedRows = table.getSelectedRows();

        if (selectedRows.length == 0) {
            selectedRows = getIndexes(table.getModel().getRowCount());
        }

        slider.resetValue();

        showTheUserFeedbackPanelToUser(selectedRows);
    }

    protected void showTheUserFeedbackPanelToUser(int[] indexes) {

        if (LOGGER.isInfoEnabled()) LOGGER.info("The user opened the user feedback panel");

        MessageBox.confirm(getPanel(), "User Feedback", new MessageBoxListener() {

            @Override
            public void onAccepted(Object selectedOption) {

                if (LOGGER.isInfoEnabled()) LOGGER.info("The user provided as feedback " + slider.getValue());

                for (int i = 0; i < indexes.length; i++) {
                    window.getRefactorings().get(indexes[i]).setUserFeedback(slider.getNormalizedValue());
                }

                window.updateWindow();
            }
        });
    }

    public int[] getIndexes(int total) {

        int[] array = new int[total];

        for (int i = 0; i < total; i++) {
            array[i] = i;
        }

        return array;
    }
}
