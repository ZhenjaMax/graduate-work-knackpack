package main.java.com.github.graduateworkknapsack.elements;

import java.util.ArrayList;
import java.util.Collections;

public class Knapsack {
	private int capacity;
	private long timeUs = 0;
	public ArrayList<Item> items;
	
	public Knapsack(int capacity) {
		this.items = new ArrayList<>();
		if(capacity < 1) {
			throw new IllegalArgumentException("Error: invalid Knapsack capacity: " + capacity + " (must be integer greater than 0).");
		}
		this.capacity = capacity;
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
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if (!(o instanceof Knapsack)) {
            return false;
        }
		
		Knapsack that = (Knapsack) o;
		if(
			(this.getCapacity() != that.getCapacity()) ||
			(this.items.size() != that.items.size())
		) {
			return false;
		}
		
		Collections.sort(this.items);
		Collections.sort(that.items);
		for(int i = 0; i < this.items.size(); i++) {
			if( 
				(this.items.get(i).getWeight() != that.items.get(i).getWeight()) ||
				(this.items.get(i).getValue() != that.items.get(i).getValue())
			) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public String toString() {
	    String output = ("Knapsack capacity: " + this.capacity + "\n");
	    output += ("Items amount: " + this.items.size() + "\n");
	    if(this.items.size() != 0) {
	    	output += ("Items list:" + "\n");
	    	for(Item item: this.items) {
	    		output += item.toString();
	    	}
	    }
	    return output;
	}
}
