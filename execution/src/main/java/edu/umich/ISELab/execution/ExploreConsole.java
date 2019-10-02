package edu.umich.ISELab.execution;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import edu.umich.ISELab.core.backlog.Bug;
import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.factory.GroomingFactory;
import edu.umich.ISELab.core.grooming.AssignTask;
import edu.umich.ISELab.core.grooming.Grooming;
import edu.umich.ISELab.core.grooming.util.Candidate;
import edu.umich.ISELab.core.projectResources.Person;
import edu.umich.ISELab.core.util.GroomingUtils;
import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.evaluation.qualityattributes.Optimization;
import edu.umich.ISELab.evaluation.util.DesignMetricsUtil;
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
import edu.umich.ISELab.optimization.variables.GroomingVariable;
import org.uma.jmetal.algorithm.impl.AbstractEvolutionaryAlgorithm;
import org.uma.jmetal.util.AlgorithmRunner;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ExploreConsole {


    private final static String COMMA_DELIMITER = ",";

    public static void main(String[] args) throws Exception {
        //personList
        List<Person> personList = new ArrayList<>();
        /*Person person1 = new Person();
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
        person3.setName("Nick");*/

        //personList.add(person1);
        //personList.add(person2);
        //personList.add(person3);

        //WorkItemList
        List<WorkItem> workItemList = new ArrayList<>();
        /*Task workItem1 = new Task();
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
        workItem3.setReadyForImplementation(true);*/

        //workItemList.add(workItem1);
        //workItemList.add(workItem2);
        //workItemList.add(workItem3);



        List<String[]> listOfbugs = readDataFromCustomSeperator("/home/vagrant/projects/gitProjects/edu-umich-ISELab-monrp/execution/src/main/resources/nrpData/bugs-training.csv");
        List<Bug> recordsRecords = getTheListOfBugs(listOfbugs);
        int recordCount =0;
        for (Bug workItem : recordsRecords) {
            if(recordCount<300) {
                workItemList.add(workItem);
                recordCount++;
            }
        }


        List<String[]> listOfPeople = readDataFromCustomSeperator("/home/vagrant/projects/gitProjects/edu-umich-ISELab-monrp/execution/src/main/resources/nrpData/profiles.csv");
        List<Person> listOfResources = getTheListOfResources(listOfPeople);
        int personCount =0;
        for (Person worker : listOfResources) {
            if(personCount<300) {
                personList.add(worker);
                personCount++;
            }
        }



        //project
        Project project = new Project();
        project.setPersonList(personList);
        project.setWorkItemList(workItemList);

        // The list of objectives used to optimize the problem
        CommandLineValues command = new CommandLineValues();
        List<Objective> objectives = new ArrayList<>();
        Optimization optimization = new Optimization();
        objectives.add(optimization);
        List<Grooming> selectedGrooming = new ArrayList<>();

        AssignTask assign_task = GroomingFactory.getNrpOptimization("Assign Task");
        // ProjectObjectUtils.cleanTheProject(project);
        Candidate candidate = assign_task.loadActors(project);
        assign_task.setProject(project);
        assign_task.setCandidate(candidate);
        assign_task.setResources(candidate.getResources());
        assign_task.setWorkItems(candidate.getWorkItems());
        selectedGrooming.add(new AssignTask(assign_task));

        //problem
        GroomingProblem problem = new GroomingProblem(project, objectives, selectedGrooming, candidate);
        problem.setMinSolutionSize(3);
        problem.setMaxSolutionSize(3);

        // Initiate the algorithm
        Builder builder_NSGA2 = new BuilderCustomNSGAII();
        Builder builder_NSGA3 = new BuilderCustomNSGAIII();

        // Define its parameters
        builder_NSGA2.setProblem(problem);
        builder_NSGA2.setPopulationSize(2);
        builder_NSGA2.setMaxEvalutions(2);
        builder_NSGA2.setCrossover(new SinglePointCrossover(command.getCrossoverProbability()));
        builder_NSGA2.setMutation(new BitFlipMutation(command.getMutationProbability()));
        builder_NSGA2.setSelection(new BinaryTournamentSelection());
        AbstractEvolutionaryAlgorithm<Solution, List<Solution>> algorithm = builder_NSGA2.build();
        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute();

        long computingTime = algorithmRunner.getComputingTime();
        List<Solution> paretoFront = algorithm.getResult();
        printResults(problem, project, paretoFront);

    }

    public static void printResults(GroomingProblem problem, Project originalProject,
                                    List<Solution> paretoFront) throws Exception {
        int count = 0;
        for (int i = 0; i < paretoFront.size(); i++) {

           Solution solution = paretoFront.get(i);



            //startTest remove after

            //End Test remove after
            System.out.println("************************** SOLUTION BEFORE ********************** :");
            GroomingSolution solutions =  (GroomingSolution)paretoFront.get(i);
            GroomingVariable variables = (GroomingVariable) solutions.getVariables();
            /*List<WorkItem> workItems1 = variables.getRefactorings();
            for (int j = 0; j <workItems1.size() ; j++) {
                WorkItem item = workItems1.get(j);
                System.out.println("solution number " + i + " Task : "+item.getWorkItemID()+" Person assigned to "+item.getPerson().getName());

            }*/
            //System.out.println(solution);
            System.out.println("========================================================================");

            List<Grooming> grooming = ((GroomingVariable) solution.getVariableValue(0)).getRefactorings();

            Project refactored = GroomingUtils.apply(originalProject, grooming);
            refactored.setDesignMetrics(DesignMetricsUtil.calculate(refactored));
            problem.calculateFitnessFunction(solution, refactored);

            //System.out.println("************************** SOLUTION After ********************** :");
            //  System.out.println(solution);
            // System.out.println("========================================================================");

        }
    }


    public static List<Bug> getTheListOfBugs(List<String[]> allData) {
        List<Bug> bugList = new ArrayList<>();

        // Print Data.
        for (String[] row : allData) {
            // for (String cell : row) {
            Bug currentBug = new Bug(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8]);
            // bug_id,short_desc,creation_ts,reporter,rep_platform,op_sys,assigned_to,version,component_id

            bugList.add(currentBug);
            //System.out.print(currentBug.toString() + "\t");
            //}
            System.out.println();
        }
        return bugList;

    }


    public static List<Person> getTheListOfResources(List<String[]> allData) {
        List<Person> resourceList = new ArrayList<>();

        // Print Data.
        for (String[] row : allData) {

            Person resource = new Person(row[0], row[1], row[2], row[3]);

            resourceList.add(resource);
        }
        return resourceList;
    }


    public static List<String[]> readDataFromCustomSeperator(String file) {
        List<String[]> allData = null;

        try {

            // Create an object of file reader class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvParser object with
            // custom seperator semi-colon
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

            // create csvReader object with parameter
            // filereader and parser
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();

            // Read all data at once
            allData = csvReader.readAll();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return allData;
    }


}
