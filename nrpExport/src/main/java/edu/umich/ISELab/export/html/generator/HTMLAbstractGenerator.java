package edu.umich.ISELab.export.html.generator;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import edu.umich.ISELab.export.ExportGenerator;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public abstract class HTMLAbstractGenerator extends ExportGenerator {
    protected static final Logger LOGGER = Logger.getLogger(HTMLAbstractGenerator.class);


    public HTMLAbstractGenerator(String filename) {
        super(filename);
    }

    protected String getTemplate(Object parent, String templateFile) throws IOException {

        StringBuilder builder = new StringBuilder();

        ClassLoader classLoader = parent.getClass().getClassLoader();

        try {
//			InputStream inputStream = classLoader.getResourceAsStream("templates" + File.separator + templateFile);
            InputStream inputStream = classLoader.getResourceAsStream(templateFile);
            if (LOGGER.isInfoEnabled()) LOGGER.info("Reading the HTML template");
            InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader in = new BufferedReader(streamReader);

            for (String line; (line = in.readLine()) != null; ) {
                builder.append(line);
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            List<String> lines = Files.readLines(new File("templates/" + templateFile), Charsets.UTF_8);

            for (String line : lines) {
                builder.append(line);
            }
        }


        return builder.toString();
    }
}
