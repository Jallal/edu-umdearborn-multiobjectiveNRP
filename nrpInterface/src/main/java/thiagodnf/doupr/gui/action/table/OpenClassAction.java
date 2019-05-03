package thiagodnf.doupr.gui.action.table;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.component.JTable;
import thiagodnf.doupr.gui.panel.SummaryPanel;
import thiagodnf.doupr.gui.subwindow.ViewClassSubWindow;
import thiagodnf.doupr.gui.window.MainWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OpenClassAction extends MouseAdapter {

    protected static final Logger LOGGER = Logger.getLogger(OpenClassAction.class);

    protected SummaryPanel summaryPanel;

    public OpenClassAction(SummaryPanel summaryPanel) {
        this.summaryPanel = summaryPanel;
    }

    @Override
    public void mousePressed(MouseEvent me) {

        if (me.getClickCount() == 2) {

            JTable table = (JTable) me.getSource();

            int selectedRow = table.getSelectedRow();

            ProjectObject project = summaryPanel.getProject();

            ClassObject cls = project.getClasses().get(selectedRow);

            if (LOGGER.isInfoEnabled()) LOGGER.info("Opening the '" + cls.getSimpleName() + "' class");

            MainWindow.getIntance().openSubWindow(new ViewClassSubWindow(project, cls));
        }
    }
}
