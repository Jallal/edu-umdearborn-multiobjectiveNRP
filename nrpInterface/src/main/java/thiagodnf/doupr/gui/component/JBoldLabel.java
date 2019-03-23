package thiagodnf.doupr.gui.component;

import javax.swing.*;
import java.awt.*;

public class JBoldLabel extends JLabel {

    private static final long serialVersionUID = 4414075149434481299L;

    public JBoldLabel(String text) {
        super(text);

        setFont(getFont().deriveFont(Font.BOLD));
    }

}
