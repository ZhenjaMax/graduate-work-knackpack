package graduateWorkKnapsack;

public class Main {
	public static void main(String[] args) {		
		String input = "tests/file.txt";
		String output = "tests/output.txt";
		
		// System.out.println(a);
		
		LibAPI.reductionDynamicProgramming(input, output, true);
		return;
	}
}
