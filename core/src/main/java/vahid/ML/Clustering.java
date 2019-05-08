package src.main.java.vahid.ML;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static src.main.java.vahid.util.HashUtil.sortByValue;

public class Clustering {

    protected static final Logger LOGGER = Logger.getLogger(Clustering.class);
    public static Clustering instanceCL = null;
    static String OUTPUT_DIR = System.getProperty("user.dir") + "/VAHID-output/";
    public String exec_id;
    public int counter;
    public int obj_num;

    public List<String[]> clusterLabels;

    public List<String[]> clusterCenters;

    public List<List<String[]>> operationsFreq;

    public List<List<String[]>> classesFreq;
    public int max_ref;
    public Map<String, Double> operationsProbHash;
    protected double[] clusterRanks;
    protected List<Integer> selectedClusters;

    public Clustering(Path outputFolder, String id, int counter, int obj_num, int max_ref) throws IOException {
        this.exec_id = id;
        this.counter = counter;
        this.obj_num = obj_num;
        this.max_ref = max_ref;
        OUTPUT_DIR = outputFolder.toAbsolutePath().toString() + "/";

        this.execute();
        this.ClusterCenters();
        this.ClusterLabels();
        this.OperationsFreq();
        this.ClassesFreq();


        this.updateInstance();

    }

    private static void copyTempPy(Path tempDir, String File) throws IOException {
        File destFile = new File(tempDir.toAbsolutePath() + "/" + File);
        destFile.mkdirs();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(File);
        Files.copy(in, destFile.toPath(), StandardCopyOption.REPLACE_EXISTING); // Copy to temporary file.

    }

