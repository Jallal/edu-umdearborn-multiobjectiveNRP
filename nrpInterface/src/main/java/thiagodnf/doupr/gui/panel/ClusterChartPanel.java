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
import thiagodnf.doupr.optimization.problem.Problem;
import thiagodnf.doupr.optimization.solution.Solution;
import thiagodnf.doupr.optimization.util.SolutionListUtils;
import vahid.ML.Clustering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.util.List;

public class ClusterChartPanel extends JPanelForTabbedPane implements ChartMouseListener {

    private static final long serialVersionUID = 8783372744074980645L;
    protected JFreeChart chart;
    protected Problem problem;
    protected List<Solution> paretoFront;
    protected ViewParetoFrontSubWindow window;
    protected String ChartType;
    protected Clustering clustering;

    public ClusterChartPanel(ViewParetoFrontSubWindow window,
                             Problem problem, List<Solution> paretoFront, Clustering clustering) {

        this.window = window;
        this.problem = problem;
        this.paretoFront = paretoFront;
        this.clustering = clustering;

        addComponents();
    }

    protected void addComponents() {

        JPanel controls = new JPanel();
        GridLayout gl_controls = new GridLayout(3, 2);
        gl_controls.setVgap(5);
        controls.setLayout(gl_controls);
        Object[] objChoices = problem.getObjectives().toArray();
        String[] chartTypeChoices = {"Scatter", "Line"};

        JComboBox xAxisCombo;
        JComboBox yAxisCombo;
        JComboBox chartTypeCombo;
        xAxisCombo = new JComboBox(objChoices);
        yAxisCombo = new JComboBox(objChoices);
        chartTypeCombo = new JComboBox(chartTypeChoices);
        chartTypeCombo.setSelectedIndex(1);

        controls.add(new Label("Chart Type:"));
        controls.add(chartTypeCombo);
        controls.add(new Label("X axis:"));
        controls.add(xAxisCombo);
        controls.add(new Label("Y axis:"));
        controls.add(yAxisCombo);
        yAxisCombo.setSelectedIndex(1);

        JPanel chartpanel = new JPanel();
        chartpanel.setLayout(new GridLayout(1, 1));
        chartpanel.add(plotChart(0, 1, "Line"));

        this.setLayout(new BorderLayout());
        this.add(controls, BorderLayout.PAGE_START);
        this.add(new JSeparator(), BorderLayout.CENTER);
        this.add(chartpanel, BorderLayout.CENTER);

        //process the selection of axis
        xAxisCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int xAxisIdx = xAxisCombo.getSelectedIndex();
                int yAxisIdx = yAxisCombo.getSelectedIndex();

                chartpanel.removeAll();
                chartpanel.setLayout(new GridLayout(1, 1));
                chartpanel.add(plotChart(xAxisIdx, yAxisIdx, "Scatter"));

            }
        });

        yAxisCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int xAxisIdx = xAxisCombo.getSelectedIndex();
                int yAxisIdx = yAxisCombo.getSelectedIndex();

                chartpanel.removeAll();
                chartpanel.setLayout(new GridLayout(1, 1));
                chartpanel.add(plotChart(xAxisIdx, yAxisIdx, "Scatter"));
            }
        });

        //process the selection of chart type
        chartTypeCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int xAxisIdx = xAxisCombo.getSelectedIndex();
                int yAxisIdx = yAxisCombo.getSelectedIndex();
                chartpanel.removeAll();
                chartpanel.setLayout(new GridLayout(1, 1));
                chartpanel.add(plotChart(xAxisIdx, yAxisIdx, chartTypeCombo.getSelectedItem().toString()));

            }
        });


    }

    protected ChartPanel plotChart(int xAxis, int yAxis, String ChartType) {

        XYDataset dataset = null;

        String xTitle;
        String yTitle;

        if (ChartType == "Scatter") {
            xTitle = problem.getObjectives().get(xAxis).toString();
            yTitle = problem.getObjectives().get(yAxis).toString();
            dataset = createDatasetForTwoObjectives(xAxis, yAxis);
            this.chart = ChartFactory.createScatterPlot(
                    "",                        // Title
                    xTitle,                    // X-Axis Title
                    yTitle,                    // Y-Axix Title
                    dataset,                        // Dataset
                    PlotOrientation.VERTICAL,
                    false,                    // Legend
                    true,                    // Tootip
                    true                    // Urls
            );
        } else if (ChartType == "Line") {
            xTitle = "";
            yTitle = "Values";
            dataset = createDatasetForManyObjectives();
            this.chart = ChartFactory.createXYLineChart(
                    "",                        // Title
                    xTitle,                    // X-Axis Title
                    yTitle,                    // Y-Axix Title
                    dataset,                    // Dataset
                    PlotOrientation.VERTICAL,
                    false,                    // Legend
                    true,                    // Tootip
                    true                    // Urls
            );
        }

        chart.setAntiAlias(true);

        ChartPanel clusterChart = new ChartPanel(chart);

        clusterChart.addChartMouseListener(new OpenSolutionAction(window, problem));
        clusterChart.addChartMouseListener(this);
        clusterChart.setMouseWheelEnabled(true);

        if (ChartType == "Line") {

            NumberAxis domainAxis = (NumberAxis) chart.getXYPlot().getDomainAxis();

            domainAxis.setTickUnit(new NumberTickUnit(1, new XAxisLabelFormatter(problem.getObjectives())));
            domainAxis.setRange(-1, problem.getObjectives().size());
        }

        colorizeLines();

        return clusterChart;

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

    ;

    protected XYDataset createDatasetForTwoObjectives(int xAxis, int yAxis) {

        final XYSeriesCollection dataset = new XYSeriesCollection();

        int index = 0;

        for (Solution solution : window.getFilteredSolutions()) {

            final XYSeries serie = new XYSeries("Solutions " + (index++));

            //since the values are mutiplied by -1 during optimization, we recover them here
            int constantX = problem.getObjectives().get(xAxis).isMinimize() ? 1 : (-1);
            int constantY = problem.getObjectives().get(yAxis).isMinimize() ? 1 : (-1);

            serie.add(constantX * solution.getObjective(xAxis), constantY * solution.getObjective(yAxis));

            dataset.addSeries(serie);
        }

        return dataset;
    }

    protected Color[] VAGenerateColors(int n) {
        Color[] cols = new Color[n];
        for (int i = 0; i < n; i++) {
            cols[i] = Color.getHSBColor((float) i / (float) n, 1.0f, 1.0f);
        }
        return cols;
    }

    protected void colorizeLines() {

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        List<Solution> solutions = window.getFilteredSolutions();

        List<Solution> targets = SolutionListUtils.getSolutionsSelectedByTheUser(solutions);

        int num_clusters = clustering.clusterCenters.size();

        for (int i = 0; i < solutions.size(); i++) {
            int clusterID = Integer.parseInt(clustering.clusterLabels.get(i)[1]);
            renderer.setSeriesPaint(i, VAGenerateColors(num_clusters)[clusterID]);
            renderer.setSeriesStroke(i, new BasicStroke(1.0f));
            renderer.setSeriesShape(i, ShapeUtilities.createDiamond(2));
        }

        for (int i = 0; i < num_clusters; i++) {
            int centerID = Integer.parseInt(clustering.clusterCenters.get(i)[1]);
            renderer.setSeriesStroke(centerID, new BasicStroke(0.5f));
            renderer.setSeriesShape(centerID, ShapeUtilities.createUpTriangle(4));
        }

        chart.getXYPlot().setRenderer(renderer);
    }

    public void update(Object... objects) {

        if (ChartType == "line") {
            chart.getXYPlot().setDataset(createDatasetForManyObjectives());
        } else if (ChartType == "scatter") {
            chart.getXYPlot().setDataset(createDatasetForTwoObjectives(0, 1));
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


//
//import java.awt.Color;
//
//import javax.swing.JFrame;
//import javax.swing.SwingUtilities;
//import javax.swing.WindowConstants;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.XYPlot;
//import org.jfree.data.xy.XYDataset;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
//
///**
// * @author imssbora
// */
//public class ClusterChartPanel extends JFrame {
//	private static final long serialVersionUID = 6294689542092367723L;
//
//	public ClusterChartPanel(String title) {
//		super(title);
//
//		// Create dataset
//		XYDataset dataset = createDataset();
//
//		// Create chart
//		JFreeChart chart = ChartFactory.createScatterPlot(
//				"Boys VS Girls weight comparison chart", 
//				"X-Axis", "Y-Axis", dataset);
//
//
//		//Changes background color
//		XYPlot plot = (XYPlot)chart.getPlot();
//		plot.setBackgroundPaint(new Color(255,228,196));
//
//
//		// Create Panel
//		ChartPanel panel = new ChartPanel(chart);
//		setContentPane(panel);
//	}
//
//	private XYDataset createDataset() {
//		XYSeriesCollection dataset = new XYSeriesCollection();
//
//		//Boys (Age,weight) series
//		XYSeries series1 = new XYSeries("Boys");
//		series1.add(1, 72.9);
//		series1.add(2, 81.6);
//		series1.add(3, 88.9);
//		series1.add(4, 96);
//		series1.add(5, 102.1);
//		series1.add(6, 108.5);
//		series1.add(7, 113.9);
//		series1.add(8, 119.3);
//		series1.add(9, 123.8);
//		series1.add(10, 124.4);
//
//		dataset.addSeries(series1);
//
//		//Girls (Age,weight) series
//		XYSeries series2 = new XYSeries("Girls");
//		series2.add(1, 72.5);
//		series2.add(2, 80.1);
//		series2.add(3, 87.2);
//		series2.add(4, 94.5);
//		series2.add(5, 101.4);
//		series2.add(6, 107.4);
//		series2.add(7, 112.8);
//		series2.add(8, 118.2);
//		series2.add(9, 122.9);
//		series2.add(10, 123.4);
//
//		dataset.addSeries(series2);
//
//		return dataset;
//	}
//
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> {
//			ClusterChartPanel example = new ClusterChartPanel("Scatter Chart Example | BORAJI.COM");
//			example.setSize(800, 400);
//			example.setLocationRelativeTo(null);
//			example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//			example.setVisible(true);
//		});
//	}
//}