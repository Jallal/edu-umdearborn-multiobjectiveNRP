package thiagodnf.doupr.gui.subwindow;

import thiagodnf.doupr.gui.panel.ShowImagePanel;

import javax.swing.*;

public class ViewClusterExplainWindow extends SubWindow {

    private static final long serialVersionUID = -837390555384578392L;

    protected String chartsDir;

    public ViewClusterExplainWindow(String chartsDir) {
        super("Cluster Stats");

        this.chartsDir = chartsDir;

        addComponents();

        setVisible(true);
    }

    public void addComponents() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Class Frequency", null, new JScrollPane(new ShowImagePanel(chartsDir + "/RefactoredClasses.png")));
        tabbedPane.addTab("Refactoring Frequency", null, new JScrollPane(new ShowImagePanel(chartsDir + "/RefactoringOperations.png")));
        tabbedPane.addTab("Quality", null, new JScrollPane(new ShowImagePanel(chartsDir + "/QualityRadarChart.png")));

        add(tabbedPane);
    }

    @Override
    public void updateWindow() {
        // TODO Auto-generated method stub

    }
}
