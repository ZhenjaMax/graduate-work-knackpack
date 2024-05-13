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
		ArrayList<ArrayList<Float>> matrix = new ArrayList<ArrayList<Float>>();
		for(int i = 0; i <= N; i++) {
			matrix.add(new ArrayList<Float>(Collections.nCopies(C+1, (float) 0)));
		}
		
		for(int i = 1; i <= N; i++) {
			for(int w = 1; w <= C; w++) {
				float prevMatrixElement = matrix.get(i-1).get(w);
				int prevW = items.get(i-1).getWeight();
				if(w < prevW) {
					matrix.get(i).set(w, prevMatrixElement);
				} else {
					float prevValue = items.get(i-1).getValue();
					matrix.get(i).set(w,
						Math.max(prevMatrixElement, matrix.get(i-1).get(w-prevW) + prevValue)
					);
				}
			}
		}
		
		for(int i = 0; i <= N; i++) {
			System.out.println(matrix.get(i));
		}
		
		int i = N;
		int w = C;
		ArrayList<Float> row = matrix.get(i);
		float R = row.get(w);
		
		while(R != (float) 0) {
			System.out.println(i + " " + w);
			if(matrix.get(i-1).get(w) != R) {
				knapsack.items.add(items.get(i-1));
				w -= knapsack.items.get(knapsack.items.size()-1).getWeight();
			}
			i--;
			row = matrix.get(i);
			R = row.get(w);
		}
		Collections.reverse(knapsack.items);
	}
}
