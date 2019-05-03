package thiagodnf.doupr.gui.subwindow;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.action.button.ApplyRefactoringsAction;
import thiagodnf.doupr.gui.action.button.OptimizeAction;
import thiagodnf.doupr.gui.action.button.ViewChartsAction;
import thiagodnf.doupr.gui.component.JToolBarButton;
import thiagodnf.doupr.gui.panel.AbstractPanel;
import thiagodnf.doupr.gui.panel.DesignMetricsForProjectPanel;
import thiagodnf.doupr.gui.panel.QualityAttributesPanel;
import thiagodnf.doupr.gui.panel.SummaryPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class ViewFileSubWindow extends SubWindow implements ChangeListener {

	protected static final Logger LOGGER = Logger.getLogger(RecentFilesSubWindow.class);

	private static final long serialVersionUID = -6786161270072119947L;

	protected ProjectObject project;

	protected File selectedFile;

	protected SummaryPanel summaryPanel;

	protected JTabbedPane tabbedPane;

	public ViewFileSubWindow(File selectedFile, ProjectObject project) {
		super(selectedFile.getName());

		this.selectedFile = selectedFile;
		this.project = project;
		this.summaryPanel = new SummaryPanel();

		addComponents();
		addToolBar();

		this.summaryPanel.load(project, new ArrayList<>());

		if (LOGGER.isInfoEnabled()) LOGGER.info("Opening the 'Summary' tab");

		setVisible(true);
	}

	protected void addComponents() {

		this.tabbedPane = new JTabbedPane();

		tabbedPane.addTab("Classes Summary", null, summaryPanel);
		tabbedPane.addTab("Design Metrics", null, new DesignMetricsForProjectPanel(project));
		tabbedPane.addTab("Quality Attributes", null, new QualityAttributesPanel(project));

		tabbedPane.addChangeListener(this);

		add(tabbedPane);
	}

	@Override
	public void stateChanged(ChangeEvent changeEvent) {
		JTabbedPane tabbedPane = (JTabbedPane) changeEvent.getSource();
		AbstractPanel panel = (AbstractPanel) tabbedPane.getSelectedComponent();
		panel.load(project, new ArrayList<>());

		String title = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());

		if (LOGGER.isInfoEnabled()) LOGGER.info("Opening the '" + title + "' tab");
	}

	protected void addToolBar() {

		JToolBar toolbar = new JToolBar();

		toolbar.setFloatable(false);

		String optimizeTip = "Find best refactoring solutions to improve the quality of this project using multi-objective optimization.";
		String chartsTip = "Class diagrams and Dependency graphs.";
		String applyTip = "Apply the selected refactorings on the project (Under construction).";
		String exportTip = "Export the solutions and design metrics into CSV files.";

		toolbar.add(new JToolBarButton(this, "Optimize", "Optimize.png", optimizeTip, new OptimizeAction(selectedFile, project)));
		toolbar.add(new JToolBarButton(this, "Charts", "Charts.png", chartsTip, new ViewChartsAction(project)));
		toolbar.add(new JToolBarButton(this, "Apply Refactorings", "Apply.png", applyTip, new ApplyRefactoringsAction(selectedFile.getName(), project)));
		toolbar.add(new JToolBarButton(this, "Export", "save.png", exportTip, new main.java.VAHID.ExportDesignMetricsVAHID(project)));


		getContentPane().add(toolbar, BorderLayout.NORTH);
	}

	@Override
	public void updateWindow() {
		// TODO Auto-generated method stub

	}
}
