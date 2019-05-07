package vahid.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.umich.ISELab.optimization.solution.Solution;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ParetoObjectCRUD {

    private final double execution_id;
    private final double iteration_id;
    private final Long project_id;
    private Logger logger = LoggerFactory.getLogger(ParetoObjectCRUD.class);
    private String outputDir;


    public ParetoObjectCRUD(String outputDir, Long project_id, double execution_id, double iteration_id) throws FileNotFoundException, IOException {

        this.outputDir = outputDir;
        this.project_id = project_id;
        this.execution_id = execution_id;
        this.iteration_id = iteration_id;
    }


    // Writing pareto front object to a file
    public String saveParetoObject(List<Solution> paretoFront) throws IOException {

        logger.info("Saving the pareto front object");

        String objectPath = outputDir + "\\" + "Pareto-project" + project_id + "-exec" + execution_id + "-it" + iteration_id + ".paretoObject";
        FileOutputStream f = new FileOutputStream(new File(objectPath));
        ObjectOutputStream o = new ObjectOutputStream(f);

        // Write objects to file
        o.writeObject(paretoFront);

        o.close();
        f.close();

        return objectPath;
    }

    // Reading pareto front object to a file
    public List<Solution> loadParetoObject() throws IOException, ClassNotFoundException {

        logger.info("Loading the pareto front object");

        String objectPath = outputDir + "\\" + "Pareto-project" + project_id + "-exec" + execution_id + "-it" + iteration_id + ".paretoObject";
        FileInputStream fi = new FileInputStream(new File(objectPath));
        ObjectInputStream oi = new ObjectInputStream(fi);
        // Read objects
        List<Solution> pr1 = (List<Solution>) oi.readObject();

        return pr1;
    }

}
