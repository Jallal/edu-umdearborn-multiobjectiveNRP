package thiagodnf.doupr.gui.component;

import thiagodnf.doupr.gui.util.OSUtils;

import javax.swing.*;
import java.awt.*;

public class JButtonGroup extends JOpaquePanel {

    private static final long serialVersionUID = 1857596875243941875L;

    public JButtonGroup(JButton... buttons) {
        super(new GridBagLayout());

        for (int i = 0; i < buttons.length; i++) {

            GridBagConstraints c = new GridBagConstraints();

            c.gridx = i;
            c.gridy = 0;
            if (OSUtils.isMacOS()) {
                c.ipadx = -12;
            } else {
                c.ipadx = 0;
            }
            c.ipady = -7;
            c.anchor = GridBagConstraints.WEST;

            this.add(buttons[i], c);
            ;
        }
    }
}
