package main.java.com.github.graduateworkknapsack.elements;

public class ItemTSP {
	public int i, j, weight, amount;
	public float value;
	
	public ItemTSP(int i, int j, int weight, float value) {
		this.i = i;
		this.j = j;
		this.weight = weight;
		this.value = value;
		this.amount = 1;
	}
	
	public static Item convertToItemKP(ItemTSP item) {
		return new Item(item.weight, item.value);
	}
}
