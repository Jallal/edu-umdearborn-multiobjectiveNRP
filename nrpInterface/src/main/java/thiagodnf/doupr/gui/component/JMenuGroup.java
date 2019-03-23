package thiagodnf.doupr.gui.component;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JMenuGroup extends JMenu {

    private static final long serialVersionUID = 8941839313921661986L;

    public JMenuGroup(String name, String[] options, ActionListener listener, String defaultOption) {
        super(name);

        ButtonGroup group = new ButtonGroup();

        for (int i = 0; i < options.length; i++) {

            JMenuItem menuItem = new JRadioButtonMenuItem(options[i]);

            menuItem.addActionListener(listener);
            menuItem.setActionCommand(options[i]);

            if (options[i].equalsIgnoreCase(defaultOption)) {
                menuItem.setSelected(true);
            }

            add(menuItem);

            group.add(menuItem);
        }
    }
}
