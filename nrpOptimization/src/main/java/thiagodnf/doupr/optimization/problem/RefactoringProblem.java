package thiagodnf.doupr.optimization.problem;

import org.apache.commons.io.FilenameUtils;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.core.util.FileReaderUtils;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.RandomUtils;
import thiagodnf.doupr.core.util.RefactoringUtils;
import thiagodnf.doupr.core.util.UUIDUtils;
import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.evaluation.util.DesignMetricsUtil;
import thiagodnf.doupr.optimization.solution.RefactoringSolution;
import thiagodnf.doupr.optimization.solution.Solution;
import thiagodnf.doupr.optimization.variables.RefactoringVariable;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Software Refactoring Problem Class
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-09
 */
public class RefactoringProblem extends Problem {

	private static final long serialVersionUID = 8592620571577924372L;
	/**
	 * Define the minimum number of refactoring in a solution
	 */
	protected int minSolutionSize = 5;
	/**
	 * Define the maximum number of refactoring in a solution
	 */
	protected int maxSolutionSize = 10;
	protected EvolutionListener listener;
	protected ProjectObject project;
	protected List<Refactoring> selectedRefactorings;
	protected File file;

	public RefactoringProblem(File file, ProjectObject project, List<Objective> objectives, List<Refactoring> selectedRefactorings) {
		super(objectives);

		//Verify the arguments
		checkNotNull(project, "The project cannot be null");

		this.selectedRefactorings = selectedRefactorings;
		this.project = project;
		this.file = file;

		// JMetal's Settings
		setNumberOfVariables(1);
	}

	public RefactoringProblem(File file, List<Objective> objectives, List<Refactoring> selectedRefactorings) {
		super(objectives);

		checkNotNull(file, "The 'filename' cannot be null");

		this.selectedRefactorings = selectedRefactorings;
		this.file = file;

		try {
			this.project = FileReaderUtils.read(file);
			this.project.setDesignMetrics(DesignMetricsUtil.calculate(this.project));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// JMetal's Settings
		setNumberOfVariables(1);
	}

	public void evaluate(Solution solution) {

		UUIDUtils.restart();

		ProjectObject copy = ProjectObjectUtils.copy(project);

		List<Refactoring> refactorings = ((RefactoringVariable) solution.getVariableValue(0)).getRefactorings();

		List<Refactoring> valids = RefactoringUtils.getValids(copy, refactorings);

		while (valids.size() > maxSolutionSize) {
			valids.remove(valids.size() - 1);
		}

		while (valids.size() < minSolutionSize) {

			Refactoring refactoring = RandomUtils.getRandomRefactoring(selectedRefactorings);

			try {
				RefactoringUtils.apply(copy, refactoring);
				valids.add(refactoring);
			} catch (Exception ex) { /*If the exception was thrown, we have to ignore it*/ }
		}

		((RefactoringVariable) solution.getVariableValue(0)).setRefatorings(valids);

		// ===============================
		// Calculate the Fitness Function
		// ===============================
		try {
			copy.setDesignMetrics(DesignMetricsUtil.calculate(copy));
			calculateFitnessFunction(solution, copy);
		} catch (Exception ex) {
			for (int i = 0; i < objectives.size(); i++) {
				solution.setObjective(i, Double.MAX_VALUE);
			}
		}

		if (listener != null) listener.evaluated();
	}

	public void calculateFitnessFunction(Solution solution, ProjectObject refactored) {

		for (int i = 0; i < objectives.size(); i++) {
			solution.setObjective(i, objectives.get(i).calculate(project, refactored, ((RefactoringVariable) solution.getVariableValue(0)).getRefactorings()));
		}
	}

	/**
	 * Create a random solution that represents a list of refactorings
	 *
	 * @return a random solution
	 */
	public RefactoringSolution createSolution() {

		// Generating the amount of refactoring that this solution will have
		int numberOfRefactorings = JMetalRandom.getInstance().nextInt(minSolutionSize, maxSolutionSize);

		// Create a refactoring variable for saving the list of refactorings
		RefactoringVariable variable = new RefactoringVariable();

		for (int i = 0; i < numberOfRefactorings; i++) {
			variable.getRefactorings().add(RandomUtils.getRandomRefactoring(selectedRefactorings));
		}

		// Generate a solution
		RefactoringSolution solution = new RefactoringSolution(this);

		// Save the variable (list of refactorings) in the solution
		solution.setVariableValue(0, variable);

		return solution;
	}

	public int getMinSolutionSize() {
		return minSolutionSize;
	}

	public void setMinSolutionSize(int minSolutionSize) {
		this.minSolutionSize = minSolutionSize;
	}

	public int getMaxSolutionSize() {
		return maxSolutionSize;
	}

	public void setMaxSolutionSize(int maxSolutionSize) {
		this.maxSolutionSize = maxSolutionSize;
	}

	public ProjectObject getProject() {
		return project;
	}

	public void setProject(ProjectObject project) {
		this.project = project;
	}

	public void addEvolutionListener(EvolutionListener listener) {
		this.listener = listener;
	}

	public List<Refactoring> getSelectedRefactorings() {
		return selectedRefactorings;
	}

	public void setSelectedRefactorings(List<Refactoring> selectedRefactorings) {
		this.selectedRefactorings = selectedRefactorings;
	}

	@Override
	public String getName() {
		return "Refactoring Problem";
	}

	public String getFileName() {
		return FilenameUtils.getBaseName(file.getAbsolutePath());
	}

	public File getFile() {
		return file;
	}

	public interface EvolutionListener {
		void evaluated();
	}
}
