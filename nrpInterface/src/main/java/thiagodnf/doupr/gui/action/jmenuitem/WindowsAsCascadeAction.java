package thiagodnf.doupr.gui.action.jmenuitem;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowsAsCascadeAction implements ActionListener {

    protected static final Logger LOGGER = Logger.getLogger(WindowsAsCascadeAction.class);

    public static void cascade(JDesktopPane desktopPane, int layer) {
        JInternalFrame[] frames = desktopPane.getAllFramesInLayer(layer);

        if (frames.length == 0) {
            return;
        }

        cascade(frames, desktopPane.getBounds(), 24);
    }

    public static void cascade(JDesktopPane desktopPane) {
        JInternalFrame[] frames = desktopPane.getAllFrames();

        if (frames.length == 0) {
            return;
        }

        cascade(frames, desktopPane.getBounds(), 24);
    }

    private static void cascade(JInternalFrame[] frames, Rectangle dBounds, int separation) {

        if (LOGGER.isInfoEnabled()) LOGGER.info("The user selected the Cascade Mode for windows");

        int width = 600;
        int height = 400;

        for (int i = 0; i < frames.length; i++) {
            frames[i].setBounds(separation + dBounds.x + i * separation, separation + dBounds.y + i * separation, width, height);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cascade(MainWindow.getIntance().getDesktop());
    }

}
