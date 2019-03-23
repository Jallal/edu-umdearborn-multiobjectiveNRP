package thiagodnf.doupr.gui.action.jmenuitem;

import thiagodnf.doupr.gui.subwindow.SubWindow;
import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class handles the actions related to close sub-windows ones. You can use this either
 * the application menu or just by closing the sub-window by using the close button
 *
 * @author Thiago Ferreira
 * @version 1.0.0
 * @since 2017-10-08
 */
public class CloseWindowsAction extends InternalFrameAdapter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        closeWindow();
    }

    public void internalFrameClosing(InternalFrameEvent e) {
        closeWindow();
    }

    public void closeWindow() {

        JDesktopPane desktop = MainWindow.getIntance().getDesktop();

        // There is no sub-window loaded
        if (desktop.getAllFrames().length == 0) {
            return;
        }

        SubWindow selected = (SubWindow) desktop.getSelectedFrame();

        // It was not possible to get the selected sub-window
        if (selected == null) {
            return;
        }

        SubWindow.SUBWINDOW_COUNTER -= 20;

        MainWindow.getIntance().closeSubWindow(selected);
    }
}
