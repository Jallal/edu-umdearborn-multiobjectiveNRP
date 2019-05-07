/*
 * Copyright (c) 2019.
 * Author: Vahid Alizadeh
 * Email: alizadeh@umich.edu
 */

package vahid.util;

import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.fileoutput.FileOutputContext;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

// The reason for this extension is just to change the output in order to have the header for csv file, so that it can be input for Weka clustering
public class SolutionListOutputVahid extends SolutionListOutput {

    public SolutionListOutputVahid(List<? extends Solution<?>> solutionList) {
        super(solutionList);
    }

    @Override
    public void printObjectivesToFile(FileOutputContext context, List<? extends Solution<?>> solutionList, List<Boolean> minimizeObjective) {
        BufferedWriter bufferedWriter = context.getFileWriter();

        try {
            if (solutionList.size() > 0) {
                int numberOfObjectives = solutionList.get(0).getNumberOfObjectives();
                if (numberOfObjectives != minimizeObjective.size()) {
                    throw new JMetalException("The size of list minimizeObjective is not correct: " + minimizeObjective.size());
                }

                for (int i = 1; i <= numberOfObjectives; i++) {
                    bufferedWriter.write("Obj" + i);
                    if (i != numberOfObjectives) {
                        bufferedWriter.write(context.getSeparator());
                    }
                }
                bufferedWriter.newLine();

                for (int i = 0; i < solutionList.size(); ++i) {
                    for (int j = 0; j < numberOfObjectives; ++j) {
                        if (minimizeObjective.get(j)) {
                            bufferedWriter.write(solutionList.get(i).getObjective(j) + "");
                        } else {
                            bufferedWriter.write(-1.0D * solutionList.get(i).getObjective(j) + "");
                        }
                        if (j != numberOfObjectives - 1) {
                            bufferedWriter.write(context.getSeparator());
                        }
                    }

                    bufferedWriter.newLine();
                }
            }

            bufferedWriter.close();
        } catch (IOException var8) {
            throw new JMetalException("Error printing objecives to file: ", var8);
        }
    }
}
