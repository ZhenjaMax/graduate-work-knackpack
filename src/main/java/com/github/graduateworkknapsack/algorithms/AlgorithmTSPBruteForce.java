package main.java.com.github.graduateworkknapsack.algorithms;

import java.util.ArrayList;
import java.util.Collections;

import main.java.com.github.graduateworkknapsack.util.Constant;

public final class AlgorithmTSPBruteForce extends AlgorithmTSP {
	private ArrayList<Integer> currentTour = new ArrayList<>();
	
	private void checkTour() {
		float currentTourCost = 0;
		for(int i = 0; i < this.nodesAmount; i++) {
			currentTourCost += this.distance[(this.currentTour.get(i))][this.currentTour.get(i+1)];
		}
		if(currentTourCost < this.minTourCost) {
			this.minTourCost = currentTourCost;
			Collections.copy(this.minTour, this.currentTour);
		}
	}
	
	private void tspRecurcive() {
		if(this.currentTour.size() == this.nodesAmount) {
			if(this.distance[this.currentTour.get(this.nodesAmount-1)][0] < Constant.INFINITY) {
				this.currentTour.add(0);
				checkTour();
				this.currentTour.remove(this.nodesAmount);
			}
			return;
		}
		
		for(int i = 1; i < this.nodesAmount; i++) {
			if(!this.currentTour.contains(i) &&  (this.distance[this.currentTour.get(this.currentTour.size()-1)][i] < Constant.INFINITY)
			) {
				this.currentTour.add(i);
				this.tspRecurcive();
				this.currentTour.remove(this.currentTour.size()-1);
			}
		}
	}
	
	public AlgorithmTSPBruteForce(int startNode, float[][] distance) {
		super(startNode, distance);
	}
	
	@Override
	public void run() {
		this.currentTour.add(startNode);
		this.tspRecurcive();
	}

}
