import java.util.ArrayList;

import main.java.com.github.graduateworkknapsack.API;
import main.java.com.github.graduateworkknapsack.FileIO;
import main.java.com.github.graduateworkknapsack.elements.Item;
import main.java.com.github.graduateworkknapsack.elements.Knapsack;
import main.java.com.github.graduateworkknapsack.util.Pair;

public class Main {
	public static void main(String[] args) {		
		String input = "file.txt";
		String output = "output.txt";
		
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		Pair<Knapsack, ArrayList<Item>> pair = FileIO.readDataKP(input);
		API.reductionDynamicProgramming(pair);
		System.out.println("Time passed, us: " + pair.t.getTimeUs());
		FileIO.saveResultKP(output, pair.t);
		
		/*
		float[][] distance = {
			{Constant.INFINITY, 15, 21, 24},
			{27, Constant.INFINITY, 7, 30},
			{32, 23, Constant.INFINITY, 1},
			{25, 3, 20, Constant.INFINITY},
		};
		AlgorithmTSPDynProg tsp = new AlgorithmTSPDynProg(2, distance);
		tsp.run();
		System.out.println("DP: " + tsp.minTour);
		*/
	}
}
