package graduateWorkKnapsack.algorithms;

import java.util.ArrayList;

import graduateWorkKnapsack.elements.Item;
import graduateWorkKnapsack.elements.Knapsack;

public final class AlgorithmKnapsackReductionBruteForce extends AlgorithmKnapsackReduction {
	@Override
	public void run(Knapsack knapsack, ArrayList<Item> items) {
		ArrayList<ArrayList<Float>> matrixTSP = this.buildMatrixForTSP(knapsack, items);
		AlgorithmTSPBruteForce tsp = new AlgorithmTSPBruteForce(0, matrixTSP);
		tsp.run();
		ArrayList<ArrayList<Integer>> solutionTSP = tsp.tourMatrix;
		for(int i = 0; i < matrixTSP.size(); i++) {
			for(int j = 0; j < matrixTSP.size(); j++) {
				System.out.print(solutionTSP.get(i).get(j) + " ");
			}
			System.out.print("\n");
		}
		System.out.println("\n" + tsp.tour);
		System.out.println("Min tour cost: " + tsp.minTourCost + "\n");
		for(int i = 0; i < matrixTSP.size(); i++) {
			for(int j = 0; j < matrixTSP.size(); j++) {
				System.out.print(matrixTSP.get(i).get(j) + " ");
			}
			System.out.print("\n");
		}
		this.convertSolutionToKP(matrixTSP, tsp.tour, knapsack, items);
	}
}
