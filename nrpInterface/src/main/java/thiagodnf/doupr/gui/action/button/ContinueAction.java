package thiagodnf.doupr.gui.action.button;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.gui.panel.ContinuePanel;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.gui.util.MessageBox.MessageBoxListener;
import thiagodnf.doupr.gui.util.PreferencesUtils;
import thiagodnf.doupr.optimization.algorithm.builder.Builder;
import thiagodnf.doupr.optimization.problem.RefactoringProblem;
import thiagodnf.doupr.optimization.solution.RefactoringSolution;
import thiagodnf.doupr.optimization.solution.Solution;
import thiagodnf.doupr.optimization.util.NormalizerUtils;
import thiagodnf.doupr.optimization.util.SolutionListUtils;
import vahid.ML.Clustering;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ContinueAction implements ActionListener {

    protected static final Logger LOGGER = Logger.getLogger(ContinueAction.class);

    protected ViewParetoFrontSubWindow window;

    protected Clustering clustering;

    public ContinueAction(ViewParetoFrontSubWindow window, Clustering clustering) {
        this.window = window;
        this.clustering = clustering;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        List<Solution> paretoFront = SolutionListUtils.copy(window.getParetoFront());

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Selected Solutions");

            for (Solution sol : paretoFront) {
                if (sol.getUserSelection()) {
                    LOGGER.info(sol);
                    LOGGER.info("Feedback:" + sol.getUserFeedback());
                }
            }
        }

        double[] clusterRanks = new double[clustering.clusterCenters.size()];
        int solClusterLabel;
        for (int idx = 0; idx < paretoFront.size(); idx++) {
            solClusterLabel = Integer.parseInt(clustering.clusterLabels.get(idx)[1]);
            clusterRanks[solClusterLabel] += paretoFront.get(idx).getUserFeedback();
        }

        LOGGER.warn("Final cluster ranks:" + Arrays.toString(clusterRanks));
        clustering.setClusterRanks(clusterRanks);


        List<Solution> normalizedParetoFront = NormalizerUtils.normalize(paretoFront);

        List<Solution> selectedSolutions = new ArrayList<>();

        for (int i = 0; i < normalizedParetoFront.size(); i++) {
            if (normalizedParetoFront.get(i).getUserSelection()) {
                selectedSolutions.add(normalizedParetoFront.get(i).copy());
            }
        }


        List<Objective> objectives = new ArrayList<>();

        for (Objective objective : window.getBuilder().getObjectives()) {
            objectives.add(objective);
        }

        double[] rankingForUserSelection = new double[objectives.size()];

        for (Solution selectedSolution : selectedSolutions) {
            for (int i = 0; i < selectedSolution.getNumberOfObjectives(); i++) {
                rankingForUserSelection[i] += selectedSolution.getObjective(i);
            }
        }

        rankingForUserSelection = mean(rankingForUserSelection, selectedSolutions.size());

        double[] rankingForUserFeedback = new double[objectives.size()];

        for (Solution selectedSolution : selectedSolutions) {

            double normalizedValue = selectedSolution.getUserFeedback();

            for (int i = 0; i < selectedSolution.getNumberOfObjectives(); i++) {
                rankingForUserFeedback[i] += selectedSolution.getObjective(i) * normalizedValue;
            }
        }

        rankingForUserFeedback = mean(rankingForUserFeedback, selectedSolutions.size());

        if (LOGGER.isInfoEnabled())
            LOGGER.info("Ranking for User Selection'" + Arrays.toString(rankingForUserSelection));
        if (LOGGER.isInfoEnabled()) LOGGER.info("Ranking for User Feedback'" + Arrays.toString(rankingForUserFeedback));

//##########################################################		

        ContinuePanel continuePanel = new ContinuePanel(objectives, clustering, rankingForUserFeedback, rankingForUserSelection);

        MessageBox.confirm(continuePanel, "Continue", new MessageBoxListener() {

            @Override
            public void onAccepted(Object selectedOption) {

                List<Integer> selectedClusters = continuePanel.getSelectedClusters();
                clustering.setSelectedClusters(selectedClusters);
                clustering.OperationsProbHash();

                SolutionListUtils.clearAttributes(paretoFront);

                List<Objective> selectedObjectives = continuePanel.getSelectedObjectives();
                List<Objective> allObjectives = continuePanel.getAllObjectives();


                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("Alpha: " + continuePanel.getAlpha());
                    LOGGER.info("Range: " + continuePanel.getRange());
                    LOGGER.info("Selected Objectives: ");

                    for (Objective objective : selectedObjectives) {
                        LOGGER.info("\t" + objective);
                    }
                }

                RefactoringProblem problemWithSubSet = getANewProblemWith(allObjectives);
                problemWithSubSet.setMaxSolutionSize(window.getProblem().getMaxSolutionSize());
                problemWithSubSet.setMinSolutionSize(window.getProblem().getMinSolutionSize());

                List<Solution> newParetoFront = new ArrayList<>();

                int solClusterLabel;
                for (int idx = 0; idx < paretoFront.size(); idx++) {
                    solClusterLabel = Integer.parseInt(clustering.clusterLabels.get(idx)[1]);
                    Solution solution = paretoFront.get(idx);
                    if (selectedClusters.contains(solClusterLabel)) {
                        RefactoringSolution newSolution = new RefactoringSolution(problemWithSubSet);
                        newSolution.setAttributes(new HashMap<>(solution.getAttributes()));
                        newSolution.setVariableValue(0, solution.getVariableValue(0).copy());
                        newParetoFront.add(newSolution);
                    }
                }

//				for (Solution solution : paretoFront) {
//					RefactoringSolution newSolution = new RefactoringSolution(problemWithSubSet);
//					newSolution.setAttributes(new HashMap<>(solution.getAttributes()));
//					newSolution.setVariableValue(0, solution.getVariableValue(0).copy());
//					newParetoFront.add(newSolution);
//				}

                Builder builder = window.getBuilder();
                builder.setProblem(problemWithSubSet);
                builder.setInitialPopulation(newParetoFront);
//				builder.setMutation(new BitFlipMutationClustering(PreferencesUtils.getMutationProbability(), clustering));

                if (PreferencesUtils.getSaveContinuePreferences()) {
                    PreferencesUtils.setAlpha(continuePanel.getAlpha());
                    PreferencesUtils.setRange(continuePanel.getRange());
                }

                if (LOGGER.isInfoEnabled()) LOGGER.info("Continuing the search");

                new OptimizeAction(builder.getProblem().getFile(), null).execute(builder);
            }
        });
    }

    protected RefactoringProblem getANewProblemWith(List<Objective> objectives) {

        RefactoringProblem problem = window.getProblem();

        ProjectObject project = problem.getProject();

        List<Refactoring> refactorings = problem.getSelectedRefactorings();

        return new RefactoringProblem(problem.getFile(), project, objectives, refactorings);
    }

    protected double[] mean(double[] array, double size) {

        if (size == 0) {
            return array;
        }

        double[] newArray = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = 1.0 - array[i] / size;
        }

        return newArray;
    }
}
	

