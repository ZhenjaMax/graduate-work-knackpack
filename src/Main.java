import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmTSPBnB;
import main.java.com.github.graduateworkknapsack.util.Constant;

public class Main {
	public static void main(String[] args) {		
		//String input = "input.txt";
		//String output = "output.txt";
		
		float[][] matrix = {
			{Constant.INFINITY, 27, 43, 16, 30, 26},
			{7, Constant.INFINITY, 16, 1, 30, 25},
			{20, 13, Constant.INFINITY, 35, 5, 0},
			{21, 16, 25, Constant.INFINITY, 18, 18},
			{12, 46, 27, 48, Constant.INFINITY, 5},
			{23, 5, 5, 9, 5, Constant.INFINITY},
		};
		AlgorithmTSPBnB tsp = new AlgorithmTSPBnB(0, matrix);
		tsp.run();
		System.out.println(tsp.minTourCost);
		System.out.println(tsp.minTour);
	}
}
