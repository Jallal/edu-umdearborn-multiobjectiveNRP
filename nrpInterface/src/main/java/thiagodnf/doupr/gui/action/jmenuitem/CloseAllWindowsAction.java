package thiagodnf.doupr.gui.action.jmenuitem;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.subwindow.SubWindow;
import thiagodnf.doupr.gui.window.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseAllWindowsAction implements ActionListener {

    protected static final Logger LOGGER = Logger.getLogger(CloseAllWindowsAction.class);

    @Override
    public void actionPerformed(ActionEvent e) {

        SubWindow.SUBWINDOW_COUNTER = 20;

        if (LOGGER.isInfoEnabled()) LOGGER.info("The user closed all windows");

        MainWindow.getIntance().getDesktop().removeAll();
        MainWindow.getIntance().getDesktop().updateUI();
    }
}