    public void execute() {

        if (LOGGER.isInfoEnabled()) LOGGER.info("***************Starting to cluster the paretofront solutions");

        try {
            Path tempDir = Files.createTempDirectory("VAHID-TempPythonRefactoringDir");

            copyTempPy(tempDir, "VA-ML-Python/VA_ML/clustering_tools.py");
            copyTempPy(tempDir, "VA-ML-Python/VA_ML/__init__.py");
            copyTempPy(tempDir, "VA-ML-Python/VA_Tools/config.py");
            copyTempPy(tempDir, "VA-ML-Python/VA_Tools/designmetrics.py");
            copyTempPy(tempDir, "VA-ML-Python/VA_Tools/refactoringsolution.py");
            copyTempPy(tempDir, "VA-ML-Python/VA_Tools/__init__.py");
            copyTempPy(tempDir, "VA-ML-Python/VA_Viz/__init__.py");
            copyTempPy(tempDir, "VA-ML-Python/VA_Viz/ClusterExplain.py");
            copyTempPy(tempDir, "VA-ML-Python/JavaClustering.py");

            if (LOGGER.isInfoEnabled()) LOGGER.info("temp dir: " + tempDir.toAbsolutePath().toString());

//			copyTempPy(tempDir, "VA_Util/handlePath.py");


            //			ProcessBuilder pb = new ProcessBuilder("py",Config.PYTHONPATH+"Java_Link\\Clustering.py",
            //					OUTPUT_DIR ,this.exec_id,""+this.counter,""+this.obj_num, ""+this.max_ref);
//			ProcessBuilder pb = new ProcessBuilder("python",tempDir.toAbsolutePath() + "/VA_Util/handlePath.py",
//					tempDir.toAbsolutePath().toString().replace("\\", "/"));
//
//			Process p = pb.start();
//
//			String result = IOUtils.toString(p.getInputStream());
//			String error = IOUtils.toString(p.getErrorStream());
//
//			if (LOGGER.isInfoEnabled()) LOGGER.info(result);
//			if (LOGGER.isInfoEnabled()) LOGGER.info(error);
            ///////////////////////////////////////////////////////////////////////////////////////////////////////
//			ProcessBuilder pb1 = new ProcessBuilder("python",tempDir.toAbsolutePath() + "/VA-ML-Python/JavaClustering.py",
//					OUTPUT_DIR ,this.exec_id,""+this.counter,""+this.obj_num, ""+this.max_ref);

            ProcessBuilder pb1 = new ProcessBuilder("python", tempDir.toAbsolutePath() + "/VA-ML-Python/JavaClustering.py",
                    OUTPUT_DIR, this.exec_id, "" + this.counter, "" + this.obj_num, "" + this.max_ref);

            Process p1 = pb1.start();

            String result1 = IOUtils.toString(p1.getInputStream());
            String error1 = IOUtils.toString(p1.getErrorStream());

            if (LOGGER.isInfoEnabled()) LOGGER.info(result1);
            if (LOGGER.isInfoEnabled()) LOGGER.info(error1);

            FileUtils.forceDelete(tempDir.toFile());
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) LOGGER.info(e);
        }
    }

    public void ClusterLabels() throws IOException {

        if (LOGGER.isInfoEnabled()) LOGGER.info("Generating cluster labels");

        String CSV_Labels = OUTPUT_DIR + "ClusterLabels-" + exec_id + "-" + counter + ".csv";
        Reader reader = Files.newBufferedReader(Paths.get(CSV_Labels));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> records = csvReader.readAll();

        //			for (String[] record : records) {
        //				System.out.println("label : " + record[0]);
        //			}
        this.clusterLabels = records;
    }

    public void ClusterCenters() throws IOException {

        if (LOGGER.isInfoEnabled()) LOGGER.info("Generating cluster centers");

        String CSV_Centers = OUTPUT_DIR + "ClusterCenters-" + exec_id + "-" + counter + ".csv";
        Reader reader = Files.newBufferedReader(Paths.get(CSV_Centers));
        //		CSVReader csvReader = new CSVReader(reader);
        //		Using below command we skip the header
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();


        List<String[]> records = csvReader.readAll();

        this.clusterCenters = records;
    }

    public void OperationsFreq() throws IOException {

        if (LOGGER.isInfoEnabled()) LOGGER.info("Generating operation frequencies");

        List<List<String[]>> operationsFreq = new ArrayList<List<String[]>>();
        int numClusters = clusterCenters.size();
        for (int c = 0; c < numClusters; c++) {
            String CSV_OpFreq = OUTPUT_DIR + "OperationsFreq-" + exec_id + "-" + counter + "-" + c + ".csv";
            Reader reader = Files.newBufferedReader(Paths.get(CSV_OpFreq));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> records = csvReader.readAll();
            operationsFreq.add(records);
        }
        this.operationsFreq = operationsFreq;

    }

    public void OperationsProbHash() {

        if (LOGGER.isInfoEnabled()) LOGGER.info("Generating grooming probability Hash table");

        int numClustersSelected = this.selectedClusters.size();
        Map<String, Double> totalProbHash = new HashMap<>();
        Map<String, Double> averageProbHash = new HashMap<>();

        for (int clusterNum : selectedClusters) {
            Map<String, Double> probHash = new HashMap<>();
            for (String[] Op : operationsFreq.get(clusterNum)) probHash.put(Op[1], Double.parseDouble(Op[3]));
            probHash.forEach((k, v) -> totalProbHash.merge(k, v, Double::sum));
        }
        totalProbHash.forEach((k, v) -> averageProbHash.put(k, v / numClustersSelected));

        Map<String, Double> sortedAverageProbHash = sortByValue(averageProbHash);

        this.operationsProbHash = sortedAverageProbHash;

        this.updateInstance();
        //		PrintHelper.printMap(sortedAverageProbHash);
    }

    public void ClassesFreq() throws IOException {

        if (LOGGER.isInfoEnabled()) LOGGER.info("Generating classes frequencies");

        List<List<String[]>> classesFreq = new ArrayList<List<String[]>>();
        int numClusters = clusterCenters.size();
        for (int c = 0; c < numClusters; c++) {
            String CSV_ClFreq = OUTPUT_DIR + "ClassesFreq-" + exec_id + "-" + counter + "-" + c + ".csv";
            Reader reader = Files.newBufferedReader(Paths.get(CSV_ClFreq));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> records = csvReader.readAll();
            classesFreq.add(records);
        }
        this.classesFreq = classesFreq;
    }

    public double[] getClusterRanks() {
        return this.clusterRanks;
    }

    public void setClusterRanks(double[] clusterRanks) {
        this.clusterRanks = clusterRanks;
        this.updateInstance();
    }

    public void setSelectedClusters(List<Integer> selectedClusters) {
        this.selectedClusters = selectedClusters;
        this.updateInstance();
    }

    public List<Integer> getSlectedClusters() {
        return this.selectedClusters;
    }

    public void updateInstance() {
        instanceCL = null;
        instanceCL = this;

    }

} 
