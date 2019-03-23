package thiagodnf.doupr.gui.panel;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.util.GridBagUtils;
import thiagodnf.doupr.gui.util.ImageUtils;
import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {

    protected static final Logger LOGGER = Logger.getLogger(AboutPanel.class);
    private static final long serialVersionUID = 1051434541014045758L;

    public AboutPanel() {

        if (LOGGER.isInfoEnabled()) LOGGER.info("Loading About Panel");

        setLayout(new GridBagLayout());

        addComponents();
    }

    protected void addComponents() {

        ImageIcon image = ImageUtils.getImageIcon(MainWindow.getIntance(), "logo.png");

        ImageIcon logo = new ImageIcon(image.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

        JLabel name = new JLabel("Doupr");
        JLabel version = new JLabel("Version 1.0.0");

        name.setFont(name.getFont().deriveFont(Font.BOLD, 15));
        version.setFont(name.getFont().deriveFont(Font.PLAIN, 11));

        GridBagUtils.setComponent(this, new JLabel(logo), 0, 0, "CENTER", "NONE");

        GridBagUtils.setComponent(this, new JPanel(), 1, 0, "CENTER", "NONE");
        GridBagUtils.setComponent(this, name, 2, 0, "CENTER", "NONE");

        GridBagUtils.setComponent(this, new JPanel(), 3, 0, "CENTER", "NONE");
        GridBagUtils.setComponent(this, new JLabel("A Software Refactoring Tool"), 4, 0, "CENTER", "NONE");

        GridBagUtils.setComponent(this, new JPanel(), 5, 0, "CENTER", "NONE");
        GridBagUtils.setComponent(this, version, 6, 0, "CENTER", "NONE");
    }
}
