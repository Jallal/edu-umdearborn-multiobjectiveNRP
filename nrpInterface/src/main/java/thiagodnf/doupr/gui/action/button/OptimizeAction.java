package thiagodnf.doupr.gui.action.button;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.gui.asynctask.AsyncTask;
import thiagodnf.doupr.gui.asynctask.OptimizeAsyncTask;
import thiagodnf.doupr.gui.listener.AsyncTaskListener;
import thiagodnf.doupr.gui.panel.OptimizePanel;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.gui.util.MessageBox.MessageBoxListener;
import thiagodnf.doupr.gui.util.PreferencesUtils;
import thiagodnf.doupr.gui.window.MainWindow;
import thiagodnf.doupr.optimization.algorithm.builder.Builder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class OptimizeAction implements ActionListener {

    protected static final Logger LOGGER = Logger.getLogger(OptimizeAction.class);

    protected ProjectObject project;

    protected File selectedFile;

    public OptimizeAction(File selectedFile, ProjectObject project) {
        this.project = project;
        this.selectedFile = selectedFile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final OptimizePanel panel = new OptimizePanel();

        MessageBox.confirm(panel, "Optimize", new MessageBoxListener() {

            @Override
            public void onAccepted(Object selectedOption) {

                if (panel.getMinRefactorings() > panel.getMaxRefactorings()) {
                    MessageBox.error("The # min refactorings should be less or equal than # max refactorings");
                    return;
                }

                // The list of objectives used to optimize the problem
                List<Objective> objectives = panel.getObjectives();

                List<Refactoring> refactorings = panel.getRefactorings();

                // Initiate the problem
                RefactoringProblem problem = new RefactoringProblem(selectedFile, project, objectives, refactorings);

                problem.setMinSolutionSize(panel.getMinRefactorings());
                problem.setMaxSolutionSize(panel.getMaxRefactorings());

                // Initiate the algorithm
                Builder builder = panel.getBuilder();

                // Define its parameters
                builder.setProblem(problem);
                builder.setPopulationSize(panel.getPopulationSize());
                builder.setMaxEvalutions(panel.getMaxEvaluations());
                builder.setObjectives(panel.getObjectives());

                builder.setCrossover(panel.getCrossoverOperator());
                builder.setMutation(panel.getMutationOperator());
                builder.setSelection(panel.getSelectionOperator());

                // Save the user preferences

                if (PreferencesUtils.getSaveOptimizationPreferences()) {
                    PreferencesUtils.setAlgorithm(panel.getAlgorithm());
                    PreferencesUtils.setPolulationSize(panel.getPopulationSize());
                    PreferencesUtils.setMaxEvaluation(panel.getMaxEvaluations());
                    PreferencesUtils.setMinRefactorings(panel.getMinRefactorings());
                    PreferencesUtils.setMaxRefactorings(panel.getMaxRefactorings());
                    PreferencesUtils.setCrossoverProbability(panel.getCrossoverProbability());
                    PreferencesUtils.setMutationProbability(panel.getMutationProbability());
                    PreferencesUtils.setObjectives(panel.getSelectedObjectives());
                    PreferencesUtils.setRefactorings(panel.getSelectedRefactorings());
                }

                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("======= Builder =======");

                    LOGGER.info("algorithm: " + panel.getAlgorithm());
                    LOGGER.info("populationSize: " + builder.getPopulationSize());
                    LOGGER.info("maxEvaluations: " + builder.getMaxEvalutions());

                    LOGGER.info("Crossover Probability: " + panel.getCrossoverProbability());
                    LOGGER.info("Mutation Probability: " + panel.getMutationProbability());

                    LOGGER.info("minRefactorings: " + panel.getMinRefactorings());
                    LOGGER.info("maxRefactorings: " + panel.getMaxRefactorings());
                    LOGGER.info("Objectives" + builder.getObjectives());
                    LOGGER.info("Refactorings" + builder.getProblem().getSelectedRefactorings());

                    LOGGER.info("=======================");
                }

                execute(builder);
            }
        });
    }

    public void execute(Builder builder) {

        AsyncTask<?> asyncTask = new OptimizeAsyncTask(builder);

        asyncTask.addListener(new AsyncTaskListener() {

            @Override
            public void done(Object object) {

                if (LOGGER.isInfoEnabled()) LOGGER.info("Stopped");

                MessageBox.info("Done");

                try {
                    MainWindow.getIntance().openSubWindow(new ViewParetoFrontSubWindow(builder));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        asyncTask.execute();
    }
}
