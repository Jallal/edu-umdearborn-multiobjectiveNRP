package thiagodnf.doupr.gui.subwindow;

import thiagodnf.doupr.gui.panel.FrequencyByOperationPanel;
import thiagodnf.doupr.gui.panel.FrequencyByRefactoringClassPanel;
import thiagodnf.doupr.optimization.solution.Solution;

import javax.swing.*;
import java.util.List;

public class ViewStatsWindow extends SubWindow {

    private static final long serialVersionUID = -6786161270072119947L;

    protected List<Solution> paretoFront;

    protected JTable table;

    public ViewStatsWindow(List<Solution> paretoFront) {
        super("Stats");

        this.paretoFront = paretoFront;

        addComponents();

        setVisible(true);
    }

    public void addComponents() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Frequency by Operation", null, new FrequencyByOperationPanel(paretoFront));
        tabbedPane.addTab("Frequency by Refactoring", null, new FrequencyByRefactoringClassPanel(paretoFront));

        add(tabbedPane);
    }

    @Override
    public void updateWindow() {
        // TODO Auto-generated method stub

    }
}
