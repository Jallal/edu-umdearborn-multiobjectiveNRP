package edu.umich.ISELab.execution.util;

public class CommandLineValues {


    public int populationSize = 100;

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public int getMaxEvaluations() {
		return maxEvaluations;
	}

	public void setMaxEvaluations(int maxEvaluations) {
		this.maxEvaluations = maxEvaluations;
	}

	public double getCrossoverProbability() {
		return crossoverProbability;
	}

	public void setCrossoverProbability(double crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}

	public double getMutationProbability() {
		return mutationProbability;
	}

	public void setMutationProbability(double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public int getMinRefatorings() {
		return minRefatorings;
	}

	public void setMinRefatorings(int minRefatorings) {
		this.minRefatorings = minRefatorings;
	}

	public int getMaxRefatorings() {
		return maxRefatorings;
	}

	public void setMaxRefatorings(int maxRefatorings) {
		this.maxRefatorings = maxRefatorings;
	}

	public int maxEvaluations = 1000;

    public double crossoverProbability = 0.9;

    public double mutationProbability = 0.1;

    public String algorithm = "NSGA2";

    public int minRefatorings = 2;

    public int maxRefatorings = 10;
}
