package main.java.com.github.graduateworkknapsack.elements;

public class Item implements Comparable<Item> {
	private int weight;
    private float value;
	
	public Item(int weight, float value) {
		if(weight < 1) {
			throw new IllegalArgumentException("Error: invalid Item weight: " + weight + " (must be integer greater than 0).");
		}
		if(value <= 0) {
			throw new IllegalArgumentException("Error: invalid Item value: " + value + " (must be float greater than 0).");
		}
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return this.weight;
    }

    public float getValue() {
        return this.value;
    }
    
    @Override
	public boolean equals(Object o) {
    	if(this == o) {
    		return true;
    	}
    	
    	if (!(o instanceof Item)) {
            return false;
        }
    	
    	Item that = (Item) o;
    	
		return (
			(this.getWeight() == that.getWeight()) &&
			(this.getValue() == that.getValue())
		);	
	}
    
    @Override
    public int compareTo(Item that) {
    	if(this.weight != that.weight) {
    		return this.weight - that.weight;
    	}
    	return (this.value > that.value) ? 1 : (this.value < that.value) ? -1 : 0;
    }
    
    @Override
	public String toString() {
	    return "Item (weight and value): " + this.weight + ", " + this.value + "\n";
	}
}

