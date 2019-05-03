package main.java.VAHID;


import edu.umich.ISELab.core.sys.LOGGER;
import thiagodnf.doupr.gui.component.JExportAsChooser;
import thiagodnf.doupr.gui.component.JExportAsChooser.JExportAsChooserListener;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExportDesignMetricsVAHID implements ActionListener, JExportAsChooserListener {

	protected ViewParetoFrontSubWindow window;
	private ProjectObject project;

	public ExportDesignMetricsVAHID(ProjectObject project) {
//		System.out.println(project.getDesignMetrics());
		this.project = project;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		LOGGER.info(this, "Exporting the Pareto-front");

		JExportAsChooser chooser = new JExportAsChooser();

		// The possible formats that the user can choose
		chooser.addFileFilter("Objective Values (.fun)", "fun", this);
		chooser.addFileFilter("Variable Values (.var)", "var", this);
		chooser.addFileFilter("Class Design Metrics (.cdm)", "cdm", this);
		chooser.addFileFilter("Project Metrics (.pm)", "pm", this);

		// Show the dialog to user until s(he) return a valid output file
		chooser.showDialog();
	}

	@Override
	public void save(String extension, File output) {

		LOGGER.info(this, "Saving the file");

		try {

			if (extension.equalsIgnoreCase("cdm")) {
				new VAHID.ExportToCDMFilesVAHID(this.project, output);
			} else if (extension.equalsIgnoreCase("pm")) {
				new main.java.VAHID.ExportToPMFilesVAHID(this.project, output);
			}


			MessageBox.info("The file was successfully exported");

		} catch (Exception ex) {
			MessageBox.error(ex);
		}
	}


}
