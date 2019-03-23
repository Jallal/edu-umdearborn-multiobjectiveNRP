package thiagodnf.doupr.gui.subwindow;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.gui.panel.DesignMetricsForClassPanel;

import javax.swing.*;

public class ViewClassSubWindow extends SubWindow {

    private static final long serialVersionUID = -6786161270072119947L;

    public ViewClassSubWindow(ProjectObject project, ClassObject cls) {
        super(cls.getSimpleName() + " class");

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Design Metrics", null, new DesignMetricsForClassPanel(cls));

        add(tabbedPane);

        setVisible(true);
    }

    @Override
    public void updateWindow() {
        // TODO Auto-generated method stub

    }
}
