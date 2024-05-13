package graduateWorkKnapsack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import graduateWorkKnapsack.elements.Item;
import graduateWorkKnapsack.elements.Knapsack;

public class FileIO {
	private static Knapsack deserializeKnapsack(ArrayList<Float> numbers) {
		return new Knapsack(Math.round(numbers.get(0)));
	}
	
	// Пары вес-стоимость
	private static ArrayList<Item> deserializeItems(ArrayList<Float> numbers) {
		ArrayList<Item> items = new ArrayList<>();
		for(int i = 1; i < numbers.size(); i += 2) {
			items.add(new Item(Math.round(numbers.get(i)), numbers.get(i+1)));
		}
		return items;
	}
	
	public static Knapsack readDataKP(String filename) {
		ArrayList<Float> numbers = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = br.readLine();
            if (line == null) {
            	throw new IOException("File is empty or does not contain any numbers.");
            }
            numbers.add(Float.parseFloat(line));
            
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                if (tokens.length == 2) {
                	for(int i = 0; i < 2; i++) {
                		numbers.add(Float.parseFloat(tokens[i]));
                	}
                } else {
                    throw new IOException("Invalid line format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
		
		Knapsack knapsack = FileIO.deserializeKnapsack(numbers);
		ArrayList<Item> items = FileIO.deserializeItems(numbers);
		knapsack.items = items;
		return knapsack;
	}
	
	// Суммарный вес, стоимость, количество объектов
	// Перечисление объектов пары вес-стоимость
    public static void saveResultKP(String output, Knapsack knapsack) {
        try (FileWriter writer = new FileWriter(output)) {
        	writer.write(knapsack.getTotalWeight() + " " + knapsack.getTotalValue() + " " + knapsack.items.size() + "\n");
        	for(Item item: knapsack.items) {
        		writer.write(item.getWeight() + " " + item.getValue() + "\n");
        	}
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
