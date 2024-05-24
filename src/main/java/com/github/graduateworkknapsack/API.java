package main.java.com.github.graduateworkknapsack;

import java.util.ArrayList;

import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsack;
import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsackBnB;
import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsackBruteForce;
import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsackDynProg;
import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsackReductionBnB;
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
	
	public static void bnb(Pair<Knapsack, ArrayList<Item>> pair) {
		API.run(pair.t, pair.u, new AlgorithmKnapsackBnB());
	}
	
	public static void reductionBruteForce(Pair<Knapsack, ArrayList<Item>> pair) {
		API.run(pair.t, pair.u, new AlgorithmKnapsackReductionBruteForce());
	}
	
	public static void reductionDynamicProgramming(Pair<Knapsack, ArrayList<Item>> pair) {
		API.run(pair.t, pair.u, new AlgorithmKnapsackReductionDynProg());
	}
	
	public static void reductionBnB(Pair<Knapsack, ArrayList<Item>> pair) {
		API.run(pair.t, pair.u, new AlgorithmKnapsackReductionBnB());
	}
	
	// ================
	
	public static void bruteForce(Knapsack knapsack, ArrayList<Item> items) {
		API.run(knapsack, items, new AlgorithmKnapsackBruteForce());
	}
	
	public static void dynamicProgramming(Knapsack knapsack, ArrayList<Item> items) {
		API.run(knapsack, items, new AlgorithmKnapsackDynProg());
	}
	
	public static void bnb(Knapsack knapsack, ArrayList<Item> items) {
		API.run(knapsack, items, new AlgorithmKnapsackBnB());
	}
	
	public static void reductionBruteForce(Knapsack knapsack, ArrayList<Item> items) {
		API.run(knapsack, items, new AlgorithmKnapsackReductionBruteForce());
	}
	
	public static void reductionDynamicProgramming(Knapsack knapsack, ArrayList<Item> items) {
		API.run(knapsack, items, new AlgorithmKnapsackReductionDynProg());
	}
	
	public static void reductionBnB(Knapsack knapsack, ArrayList<Item> items) {
		API.run(knapsack, items, new AlgorithmKnapsackReductionBnB());
	}
	
	// ================
	
	public static Pair<Knapsack, ArrayList<Item>> generateRandomKP(
		int capacity,
		int itemsAmount,
		int weightMin, int weightMax,
		float valueMin, float valueMax
	) {
		if(
			(capacity < 1) ||
			(itemsAmount < 1) ||
			(weightMin < 1) || 
			(valueMin <= 0) || 
			(weightMax-weightMin < 0) || 
			(valueMax-valueMin < 0) ||
			
			(capacity > 1E5) ||
			(itemsAmount > 1E3) ||
			(weightMax > 1E5) ||
			(valueMax > 1E5)
		) {
			String err = "Error: invalid values to generate random KP.\n";
			if(capacity < 1) {
				err += ("capacity = " + capacity + ", must be integer greater than 0.\n");
			}
			if(itemsAmount < 1) {
				err += ("itemsAmount = " + itemsAmount + ", must be integer greater than 0.\n");
			}
			if(weightMin < 1) {
				err += ("weightMin = " + itemsAmount + ", must be integer greater than 0.\n");
			}
			if(valueMin <= 0) {
				err += ("valueMin = " + itemsAmount + ", must be float greater than 0.\n");
			}
			if(weightMax-weightMin < 0) {
				err += ("weightMax = " + itemsAmount + ", must be not less than weightMin = " + weightMin + ".\n");
			}
			if(valueMax-valueMin < 0) {
				err += ("valueMax = " + itemsAmount + ", must be not less than valueMin = " + valueMin + ".\n");
			} else {
				err += ("Input values are too large to process.\n");
			}
			throw new IllegalArgumentException(err);
		}

		Knapsack knapsack = new Knapsack(capacity);
		ArrayList<Item> items = new ArrayList<>();
		for(int i = 0; i < itemsAmount; i++) {
			int weight = weightMin + (int) Math.floor(Math.random()*(weightMax-weightMin+1));
			float value = valueMin + (int) Math.floor(Math.random()*(valueMax-valueMin+1));
			items.add(new Item(weight, value));
		}
		return new Pair<Knapsack, ArrayList<Item>>(knapsack, items);
	}
}
