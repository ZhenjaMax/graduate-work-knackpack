package graduateWorkKnapsack.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graduateWorkKnapsack.elements.Item;
import graduateWorkKnapsack.elements.Knapsack;

public final class AlgorithmKnapsackReductionDynProg extends AlgorithmKnapsack {
	private float INFINITY = (float) 10E9;
	
	private ArrayList<ArrayList<Float>> buildMatrixForTSP(Knapsack knapsack, ArrayList<Item> items) {
		int N = knapsack.getCapacity();
		int countNodes = 2*N - 1;
		ArrayList<ArrayList<Float>> matrixTSP = new ArrayList<ArrayList<Float>>();
		for(int i = 0; i < countNodes; i++) {
			matrixTSP.add(new ArrayList<Float>(Collections.nCopies(countNodes, this.INFINITY)));
		}
		
		for(int i = 0; i < items.size(); i++) {
			for(int j = 0; j < N; j++) {
				int itemWeight = items.get(i).getWeight();
				if(j+itemWeight < N+1) {
					int itemValue = items.get(i).getValue();
					matrixTSP
						.get(j)
						.set(j + itemWeight, 
							(float) itemWeight*itemWeight/itemValue
						);
				}
			}
		}
		
		for(int j = N; j < countNodes-1; j++) {
			matrixTSP.get(j).set(j+1, (float) 0);
		}
		matrixTSP.get(countNodes-1).set(0, (float) 0);
		
		for(int j = N-1; j > 1; j--) {
			matrixTSP.get(j).set(2*N - j, (float) 0);
		}
		matrixTSP.get(1).set(0, (float) 0);
		
		return matrixTSP;
		
	}
	
	class ItemMatrixTSP {
		int i, j;
		
		float value;
		
		public ItemMatrixTSP(int i, int j, float value) {
			this.i = i;
			this.j = j;
			this.value = value;
		}
	}
	
	private void convertSolutionToKP(ArrayList<ArrayList<Float>> matrixTSP, ArrayList<ArrayList<Integer>> solutionTSP, Knapsack knapsack) {
		int n = matrixTSP.size();
		float sumW = 0;
		for(ArrayList<Float> matrixRow: matrixTSP) {
			for(float matrixElement: matrixRow) {
				if(matrixElement != this.INFINITY) {
					sumW += matrixElement;
				}	
			}
		}
		ArrayList<ArrayList<Float>> itemsMatrix = new ArrayList<ArrayList<Float>>();
		
		ArrayList<Integer> fragileMatrix = new ArrayList<>(Collections.nCopies(n, 1));
		fragileMatrix.set(0, -1);
	}
	
	@Override
	public void run(Knapsack knapsack, ArrayList<Item> items) {
		ArrayList<ArrayList<Float>> matrixTSP = this.buildMatrixForTSP(knapsack, items);
		AlgorithmTSPDynProg tsp = new AlgorithmTSPDynProg(0, matrixTSP);
		tsp.findSolution();
		ArrayList<ArrayList<Integer>> solutionTSP = tsp.tourMatrix;
		this.convertSolutionToKP(matrixTSP, solutionTSP, knapsack);
	}
}
