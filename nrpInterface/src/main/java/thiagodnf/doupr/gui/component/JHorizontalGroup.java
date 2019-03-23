package thiagodnf.doupr.gui.component;

import thiagodnf.doupr.gui.util.GridBagUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JHorizontalGroup extends JOpaquePanel {

    private static final long serialVersionUID = -2008161233727330109L;

    public JHorizontalGroup(JComponent... components) {
        super(new GridBagLayout());

        setBorder(new EmptyBorder(0, 0, 0, 0));

        for (int i = 0; i < components.length; i++) {
            GridBagUtils.setComponent(this, components[i], 0, i, "LEFT", "NONE");
        }
    }

    public JHorizontalGroup(String name, JComponent components) {
        this(new JLabel(name), components);
    }
}
