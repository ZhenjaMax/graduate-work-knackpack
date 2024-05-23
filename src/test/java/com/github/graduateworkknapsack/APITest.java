package test.java.com.github.graduateworkknapsack;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import main.java.com.github.graduateworkknapsack.API;
import main.java.com.github.graduateworkknapsack.elements.Item;
import main.java.com.github.graduateworkknapsack.elements.Knapsack;
import main.java.com.github.graduateworkknapsack.util.Pair;

public class APITest {
	private final int iterationsAmount = 10;
	
	private String[] errorMsgs = {
		"capacity error.",
		"itemsAmount error.",
		"weightMin error.",
		"weightMax error.",
		"valueMin error.",
		"valueMax error..",
	};
	
	private int isGeneratedKnapsackCorrect (
		Pair<Knapsack, ArrayList<Item>> pair,
		int capacity, int itemsAmount,
		int weightMin, int weightMax,
		float valueMin, float valueMax
	) {
		Knapsack knapsack = pair.t;
		knapsack.items = pair.u;
		if(knapsack.getCapacity() != capacity) {
			return 1;
		}
		if(knapsack.items.size() != itemsAmount) {
			return 2;
		}
		if(itemsAmount == 0) {
			return 0;
		}
		
		ArrayList<Integer> weights = new ArrayList<>();
		ArrayList<Float> values = new ArrayList<>();
		for(Item item: knapsack.items) {
			weights.add(item.getWeight());
			values.add(item.getValue());
		}
		
		if(weightMin > Collections.min(weights)) {
			return 3;
		}
		if(weightMax < Collections.max(weights)) {
			return 4;
		}
		if(valueMin > Collections.min(values)) {
			return 5;
		}
		if(valueMax < Collections.max(values)) {
			return 6;
		}
		
		return 0;
	}
	
	private boolean runPairsGeneration(
		int capacity, int itemsAmount,
		int weightMin, int weightMax,
		float valueMin, float valueMax
	) {
		for(int i = 0; i < this.iterationsAmount; i++) {
			try {
				@SuppressWarnings("unused")
				Pair<Knapsack, ArrayList<Item>> pair = API.generateRandomKP(
					capacity, itemsAmount, weightMin, weightMax, valueMin, valueMax
				);
				return false;
			} catch (Exception ignore) {}
		}
		return true;
	}
	
	@Test
	public void testGenerateRandomKPCommon() {
		int capacity = 15;
		int itemsAmount = 7;
		int weightMin = 3;
		int weightMax = 11;
		float valueMin = 5;
		float valueMax = 25;
		
		Pair<Knapsack, ArrayList<Item>> pair;
		int checkCorrect;
		for(int i = 0; i < this.iterationsAmount; i++) {
			pair = API.generateRandomKP(capacity, itemsAmount, weightMin, weightMax, valueMin, valueMax);
			checkCorrect = this.isGeneratedKnapsackCorrect(
				pair, 
				capacity, itemsAmount, weightMin, weightMax, valueMin, valueMax
			);
			if(checkCorrect != 0) {
				fail("Generated Knapsack is incorrect: " + this.errorMsgs[checkCorrect-1]);
			}
		}
	}

	@Test
	public void testGenerateRandomKPIncorrectCapacity() {
		int capacity = -6;
		int itemsAmount = 7;
		int weightMin = 3;
		int weightMax = 11;
		float valueMin = 5;
		float valueMax = 25;
		
		Assert.assertTrue(
			"Capacity error is not detected.", 
			this.runPairsGeneration(capacity, itemsAmount, weightMin, weightMax, valueMin, valueMax)
		);
	}
	
	@Test
	public void testGenerateRandomKPIncorrectItemsAmount() {
		int capacity = 44;
		int itemsAmount = 0;
		int weightMin = 3;
		int weightMax = 11;
		float valueMin = 5;
		float valueMax = 25;
		
		Assert.assertTrue(
			"Items amount error is not detected.", 
			this.runPairsGeneration(capacity, itemsAmount, weightMin, weightMax, valueMin, valueMax)
		);
	}
	
	@Test
	public void testGenerateRandomKPIncorrectMinWeight() {
		int capacity = 44;
		int itemsAmount = 10;
		int weightMin = -1;
		int weightMax = 11;
		float valueMin = 5;
		float valueMax = 25;
		
		Assert.assertTrue(
			"Minimum weight error is not detected.", 
			this.runPairsGeneration(capacity, itemsAmount, weightMin, weightMax, valueMin, valueMax)
		);
	}
	
	@Test
	public void testGenerateRandomKPIncorrectDeltaWeight() {
		int capacity = 44;
		int itemsAmount = 10;
		int weightMin = 6;
		int weightMax = 3;
		float valueMin = 5;
		float valueMax = 25;
		
		Assert.assertTrue(
			"Delta weight error is not detected.", 
			this.runPairsGeneration(capacity, itemsAmount, weightMin, weightMax, valueMin, valueMax)
		);
	}
	
	@Test
	public void testGenerateRandomKPIncorrectMinValue() {
		int capacity = 44;
		int itemsAmount = 10;
		int weightMin = 3;
		int weightMax = 6;
		float valueMin = -0.1F;
		float valueMax = 25;
		
		Assert.assertTrue(
			"Minimum value error is not detected.", 
			this.runPairsGeneration(capacity, itemsAmount, weightMin, weightMax, valueMin, valueMax)
		);
	}
	
	@Test
	public void testGenerateRandomKPIncorrectDeltaValue() {
		int capacity = 44;
		int itemsAmount = 10;
		int weightMin = 3;
		int weightMax = 6;
		float valueMin = 24;
		float valueMax = 23.9F;
		
		Assert.assertTrue(
			"Delta value error is not detected.", 
			this.runPairsGeneration(capacity, itemsAmount, weightMin, weightMax, valueMin, valueMax)
		);
	}
}
