package main.java.com.github.graduateworkknapsack.algorithms;

import java.util.TreeSet;

import main.java.com.github.graduateworkknapsack.elements.PathDataBnB;
import main.java.com.github.graduateworkknapsack.util.Constant;

public class AlgorithmTSPBnB extends AlgorithmTSP {
	public TreeSet<PathDataBnB> data = new TreeSet<PathDataBnB>();
	
	public AlgorithmTSPBnB(int startNode, float[][] distance) {
		super(startNode, distance);
		this.data.add(new PathDataBnB(distance));
	}

    public void run() {
    	PathDataBnB solution = null;
    	float bnbMinTour = Constant.INFINITY;
    
        while(data.size() > 0) {
        	PathDataBnB first = data.first();
        	data.remove(first);
        	first.reduce();
        	
        	boolean isPathCompleted = (first.currentPath.size() == this.nodesAmount);
        	boolean isTooMuchCost = (bnbMinTour <= first.baseCost);
        	
        	if(isTooMuchCost) {
        		continue;
        	}
        	if(isPathCompleted && !isTooMuchCost) {
        		bnbMinTour = first.baseCost;
    			solution = first;
        		continue;
        	}
        	
        	first.setPenalty();
        	PathDataBnB second = first.clone();
        	second.adjacencyMatrix[first.penaltyI][first.penaltyJ] = Constant.INFINITY;
        	second.reduce();
        	first.setPathEdge(first.penaltyI, first.penaltyJ);
    		data.add(first);
    		data.add(second);
        }
        
        if(solution != null) {
        	this.minTour = solution.restorePath(this.startNode);
        	this.minTourCost = solution.baseCost;
        }
    }
}
