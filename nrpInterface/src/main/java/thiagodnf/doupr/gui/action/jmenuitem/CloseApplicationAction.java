package thiagodnf.doupr.gui.action.jmenuitem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseApplicationAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
