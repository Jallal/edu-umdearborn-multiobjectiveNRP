package thiagodnf.doupr.gui.util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GridBagUtils {

    public static void setComponent(JPanel panel, Component comp, int rowIndex, int columnIndex) {
        setComponent(panel, comp, rowIndex, columnIndex, "LEFT", "NONE", 1, 1, 1.0, 1.0);
    }

    public static GridBagConstraints getConstraints(int rowIndex, int columnIndex, String align, String fill, int gridwidth, int gridheight, double weightx, double weighty) {

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = columnIndex;
        constraints.gridy = rowIndex;

        constraints.weightx = weightx;
        constraints.weighty = weighty;

        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;

        if (rowIndex == 0) {
            constraints.insets = new Insets(0, 0, 0, 0);
        } else {
            constraints.insets = new Insets(9, 0, 0, 0);
        }

        if (align.equalsIgnoreCase("RIGHT")) {
            constraints.anchor = GridBagConstraints.EAST;
        } else if (align.equalsIgnoreCase("LEFT")) {
            constraints.anchor = GridBagConstraints.WEST;
        } else if (align.equalsIgnoreCase("NORTH")) {
            constraints.anchor = GridBagConstraints.NORTH;
        }

        if (fill.equalsIgnoreCase("NONE")) {
            constraints.fill = GridBagConstraints.NONE;
        } else if (fill.equalsIgnoreCase("HORIZONTAL")) {
            constraints.fill = GridBagConstraints.HORIZONTAL;
        } else if (fill.equalsIgnoreCase("VERTICAL")) {
            constraints.fill = GridBagConstraints.VERTICAL;
        } else if (fill.equalsIgnoreCase("BOTH")) {
            constraints.fill = GridBagConstraints.BOTH;
        }

        return constraints;
    }

    public static void setComponent(JPanel panel, Component comp, int rowIndex, int columnIndex, String align, String fill) {
        setComponent(panel, comp, rowIndex, columnIndex, align, fill, 1, 1, 1.0, 1.0);
    }

    public static void setComponent(JPanel panel, Component comp, int rowIndex, int columnIndex, String align, String fill, int gridwidth, int gridheight, double weightx, double weighty) {
        panel.add(comp, getConstraints(rowIndex, columnIndex, align, fill, gridwidth, gridheight, weightx, weighty));
    }

    public static void setSeparator(JPanel panel, int rowIndex, int columns) {
        GridBagUtils.setComponent(panel, new JSeparator(SwingConstants.HORIZONTAL), rowIndex, 0, "LEFT", "HORIZONTAL", columns, 1, 1.0, 1.0);
    }

    public static Border getBorder(String title) {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(title),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        );
    }

    public static Border getSimpleBorder(String title) {

        Border border = BorderFactory.createLineBorder(new Color(153, 155, 159, 0), 1, true);

        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, title, TitledBorder.LEFT, TitledBorder.TOP);

        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));

        return titledBorder;
    }
}
