package thiagodnf.doupr.gui.component;

import thiagodnf.doupr.gui.util.GridBagUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JCheckBoxGroup extends JOpaquePanel {

    private static final long serialVersionUID = -689853783856073981L;

    protected int lines;

    protected int currentLine = 0;

    protected int currentColumn = 0;

    public JCheckBoxGroup(String name, int lines) {

        setLayout(new GridBagLayout());
        setBorder(GridBagUtils.getSimpleBorder(name));

        this.lines = lines;
    }

    public void add(String name) {
        add(name, false);
    }

    public void add(String name, boolean state) {

        JCheckBox ckb = new JCheckBox(name, state);

        if (currentLine == lines) {
            currentLine = 0;
            currentColumn++;
        }

        ckb.setOpaque(false);

        GridBagConstraints c = GridBagUtils.getConstraints(currentLine, currentColumn, "LEFT", "VERTICAL", 1, 1, 1, 1);

        if (currentLine == 0) {
            c.insets = new Insets(9, 0, 0, 0);
        }

        this.add(ckb, c);

        currentLine++;
    }

    public List<JCheckBox> getCheckBoxes() {

        List<JCheckBox> checkBoxes = new ArrayList<>();

        for (int i = 0; i < getComponentCount(); i++) {

            if (getComponent(i) instanceof JCheckBox) {

                checkBoxes.add((JCheckBox) getComponent(i));
            }
        }

        return checkBoxes;
    }

    public List<String> getSelected() {

        List<String> selected = new ArrayList<>();

        for (JCheckBox checkBox : getCheckBoxes()) {
            if (checkBox.isSelected()) {
                selected.add(checkBox.getText());
            }
        }

        return selected;
    }

    public void setSelected(List<String> values) {

        if (values == null || values.isEmpty()) {
            return;
        }

        for (JCheckBox checkBox : getCheckBoxes()) {
            if (values.contains(checkBox.getText())) {
                checkBox.setSelected(true);
            } else {
                checkBox.setSelected(false);
            }
        }
    }

    public List<Integer> getSelectedIndex() {

        List<Integer> selected = new ArrayList<>();
        int index = 0;
        for (JCheckBox checkBox : getCheckBoxes()) {
            if (checkBox.isSelected()) {
                selected.add(index);
            }
            index++;
        }

        return selected;
    }
}
