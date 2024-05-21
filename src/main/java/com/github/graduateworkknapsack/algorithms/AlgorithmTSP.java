package main.java.com.github.graduateworkknapsack.algorithms;

import java.util.ArrayList;
import java.util.Collections;

import main.java.com.github.graduateworkknapsack.util.Constant;

public abstract class AlgorithmTSP {
	protected int startNode;
	protected float[][] distance;
	protected int nodesAmount;
	
	public float minTourCost = Constant.INFINITY;
	public ArrayList<Integer> minTour;
	
	protected AlgorithmTSP(int startNode, float[][] distance) {
		this.startNode = startNode;
		this.distance = distance;
		this.nodesAmount = distance.length;
		this.minTour = new ArrayList<>(Collections.nCopies(nodesAmount+1, 0));
	}
	
	public abstract void run();
}
