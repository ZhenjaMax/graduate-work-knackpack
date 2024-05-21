package main.java.com.github.graduateworkknapsack.algorithms;

import java.util.ArrayList;
import java.util.Collections;

import main.java.com.github.graduateworkknapsack.elements.Item;
import main.java.com.github.graduateworkknapsack.elements.Knapsack;

public final class AlgorithmKnapsackDynProg extends AlgorithmKnapsack {
	
	@Override
	public void run(Knapsack knapsack, ArrayList<Item> items) {
		final int C = knapsack.getCapacity();
		final int N = items.size();
		if((C == 0) || (N == 0)) {
			return;
		}
		
		int[][] matrix = new int[N+1][C+1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= C; j++) {
				Item currentItem = items.get(i-1);
				int currentWeight = currentItem.getWeight();
				int currentValue = (int) currentItem.getValue();
				int prevMatrixElement = matrix[i-1][j];
				if(j < currentWeight) {
					matrix[i][j] = prevMatrixElement;
				} else {
					matrix[i][j] = Math.max(
						prevMatrixElement, 
						matrix[i-1][j-currentWeight] + (int) currentValue
					);
				}
			}
		}
		
		for(int i = N, j = C; matrix[i][j] > 0; i--) {
			if(matrix[i-1][j] != matrix[i][j]) {
				Item correctItem = items.get(i-1);
				knapsack.items.add(correctItem);
				j -= correctItem.getWeight();
			}
		}
		Collections.reverse(knapsack.items);
	}
}
