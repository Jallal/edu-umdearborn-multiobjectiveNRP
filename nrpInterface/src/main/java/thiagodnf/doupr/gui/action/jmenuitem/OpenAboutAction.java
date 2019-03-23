package thiagodnf.doupr.gui.action.jmenuitem;

import thiagodnf.doupr.gui.panel.AboutPanel;
import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenAboutAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showOptionDialog(
                MainWindow.getIntance(),
                new AboutPanel(),
                "About",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{},
                null
        );
    }
}
