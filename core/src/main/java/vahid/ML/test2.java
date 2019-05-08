package src.main.java.vahid.ML;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

import static src.main.java.vahid.ML.Config.PYTHONPATH;

class test2 {

    static String OUTPUT_DIR = System.getProperty("user.dir") + "/VAHID-output/";

    public static void main(String a[]) throws InterruptedException, IOException {
        try {
            //String prg = "import sys\nprint int(sys.argv[1])+int(sys.argv[2])\n";
            //BufferedWriter out = new BufferedWriter(new FileWriter("test1.py"));
            //out.write(prg);
            //out.close();
            //int number1 = 12;
            //int number2 = 32;
            //ProcessBuilder pb = new ProcessBuilder("py",Config.PYTHONPATH+"Legacy\\tempExecArg.py",""+number1,""+number2);

            ProcessBuilder pb = new ProcessBuilder("py", PYTHONPATH + "Java_Link\\Clustering.py", OUTPUT_DIR, "39dc1a14", "1", "9");

            //System.out.println(Config.PYTHONPATH+"Java_Link\\Clustering.py");

            Process p = pb.start();

            String result = IOUtils.toString(p.getInputStream());
            String error = IOUtils.toString(p.getErrorStream());


            ///////////////////// To Print result also we can use the following lines/////////////////////
            //BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //StringBuilder builder = new StringBuilder();
            //String line = null;
            //while ( (line = in.readLine()) != null) {
            //   builder.append(line);
            //   builder.append(System.getProperty("line.separator"));
            //}
            //String result = builder.toString();
            ////////////////////////////////////////////////////////////////////////////////////

            System.out.println(result);
            System.out.println(error);


            //System.out.println(in.readLine());
            //int ret = new Integer(in.readLine()).intValue();
            //System.out.println("value is : "+ret);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}