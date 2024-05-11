package graduateWorkKnapsack.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmTSPDynProg {
	private final int N;
	private final int START_NODE;
	private final int FINISHED_STATE;
	private double[][] distance;
	public double minTourCost = Double.POSITIVE_INFINITY;
	public List<Integer> tour = new ArrayList<>();
	public ArrayList<ArrayList<Integer>> tourMatrix;
	
	public AlgorithmTSPDynProg(int startNode, ArrayList<ArrayList<Float>> distance) {
		N = distance.size();
		this.distance = new double[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				this.distance[i][j] = (double) distance.get(i).get(j);
			}
		}
		START_NODE = startNode;
		FINISHED_STATE = (1 << N) - 1;
		this.tourMatrix = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			this.tourMatrix.add(new ArrayList<Integer>(Collections.nCopies(N, 0)));
		}
	}
	
	public void findSolution() {
		int state = 1 << START_NODE;
		Double[][] memo = new Double[N][1 << N];
		Integer[][] prev = new Integer[N][1 << N];
		minTourCost = tsp(START_NODE, state, memo, prev);
		
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
	
	private double tsp(int i, int state, Double[][] memo, Integer[][] prev) {
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
			double newCost = distance[i][next] + tsp(next, nextState, memo, prev);	// Рекурсия
			if (newCost < minCost) {
				minCost = newCost;
				index = next;
			}
		}
		
		prev[i][state] = index;
		return memo[i][state] = minCost;
	}
}
