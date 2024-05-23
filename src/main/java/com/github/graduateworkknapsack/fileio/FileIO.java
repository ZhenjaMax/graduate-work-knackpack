package main.java.com.github.graduateworkknapsack.fileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import main.java.com.github.graduateworkknapsack.elements.Item;
import main.java.com.github.graduateworkknapsack.elements.Knapsack;
import main.java.com.github.graduateworkknapsack.util.Pair;

public class FileIO {
	// Пары вес-стоимость
	public static Pair<Knapsack, ArrayList<Item>> readDataKP(String filename) {
		ArrayList<Float> numbers = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = br.readLine();
            if (line == null) {
            	throw new IOException("FileIO error: file is empty or does not contain any numbers.");
            }
            numbers.add(Float.parseFloat(line));
            
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                if (tokens.length == 2) {
                	for(int i = 0; i < 2; i++) {
                		numbers.add(Float.parseFloat(tokens[i]));
                	}
                } else {
                    throw new IOException("FileIO error: invalid line format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
        	if(e instanceof NumberFormatException) {
        		System.err.println("FileIO error: invalid file format.");
        	}
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
		
		Knapsack knapsack = new Knapsack(Math.round(numbers.get(0)));
		ArrayList<Item> items = new ArrayList<>();
		for(int i = 1; i < numbers.size(); i += 2) {
			items.add(new Item(Math.round(numbers.get(i)), numbers.get(i+1)));
		}
		
		return new Pair<Knapsack, ArrayList<Item>>(knapsack, items);
	}
	
	// Суммарный вес, стоимость, количество объектов
	// Перечисление объектов пары вес-стоимость
    public static void saveResultKP(String output, Knapsack knapsack) {
        try (FileWriter writer = new FileWriter(output)) {
        	writer.write(knapsack.getCapacity() + "\n");
        	for(Item item: knapsack.items) {
        		writer.write(item.getWeight() + " " + item.getValue() + "\n");
        	}
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
