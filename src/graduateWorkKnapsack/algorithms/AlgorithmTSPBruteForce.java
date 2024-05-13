package graduateWorkKnapsack.algorithms;

import java.util.ArrayList;

public final class AlgorithmTSPBruteForce extends AlgorithmTSP {
	private ArrayList<Integer> currentTour = new ArrayList<>();

	public AlgorithmTSPBruteForce(int startNode, ArrayList<ArrayList<Float>> distance) {
		super(startNode, distance);
	}
	
	private void checkTour() {
		double tourCost = 0;
		for(int i = 0; i < N; i++) {
			tourCost += this.distance[(this.currentTour.get(i))][this.currentTour.get(i+1)];
		}
		if(tourCost < this.minTourCost) {
			this.minTourCost = tourCost;
			this.tour = new ArrayList<>();
			for(int i = 0; i < N+1; i++) {
				this.tour.add(this.currentTour.get(i));
			}
		}
	}
	
	private void tspRecurcive() {
		if(this.currentTour.size() == this.N) {
			this.currentTour.add(0);
			checkTour();
			this.currentTour.remove(this.N);
			return;
		}
		
		for(int i = 1; i < this.N; i++) {
			if(!this.currentTour.contains(i) && this.distance[this.currentTour.get(this.currentTour.size()-1)][i] < AlgorithmTSP.INFINITY) {
				this.currentTour.add(i);
				this.tspRecurcive();
				this.currentTour.remove(this.currentTour.size()-1);
			}
		}
	}
	
	@Override
	public void run() {
		this.tour.add(0);
		this.tspRecurcive();
		
		for(int i = 0; i < tour.size()-1; i++) {
			tourMatrix.get(tour.get(i)).set(tour.get(i+1), 1);
		}
	}

}
