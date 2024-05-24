package main.java.com.github.graduateworkknapsack.algorithms;

import java.util.ArrayList;

import main.java.com.github.graduateworkknapsack.elements.Item;
import main.java.com.github.graduateworkknapsack.elements.Knapsack;

public final class AlgorithmKnapsackBruteForce extends AlgorithmKnapsack {
	private void packKnapsackFromSolutionInteger(Knapsack knapsack, ArrayList<Item> items, int solution) {
		String binaryString = Integer.toBinaryString(solution);
		knapsack.items.clear();
		
		for (int j = 0; j < binaryString.length(); j++) {
            if(binaryString.charAt(j) == '1') {
            	knapsack.items.add(items.get(j + items.size() - binaryString.length()));
            }
        }
	}
	
	@Override
	public void run(Knapsack knapsack, ArrayList<Item> items) {
		int solution = 0;
		float mostValue = 0;
		
		for(int i = 1; i < (int) Math.pow(2, items.size()); i++) {
			this.packKnapsackFromSolutionInteger(knapsack, items, i);
			if(knapsack.isPacked() && knapsack.getTotalValue() > mostValue) {
				solution = i;
				mostValue = knapsack.getTotalValue();
			}
		}
		
		this.packKnapsackFromSolutionInteger(knapsack, items, solution);
	}
}
