package thiagodnf.doupr.gui.component;

import thiagodnf.doupr.gui.util.OSUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JShortButton extends JButton {

    private static final long serialVersionUID = 4793333672055006129L;

    public JShortButton(char c) {
        super(String.valueOf(c), null);
    }

    public JShortButton(char c, ActionListener action) {
        this(c);
        addActionListener(action);

        setPreferredSize(new Dimension(40, getPreferredSize().height));

        if (OSUtils.isWindows()) {
            setPreferredSize(new Dimension(40, getPreferredSize().height + 5));
        }
    }
}
