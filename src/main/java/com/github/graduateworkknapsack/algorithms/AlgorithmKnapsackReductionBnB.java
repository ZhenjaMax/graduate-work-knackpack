package main.java.com.github.graduateworkknapsack.algorithms;

import java.util.ArrayList;

import main.java.com.github.graduateworkknapsack.elements.Item;
import main.java.com.github.graduateworkknapsack.elements.Knapsack;

public final class AlgorithmKnapsackReductionBnB extends AlgorithmKnapsackReduction {
	@Override
	public void run(Knapsack knapsack, ArrayList<Item> items) {
		float[][] matrixTSP = this.buildMatrixForTSP(knapsack, items);
		AlgorithmTSPBnB tsp = new AlgorithmTSPBnB(0, matrixTSP);
		tsp.run();
		this.convertSolutionToKP(matrixTSP, tsp.minTour, knapsack, items);
	}

}
