package thiagodnf.doupr.gui.action.button;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODFlexibility;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODReusability;
import thiagodnf.doupr.optimization.problem.RefactoringProblem;
import thiagodnf.doupr.optimization.solution.RefactoringSolution;
import thiagodnf.doupr.optimization.variables.RefactoringVariable;

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

        List<Refactoring> selectedRefactorings = new ArrayList<>();

        RefactoringProblem problem = new RefactoringProblem(new File(fileName), project, objectives, selectedRefactorings);

        RefactoringSolution solution = new RefactoringSolution(problem);

        // Create a refactoring variable for saving the list of refactorings
        RefactoringVariable variable = new RefactoringVariable();

        // Save the variable (list of refactorings) in the solution
        solution.setVariableValue(0, variable);


//		SubWindow frame = new ViewSolutionSubWindow("Apply Refactorings | " + fileName, problem, solution);
//
//		MainWindow.getIntance().openSubWindow(frame);
    }
}
