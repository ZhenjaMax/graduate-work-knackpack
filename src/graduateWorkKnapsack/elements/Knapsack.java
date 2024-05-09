package graduateWorkKnapsack.elements;

import java.util.ArrayList;

public class Knapsack {
	private int capacity;
	public ArrayList<Item> items;
	
	public Knapsack(int capacity) {
		this.capacity = capacity;
		this.items = new ArrayList<>();
	}
	
	public int getCapacity() {
        return this.capacity;
    }
	
	public int getTotalValue() {
		int total = 0;
		for(Item item: this.items) {
			total += item.getValue();
		}
		return total;
	}
	
	public int getTotalWeight() {
		int total = 0;
		for(Item item: this.items) {
			total += item.getWeight();
		}
		return total;
	}
	
	public boolean isPacked() {
		return this.capacity >= this.getTotalWeight();
	}
}
