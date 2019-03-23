package thiagodnf.doupr.gui.action.jmenuitem;

import thiagodnf.doupr.gui.subwindow.DebugConsoleSubWindow;
import thiagodnf.doupr.gui.window.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenConsoleAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MainWindow.getIntance().openSubWindow(new DebugConsoleSubWindow());
    }
}
