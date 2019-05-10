package edu.umich.ISELab.execution;

import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.Task;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.factory.GroomingFactory;
import edu.umich.ISELab.core.grooming.AssignTask;
import edu.umich.ISELab.core.grooming.Grooming;
import edu.umich.ISELab.core.projectResources.Person;
import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.evaluation.qualityattributes.Optimization;
import edu.umich.ISELab.execution.util.CommandLineValues;
import edu.umich.ISELab.optimization.algorithm.builder.Builder;
import edu.umich.ISELab.optimization.algorithm.builder.BuilderCustomNSGAII;
import edu.umich.ISELab.optimization.algorithm.builder.BuilderCustomNSGAIII;
import edu.umich.ISELab.optimization.operators.crossovers.SinglePointCrossover;
import edu.umich.ISELab.optimization.operators.mutations.BitFlipMutation;
import edu.umich.ISELab.optimization.operators.selections.BinaryTournamentSelection;
import edu.umich.ISELab.optimization.problem.GroomingProblem;
import edu.umich.ISELab.optimization.solution.GroomingSolution;
import edu.umich.ISELab.optimization.solution.Solution;
import org.uma.jmetal.algorithm.impl.AbstractEvolutionaryAlgorithm;
import org.uma.jmetal.util.AlgorithmRunner;

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
        Person person2 = new Person();
        person2.setAssigned(false);
        person2.setOccupation("Developer");
        person2.setName("Bill");
        Person person3 = new Person();
        person3.setAssigned(false);
        person3.setOccupation("Developer");
        person3.setName("Nick");

        personList.add(person1);
        personList.add(person2);
        personList.add(person3);

        //WorkItemList
       List<WorkItem> workItemList = new ArrayList<>();
        Task workItem1 = new Task();
        workItem1.setAssigned(false);
        workItem1.setReadyForImplementation(true);
        workItem1.setWorkItemID("TASK1");
        Task workItem2 = new Task();
        workItem2.setAssigned(false);
        workItem2.setWorkItemID("TASK2");
        workItem2.setReadyForImplementation(true);
        Task workItem3 = new Task();
        workItem3.setAssigned(false);
        workItem3.setWorkItemID("TASK3");
        workItem3.setReadyForImplementation(true);

        workItemList.add(workItem1);
        workItemList.add(workItem2);
        workItemList.add(workItem3);

        //project
        Project project = new Project();
        project.setPersonList(personList);
        project.setWorkItemList(workItemList);

        // The list of objectives used to optimize the problem
        CommandLineValues command = new CommandLineValues();
        List<Objective> objectives = new ArrayList<>();
        Optimization optimization = new Optimization();
        objectives.add(optimization);
        List<Grooming> selectedRefactorings = new ArrayList<>();
        for(int i=0;i<3;i++) {
            AssignTask assign_task = GroomingFactory.getNrpOptimization("Assign Task");
            // assign_task.loadActors(project);
            //added not sure if it need to be here
            assign_task.execute(project);
            selectedRefactorings.add(assign_task);
        }


        //problem
        GroomingProblem problem = new GroomingProblem(project, objectives, selectedRefactorings);
        problem.setMinSolutionSize(3);
        problem.setMaxSolutionSize(10);

        // Initiate the algorithm
        Builder builder_NSGA2 = new BuilderCustomNSGAII();
        Builder builder_NSGA3 = new BuilderCustomNSGAIII();

        // Define its parameters
        builder_NSGA2.setProblem(problem);
        builder_NSGA2.setPopulationSize(3);
        builder_NSGA2.setMaxEvalutions(10);
        builder_NSGA2.setCrossover(new SinglePointCrossover(command.getCrossoverProbability()));
        builder_NSGA2.setMutation(new BitFlipMutation(command.getMutationProbability()));
        builder_NSGA2.setSelection(new BinaryTournamentSelection());
        AbstractEvolutionaryAlgorithm<Solution, List<Solution>> algorithm = builder_NSGA2.build();
        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute();

        long computingTime = algorithmRunner.getComputingTime();
        List<Solution> paretoFront = algorithm.getResult();
        for (Solution solution : paretoFront) {
            System.out.println(solution.toString());
        }

    }

    public static void printResults(GroomingProblem problem, Project originalProject,
                                    List<GroomingSolution> paretoFront) throws IOException {

        for (int i = 0; i < paretoFront.size(); i++) {

            GroomingSolution solution = paretoFront.get(i);

            System.out.println(solution);

            //List<Grooming> refactorings = ((GroomingVariable) solution.getVariableValue(0)).getRefactorings();
            List<Grooming> refactorings = null;

//			ProjectObject refactored = NrpUtils.applyRefactorings(originalProject, refactorings);
//			refactored.setDesignMetrics(DesignMetricsUtil.calculate(refactored));

//			problem.calculateFitnessFunction(solution, refactored);

            System.out.println(solution);
            System.out.println("==================");
        }
    }
}
