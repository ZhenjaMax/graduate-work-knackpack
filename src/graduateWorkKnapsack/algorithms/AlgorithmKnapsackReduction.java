package graduateWorkKnapsack.algorithms;

import java.util.ArrayList;
import java.util.Collections;

import graduateWorkKnapsack.elements.Item;
import graduateWorkKnapsack.elements.ItemTSP;
import graduateWorkKnapsack.elements.Knapsack;

public abstract class AlgorithmKnapsackReduction extends AlgorithmKnapsack {
	public static float INFINITY = (float) 1E7;
	public ArrayList<ItemTSP> currentResult = new ArrayList<>();
	public ArrayList<ItemTSP> itemsMatrix = new ArrayList<>();
	
	public int maxWeight = 0;
	public float maxValue = 0;
	public ArrayList<ItemTSP> result = new ArrayList<>();
	
	protected ArrayList<ArrayList<Float>> buildMatrixForTSP(Knapsack knapsack, ArrayList<Item> items) {
		int N = knapsack.getCapacity();
		int countNodes = 2*N - 1;
		ArrayList<ArrayList<Float>> matrixTSP = new ArrayList<ArrayList<Float>>();
		for(int i = 0; i < countNodes; i++) {
			matrixTSP.add(new ArrayList<Float>(Collections.nCopies(countNodes, AlgorithmKnapsackReduction.INFINITY)));
		}
		
		ArrayList<Integer> n = new ArrayList<Integer>();
		ArrayList<Float> c = new ArrayList<Float>();
		for(Item item: items) {
			n.add(item.getWeight());
			c.add((float) item.getValue());
		}
		
		int nMin = Collections.min(n);
		if(nMin > 1) {
			int nDelta = nMin-1;
			for(int i = 0; i < n.size(); i++) {
				int nOld = n.get(i);
				int nNew = nOld-nDelta;
				float cNew = c.get(i)*nNew/nOld;
				n.set(i, nNew);
				c.set(i, cNew);
			}
		}
		
		System.out.println(n);
		System.out.println(c);
		System.out.println("Items size: " + items.size() + "\n");
 		
		for(int i = 0; i < n.size(); i++) {
			for(int j = 0; j < N; j++) {
				int itemWeight = n.get(i);
				if(j+itemWeight <= N) {
					float itemValue = c.get(i);
					matrixTSP
						.get(j)
						.set(j + itemWeight, 
							(float) itemWeight*itemWeight/itemValue
						);
				}
			}
		}
		
		// Впереди
		for(int j = N; j < countNodes-1; j++) {
			matrixTSP.get(j).set(j+1, (float) 0);
		}
		matrixTSP.get(countNodes-1).set(0, (float) 0);
		
		// Снизу вверх
		for(int j = N; j < countNodes; j++) {
			matrixTSP.get(j).set(countNodes-j, (float) 0);
		}
		
		// Сверху вниз
		for(int j = N-1; j > 1; j--) {
			matrixTSP.get(j).set(2*N - j, (float) 0);
		}
		matrixTSP.get(1).set(0, (float) 0);
		
		return matrixTSP;
	}
	
	// ================================
	
	protected void convertSolutionToKP(
		ArrayList<ArrayList<Float>> matrixTSP, 
		ArrayList<Integer> tour,
		Knapsack knapsack, 
		ArrayList<Item> items
	) {
		int n = matrixTSP.size();
		float sumW = 0;
		for(ArrayList<Float> matrixRow: matrixTSP) {
			for(float matrixElement: matrixRow) {
				if(matrixElement < AlgorithmKnapsackReduction.INFINITY) {
					sumW += matrixElement;
				}	
			}
		}
		
		for(int i = 0; i < matrixTSP.size(); i++) {
			for(int j = 0; j < matrixTSP.size(); j++) {
				float w = matrixTSP.get(i).get(j);
				if(w < AlgorithmKnapsackReduction.INFINITY) {
					this.itemsMatrix.add(new ItemTSP(i, j, 1, 1 - w/sumW));
				}
			}
		}
		
		knapsack.items = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			for(ItemTSP item: this.itemsMatrix) {
				int indexI = tour.get(i);
				int indexJ = tour.get(i+1);
				if((item.i == indexI) && item.j == indexJ) {
					knapsack.items.add(new Item(item.weight, item.value));
				}
			}
		}	
	}
}
