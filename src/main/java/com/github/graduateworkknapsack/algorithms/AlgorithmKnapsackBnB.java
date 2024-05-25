package main.java.com.github.graduateworkknapsack.algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import main.java.com.github.graduateworkknapsack.elements.Item;
import main.java.com.github.graduateworkknapsack.elements.Knapsack;

public final class AlgorithmKnapsackBnB extends AlgorithmKnapsack {
	private void packKnapsackFromBinaryString(Knapsack knapsack, ArrayList<Item> items, String binaryString) {
		knapsack.items.clear();
		for (int j = 0; j < binaryString.length(); j++) {
            if(binaryString.charAt(j) == '1') {
            	knapsack.items.add(items.get(j));
            }
        }
	}
	
	@Override
	public void run(Knapsack knapsack, ArrayList<Item> items) {
		String solution = "";
		float mostValue = 0;
		int itemsAmount = items.size();
		
		Deque<String> packages = new ArrayDeque<String>();
		packages.push("");
		
		
		while(!packages.isEmpty()) {
			String combination = packages.pop();
			this.packKnapsackFromBinaryString(knapsack, items, combination);
			boolean isTooMuch = !knapsack.isPacked();
			boolean isMaxLength = (combination.length() == itemsAmount);
			
			if(isTooMuch) {
				continue;
			}
			float totalValue = knapsack.getTotalValue();
			if(totalValue > mostValue) {
				mostValue = totalValue;
				solution = combination;
			}
			if(isMaxLength) {
				continue;
			}
			
			packages.push(combination + "0");
			packages.push(combination + "1");
		}

		this.packKnapsackFromBinaryString(knapsack, items, solution);
	}
}
