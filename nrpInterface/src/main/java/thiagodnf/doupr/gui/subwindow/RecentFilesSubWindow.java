package thiagodnf.doupr.gui.subwindow;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.action.jmenuitem.OpenFileAction;
import thiagodnf.doupr.gui.model.list.RecentFilesListModel;
import thiagodnf.doupr.gui.renderer.RecentFilesCellRenderer;
import thiagodnf.doupr.gui.util.PreferencesUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RecentFilesSubWindow extends SubWindow {

    protected static final Logger LOGGER = Logger.getLogger(RecentFilesSubWindow.class);

    private static final long serialVersionUID = -1241533324611556819L;

    protected JList<String> list;

    public RecentFilesSubWindow() {
        super("Recent Files");

        addComponents();
        initLayout();
        updateWindow();

        if (LOGGER.isInfoEnabled()) {

            LOGGER.info("Recent Files:");

            for (String recentFile : PreferencesUtils.getRecentFiles()) {
                LOGGER.info("\t" + recentFile);
            }
        }
    }

    private void addComponents() {
        this.list = new JList<>(new RecentFilesListModel());
        this.list.setCellRenderer(new RecentFilesCellRenderer());
        this.list.addMouseListener(new OpenFileAction(this));
        this.list.addKeyListener(new OpenFileAction(this));
    }

    private void initLayout() {

        setSize(300, 400);

        JPanel panel = new JPanel(new BorderLayout());

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void updateWindow() {
        super.updateWindow();
        // We have to update the list with the most-recent opened files
        ((RecentFilesListModel) this.list.getModel()).setRecentFiles(PreferencesUtils.getRecentFiles());
        // After reload all contents, we have to select the first row
        this.list.setSelectedIndex(0);
    }
}
