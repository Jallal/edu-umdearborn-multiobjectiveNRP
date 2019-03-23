package thiagodnf.doupr.gui.component;

import thiagodnf.doupr.optimization.util.NormalizerUtils;

import javax.swing.*;
import java.util.Hashtable;

public class JHorizontalSlider extends JSlider {

    private final static int MIN_RANGE = 0;

    private final static int MAX_RANGE = 10000;
    private static final long serialVersionUID = 1858989137449568778L;
    protected double min;
    protected double max;

    public JHorizontalSlider() {
        this(0.0, 1.0);
    }

    public JHorizontalSlider(double min, double max) {
        super(JSlider.HORIZONTAL, MIN_RANGE, MAX_RANGE, MAX_RANGE / 2);

        this.min = min;
        this.max = max;

        setMajorTickSpacing(MAX_RANGE / 18);
        setPaintTicks(true);

        // Create the label table
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();

        labelTable.put(MIN_RANGE, new JLabel("Bad"));
        labelTable.put(MAX_RANGE / 2, new JLabel("|"));
        labelTable.put(MAX_RANGE, new JLabel("Good"));

        setLabelTable(labelTable);
        setPaintLabels(true);
    }

    public double getNormalizedValue() {
        return NormalizerUtils.normalize(super.getValue(), min, max, MIN_RANGE, MAX_RANGE);
    }

    public void setNormalizedValue(double value) {
        super.setValue((int) NormalizerUtils.normalize(value, MIN_RANGE, MAX_RANGE, min, max));
    }

    public void resetValue() {
        super.setValue(MAX_RANGE / 2);
    }
}
