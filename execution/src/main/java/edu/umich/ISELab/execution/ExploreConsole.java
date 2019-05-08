package edu.umich.ISELab.execution;

import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.Task;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.factory.NrpFactory;
import edu.umich.ISELab.core.grooming.AssignTask;
import edu.umich.ISELab.core.grooming.NrpBase;
import edu.umich.ISELab.core.projectResources.Person;
import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.evaluation.qualityattributes.NumberOfNRPOptimization;
import edu.umich.ISELab.optimization.algorithm.builder.Builder;
import edu.umich.ISELab.optimization.algorithm.builder.BuilderCustomNSGAII;
import edu.umich.ISELab.optimization.algorithm.builder.BuilderCustomNSGAIII;
import edu.umich.ISELab.optimization.operators.crossovers.SinglePointCrossover;
import edu.umich.ISELab.optimization.operators.mutations.BitFlipMutation;
import edu.umich.ISELab.optimization.operators.selections.BinaryTournamentSelection;
import edu.umich.ISELab.optimization.problem.NrpProblem;
import edu.umich.ISELab.optimization.solution.NrpSolution;
import edu.umich.ISELab.optimization.solution.Solution;
import org.uma.jmetal.algorithm.impl.AbstractEvolutionaryAlgorithm;
import org.uma.jmetal.util.AlgorithmRunner;
import src.main.java.edu.umich.ISELab.execution.util.CommandLineValues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExploreConsole {


	public static void main(String[] args) throws IOException {
		//personList
		List<Person> personList = new ArrayList<>();
		Person person1 = new Person();
		person1.setAssigned(false);
		person1.setOccupation("Developer");
		person1.setName("Jallal");
		personList.add(person1);

		//WorkItemList
		List<WorkItem> workItemList = new ArrayList<>();
		Task workItem1 = new Task();
		workItem1.setAssigned(false);
		workItem1.setReadyForImplementation(true);

		workItemList.add(workItem1);

		//project
		Project project = new Project();
		project.setPersonList(personList);
		project.setWorkItemList(workItemList);

		// The list of objectives used to optimize the problem
		CommandLineValues command = new CommandLineValues();
		List<Objective> objectives = new ArrayList<>();
		objectives.add(new NumberOfNRPOptimization());
		List<NrpBase> selectedRefactorings = new ArrayList<>();
		AssignTask assign_task = NrpFactory.getNrpOptimization("Assign Task");
		assign_task.loadActors(project);
		selectedRefactorings.add(assign_task);


		//problem
		NrpProblem problem = new NrpProblem(project, objectives, selectedRefactorings);
		problem.setMinSolutionSize(5);
		problem.setMaxSolutionSize(10);

		// Initiate the algorithm
		Builder builder_NSGA2 = new BuilderCustomNSGAII();
		Builder builder_NSGA3 = new BuilderCustomNSGAIII();

		// Define its parameters
		builder_NSGA2.setProblem(problem);
		builder_NSGA2.setPopulationSize(6);
		builder_NSGA2.setMaxEvalutions(10);
		builder_NSGA2.setCrossover(new SinglePointCrossover(command.getCrossoverProbability()));
		builder_NSGA2.setMutation(new BitFlipMutation(command.getMutationProbability()));
		builder_NSGA2.setSelection(new BinaryTournamentSelection());
		AbstractEvolutionaryAlgorithm<Solution, List<Solution>> algorithm = builder_NSGA2.build();
		AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute();

		long computingTime = algorithmRunner.getComputingTime();
		List<Solution> paretoFront = algorithm.getResult();

	}

	public static void printResults(NrpProblem problem, Project originalProject,
									List<NrpSolution> paretoFront) throws IOException {

		for (int i = 0; i < paretoFront.size(); i++) {

			NrpSolution solution = paretoFront.get(i);

			System.out.println(solution);

			//List<NrpBase> refactorings = ((NrpVariable) solution.getVariableValue(0)).getRefactorings();
			List<NrpBase> refactorings = null;

//			ProjectObject refactored = NrpUtils.applyRefactorings(originalProject, refactorings);
//			refactored.setDesignMetrics(DesignMetricsUtil.calculate(refactored));

//			problem.calculateFitnessFunction(solution, refactored);

			System.out.println(solution);
			System.out.println("==================");
		}
	}
}
