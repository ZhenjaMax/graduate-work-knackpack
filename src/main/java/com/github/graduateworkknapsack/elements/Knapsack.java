package main.java.com.github.graduateworkknapsack.elements;

import java.util.ArrayList;

public class Knapsack {
	private int capacity;
	private long timeUs = 0;
	public ArrayList<Item> items;
	
	public Knapsack(int capacity) {
		this.capacity = capacity;
		this.items = new ArrayList<>();
	}
	
	public int getCapacity() {
        return this.capacity;
    }
	
	public float getTotalValue() {
		float total = 0;
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
	
	public void setTimeUs(long timeUs) {
		this.timeUs = timeUs;
	}
	
	public long getTimeUs() {
		return this.timeUs;
	}
}
