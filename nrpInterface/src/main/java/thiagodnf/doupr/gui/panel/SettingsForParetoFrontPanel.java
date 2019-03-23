package thiagodnf.doupr.gui.panel;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.gui.action.button.AddFilterAction;
import thiagodnf.doupr.gui.action.button.ChooseColorizeAction;
import thiagodnf.doupr.gui.action.button.RemoveFilterAction;
import thiagodnf.doupr.gui.action.button.ViewObjectiveValuesAction;
import thiagodnf.doupr.gui.colorize.Colorize;
import thiagodnf.doupr.gui.colorize.ColorizeByEuclideanDistance;
import thiagodnf.doupr.gui.colorize.ColorizeByLevenshteinDistance;
import thiagodnf.doupr.gui.colorize.NoColorize;
import thiagodnf.doupr.gui.component.JButtonGroup;
import thiagodnf.doupr.gui.component.JHorizontalGroup;
import thiagodnf.doupr.gui.component.JPanelForTabbedPane;
import thiagodnf.doupr.gui.component.JScroll;
import thiagodnf.doupr.gui.component.JShortButton;
import thiagodnf.doupr.gui.component.JSortedComboBox;
import thiagodnf.doupr.gui.model.table.FilterTableModel;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import thiagodnf.doupr.gui.util.GridBagUtils;
import thiagodnf.doupr.optimization.filter.Filter;

import javax.swing.*;
import java.util.List;

public class SettingsForParetoFrontPanel extends JPanelForTabbedPane {

	private static final long serialVersionUID = 7545508284055182435L;

	protected JTable table;

	protected JShortButton addLimitButton;

	protected JShortButton removeLimitButton;

	protected JSortedComboBox<Colorize> colorizeCombobox;

	protected JSortedComboBox<String> normalizedCombobox;

	protected ViewParetoFrontSubWindow window;

	public SettingsForParetoFrontPanel(ViewParetoFrontSubWindow window) {

		this.window = window;

		addComponents();
		initComponents();
		initLayout();
	}

	private void initComponents() {
		this.colorizeCombobox.addItem(new ColorizeByEuclideanDistance(), true);
		this.colorizeCombobox.addItem(new ColorizeByLevenshteinDistance());
		this.colorizeCombobox.addItem(new NoColorize());

		this.normalizedCombobox.addItem("Normalized");
		this.normalizedCombobox.addItem("Original", true);
	}

	private void addComponents() {
		this.table = new JTable(new FilterTableModel());

		this.colorizeCombobox = new JSortedComboBox<>(new ChooseColorizeAction(window));
		this.normalizedCombobox = new JSortedComboBox<>(new ViewObjectiveValuesAction(window));

		this.addLimitButton = new JShortButton('+', new AddFilterAction(window));
		this.removeLimitButton = new JShortButton('-', new RemoveFilterAction(window, this));
	}

	private void initLayout() {
		GridBagUtils.setComponent(this, new JHorizontalGroup("Colorize chart by ", colorizeCombobox), 0, 0, "LEFT", "NONE");
		GridBagUtils.setComponent(this, new JHorizontalGroup(new JLabel("View "), normalizedCombobox, new JLabel(" objective values")), 1, 0, "LEFT", "NONE");
		GridBagUtils.setSeparator(this, 2, 2);
		GridBagUtils.setComponent(this, new JLabel("Filters:"), 3, 0, "LEFT", "HORIZONTAL");
		GridBagUtils.setComponent(this, new JScroll(table), 4, 0, "LEFT", "BOTH", 1, 1, 1, 40);
		GridBagUtils.setComponent(this, new JButtonGroup(addLimitButton, removeLimitButton), 5, 0, "LEFT", "NONE");
	}

	public JTable getTable() {
		return this.table;
	}

	@Override
	public void load(ProjectObject project, List<Refactoring> refactorings) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public void update(Object... objects) {
		((FilterTableModel) this.table.getModel()).setFilters((List<Filter>) objects[1]);
	}
}
