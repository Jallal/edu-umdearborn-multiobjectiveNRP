package thiagodnf.doupr.gui.panel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.gui.action.table.OpenSolutionAction;
import thiagodnf.doupr.gui.component.JPanelForTabbedPane;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import thiagodnf.doupr.gui.util.ColorUtils;
import thiagodnf.doupr.optimization.problem.Problem;
import thiagodnf.doupr.optimization.solution.Solution;
import thiagodnf.doupr.optimization.util.SolutionListUtils;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.util.List;

public class XYChartPanel extends JPanelForTabbedPane implements ChartMouseListener {

    private static final long serialVersionUID = 8783372744074980645L;
    protected JFreeChart chart;
    protected Problem problem;
    protected List<Solution> paretoFront;
    protected ViewParetoFrontSubWindow window;

    public XYChartPanel(ViewParetoFrontSubWindow window, Problem problem, List<Solution> paretoFront) {

        this.window = window;
        this.problem = problem;
        this.paretoFront = paretoFront;

        addComponents();
    }

    protected void addComponents() {

        setLayout(new BorderLayout());

        XYDataset dataset = null;

        String xTitle = "";
        String yTitle = "Values";

        if (problem.getObjectives().size() == 2) {
            xTitle = problem.getObjectives().get(0).toString();
            yTitle = problem.getObjectives().get(1).toString();
            dataset = createDatasetForTwoObjectives();
        } else {
            dataset = createDatasetForManyObjectives();
        }

        this.chart = ChartFactory.createXYLineChart(
                "",                        // Title
                xTitle,                    // X-Axis Title
                yTitle,                    // Y-Axix Title
                dataset,                // Dataset
                PlotOrientation.VERTICAL,
                false,                    // Legend
                true,                    // Tootip
                false                    // Urls
        );

        chart.setAntiAlias(true);

        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.addChartMouseListener(new OpenSolutionAction(window, problem));
        chartPanel.addChartMouseListener(this);
        chartPanel.setMouseWheelEnabled(true);

        if (problem.getObjectives().size() > 2) {

            NumberAxis domainAxis = (NumberAxis) chart.getXYPlot().getDomainAxis();

            domainAxis.setTickUnit(new NumberTickUnit(1, new XAxisLabelFormatter(problem.getObjectives())));
            domainAxis.setRange(-1, problem.getObjectives().size());
        }

        colorizeLines();

        this.add(chartPanel);
    }

    protected XYDataset createDatasetForManyObjectives() {

        final XYSeriesCollection dataset = new XYSeriesCollection();

        int index = 0;

        for (Solution solution : window.getFilteredSolutions()) {

            final XYSeries serie = new XYSeries("Solution " + (index++));

            for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
                //since the values are mutiplied by -1 during optimization, we recover them here
                int constant = problem.getObjectives().get(i).isMinimize() ? 1 : (-1);
                serie.add(i, constant * solution.getObjective(i));
            }

            dataset.addSeries(serie);
        }

        return dataset;
    }

    protected XYDataset createDatasetForTwoObjectives() {

        final XYSeriesCollection dataset = new XYSeriesCollection();

        int index = 0;

        for (Solution solution : window.getFilteredSolutions()) {

            final XYSeries serie = new XYSeries("Solutions " + (index++));

            serie.add(solution.getObjective(0), solution.getObjective(1));

            dataset.addSeries(serie);
        }

        return dataset;
    }

    protected void colorizeLines() {

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        List<Solution> solutions = window.getFilteredSolutions();

        List<Solution> targets = SolutionListUtils.getSolutionsSelectedByTheUser(solutions);

        double[] distances = window.getColorize().getColor(targets, solutions);

        for (int i = 0; i < solutions.size(); i++) {
            renderer.setSeriesPaint(i, ColorUtils.getColor(1.0 - distances[i]));
            renderer.setSeriesStroke(i, new BasicStroke(1.0f));
            renderer.setSeriesShape(i, ShapeUtilities.createDiamond(5));
        }

        chart.getXYPlot().setRenderer(renderer);
    }

    public void update(Object... objects) {

        if (problem.getObjectives().size() > 2) {
            chart.getXYPlot().setDataset(createDatasetForManyObjectives());
        } else {
            chart.getXYPlot().setDataset(createDatasetForTwoObjectives());
        }

        colorizeLines();
    }

    public void updateChart() {
        colorizeLines();
    }

    @Override
    public void load(ProjectObject project, List<Refactoring> refactorings) {
        updateChart();
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent event) {
        updateChart();
    }

    @Override
    public void chartMouseMoved(ChartMouseEvent event) {
    }

    protected class XAxisLabelFormatter extends DecimalFormat {

        private static final long serialVersionUID = -766315025076339836L;

        protected List<Objective> objectives;

        public XAxisLabelFormatter(List<Objective> objectives) {
            super("");
            this.objectives = objectives;
        }

        @Override
        public StringBuffer format(double number, StringBuffer result, FieldPosition fieldPosition) {

            // Ignore the another labels
            if (number <= -1 || number >= objectives.size()) {
                return new StringBuffer();
            }

            return new StringBuffer().append(objectives.get((int) number));
        }
    }
}
