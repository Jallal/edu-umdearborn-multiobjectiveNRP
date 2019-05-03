package edu.umich.ISELab.execution.util;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class CommandLineValues {

    @Option(name = "-in", usage = "Instance File", required = true)
    public String instanceFile;

    @Option(name = "-pop", usage = "Population Size")
    public int populationSize = 100;

    @Option(name = "-maxEval", usage = "Max Evaluations")
    public int maxEvaluations = 1000;

    @Option(name = "-cProb", usage = "Crossover Probability")
    public double crossoverProbability = 0.9;

    @Option(name = "-mProb", usage = "Mutation Probability")
    public double mutationProbability = 0.1;

    @Option(name = "-alg", usage = "Algorithm")
    public String algorithm = "NSGA2";

    @Option(name = "-minRef", usage = "Min Refactorings")
    public int minRefatorings = 2;

    @Option(name = "-maxRef", usage = "Max Refactorings")
    public int maxRefatorings = 10;

    private boolean errorFree = false;

    public CommandLineValues(String... args) {
        CmdLineParser parser = new CmdLineParser(this);
        parser.setUsageWidth(80);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }

    /**
     * Returns whether the parameters could be parsed without an error.
     *
     * @return true if no error occurred.
     */
    public boolean isErrorFree() {
        return errorFree;
    }
}
