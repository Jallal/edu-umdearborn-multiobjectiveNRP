package thiagodnf.doupr.gui.subwindow;

import edu.umich.ISELab.core.sys.LOGGER;
import thiagodnf.doupr.gui.action.jmenuitem.CloseWindowsAction;
import thiagodnf.doupr.gui.util.LookAndFeelUtils;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;

public abstract class SubWindow extends JInternalFrame {

    private static final long serialVersionUID = -8980021127148753735L;

    public static int SUBWINDOW_COUNTER = 20;

    public SubWindow(String title) {
        this(title, new BorderLayout());
    }

    public SubWindow(String title, LayoutManager layout) {
        super(title,
                true,    // resizable
                true,    // closable
                true,    // maximizable
                true        // iconifiable
        );

        setLayout(layout);

        setSize(600, 400);

        setLocation(0 + SUBWINDOW_COUNTER, 0 + SUBWINDOW_COUNTER);

        SUBWINDOW_COUNTER += 20;

        if (SUBWINDOW_COUNTER == 300) {
            SUBWINDOW_COUNTER = 0;
        }

        addInternalFrameListener(new CloseWindowsAction());
        addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                updateWindow();
            }
        });

        if (LookAndFeelUtils.isAqua()) {
            putClientProperty("JInternalFrame.frameType", "normal");
            getRootPane().setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(212, 212, 212)));
        }

        LOGGER.info(this, "Opening the '" + this.getTitle() + "' sub-window");
    }

    public void createAndShowGUI() {
        setVisible(true);
    }

    public void updateWindow() {
        //if (LOGGER.isDebugEnabled()) LOGGER.debug("Updating the " + this.getTitle() + " sub-window");
    }
}
