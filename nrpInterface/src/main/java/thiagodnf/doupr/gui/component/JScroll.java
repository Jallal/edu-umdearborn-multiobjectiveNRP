package thiagodnf.doupr.gui.component;

import javax.swing.*;
import java.awt.*;

public class JScroll extends JScrollPane {

    private static final long serialVersionUID = 2592497332735564370L;

    public JScroll(Component view) {
        super(view);

        setPreferredSize(new Dimension(50, 50));

        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}
