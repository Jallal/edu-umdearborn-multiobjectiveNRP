package thiagodnf.doupr.gui.action.jmenuitem;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

public class WindowAsTileAction implements ActionListener {

    protected static final Logger LOGGER = Logger.getLogger(WindowAsTileAction.class);

    @Override
    public void actionPerformed(ActionEvent e) {

        if (LOGGER.isInfoEnabled()) LOGGER.info("The user selected the Tile Mode for windows");

        JDesktopPane desk = MainWindow.getIntance().getDesktop();

        // How many frames do we have?
        JInternalFrame[] allframes = desk.getAllFrames();

        int count = allframes.length;

        if (count == 0) {
            return;
        }

        // Determine the necessary grid size
        int sqrt = (int) Math.sqrt(count);
        int rows = sqrt;
        int cols = sqrt;

        if (rows * cols < count) {
            cols++;
            if (rows * cols < count) {
                rows++;
            }
        }

        // Define some initial values for size & location.
        Dimension size = desk.getSize();

        int w = size.width / cols;
        int h = size.height / rows;
        int x = 0;
        int y = 0;

        // Iterate over the frames, deiconifying any iconified frames and then
        // relocating & resizing each.
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols && ((i * cols) + j < count); j++) {

                JInternalFrame f = allframes[(i * cols) + j];

                if (!f.isClosed() && f.isIcon()) {
                    try {
                        f.setIcon(false);
                    } catch (PropertyVetoException ignored) {
                    }
                }

                f.setBounds(x, y, w, h);

                x += w;
            }
            y += h; // start the next row
            x = 0;
        }
    }
}
