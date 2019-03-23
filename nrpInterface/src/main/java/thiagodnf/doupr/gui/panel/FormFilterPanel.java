package thiagodnf.doupr.gui.panel;

import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.gui.component.JOpaquePanel;
import thiagodnf.doupr.gui.component.JSortedComboBox;
import thiagodnf.doupr.gui.util.GridBagUtils;
import thiagodnf.doupr.optimization.filter.Filter;
import thiagodnf.doupr.optimization.problem.Problem;
import thiagodnf.doupr.optimization.util.FilterUtils;
import thiagodnf.doupr.optimization.util.constants.FilterConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FormFilterPanel extends JOpaquePanel implements ItemListener {

    private static final long serialVersionUID = 8567251071067131442L;

    protected JSortedComboBox<String> targetComboBox;

    protected JSortedComboBox<String> objectiveComboBox;

    protected JSortedComboBox<String> ruleComboBox;

    protected JTextField valueAsTextField;

    protected JSpinner valueAsNumberSpinner;

    protected Problem problem;

    public FormFilterPanel(Problem problem) {

        this.problem = problem;

        addComponents();
        initComponents();
        initLayout();
    }

    private void addComponents() {
        this.targetComboBox = new JSortedComboBox<String>();
        this.objectiveComboBox = new JSortedComboBox<String>();
        this.ruleComboBox = new JSortedComboBox<String>();
        this.valueAsTextField = new JTextField();
        this.valueAsNumberSpinner = new JSpinner(new SpinnerNumberModel(0.0, -10000000, 10000000, 0.1));

        this.valueAsTextField.setPreferredSize(new Dimension(120, valueAsTextField.getPreferredSize().height));
        this.valueAsNumberSpinner.setPreferredSize(new Dimension(120, valueAsNumberSpinner.getPreferredSize().height));

        this.targetComboBox.addItemListener(this);
    }

    private void initComponents() {
        this.valueAsNumberSpinner.setVisible(false);

        this.targetComboBox.addItem(FilterConstants.OBJECTIVE, true);
        this.targetComboBox.addItem(FilterConstants.SOLUTION);

        for (Objective objective : problem.getObjectives()) {
            this.objectiveComboBox.addItem(objective.toString());
        }

        this.objectiveComboBox.setSelectedIndex(0);

        loadObjectiveRules();
    }

    private void initLayout() {
        setLayout(new GridBagLayout());

        GridBagUtils.setComponent(this, targetComboBox, 0, 0, "NONE", "HORIZONTAL");
        GridBagUtils.setComponent(this, objectiveComboBox, 0, 1, "NONE", "HORIZONTAL");
        GridBagUtils.setComponent(this, ruleComboBox, 0, 2, "NONE", "HORIZONTAL");
        GridBagUtils.setComponent(this, valueAsTextField, 0, 3, "NONE", "HORIZONTAL");
        GridBagUtils.setComponent(this, valueAsNumberSpinner, 0, 4, "NONE", "HORIZONTAL");
    }

    private void loadObjectiveRules() {
        this.ruleComboBox.removeAllItems();
        this.ruleComboBox.addItem(FilterConstants.EQUAL_TO, true);
        this.ruleComboBox.addItem(FilterConstants.NOT_EQUAL_TO);
        this.ruleComboBox.addItem(FilterConstants.GREATER_THAN);
        this.ruleComboBox.addItem(FilterConstants.LESS_THAN);
        this.ruleComboBox.addItem(FilterConstants.GREATER_THAN_OR_EQUAL_TO);
        this.ruleComboBox.addItem(FilterConstants.LESS_THAN_OR_EQUAL_TO);
    }

    private void loadSolutionRules() {
        this.ruleComboBox.removeAllItems();
        this.ruleComboBox.addItem(FilterConstants.CONTAINS, true);
        this.ruleComboBox.addItem(FilterConstants.NOT_CONTAINS);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if (e.getSource() == targetComboBox) {

            if (getSelectedTarget().equalsIgnoreCase(FilterConstants.OBJECTIVE)) {
                this.objectiveComboBox.setVisible(true);
                this.valueAsNumberSpinner.setVisible(true);
                this.valueAsTextField.setVisible(false);
                loadObjectiveRules();
            } else {
                this.objectiveComboBox.setVisible(false);
                this.valueAsNumberSpinner.setVisible(false);
                this.valueAsTextField.setVisible(true);
                loadSolutionRules();
            }
        }
    }

    public String getSelectedTarget() {
        return String.valueOf(targetComboBox.getSelectedItem());
    }

    public String getSelectedValue() {
        if (getSelectedTarget().equalsIgnoreCase(FilterConstants.SOLUTION)) {
            return this.valueAsTextField.getText();
        }

        return String.valueOf(valueAsNumberSpinner.getValue());
    }

    public String getSelectedObjective() {
        return String.valueOf(objectiveComboBox.getSelectedItem());
    }

    public String getSelectedRule() {
        return String.valueOf(ruleComboBox.getSelectedItem());
    }

    public Filter getSelectedFilter() {

        Filter filter = new Filter();

        filter.setTarget(getSelectedTarget());
        filter.setObjectiveName(getSelectedObjective());
        filter.setRule(FilterUtils.convert(getSelectedRule()));
        filter.setValue(getSelectedValue());

        return filter;
    }
}
