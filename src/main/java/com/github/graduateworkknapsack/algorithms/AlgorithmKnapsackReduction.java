package main.java.com.github.graduateworkknapsack.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import main.java.com.github.graduateworkknapsack.elements.Item;
import main.java.com.github.graduateworkknapsack.elements.ItemTSP;
import main.java.com.github.graduateworkknapsack.elements.Knapsack;
import main.java.com.github.graduateworkknapsack.util.Constant;

public abstract class AlgorithmKnapsackReduction extends AlgorithmKnapsack {
	public ArrayList<ItemTSP> currentResult = new ArrayList<>();
	public ArrayList<ItemTSP> itemsTSP = new ArrayList<>();
	
	public int maxWeight = 0;
	public float maxValue = 0;
	public ArrayList<ItemTSP> result = new ArrayList<>();
	
	protected float[][] buildMatrixForTSP(Knapsack knapsack, ArrayList<Item> items) {
		final int N = knapsack.getCapacity();
		final int nodesAmount = 2*N - 1;
		final int itemsAmount = items.size();
		final float[][] matrixTSP = new float[nodesAmount][nodesAmount];
		for(float[] row: matrixTSP) {
			Arrays.fill(row, (float) Constant.INFINITY);
		}
		
		int[] weights = new int[itemsAmount];
		float[] values = new float[itemsAmount];
		for(int i = 0; i < itemsAmount; i++) {
			weights[i] = items.get(i).getWeight();
			values[i] = items.get(i).getValue();
		}
		
		int minWeight = Arrays.stream(weights).min().getAsInt();
		if(minWeight > 1) {
			int diffWeight = minWeight-1;
			for(int i = 0; i < itemsAmount; i++) {
				int oldWeight = weights[i];
				int newWeight = oldWeight-diffWeight;
				float newValue = values[i]*newWeight/oldWeight;
				weights[i] = newWeight;
				values[i] = newValue;
			}
		}
 		
		for(int i = 0; i < itemsAmount; i++) {
			for(int j = 0; j < N; j++) {
				if(j+weights[i] <= N) {
					matrixTSP[j][j+weights[i]] = (float) weights[i]*weights[i]/values[i];
				}
			}
		}
		
		// Впереди
		for(int j = N; j < nodesAmount-1; j++) {
			matrixTSP[j][j+1] = 0;
		}
		matrixTSP[nodesAmount-1][0] = 0;
		
		// Снизу вверх
		for(int j = N; j < nodesAmount; j++) {
			matrixTSP[j][nodesAmount-j] = 0;
		}
		
		// Сверху вниз
		for(int j = N-1; j > 1; j--) {
			matrixTSP[j][2*N-j] = 0;
		}
		matrixTSP[1][0] = 0;
		
		return matrixTSP;
	}
	
	// ================================
	
	protected void convertSolutionToKP(
		float[][] matrixTSP, 
		ArrayList<Integer> tour,
		Knapsack knapsack, 
		ArrayList<Item> items
	) {
		int nodesAmount = matrixTSP.length;
		float sumW = 0;
		for(float[] row: matrixTSP) {
			for(float element: row) {
				if(element < Constant.INFINITY) {
					sumW += element;
				}	
			}
		}
		
		for(int i = 0; i < nodesAmount; i++) {
			for(int j = 0; j < nodesAmount; j++) {
				float w = matrixTSP[i][j];
				if(w < Constant.INFINITY) {
					this.itemsTSP.add(new ItemTSP(i, j, 1, 1-w/sumW));
				}
			}
		}
		
		//knapsack.items = new ArrayList<>();
		for(int i = 0; i < nodesAmount; i++) {
			for(ItemTSP itemTSP: this.itemsTSP) {
				int indexI = tour.get(i);
				int indexJ = tour.get(i+1);
				if((itemTSP.i == indexI) && itemTSP.j == indexJ) {
					knapsack.items.add(ItemTSP.convertToItemKP(itemTSP));
				}
			}
		}	
	}
}
