package thiagodnf.doupr.gui.subwindow;

import VAHID.ExportToCDMFilesVAHID;
import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.action.button.ClearFeedbackAction;
import thiagodnf.doupr.gui.action.button.ContinueAction;
import thiagodnf.doupr.gui.action.button.ExportParetoFrontAction;
import thiagodnf.doupr.gui.action.button.ViewClusterExplainAction;
import thiagodnf.doupr.gui.action.button.ViewStatsAction;
import thiagodnf.doupr.gui.colorize.Colorize;
import thiagodnf.doupr.gui.colorize.ColorizeByEuclideanDistance;
import thiagodnf.doupr.gui.component.JToolBarButton;
import thiagodnf.doupr.gui.panel.AbstractPanel;
import thiagodnf.doupr.gui.panel.ClusterChartPanel;
import thiagodnf.doupr.gui.panel.ClustersCenterPanel;
import thiagodnf.doupr.gui.panel.ParetoFrontPanel;
import thiagodnf.doupr.gui.panel.SettingsForParetoFrontPanel;
import thiagodnf.doupr.gui.panel.XYChartPanel;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.gui.window.MainWindow;
import edu.umich.ISELab.optimization.algorithm.builder.Builder;
import edu.umich.ISELab.optimization.filter.Filter;
import edu.umich.ISELab.optimization.problem.NrpProblem;
import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.util.FilterUtils;
import edu.umich.ISELab.optimization.util.NormalizerUtils;
import vahid.ML.Clustering;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ViewParetoFrontSubWindow extends SubWindow implements ChangeListener {

	protected static final Logger LOGGER = Logger.getLogger(ViewParetoFrontSubWindow.class);

	private static final long serialVersionUID = -6786161270072119947L;

	public static int COUNTER = 0;

	protected List<Solution> paretoFront;

	protected NrpProblem problem;

	protected Builder builder;

	protected List<Filter> filters;

	protected JTabbedPane tabbedPane;

	protected XYChartPanel xYChartPanel;

	protected thiagodnf.doupr.gui.panel.ClusterChartPanel ClusterChartPanel;

	protected Clustering clustering;

	protected boolean isNormalized = false;

	protected Colorize colorize;

	protected Path outputFolder;

	public ViewParetoFrontSubWindow(Builder builder) throws InterruptedException, IOException {

		super("Pareto-front " + COUNTER++);

		this.builder = builder;
		this.problem = builder.getProblem();
		this.paretoFront = builder.getParetoFront();
		this.filters = new ArrayList<>();
		this.colorize = new ColorizeByEuclideanDistance();

		for (int i = 0; i < paretoFront.size(); i++) {
			paretoFront.get(i).setAttribute("id", i);
		}

		if (LOGGER.isInfoEnabled()) LOGGER.info("Opening the 'Chart' tab");

//		File outputFolder = new File("VAHID-output");
//		
//		if (!outputFolder.exists()) {
//			outputFolder.mkdirs();
//		}
//		
		this.outputFolder = Files.createTempDirectory("VAHID-Refactoring-output");

		String fileName = builder.getProblem().getFileName();
		String id = MainWindow.getIntance().executionId;
//		String ext = fileName + "-" + (COUNTER);
		String ext = "-" + (COUNTER - 1);

		try {
			new ExportParetoFrontAction(this).exportToFUNFiles(new File(this.outputFolder.toAbsolutePath() + "/output-" + id + "-obj" + ext + ".txt"));
			new ExportParetoFrontAction(this).exportToVARFiles(new File(this.outputFolder.toAbsolutePath() + "/output-" + id + "-var" + ext + ".txt"));
			new ExportToCDMFilesVAHID(builder.getProblem().getProject(), new File(this.outputFolder.toAbsolutePath() + "/output-" + id + "-dm" + ext + ".txt"));
		} catch (IOException ex) {
			MessageBox.error(ex);
		}


		this.clustering = new Clustering(this.outputFolder, id, COUNTER - 1, problem.getNumberOfObjectives(), problem.getMaxSolutionSize());

		this.xYChartPanel = new XYChartPanel(this, problem, paretoFront);
		this.ClusterChartPanel = new ClusterChartPanel(this, problem, paretoFront, clustering);


		addComponents();
		addToolBar();
	}

	protected void addComponents() {

		tabbedPane = new JTabbedPane();

		tabbedPane.addTab("Chart", null, xYChartPanel);
		tabbedPane.addTab("Table", null, new ParetoFrontPanel(this, problem, paretoFront, clustering));
		tabbedPane.addTab("Clusters Chart", null, ClusterChartPanel);
		tabbedPane.addTab("Clusters Center", null, new ClustersCenterPanel(this, problem, paretoFront, clustering));
		tabbedPane.addTab("Settings", null, new SettingsForParetoFrontPanel(this));

		tabbedPane.addChangeListener(this);

		add(tabbedPane);
	}

	protected void addToolBar() {

		JToolBar toolbar = new JToolBar();

		toolbar.setFloatable(false);

		String continueTip = "Continue to the next round of optimization considering your feedback.";
		String exportTip = "Export the solutions and design metrics into CSV files.";
		String statsTip = "Statistics extracted from the solutions of this pareto front.";
		String clearTip = "Clearing all feedback.";
		String explainTip = "Investigating the solution clusters.";

		toolbar.add(new JToolBarButton(this, "Continue", "Continue.png", continueTip, new ContinueAction(this, clustering)));
		toolbar.add(new JToolBarButton(this, "Export", "save.png", exportTip, new ExportParetoFrontAction(this)));
		toolbar.add(new JToolBarButton(this, "Stats", "Stats.png", statsTip, new ViewStatsAction(this)));
		toolbar.add(new JToolBarButton(this, "Clear", "clear.png", clearTip, new ClearFeedbackAction(this)));
		toolbar.add(new JToolBar.Separator());
		toolbar.add(new JToolBarButton(this, "Explain Clusters", "Charts.png", explainTip, new ViewClusterExplainAction(this)));

		getContentPane().add(toolbar, BorderLayout.NORTH);
	}

	public Path getOutputFolder() {
		return outputFolder;
	}

	public List<Solution> getParetoFront() {
		return paretoFront;
	}

	public void setParetoFront(List<Solution> paretoFront) {
		this.paretoFront = paretoFront;
	}

	public NrpProblem getProblem() {
		return problem;
	}

	public void setProblem(NrpProblem problem) {
		this.problem = problem;
	}

	public Builder getBuilder() {
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public boolean isNormalized() {
		return isNormalized;
	}

	public void setNormalized(boolean isNormalized) {
		this.isNormalized = isNormalized;
	}

	public XYChartPanel getXYChartPanel() {
		return xYChartPanel;
	}

	public void stateChanged(ChangeEvent changeEvent) {
		updateWindow();

		String title = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());

		if (LOGGER.isInfoEnabled()) LOGGER.info("Opening the '" + title + "' tab");
	}

	public Colorize getColorize() {
		return colorize;
	}

	public void setColorize(Colorize colorize) {
		this.colorize = colorize;
	}

	public List<Solution> getFilteredSolutions() {

		List<Solution> solutions = FilterUtils.filter(problem, paretoFront, filters);

		if (isNormalized()) {
			return NormalizerUtils.normalize(solutions);
		}

		return solutions;
	}

	@Override
	public void updateWindow() {

		List<Solution> filteredSolutions = getFilteredSolutions();

		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			try {
				((AbstractPanel) tabbedPane.getComponentAt(i)).update(filteredSolutions, filters);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Clustering getClustering() {
		return clustering;
	}

	public void setClustering(Clustering clustering) {
		this.clustering = clustering;
	}
}
