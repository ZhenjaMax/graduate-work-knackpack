package main.java.com.github.graduateworkknapsack;

import java.util.ArrayList;

import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsack;
import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsackBruteForce;
import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsackDynProg;
import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsackReductionBruteForce;
import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsackReductionDynProg;
import main.java.com.github.graduateworkknapsack.elements.Item;
import main.java.com.github.graduateworkknapsack.elements.Knapsack;
import main.java.com.github.graduateworkknapsack.util.Pair;

public final class API {
	// Суммарный вес, стоимость, количество объектов
	// Перечисление объектов пары вес-стоимость
	private static void run(Knapsack knapsack, ArrayList<Item> items, AlgorithmKnapsack algorithm) {
		long date1 = System.nanoTime();
		algorithm.run(knapsack, items);
		long date2 = System.nanoTime();
		knapsack.setTimeUs((date2 - date1)/1000);
		return;
	}

	// ================
	
	public static void bruteForce(Pair<Knapsack, ArrayList<Item>> pair) {
		API.run(pair.t, pair.u, new AlgorithmKnapsackBruteForce());
	}
	
	public static void dynamicProgramming(Pair<Knapsack, ArrayList<Item>> pair) {
		API.run(pair.t, pair.u, new AlgorithmKnapsackDynProg());
	}
	
	public static void reductionBruteForce(Pair<Knapsack, ArrayList<Item>> pair) {
		API.run(pair.t, pair.u, new AlgorithmKnapsackReductionBruteForce());
	}
	
	public static void reductionDynamicProgramming(Pair<Knapsack, ArrayList<Item>> pair) {
		API.run(pair.t, pair.u, new AlgorithmKnapsackReductionDynProg());
	}
	
	// ================
	
	public static void bruteForce(Knapsack knapsack, ArrayList<Item> items) {
		API.run(knapsack, items, new AlgorithmKnapsackBruteForce());
	}
	
	public static void dynamicProgramming(Knapsack knapsack, ArrayList<Item> items) {
		API.run(knapsack, items, new AlgorithmKnapsackDynProg());
	}
	
	public static void reductionBruteForce(Knapsack knapsack, ArrayList<Item> items) {
		API.run(knapsack, items, new AlgorithmKnapsackReductionBruteForce());
	}
	
	public static void reductionDynamicProgramming(Knapsack knapsack, ArrayList<Item> items) {
		API.run(knapsack, items, new AlgorithmKnapsackReductionDynProg());
	}
	
	public static Pair<Knapsack, ArrayList<Item>> generateRandomKP(
		int capacity,
		int itemsAmount,
		int weightMin, int weightMax,
		float valueMin, float valueMax
	) {
		try {
			if((capacity < 1) || (weightMin < 1) || (valueMin <= 0) || (weightMax-weightMin < 0) || (valueMax-valueMin < 0)) {
				throw new Exception("Invalid values to generate random KP!");
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
            System.exit(1);
		}

		Knapsack knapsack = new Knapsack(capacity);
		ArrayList<Item> items = new ArrayList<>();
		for(int i = 0; i < itemsAmount; i++) {
			int weight = (int) Math.floor(Math.random()*(weightMax-weightMin+1));
			float value = (int) Math.floor(Math.random()*(valueMax-valueMin+1));
			items.add(new Item(weight, value));
		}
		return new Pair<Knapsack, ArrayList<Item>>(knapsack, items);
	}
}
