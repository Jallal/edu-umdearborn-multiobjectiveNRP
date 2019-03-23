package thiagodnf.doupr.gui.action.jmenuitem;

import thiagodnf.doupr.gui.subwindow.RecentFilesSubWindow;
import thiagodnf.doupr.gui.window.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenRecentFilesAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MainWindow.getIntance().openSubWindow(new RecentFilesSubWindow());
    }
}
