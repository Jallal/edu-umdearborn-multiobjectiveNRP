package thiagodnf.doupr.gui.subwindow;

import thiagodnf.doupr.core.refactoring.NrpBase;
import thiagodnf.doupr.core.sys.LOGGER;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.UUIDUtils;
import thiagodnf.doupr.evaluation.util.DesignMetricsUtil;
import thiagodnf.doupr.gui.action.button.AddRefactoringAction;
import thiagodnf.doupr.gui.action.button.EditRefactoringAction;
import thiagodnf.doupr.gui.action.button.ExportRefactoringsAction;
import thiagodnf.doupr.gui.action.button.OpenRefactoringsAction;
import thiagodnf.doupr.gui.action.button.RemoveRefactoringAction;
import thiagodnf.doupr.gui.action.button.ViewChartsAction;
import thiagodnf.doupr.gui.action.table.EvaluateAction;
import thiagodnf.doupr.gui.component.JToolBarButton;
import thiagodnf.doupr.gui.panel.AbstractPanel;
import thiagodnf.doupr.gui.panel.BarChartPanel;
import thiagodnf.doupr.gui.panel.DesignMetricsForProjectPanel;
import thiagodnf.doupr.gui.panel.FrequencyByClassPanel;
import thiagodnf.doupr.gui.panel.FrequencyByOperationPanel;
import thiagodnf.doupr.gui.panel.FrequencyByRefactoringClassPanel;
import thiagodnf.doupr.gui.panel.QualityAttributesPanel;
import thiagodnf.doupr.gui.panel.RefactoringsPanel;
import thiagodnf.doupr.gui.panel.SummaryPanel;
import thiagodnf.doupr.gui.util.ImageUtils;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.optimization.problem.NrpProblem;
import thiagodnf.doupr.optimization.problem.Problem;
import thiagodnf.doupr.optimization.solution.Solution;
import thiagodnf.doupr.optimization.variables.NrpVariable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewSolutionSubWindow extends SubWindow implements ChangeListener {

	private static final long serialVersionUID = -6786161270072119947L;

	protected ProjectObject refactored;

	protected RefactoringsPanel refactoringsPanel;

	protected Solution solution;

	protected Problem problem;

	protected List<Solution> paretoFront;

	protected int indexSolution;

	protected ViewParetoFrontSubWindow window;

//
//	public ViewSolutionSubWindow(String title, Problem problem, Solution solution){
//
//
//	}

	public ViewSolutionSubWindow(String title, ViewParetoFrontSubWindow window, int indexSolution) {
		super(title);

		this.window = window;
		this.problem = window.getProblem();
		this.paretoFront = window.getParetoFront();
		this.indexSolution = indexSolution;
		this.solution = window.getParetoFront().get(indexSolution);
		this.refactoringsPanel = new RefactoringsPanel(this);

		addComponents();
		addToolBar();

		applyRefactorings();

		this.refactoringsPanel.load(refactored, getRefactorings());

		LOGGER.info(this, "Opening solution: " + solution);
	}

	public void addComponents() {

		JTabbedPane tabbedPane = new JTabbedPane();

		tabbedPane.addTab("Refactorings", null, refactoringsPanel);
		tabbedPane.addTab("Summary", null, new SummaryPanel());
		tabbedPane.addTab("Design Metrics", null, new DesignMetricsForProjectPanel(((NrpProblem) problem).getProject()));
		tabbedPane.addTab("Quality Attributes", null, new QualityAttributesPanel(((NrpProblem) problem).getProject()));

		List<Solution> singleSolutionInList = new ArrayList<>(Arrays.asList(solution));
		tabbedPane.addTab("Refactoring Frequency", ImageUtils.getImageIcon(this, "analytics3.png"), new FrequencyByOperationPanel(singleSolutionInList));
		tabbedPane.setForegroundAt(4, Color.red);

		tabbedPane.addTab("Class Frequency", ImageUtils.getImageIcon(this, "seo.png"), new FrequencyByClassPanel(singleSolutionInList));
		tabbedPane.setForegroundAt(5, Color.red);

		tabbedPane.addTab("Refactoring/Class Frequency", ImageUtils.getImageIcon(this, "analytics2.png"), new FrequencyByRefactoringClassPanel(singleSolutionInList));
		tabbedPane.setForegroundAt(6, Color.red);

		tabbedPane.addTab("Quality Chart", ImageUtils.getImageIcon(this, "analytics.png"), new BarChartPanel(window, problem, paretoFront, indexSolution));
		tabbedPane.setForegroundAt(7, Color.blue);

		tabbedPane.addChangeListener(this);

		add(tabbedPane);
	}

	public void stateChanged(ChangeEvent changeEvent) {
		JTabbedPane tabbedPane = (JTabbedPane) changeEvent.getSource();
		AbstractPanel panel = (AbstractPanel) tabbedPane.getSelectedComponent();
		panel.load(refactored, getRefactorings());

		String title = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());

		LOGGER.info(this, "The user is visualizing the '" + title + "' tab");
	}

	protected void addToolBar() {

		JToolBar toolbar = new JToolBar();

		toolbar.setFloatable(false);

		String openTip = "Load the refactorings.";
		String exportTip = "Export the refactoring of this solution.";
		String chartsTip = "Class diagrams and Dependency graphs.";
		String addTip = "Add refactoring manually.";
		String editTip = "Modify the selected refactoring.";
		String removeTip = "Removing the selected refactoring.";
		String evaluateTip = "Evaluate the whole solution. This will assign your feedback on all refactorings of this solution.";

		toolbar.add(new JToolBarButton(this, "Open", "open.png", openTip, new OpenRefactoringsAction(this)));
		toolbar.add(new JToolBarButton(this, "Export", "save.png", exportTip, new ExportRefactoringsAction(this)));
		toolbar.add(new JToolBarButton(this, "Charts", "Charts.png", chartsTip, new ViewChartsAction(this)));
		toolbar.addSeparator();
		toolbar.add(new JToolBarButton(this, "Add", "add.png", addTip, new AddRefactoringAction(this)));
		toolbar.add(new JToolBarButton(this, "Edit", "Edit.png", editTip, new EditRefactoringAction(this)));
		toolbar.add(new JToolBarButton(this, "Remove", "trash.png", removeTip, new RemoveRefactoringAction(this)));
		toolbar.addSeparator();
		toolbar.add(new JToolBarButton(this, "Evaluate", "Evaluate.png", evaluateTip, new EvaluateAction(this)));

		add(toolbar, BorderLayout.NORTH);
	}

	public ProjectObject getRefactored() {
		return refactored;
	}

	public void setRefactored(ProjectObject refactored) {
		this.refactored = refactored;
	}

	public RefactoringsPanel getRefactoringsPanel() {
		return refactoringsPanel;
	}

	public List<NrpBase> getRefactorings() {
		return ((NrpVariable) solution.getVariableValue(0)).getRefactorings();
	}

	public void setRefactorings(List<NrpBase> refactorings) {
		((NrpVariable) solution.getVariableValue(0)).setRefatorings(refactorings);
	}

	public Solution getSolution() {
		return solution;
	}

	public void applyRefactorings() {
		try {
			this.refactored = NrpUtils.apply(((NrpProblem) problem).getProject(), getRefactorings());
			this.refactored.setDesignMetrics(DesignMetricsUtil.calculate(this.refactored));
		} catch (Exception ex) {
			MessageBox.error(ex);
		}
	}

	public void addRefactoring(NrpBase refactoring) {

		getRefactorings().add(refactoring);

		applyRefactorings();

		refactoringsPanel.load(refactored, getRefactorings());
	}

	public void removeRefactoring(List<NrpBase> refactoringsToRemove) {

		// First we have to remove the selected refactoring into refactoring list
		for (NrpBase refactoring : refactoringsToRemove) {
			getRefactorings().remove(refactoring);
		}

		UUIDUtils.restart();

		ProjectObject copy = ProjectObjectUtils.copy(((NrpProblem) problem).getProject());

		// After remove it, some refactoring can be invalid. So, we have to remove all invalid ones
		setRefactorings(NrpUtils.getValids(copy, getRefactorings()));

		// Next we apply the valid refactoring again and we calculate again the design metrics
		applyRefactorings();

		// Load the result in the panel
		refactoringsPanel.load(refactored, getRefactorings());
	}

	public void removeAllRefactorings() {

		// First we have to remove the selected refactoring into refactoring list
		getRefactorings().clear();

		// The refactored project turns back to the original one
		this.refactored = ((NrpProblem) problem).getProject();

		// Load the result in the panel
		this.refactoringsPanel.load(refactored, getRefactorings());
	}

	public void updateWindow() {
		this.refactoringsPanel.updateRows();
	}

	public Problem getProblem() {
		return this.problem;
	}

	public void editRefactoring(int index, NrpBase refactoring) {

		getRefactorings().set(index, refactoring);

		applyRefactorings();

		refactoringsPanel.load(refactored, getRefactorings());
	}
}
