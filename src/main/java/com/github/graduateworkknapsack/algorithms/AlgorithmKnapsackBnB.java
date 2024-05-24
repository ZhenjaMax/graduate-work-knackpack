package main.java.com.github.graduateworkknapsack.algorithms;

import java.util.ArrayList;

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
		
		ArrayList<String> packages = new ArrayList<>();
		packages.add("");
		
		for(int i = 0; i <= itemsAmount; i++) {
			int computingPackagesSize = packages.size() / 2;
			for(int j = 0; j < computingPackagesSize; j++) {
				this.packKnapsackFromBinaryString(knapsack, items, packages.get(j));
				if(!knapsack.isPacked()) {
					packages.remove(j);
					j--;
					computingPackagesSize--;
					continue;
				}
				
				if(knapsack.getTotalValue() > mostValue) {
					mostValue = knapsack.getTotalValue();
					solution = packages.get(j);
				}
			}
			
			int packagesSize = packages.size();
			if(i != itemsAmount) {
				for(int j = 0; j < packagesSize; j++) {
					packages.add(packages.get(j) + "0");
					packages.set(j, packages.get(j) + "1");
				}
			}
		}

		this.packKnapsackFromBinaryString(knapsack, items, solution);
	}
}
