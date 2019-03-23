package thiagodnf.doupr.gui.action.jmenuitem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenAboutDevelopersAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        openPanel();
    }

    public void openPanel() {
        JOptionPane.showMessageDialog(null, "Developers");
    }
}
