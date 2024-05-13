package graduateWorkKnapsack.algorithms;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AlgorithmTSP {
	protected int START_NODE;
	protected int N;
	protected double[][] distance;
	public static float INFINITY = (float) 1E7;
	
	public double minTourCost = Double.POSITIVE_INFINITY;
	public ArrayList<Integer> tour = new ArrayList<>();
	public ArrayList<ArrayList<Integer>> tourMatrix = new ArrayList<>();
	
	public AlgorithmTSP(int startNode, ArrayList<ArrayList<Float>> distance) {
		N = distance.size();
		this.distance = new double[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				this.distance[i][j] = (double) distance.get(i).get(j);
			}
		}
		START_NODE = startNode;
		
		for(int i = 0; i < N; i++) {
			this.tourMatrix.add(new ArrayList<Integer>(Collections.nCopies(N, 0)));
		}
	}
	
	public abstract void run();
}
