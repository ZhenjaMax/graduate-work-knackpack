package graduateWorkKnapsack.algorithms;

import java.util.ArrayList;

public class AlgorithmTSPDynProg extends AlgorithmTSP {
	private final int FINISHED_STATE;
	
	public AlgorithmTSPDynProg(int startNode, ArrayList<ArrayList<Float>> distance) {
		super(startNode, distance);
		FINISHED_STATE = (1 << N) - 1;
	}
	
	public void run() {
		int state = 1 << START_NODE;
		Double[][] memo = new Double[N][1 << N];
		Integer[][] prev = new Integer[N][1 << N];
		minTourCost = tspRecursive(START_NODE, state, memo, prev);
		
		int index = START_NODE;
		while (true) {
			tour.add(index);
			Integer nextIndex = prev[index][state];
			if (nextIndex == null)
				break;
			int nextState = state | (1 << nextIndex);
			state = nextState;
			index = nextIndex;
		}
		tour.add(START_NODE);
		
		for(int i = 0; i < tour.size()-1; i++) {
			tourMatrix.get(tour.get(i)).set(tour.get(i+1), 1);
		}
	}
	
	private double tspRecursive(int i, int state, Double[][] memo, Integer[][] prev) {
		if (state == FINISHED_STATE)
			return distance[i][START_NODE];
		if (memo[i][state] != null) 
			return memo[i][state];
		
		double minCost = Double.POSITIVE_INFINITY;
		int index = -1;
		for (int next = 0; next < N; next++) {
			if ((state & (1 << next)) != 0)
				continue;						// Если следующая вершина была посещена то пропускаем
			
			int nextState = state | (1 << next);
			double newCost = distance[i][next] + tspRecursive(next, nextState, memo, prev);	// Рекурсия
			if (newCost < minCost) {
				minCost = newCost;
				index = next;
			}
		}
		
		prev[i][state] = index;
		return memo[i][state] = minCost;
	}
}
