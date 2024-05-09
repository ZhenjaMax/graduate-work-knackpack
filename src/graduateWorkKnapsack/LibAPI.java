package graduateWorkKnapsack;

import java.util.ArrayList;
import java.util.Date;

import graduateWorkKnapsack.elements.Knapsack;
import graduateWorkKnapsack.algorithms.AlgorithmKnapsack;
import graduateWorkKnapsack.algorithms.AlgorithmKnapsackBruteForce;
import graduateWorkKnapsack.algorithms.AlgorithmKnapsackDynProg;
import graduateWorkKnapsack.algorithms.AlgorithmKnapsackReductionDynProg;
import graduateWorkKnapsack.elements.Item;

public class LibAPI {
	// Ёмкость
	private static Knapsack deserializeKnapsack(ArrayList<Integer> numbers) {
		return new Knapsack(numbers.get(0));
	}
	
	// Пары вес-стоимость
	private static ArrayList<Item> deserializeItems(ArrayList<Integer> numbers) {
		ArrayList<Item> items = new ArrayList<>();
		for(int i = 1; i < numbers.size(); i += 2) {
			items.add(new Item(numbers.get(i), numbers.get(i+1)));
		}
		return items;
	}
	
	// Суммарный вес, стоимость, количество объектов
	// Перечисление объектов пары вес-стоимость
	private static ArrayList<Integer> serializeKnapsackSolved(Knapsack knapsack) {
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(knapsack.getTotalWeight());
		numbers.add(knapsack.getTotalValue());
		numbers.add(knapsack.items.size());
		for(Item item: knapsack.items) {
			numbers.add(item.getWeight());
			numbers.add(item.getValue());
		}
		return numbers;
	}
	
	private static void run(String input, String output, AlgorithmKnapsack algorithm, boolean isTimeTest) {
		ArrayList<Integer> numbers = FileIO.read(input);
		Knapsack knapsack = LibAPI.deserializeKnapsack(numbers);
		ArrayList<Item> items = LibAPI.deserializeItems(numbers);
		Date date1 =  new Date(), date2;

		algorithm.run(knapsack, items);
		if(isTimeTest) {
			date2 = new Date();
			long diffMilliseconds = date2.getTime() - date1.getTime();
			System.out.println("Time passed, ms: " + diffMilliseconds + "\n");
		}
		
		ArrayList<Integer> answerNumbers = LibAPI.serializeKnapsackSolved(knapsack);
		FileIO.save(answerNumbers, output);
		return;
	}
	
	// ================
	
	public static void bruteForce(String input, String output) {
		LibAPI.run(input, output, new AlgorithmKnapsackBruteForce(), false);
	}
	
	public static void dynamicProgramming(String input, String output) {
		LibAPI.run(input, output, new AlgorithmKnapsackDynProg(), false);
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
	
	public static void reductionDynamicProgramming(String input, String output, boolean isTimeTest) {
		LibAPI.run(input, output, new AlgorithmKnapsackReductionDynProg(), isTimeTest);
	}
}
