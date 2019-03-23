package thiagodnf.doupr.gui.renderer;

import org.apache.commons.io.FilenameUtils;
import thiagodnf.doupr.gui.util.LookAndFeelUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RecentFilesCellRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = 461254971719089003L;

    public RecentFilesCellRenderer() {
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        String file = (String) value;

        String[] split = file.split("_");

        String name = FilenameUtils.getName(split[0]);
        String path = split[0];

        Border border = BorderFactory.createEmptyBorder(0, 0, 0, 0);

        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, name, TitledBorder.LEFT, TitledBorder.TOP);

        setForeground(Color.GRAY);

        if (LookAndFeelUtils.isAqua() && isSelected) {
            titledBorder.setTitleColor(Color.WHITE);
            setForeground(Color.WHITE);
        }

        setFont(titledBorder.getTitleFont().deriveFont(Font.PLAIN, 11));
        setText(path);
        setBorder(titledBorder);

        return this;
    }
}
