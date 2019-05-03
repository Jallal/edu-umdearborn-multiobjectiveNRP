package main.java.VAHID;

import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.evaluation.designmetrics.AbstractDesignMetric;
import edu.umich.ISELab.evaluation.util.EvaluationUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ExportToPMFilesVAHID {

	protected List<Objective> objectives;
	protected List<AbstractDesignMetric> designMetrics;

	public ExportToPMFilesVAHID(ProjectObject project, File output) throws IOException {

		this.objectives = EvaluationUtils.OBJECTIVES;

		this.designMetrics = EvaluationUtils.DESIGN_METRICS;


		FileWriter writer = new FileWriter(output.getAbsolutePath());

		VAHID.CSVUtils.writeLine(writer, Arrays.asList("Project Name",
				objectives.get(0).toString(),
				objectives.get(1).toString(),
				objectives.get(2).toString(),
				objectives.get(3).toString(),
				objectives.get(4).toString(),
				objectives.get(5).toString(),
				objectives.get(6).toString(),
				objectives.get(7).toString(),
				objectives.get(8).toString(),
				designMetrics.get(0).getDesignProperty(),
				designMetrics.get(1).getDesignProperty(),
				designMetrics.get(2).getDesignProperty(),
				designMetrics.get(3).getDesignProperty(),
				designMetrics.get(4).getDesignProperty(),
				designMetrics.get(5).getDesignProperty(),
				designMetrics.get(6).getDesignProperty(),
				designMetrics.get(7).getDesignProperty(),
				designMetrics.get(8).getDesignProperty(),
				designMetrics.get(9).getDesignProperty(),
				designMetrics.get(10).getDesignProperty(),
				designMetrics.get(11).getDesignProperty(),
				designMetrics.get(12).getDesignProperty(),
				designMetrics.get(13).getDesignProperty()
		), ',');

		VAHID.CSVUtils.writeLine(writer, Arrays.asList("Project Name",
				Double.toString(objectives.get(0).getValue(project)),
				Double.toString(objectives.get(1).getValue(project)),
				Double.toString(objectives.get(2).getValue(project)),
				Double.toString(objectives.get(3).getValue(project)),
				Double.toString(objectives.get(4).getValue(project)),
				Double.toString(objectives.get(5).getValue(project)),
				Double.toString(objectives.get(6).getValue(project)),
				Double.toString(objectives.get(7).getValue(project)),
				Double.toString(objectives.get(8).getValue(project)),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(0).getDesignProperty())),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(1).getDesignProperty())),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(2).getDesignProperty())),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(3).getDesignProperty())),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(4).getDesignProperty())),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(5).getDesignProperty())),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(6).getDesignProperty())),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(7).getDesignProperty())),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(8).getDesignProperty())),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(9).getDesignProperty())),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(10).getDesignProperty())),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(11).getDesignProperty())),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(12).getDesignProperty())),
				Double.toString(project.getDesignMetrics().get(designMetrics.get(13).getDesignProperty()))
		), ',');


		writer.flush();
		writer.close();
		System.out.println("Project Quality Metrics have been written!");

	}

}
