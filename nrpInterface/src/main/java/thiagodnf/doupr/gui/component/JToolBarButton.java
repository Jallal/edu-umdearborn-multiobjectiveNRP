package thiagodnf.doupr.gui.component;

import thiagodnf.doupr.gui.util.ImageUtils;
import thiagodnf.doupr.gui.util.LookAndFeelUtils;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JToolBarButton extends JButton {

    private static final long serialVersionUID = 3142774957428765040L;

    public JToolBarButton(Object parent, String title, String img, String tooltip, ActionListener listener) {
        super(title, ImageUtils.getImageIcon(parent, img));

        this.setToolTipText(tooltip);

        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setOpaque(false);
        addActionListener(listener);

        if (LookAndFeelUtils.isAqua()) {
            setFont(UIManager.getDefaults().getFont("TabbedPane.font"));
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
        }
    }
}
