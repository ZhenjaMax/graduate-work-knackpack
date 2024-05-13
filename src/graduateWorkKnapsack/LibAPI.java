package graduateWorkKnapsack;

import java.util.ArrayList;
import java.util.Date;

import graduateWorkKnapsack.elements.Knapsack;
import graduateWorkKnapsack.algorithms.AlgorithmKnapsack;
import graduateWorkKnapsack.algorithms.AlgorithmKnapsackBruteForce;
import graduateWorkKnapsack.algorithms.AlgorithmKnapsackDynProg;
import graduateWorkKnapsack.algorithms.AlgorithmKnapsackReductionBruteForce;
import graduateWorkKnapsack.algorithms.AlgorithmKnapsackReductionDynProg;
import graduateWorkKnapsack.elements.Item;

public class LibAPI {
	// Суммарный вес, стоимость, количество объектов
	// Перечисление объектов пары вес-стоимость
	private static void run(String input, String output, AlgorithmKnapsack algorithm, boolean isTimeTest) {
		Knapsack knapsack = FileIO.readDataKP(input);
		ArrayList<Item> items = knapsack.items;
		knapsack.items = new ArrayList<Item>();
		Date date1 =  new Date(), date2;

		algorithm.run(knapsack, items);
		
		if(isTimeTest) {
			date2 = new Date();
			long diffMilliseconds = date2.getTime() - date1.getTime();
			System.out.println("Time passed, ms: " + diffMilliseconds + "\n");
		}
		
		FileIO.saveResultKP(output, knapsack);
		return;
	}
	
	// ================
	
	public static void bruteForce(String input, String output) {
		LibAPI.run(input, output, new AlgorithmKnapsackBruteForce(), false);
	}
	
	public static void dynamicProgramming(String input, String output) {
		LibAPI.run(input, output, new AlgorithmKnapsackDynProg(), false);
	}
	
	public static void reductionBruteForce(String input, String output) {
		LibAPI.run(input, output, new AlgorithmKnapsackReductionBruteForce(), false);
	}
	
	public static void reductionDynamicProgramming(String input, String output) {
		LibAPI.run(input, output, new AlgorithmKnapsackReductionDynProg(), false);
	}
	
	// ================
	
	public static void bruteForce(String input, String output, boolean isTimeTest) {
		LibAPI.run(input, output, new AlgorithmKnapsackBruteForce(), isTimeTest);
	}
	
	public static void dynamicProgramming(String input, String output, boolean isTimeTest) {
		LibAPI.run(input, output, new AlgorithmKnapsackDynProg(), isTimeTest);
	}
	
	public static void reductionBruteForce(String input, String output, boolean isTimeTest) {
		LibAPI.run(input, output, new AlgorithmKnapsackReductionBruteForce(), isTimeTest);
	}
	
	public static void reductionDynamicProgramming(String input, String output, boolean isTimeTest) {
		LibAPI.run(input, output, new AlgorithmKnapsackReductionDynProg(), isTimeTest);
	}
}
