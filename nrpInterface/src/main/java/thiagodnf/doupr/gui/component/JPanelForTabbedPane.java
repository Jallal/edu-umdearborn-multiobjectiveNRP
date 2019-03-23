package thiagodnf.doupr.gui.component;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JPanelForTabbedPane extends JOpaquePanel {

    private static final long serialVersionUID = 5983275686485098035L;

    public JPanelForTabbedPane(LayoutManager layout) {
        super(layout);

        setBorder(new EmptyBorder(8, 8, 8, 8));
        setOpaque(false);
    }

    public JPanelForTabbedPane() {
        this(new GridBagLayout());
    }
}
