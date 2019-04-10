package thiagodnf.doupr.gui.panel;

import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.core.sys.LOGGER;
import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.evaluation.factory.ObjectiveFactory;
import thiagodnf.doupr.evaluation.qualityattributes.Cohesion;
import thiagodnf.doupr.evaluation.qualityattributes.Complexity;
import thiagodnf.doupr.evaluation.qualityattributes.Coupling;
import thiagodnf.doupr.evaluation.qualityattributes.NumberOfNRPOptimization;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODEffectiveness;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODExtendibility;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODFlexibility;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODFunctionality;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODReusability;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODUnderstandability;
import thiagodnf.doupr.gui.component.JCheckBoxGroup;
import thiagodnf.doupr.gui.component.JOpaquePanel;
import thiagodnf.doupr.gui.component.JPanelForTabbedPane;
import thiagodnf.doupr.gui.util.GridBagUtils;
import thiagodnf.doupr.gui.util.PreferencesUtils;
import thiagodnf.doupr.optimization.algorithm.builder.Builder;
import thiagodnf.doupr.optimization.factory.AlgorithmFactory;
import thiagodnf.doupr.optimization.operators.crossovers.SinglePointCrossover;
import thiagodnf.doupr.optimization.operators.mutations.BitFlipMutation;
import thiagodnf.doupr.optimization.operators.selections.BinaryTournamentSelection;
import thiagodnf.doupr.optimization.solution.Solution;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OptimizePanel extends JOpaquePanel implements ChangeListener {

	private static final long serialVersionUID = -4597258174703827474L;

	protected JComboBox<String> algorithmComboBox;

	protected JSpinner crossoverProbabilitySpinner;

	protected JSpinner mutationProbabilitySpinner;

	protected JSpinner populationSizeSpinner;

	protected JSpinner maxEvaluationSpinner;

	protected JSpinner minRefactoringsSpinner;

	protected JSpinner maxRefactoringsSpinner;

	protected JComboBox<String> selectionOperatorComboBox;

	protected JComboBox<String> crossoverOperatorComboBox;

	protected JComboBox<String> mutationOperatorComboBox;

	protected JCheckBoxGroup groupForQMood;

	protected JCheckBoxGroup groupForStandard;

	protected JCheckBoxGroup groupForOthers;

	protected JCheckBoxGroup groupForMovingFeatures;

	protected JCheckBoxGroup groupForOrganizingData;

	protected JCheckBoxGroup groupForSimplifyingMethod;

	protected JCheckBoxGroup groupForDealingWithGeneralisation;

	protected JTabbedPane tabbedPane;

	public OptimizePanel() {

		LOGGER.info(this, "Loading Optimize Panel");

		setLayout(new GridBagLayout());

		// Create the components

		addComponents();

		// Initialize the values
		init();
	}

	protected void init() {

		this.algorithmComboBox.setSelectedItem(PreferencesUtils.getAlgorithm());
		this.populationSizeSpinner.setValue(PreferencesUtils.getPopulationSize());
		this.maxEvaluationSpinner.setValue(PreferencesUtils.getMaxEvaluation());
		this.minRefactoringsSpinner.setValue(PreferencesUtils.getMinRefactorings());
		this.maxRefactoringsSpinner.setValue(PreferencesUtils.getMaxRefactorings());
		this.crossoverProbabilitySpinner.setValue(PreferencesUtils.getCrossoverProbability());
		this.mutationProbabilitySpinner.setValue(PreferencesUtils.getMutationProbability());

		List<String> objectives = PreferencesUtils.getObjectives();

		groupForQMood.setSelected(objectives);
		groupForStandard.setSelected(objectives);
		groupForOthers.setSelected(objectives);

		List<String> refactorings = PreferencesUtils.getRefactorings();

		groupForMovingFeatures.setSelected(refactorings);
		groupForOrganizingData.setSelected(refactorings);
		groupForSimplifyingMethod.setSelected(refactorings);
		groupForDealingWithGeneralisation.setSelected(refactorings);
	}

	protected void addComponents() {

		this.algorithmComboBox = new JComboBox<String>(new String[]{"NSGA-III", "NSGA-II"});
		this.populationSizeSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 1000, 1));
		this.maxEvaluationSpinner = new JSpinner(new SpinnerNumberModel(10, 10, 1000000, 1));
		this.minRefactoringsSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 1000000, 1));
		this.maxRefactoringsSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 1000000, 1));
		this.selectionOperatorComboBox = new JComboBox<String>(new String[]{"Tournament Binary"});
		this.crossoverOperatorComboBox = new JComboBox<String>(new String[]{"Single-Point Crossover"});
		this.mutationOperatorComboBox = new JComboBox<String>(new String[]{"Bit Flip"});
		this.crossoverProbabilitySpinner = new JSpinner(new SpinnerNumberModel(0.9, 0.0, 1.0, 0.05));
		this.mutationProbabilitySpinner = new JSpinner(new SpinnerNumberModel(0.1, 0.0, 1.0, 0.005));

		this.tabbedPane = new JTabbedPane();

		tabbedPane.addTab("General", null, getPanelForGeneralSettings());
		tabbedPane.addTab("Objectives", null, getPanelForObjectivesSettings());
		tabbedPane.addTab("Refactorings", null, getPanelForRefactoringsSettings());

		tabbedPane.addChangeListener(this);

		add(tabbedPane);
	}

	@Override
	public void stateChanged(ChangeEvent changeEvent) {

		String title = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());

		LOGGER.info(this, "The user is visualizing the '" + title + "' tab");
	}

	protected JPanel getPanelForGeneralSettings() {

		JPanel panel = new JPanelForTabbedPane(new GridBagLayout());

		GridBagUtils.setComponent(panel, new JLabel("Algorithm:"), 0, 0, "RIGHT", "NONE");
		GridBagUtils.setComponent(panel, algorithmComboBox, 0, 1, "RIGHT", "HORIZONTAL");

		GridBagUtils.setSeparator(panel, 1, 2);

		GridBagUtils.setComponent(panel, new JLabel("Population Size:"), 2, 0, "RIGHT", "NONE");
		GridBagUtils.setComponent(panel, populationSizeSpinner, 2, 1, "RIGHT", "HORIZONTAL");

		GridBagUtils.setComponent(panel, new JLabel("Max Evaluation:"), 3, 0, "RIGHT", "NONE");
		GridBagUtils.setComponent(panel, maxEvaluationSpinner, 3, 1, "RIGHT", "HORIZONTAL");

		GridBagUtils.setSeparator(panel, 4, 2);

		GridBagUtils.setComponent(panel, new JLabel("Selection Operator:"), 5, 0, "RIGHT", "NONE");
		GridBagUtils.setComponent(panel, selectionOperatorComboBox, 5, 1, "RIGHT", "HORIZONTAL");

		GridBagUtils.setComponent(panel, new JLabel("Crossover Operator:"), 6, 0, "RIGHT", "NONE");
		GridBagUtils.setComponent(panel, crossoverOperatorComboBox, 6, 1, "RIGHT", "HORIZONTAL");

		GridBagUtils.setComponent(panel, new JLabel("Mutation Operator:"), 7, 0, "RIGHT", "NONE");
		GridBagUtils.setComponent(panel, mutationOperatorComboBox, 7, 1, "RIGHT", "HORIZONTAL");

		GridBagUtils.setComponent(panel, new JLabel("Crossover Probability:"), 8, 0, "RIGHT", "NONE");
		GridBagUtils.setComponent(panel, crossoverProbabilitySpinner, 8, 1, "RIGHT", "HORIZONTAL");

		GridBagUtils.setComponent(panel, new JLabel("Mutation Probability:"), 9, 0, "RIGHT", "NONE");
		GridBagUtils.setComponent(panel, mutationProbabilitySpinner, 9, 1, "RIGHT", "HORIZONTAL");

		GridBagUtils.setSeparator(panel, 10, 2);

		GridBagUtils.setComponent(panel, new JLabel("Min Refactorings:"), 11, 0, "RIGHT", "NONE");
		GridBagUtils.setComponent(panel, minRefactoringsSpinner, 11, 1, "RIGHT", "HORIZONTAL");

		GridBagUtils.setComponent(panel, new JLabel("Max Refactorings:"), 12, 0, "RIGHT", "NONE");
		GridBagUtils.setComponent(panel, maxRefactoringsSpinner, 12, 1, "RIGHT", "HORIZONTAL");

		return panel;
	}

	protected JPanel getPanelForObjectivesSettings() {

		groupForQMood = new JCheckBoxGroup("QMOOD", 3);

		groupForQMood.add(new QMOODEffectiveness().toString(), true);
		groupForQMood.add(new QMOODExtendibility().toString(), true);
		groupForQMood.add(new QMOODFlexibility().toString(), true);
		groupForQMood.add(new QMOODFunctionality().toString(), true);
		groupForQMood.add(new QMOODReusability().toString(), true);
		groupForQMood.add(new QMOODUnderstandability().toString(), true);

		groupForStandard = new JCheckBoxGroup("Standard", 2);

		groupForStandard.add(new Coupling().toString(), true);
		groupForStandard.add(new Cohesion().toString(), true);
		groupForStandard.add(new Complexity().toString(), true);

		groupForOthers = new JCheckBoxGroup("Others", 3);

		groupForOthers.add(new NumberOfNRPOptimization().toString(), false);

		JPanel p = new JOpaquePanel(new GridBagLayout());

		GridBagUtils.setComponent(p, groupForQMood, 0, 0, "LEFT", "HORIZONTAL");
		GridBagUtils.setComponent(p, groupForStandard, 1, 0, "LEFT", "HORIZONTAL");
		GridBagUtils.setComponent(p, groupForOthers, 2, 0, "LEFT", "HORIZONTAL");

		JPanel panel = new JPanelForTabbedPane(new GridBagLayout());

		GridBagUtils.setComponent(panel, p, 0, 0, "NORTH", "HORIZONTAL");

		return panel;
	}

	protected JPanel getPanelForRefactoringsSettings() {

		groupForMovingFeatures = new JCheckBoxGroup("Moving Features between Objects", 2);

		groupForMovingFeatures.add(new MoveMethod().getName(), true);
		groupForMovingFeatures.add(new MoveField().getName(), true);
		groupForMovingFeatures.add(new ExtractClass().getName(), true);

		groupForOrganizingData = new JCheckBoxGroup("Organizing Data", 3);

		groupForOrganizingData.add(new EncapsulateField().getName(), true);

		groupForSimplifyingMethod = new JCheckBoxGroup("Simplifying Method Calls", 2);

		groupForSimplifyingMethod.add(new DecreaseFieldSecurity().getName(), true);
		groupForSimplifyingMethod.add(new DecreaseMethodSecurity().getName(), true);
		groupForSimplifyingMethod.add(new IncreaseFieldSecurity().getName(), true);
		groupForSimplifyingMethod.add(new IncreaseMethodSecurity().getName(), true);

		groupForDealingWithGeneralisation = new JCheckBoxGroup("Dealing with Generalisation", 4);

		groupForDealingWithGeneralisation.add(new PullUpField().getName(), true);
		groupForDealingWithGeneralisation.add(new PullUpMethod().getName(), true);
		groupForDealingWithGeneralisation.add(new PushDownField().getName(), true);
		groupForDealingWithGeneralisation.add(new PushDownMethod().getName(), true);
		groupForDealingWithGeneralisation.add(new ExtractSuperClass().getName(), true);
		groupForDealingWithGeneralisation.add(new ExtractSubClass().getName(), true);

		JPanel p = new JOpaquePanel(new GridBagLayout());

		GridBagUtils.setComponent(p, groupForMovingFeatures, 0, 0, "LEFT", "HORIZONTAL");
		GridBagUtils.setComponent(p, groupForOrganizingData, 1, 0, "LEFT", "HORIZONTAL");
		GridBagUtils.setComponent(p, groupForSimplifyingMethod, 2, 0, "LEFT", "HORIZONTAL");
		GridBagUtils.setComponent(p, groupForDealingWithGeneralisation, 3, 0, "LEFT", "HORIZONTAL");

		JPanel panel = new JPanelForTabbedPane(new GridBagLayout());

		GridBagUtils.setComponent(panel, p, 0, 0, "NORTH", "HORIZONTAL");

		return panel;
	}

	public List<Objective> getObjectives() {

		List<Objective> objectives = new ArrayList<>();

		for (String selectedObjective : getSelectedObjectives()) {
			objectives.add(ObjectiveFactory.getObjective(selectedObjective));
		}

		return objectives;
	}

	public List<String> getSelectedObjectives() {

		List<String> selectedObjectives = new ArrayList<>();

		selectedObjectives.addAll(groupForQMood.getSelected());
		selectedObjectives.addAll(groupForStandard.getSelected());
		selectedObjectives.addAll(groupForOthers.getSelected());

		return selectedObjectives;
	}

	public List<Refactoring> getRefactorings() {

		List<Refactoring> refactorings = new ArrayList<>();

		for (String selected : getSelectedRefactorings()) {
			refactorings.add(RefactoringFactory.getRefactoring(selected));
		}

		return refactorings;
	}

	public List<String> getSelectedRefactorings() {

		List<String> selectedRefactorings = new ArrayList<>();

		selectedRefactorings.addAll(groupForMovingFeatures.getSelected());
		selectedRefactorings.addAll(groupForOrganizingData.getSelected());
		selectedRefactorings.addAll(groupForSimplifyingMethod.getSelected());
		selectedRefactorings.addAll(groupForDealingWithGeneralisation.getSelected());

		return selectedRefactorings;
	}

	public Builder getBuilder() {
		return AlgorithmFactory.getAlgorithm(getAlgorithm());
	}

	public String getAlgorithm() {
		return (String) this.algorithmComboBox.getSelectedItem();
	}

	public int getPopulationSize() {
		return ((Number) this.populationSizeSpinner.getValue()).intValue();
	}

	public int getMaxEvaluations() {
		return ((Number) this.maxEvaluationSpinner.getValue()).intValue();
	}

	public int getMinRefactorings() {
		return ((Number) this.minRefactoringsSpinner.getValue()).intValue();
	}

	public int getMaxRefactorings() {
		return ((Number) this.maxRefactoringsSpinner.getValue()).intValue();
	}

	public double getCrossoverProbability() {
		return ((Number) this.crossoverProbabilitySpinner.getValue()).doubleValue();
	}

	public double getMutationProbability() {
		return ((Number) this.mutationProbabilitySpinner.getValue()).doubleValue();
	}

	public CrossoverOperator<Solution> getCrossoverOperator() {
		return new SinglePointCrossover(getCrossoverProbability());
	}

	public MutationOperator<Solution> getMutationOperator() {
		return new BitFlipMutation(getMutationProbability());
	}

	public SelectionOperator<List<Solution>, Solution> getSelectionOperator() {
		return new BinaryTournamentSelection();
	}
}
