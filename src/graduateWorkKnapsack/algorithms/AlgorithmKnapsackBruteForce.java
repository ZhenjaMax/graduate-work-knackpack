package graduateWorkKnapsack.algorithms;

import java.util.ArrayList;

import graduateWorkKnapsack.elements.Item;
import graduateWorkKnapsack.elements.Knapsack;

public final class AlgorithmKnapsackBruteForce extends AlgorithmKnapsack {
	private void packKnapsack(Knapsack knapsack, ArrayList<Item> items, int solution) {
		String binaryString = Integer.toBinaryString(solution);
		for (int j = 0; j < binaryString.length(); j++) {
            if(binaryString.charAt(j) == '1') {
            	knapsack.items.add(items.get(j + items.size() - binaryString.length()));
            }
        }
	}
	
	@Override
	public void run(Knapsack knapsack, ArrayList<Item> items) {
		int solution = 0, mostValue = 0;
		
		for(int i = 1; i < (int) Math.pow(2, items.size()); i++) {
			this.packKnapsack(knapsack, items, i);
			if(knapsack.isPacked() && knapsack.getTotalValue() > mostValue) {
				solution = i;
				mostValue = knapsack.getTotalValue();
			}
			knapsack.items.clear();
		}
		
		this.packKnapsack(knapsack, items, solution);
	}
}
