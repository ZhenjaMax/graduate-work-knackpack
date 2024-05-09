package graduateWorkKnapsack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
	public static ArrayList<Integer> read(String filename) {
		ArrayList<Integer> numbers = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = br.readLine();
            if (line == null) {
            	throw new IOException("File is empty or does not contain any numbers.");
            }
            numbers.add(Integer.parseInt(line));
            
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                if (tokens.length == 2) {
                	for(int i = 0; i < 2; i++) {
                		numbers.add(Integer.parseInt(tokens[i]));
                	}
                } else {
                    throw new IOException("Invalid line format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        } 
		
		return numbers;
	}
	
	// Суммарный вес, стоимость, количество объектов
	// Перечисление объектов пары вес-стоимость
    public static void save(ArrayList<Integer> numbers, String output) {
        try (FileWriter writer = new FileWriter(output)) {
        	if((numbers.size() < 3) || (numbers.size() % 2 == 0)) {
        		throw new IOException("Invalid array size: " + numbers.size());
        	}
        	writer.write(numbers.get(0) + " " + numbers.get(1) + " " + numbers.get(2) + "\n");
            for (int i = 3; i < numbers.size(); i += 2) {
            	writer.write(numbers.get(i) + " " + numbers.get(i+1) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
