package thiagodnf.doupr.gui.action.button;

import edu.umich.ISELab.core.grooming.NrpBase;
import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.optimization.problem.NrpProblem;
import edu.umich.ISELab.optimization.solution.NrpSolution;
import edu.umich.ISELab.optimization.variables.NrpVariable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ApplyRefactoringsAction implements ActionListener {

    protected ProjectObject project;

    protected String fileName;

    public ApplyRefactoringsAction(String fileName, ProjectObject project) {
        this.project = project;
        this.fileName = fileName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        List<Objective> objectives = new ArrayList<>();

        objectives.add(new QMOODReusability());
        objectives.add(new QMOODFlexibility());

        List<NrpBase> selectedRefactorings = new ArrayList<>();

        NrpProblem problem = new NrpProblem(new File(fileName), project, objectives, selectedRefactorings);

        NrpSolution solution = new NrpSolution(problem);

        // Create a grooming variable for saving the list of refactorings
        NrpVariable variable = new NrpVariable();

        // Save the variable (list of refactorings) in the solution
        solution.setVariableValue(0, variable);


//		SubWindow frame = new ViewSolutionSubWindow("Apply Refactorings | " + fileName, problem, solution);
//
//		MainWindow.getIntance().openSubWindow(frame);
    }
}
