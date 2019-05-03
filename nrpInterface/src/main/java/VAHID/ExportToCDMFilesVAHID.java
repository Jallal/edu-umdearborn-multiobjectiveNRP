package VAHID;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExportToCDMFilesVAHID {

	protected static final Logger LOGGER = Logger.getLogger(ExportToCDMFilesVAHID.class);


	public ExportToCDMFilesVAHID(ProjectObject project, File output) throws IOException {

		FileWriter writer = new FileWriter(output.getAbsolutePath());
//        CSVUtils.writeLine(writer, Arrays.asList("Class" ,"Encapsulation", "Cohesion" ,"Composition", "Inheritance" , "Abstraction", 
//        		"Complexity", "Standard Cohesion", "Design Size", "Hierarchies", "Standard Coupling", "Messaging", "Coupling", "Polymorphism"),',');
		VAHID.CSVUtils.writeLine(writer, Arrays.asList("Class Name", "Encapsulation", "Cohesion", "Composition", "Inheritance", "Standard Complexity",
				"Abstraction", "Complexity", "Standard Cohesion", "Hierarchies", "Standard Coupling", "Messaging", "Coupling", "Polymorphism"), ',');

		for (PackageObject packageObject : project.getPackages()) {
			for (ClassObject cls : packageObject.getClasses()) {
//				System.out.println(cls.getSimpleName()+" "+cls.getDesignMetrics());

//				System.out.println(cls.getSimpleName()+","
//						+cls.getDesignMetrics().get("Encapsulation")+","
//						+cls.getDesignMetrics().get("Cohesion")+","
//						+cls.getDesignMetrics().get("Composition")+","
//						+cls.getDesignMetrics().get("Inheritance")+","
//						+cls.getDesignMetrics().get("Abstraction")+","
//						+cls.getDesignMetrics().get("Complexity")+","
//						+cls.getDesignMetrics().get("Standard Cohesion")+","
//						+cls.getDesignMetrics().get("Design Size")+","
//						+cls.getDesignMetrics().get("Hierarchies")+","
//						+cls.getDesignMetrics().get("Standard Coupling")+","
//						+cls.getDesignMetrics().get("Messaging")+","
//						+cls.getDesignMetrics().get("Coupling")+","
//						+cls.getDesignMetrics().get("Polymorphism")+","
//						);
				List<String> DesignMetrics = new ArrayList<>();
				DesignMetrics.add(cls.getSimpleName());
				DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Encapsulation")));
				DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Cohesion")));
				DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Composition")));
				DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Inheritance")));
				DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Standard Complexity")));
				DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Abstraction")));
				DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Complexity")));
				DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Standard Cohesion")));
//		        DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Design Size")));
				DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Hierarchies")));
				DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Standard Coupling")));
				DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Messaging")));
				DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Coupling")));
				DesignMetrics.add(Double.toString(cls.getDesignMetrics().get("Polymorphism")));


//		        System.out.println(DesignMetrics);
//				System.exit(0);

				VAHID.CSVUtils.writeLine(writer, DesignMetrics
						, ',');
			}
		}
		writer.flush();
		writer.close();
		LOGGER.info("Design Metrics have been written!");
	}

}
