package graduateWorkKnapsack.elements;

public class Item {
	private int weight;
    private float value;
	
	public Item(int weight, float value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return this.weight;
    }

    public float getValue() {
        return this.value;
    }
}

