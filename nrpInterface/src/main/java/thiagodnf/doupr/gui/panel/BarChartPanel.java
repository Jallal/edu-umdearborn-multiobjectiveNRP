package thiagodnf.doupr.gui.panel;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler;
import thiagodnf.doupr.core.sys.LOGGER;
import thiagodnf.doupr.gui.component.JPanelForTabbedPane;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import thiagodnf.doupr.optimization.problem.Problem;
import thiagodnf.doupr.optimization.solution.Solution;
import vahid.ML.Clustering;
import vahid.util.Math.Stats;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BarChartPanel extends JPanelForTabbedPane {

    private static final long serialVersionUID = 7452277168541549206L;

    protected Solution thisSolution;

    protected Problem problem;

    protected List<Solution> paretoFront;

    protected int indexThisSolution;

    protected ViewParetoFrontSubWindow window;

    public BarChartPanel(ViewParetoFrontSubWindow window, Problem problem, List<Solution> paretoFront, int indexThisSolution) {

        this.window = window;
        this.paretoFront = paretoFront;
        this.thisSolution = paretoFront.get(indexThisSolution);
        this.problem = problem;
        this.indexThisSolution = indexThisSolution;

        setLayout(new BorderLayout());

        CategoryChart chart = getChart();

        XChartPanel panel = new XChartPanel(chart);

        this.add(panel);

    }

    public CategoryChart getChart() {

        LOGGER.info(this, "Preparing data and labels for solution " + indexThisSolution);

        // Data and Label for current solution
        Number[] thisSolutionObj = new Number[thisSolution.getNumberOfObjectives()];
        Number[] AvgAllSolutionObj = new Number[thisSolution.getNumberOfObjectives()];
        String[] objectiveLabels = new String[thisSolution.getNumberOfObjectives()];

        Clustering clustering = window.getClustering();
        String thisSolutionCluster = clustering.clusterLabels.get(indexThisSolution)[1];


        for (int i = 0; i < thisSolution.getNumberOfObjectives(); i++) {

            int constant = problem.getObjectives().get(i).isMinimize() ? 1 : (-1);


            thisSolutionObj[i] = constant * thisSolution.getObjective(i);
            objectiveLabels[i] = problem.getObjectives().get(i).toString();

            //creating a list of all values of a single objective of all solutions in the cluster of interest
            List<Double> singleObjAllSolutions = new ArrayList<>();
            for (Solution solution : paretoFront) {
                if (thisSolutionCluster.equals(clustering.clusterLabels.get(paretoFront.indexOf(solution))[1])) {
                    singleObjAllSolutions.add(constant * solution.getObjective(i));
                }
            }

            //convert the list to double array so that we can calculate the mean! Damn Java!
            double[] singleObjAllSolutionsARRAY = new double[singleObjAllSolutions.size()];

            int tempIdx = 0;
            for (Double obj : singleObjAllSolutions) {
                singleObjAllSolutionsARRAY[tempIdx] = obj;
                tempIdx++;
            }

            AvgAllSolutionObj[i] = new Stats().mean(singleObjAllSolutionsARRAY);
        }

        // Create Bar Chart

        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("This Solution vs. Average of All Solutions").xAxisTitle("Objectives").yAxisTitle("Improvements").theme(Styler.ChartTheme.GGPlot2).build();
        chart.addSeries("This solution", Arrays.asList(objectiveLabels), Arrays.asList(thisSolutionObj));
        chart.addSeries(String.format("Avg. of Cluster %s", thisSolutionCluster), Arrays.asList(objectiveLabels), Arrays.asList(AvgAllSolutionObj));
        chart.getStyler().setToolTipsEnabled(true);
        chart.getStyler().setHasAnnotations(true);
//        chart.getStyler().setAxisTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
//        chart.getStyler().setLegendFont(new Font(Font.SERIF, Font.PLAIN, 30));
//        chart.getStyler().setChartTitleFont(new Font(Font.SERIF, Font.PLAIN, 30) );

        return chart;
    }
}

