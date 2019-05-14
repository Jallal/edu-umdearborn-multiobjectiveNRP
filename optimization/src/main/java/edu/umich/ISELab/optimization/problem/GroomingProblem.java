package edu.umich.ISELab.optimization.problem;

import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.grooming.Grooming;
import edu.umich.ISELab.core.grooming.util.Candidate;
import edu.umich.ISELab.core.util.GroomingUtils;
import edu.umich.ISELab.core.util.ProjectObjectUtils;
import edu.umich.ISELab.core.util.RandomUtils;
import edu.umich.ISELab.core.util.UUIDUtils;
import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.evaluation.util.DesignMetricsUtil;
import edu.umich.ISELab.optimization.solution.GroomingSolution;
import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.variables.GroomingVariable;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class GroomingProblem extends Problem {

    private static final long serialVersionUID = 8592620571577924372L;

    @Override
    public String getName() {
        return "GroomingProblem";
    }


    public interface EvolutionListener{
        public void evaluated();
    }

    /**
     * Define the minimum number of refactoring in a solution
     */
    protected int minSolutionSize = 5;

    /**
     * Define the maximum number of refactoring in a solution
     */
    protected int maxSolutionSize = 10;

    protected EvolutionListener listener;

    protected Project project;

    protected List<Grooming> selectedGroomings;


    //HashMap<String, ArrayList<String>> criticalAttributes;



    /*public GroomingProblem(Project project, List<Objective> objectives, List<Grooming> selectedGroomings)  {
        super(objectives);

        //Verify the arguments
        checkNotNull(project, "The project cannot be null");

        this.selectedGroomings = selectedGroomings;
        this.project = project;
        //this.criticalAttributes=criticalAttributes;

        // JMetal's Settings
        setNumberOfVariables(1);
    }*/



    public GroomingProblem(Project project, List<Objective> objectives, List<Grooming> selectedGroomings)
    {
        super(objectives);

        checkNotNull("The 'filename' cannot be null");


        this.selectedGroomings = selectedGroomings;
        this.project=project;

        //this.project.setDesignMetrics(DesignMetricsUtil.calculate(this.project));
        // this.project.setSecurityMetrics(SecurityMetricsUtil.calculate(this.project));

        // JMetal's Settings
        setNumberOfVariables(1);
    }




    /*public GroomingProblem(List<Objective> objectives, List<Grooming> selectedGroomings,  HashMap<String, ArrayList<String>> criticalAttributes )
    {
        super(objectives);

        checkNotNull("The 'filename' cannot be null");



        this.selectedGroomings = selectedGroomings;


        //this.project.setCriticalAttributes(criticalAttributes);
        // this.project.updateCriticalMethods();
        this.project.setDesignMetrics(DesignMetricsUtil.calculate(this.project));
        // this.project.setSecurityMetrics(SecurityMetricsUtil.calculate(this.project));

        // JMetal's Settings
        setNumberOfVariables(1);
    }*/


    public GroomingProblem(Project project, List<Objective> objectives, List<Grooming> selectedGroomings, Candidate targetClasses) {
        this(project, objectives, selectedGroomings);
        this.project.setCandidate(targetClasses);
    }

    public void evaluate(Solution solution) {

        UUIDUtils.restart();

        Project copy = ProjectObjectUtils.copy(project);

        List<Grooming> groomings = ((GroomingVariable) solution.getVariableValue(0)).getRefactorings();

        List<Grooming> valids = GroomingUtils.getValids(copy, groomings);

        while (valids.size() > maxSolutionSize) {
            valids.remove(valids.size() - 1);
        }

        while(valids.size() < minSolutionSize){

            Grooming grooming = RandomUtils.getRandomRefactoring(selectedGroomings);

            try {
                GroomingUtils.apply(copy, grooming);
                valids.add(grooming);
            } catch (Exception ex) { /*If the exception was thrown, we have to ignore it*/ }
        }

        ((GroomingVariable) solution.getVariableValue(0)).setRefatorings(valids);

        // ===============================
        // Calculate the Fitness Function
        // ===============================
        try {
            copy.setDesignMetrics(DesignMetricsUtil.calculate(copy));
          //  copy.setSecurityMetrics(SecurityMetricsUtil.calculate(copy));

            calculateFitnessFunction(solution, copy);
        }catch(Exception ex) {
            for (int i = 0; i < objectives.size(); i++) {
                solution.setObjective(i, Double.MAX_VALUE);
            }
        }

        if(listener != null) listener.evaluated();
    }

    public void calculateFitnessFunction(Solution solution, Project refactored){

        for (int i = 0; i < objectives.size(); i++) {
            solution.setObjective(i, objectives.get(i).calculate(project, refactored, ((GroomingVariable) solution.getVariableValue(0)).getRefactorings()));
        }
    }

    /**
     * Create a random solution that represents a list of refactorings
     *
     * @return a random solution
     */
    public GroomingSolution createSolution() {

        // Generating the amount of refactoring that this solution will have
        int numberOfRefactorings = JMetalRandom.getInstance().nextInt(minSolutionSize, maxSolutionSize);

        // Create a refactoring variable for saving the list of refactorings
        GroomingVariable variable = new GroomingVariable();

        for (int i = 0; i < numberOfRefactorings; i++) {
            variable.getRefactorings().add(RandomUtils.getRandomRefactoring(selectedGroomings));
        }

        // Generate a solution
        GroomingSolution solution = new GroomingSolution(this);

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void addEvolutionListener(EvolutionListener listener) {
        this.listener = listener;
    }

    public List<Grooming> getSelectedRefactorings() {
        return selectedGroomings;
    }

    public void setSelectedRefactorings(List<Grooming> selectedGroomings) {
        this.selectedGroomings = selectedGroomings;
    }

}
