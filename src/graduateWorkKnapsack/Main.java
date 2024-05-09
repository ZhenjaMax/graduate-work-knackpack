package graduateWorkKnapsack;

public class Main {
	public static void main(String[] args) {		
		String input = "tests/file.txt";
		String output = "tests/output.txt";
		LibAPI.bruteForce(input, output, true);
		return;
	}
}
