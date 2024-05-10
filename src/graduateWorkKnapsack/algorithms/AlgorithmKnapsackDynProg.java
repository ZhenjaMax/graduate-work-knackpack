package graduateWorkKnapsack.algorithms;

import java.util.ArrayList;
import java.util.Collections;

import graduateWorkKnapsack.elements.Item;
import graduateWorkKnapsack.elements.Knapsack;

public final class AlgorithmKnapsackDynProg extends AlgorithmKnapsack {
	
	@Override
	public void run(Knapsack knapsack, ArrayList<Item> items) {
		int C = knapsack.getCapacity();
		int N = items.size();
		if((C == 0) || (N == 0)) {
			return;
		}
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i <= N; i++) {
			matrix.add(new ArrayList<Integer>(Collections.nCopies(C+1, 0)));
		}
		
		for(int i = 1; i <= N; i++) {
			for(int w = 1; w <= C; w++) {
				int prevMatrixElement = matrix.get(i-1).get(w);
				int prevW = items.get(i-1).getWeight();
				if(w < prevW) {
					matrix.get(i).set(w, prevMatrixElement);
				} else {
					int prevValue = items.get(i-1).getValue();
					matrix.get(i).set(w,
						Math.max(prevMatrixElement, matrix.get(i-1).get(w-prevW) + prevValue)
					);
				}
			}
		}
		
		int i = N;
		int w = C;
		while(matrix.get(i).get(w) != 0) {
			if(matrix.get(i-1).get(w) != matrix.get(i).get(w)) {
				knapsack.items.add(items.get(i-1));
				w -= knapsack.items.get(knapsack.items.size()-1).getWeight();
			}
			i--;
		}
		Collections.reverse(knapsack.items);
	}
}
