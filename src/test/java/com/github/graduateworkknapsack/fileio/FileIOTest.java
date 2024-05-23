package test.java.com.github.graduateworkknapsack.fileio;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import main.java.com.github.graduateworkknapsack.elements.Item;
import main.java.com.github.graduateworkknapsack.elements.Knapsack;
import main.java.com.github.graduateworkknapsack.fileio.FileIO;
import main.java.com.github.graduateworkknapsack.util.Pair;

public class FileIOTest {
	private final String resourceFilesPath = "src/test/resources/com/github/graduateworkknapsack/fileio/";
	private String[] filesInput = {
		"input_1_common.txt",
		"input_2_incorrect_1.txt",
		"input_2_incorrect_2.txt",
		"input_3_many.txt",
	};
	private String[] filesOutput = {
		"output_1_common.txt",
		"output_2_many.txt"
	};
	
	@Test
	public void testReadData() {
		String pathString = this.resourceFilesPath + this.filesInput[0];
		@SuppressWarnings("unused")
		Pair<Knapsack, ArrayList<Item>> pair = FileIO.readDataKP(pathString);
	}
	
	@Test
	public void testReadDataCommon() {
		String pathString = this.resourceFilesPath + this.filesInput[0];
		Pair<Knapsack, ArrayList<Item>> pair = FileIO.readDataKP(pathString);
		Knapsack knapsackThis = pair.t;
		ArrayList<Item> itemsThis = pair.u;
		knapsackThis.items = itemsThis;
		
		int capacity = 10;
		int[][] itemsData = {
			{3, 5},
			{7, 4},
			{8, 10},
			{2, 1}
		};
		int itemsAmount = itemsData.length;
		
		Knapsack knapsackThat = new Knapsack(capacity);
		for(int i = 0; i < itemsAmount; i++) {
			knapsackThat.items.add(new Item(itemsData[i][0], (float) itemsData[i][1]));
		}
		
		Assert.assertTrue("Read input from file is incorrect.", knapsackThis.equals(knapsackThat));
	}
	
	@Test
	public void testReadDataIncorrectCapacity() {
		String pathString = this.resourceFilesPath + this.filesInput[1];
		try {
			@SuppressWarnings("unused")
			Pair<Knapsack, ArrayList<Item>> pair = FileIO.readDataKP(pathString);
		} catch (Exception e) {
			return;
		}
		fail("Knapsack error is not detected.");
	}
	
	@Test
	public void testReadDataIncorrectWeightAndValue() {
		String pathString = this.resourceFilesPath + this.filesInput[2];
		try {
			@SuppressWarnings("unused")
			Pair<Knapsack, ArrayList<Item>> pair = FileIO.readDataKP(pathString);
		} catch (Exception e) {
			return;
		}
		fail("Item error is not detected.");
	}
	
	@Test
	public void testReadDataMany() {
		String pathString = this.resourceFilesPath + this.filesInput[3];
		Pair<Knapsack, ArrayList<Item>> pair = FileIO.readDataKP(pathString);
		Knapsack knapsackThis = pair.t;
		ArrayList<Item> itemsThis = pair.u;
		knapsackThis.items = itemsThis;
		
		int capacity = 123;
		int[][] itemsData = {
			{75, 71}, 
			{42, 17}, 
			{66, 28}, 
			{96, 40},
			{70, 33}, 
			{7, 55}, 
			{13, 3}, 
			{16, 58}, 
			{89, 11}, 
			{60, 51}, 
			{80, 91}, 
			{60, 16}, 
			{95, 100}, 
			{63, 97}, 
			{35, 21}, 
			{1, 73}, 
			{69, 98}, 
			{28, 93}, 
			{63, 10}, 
			{81, 6},
			{100, 1},
			{87, 92},
			{18, 97},
			{75, 31},
			{66, 13}
		};
		int itemsAmount = itemsData.length;
		
		Knapsack knapsackThat = new Knapsack(capacity);
		for(int i = 0; i < itemsAmount; i++) {
			knapsackThat.items.add(new Item(itemsData[i][0], (float) itemsData[i][1]));
		}
		
		Assert.assertTrue("Read input from file is incorrect.", knapsackThis.equals(knapsackThat));
	}

	@Test
	public void testSaveResultCommon() {
		String pathString = this.resourceFilesPath + this.filesOutput[0];
		int capacity = 16;
		int[][] itemsData = {
			{3, 5},
			{7, 4},
			{8, 10},
			{2, 1}
		};
		int itemsAmount = itemsData.length;
		
		Knapsack knapsackThis = new Knapsack(capacity);
		for(int i = 0; i < itemsAmount; i++) {
			knapsackThis.items.add(new Item(itemsData[i][0], itemsData[i][1]));
		}
		
		FileIO.saveResultKP(pathString, knapsackThis);
		Pair<Knapsack, ArrayList<Item>> pair = FileIO.readDataKP(this.resourceFilesPath + this.filesOutput[0]);
		Knapsack knapsackThat = pair.t;
		knapsackThat.items = pair.u;
		
		try {
			Path path = Paths.get(pathString);
			Files.deleteIfExists(path);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		Assert.assertTrue("Saved file is incorrect.", knapsackThis.equals(knapsackThat));
	}
	
	@Test
	public void testSaveResultMany() {
		String pathString = this.resourceFilesPath + this.filesOutput[1];
		int capacity = 123;
		int[][] itemsData = {
			{75, 71}, 
			{42, 17}, 
			{66, 28}, 
			{96, 40},
			{70, 33}, 
			{7, 55}, 
			{13, 3}, 
			{16, 58}, 
			{89, 11}, 
			{60, 51}, 
			{80, 91}, 
			{60, 16}, 
			{95, 100}, 
			{63, 97}, 
			{35, 21}, 
			{1, 73}, 
			{69, 98}, 
			{28, 93}, 
			{63, 10}, 
			{81, 6},
			{100, 1},
			{87, 92},
			{18, 97},
			{75, 31},
			{66, 13}
		};
		int itemsAmount = itemsData.length;
		
		Knapsack knapsackThis = new Knapsack(capacity);
		for(int i = 0; i < itemsAmount; i++) {
			knapsackThis.items.add(new Item(itemsData[i][0], itemsData[i][1]));
		}
		
		FileIO.saveResultKP(pathString, knapsackThis);
		Pair<Knapsack, ArrayList<Item>> pair = FileIO.readDataKP(pathString);
		Knapsack knapsackThat = pair.t;
		knapsackThat.items = pair.u;
		
		try {
			Path path = Paths.get(pathString);
			Files.deleteIfExists(path);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		Assert.assertTrue("Saved file is incorrect.", knapsackThis.equals(knapsackThat));
	}
}
