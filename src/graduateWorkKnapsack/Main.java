package graduateWorkKnapsack;

public class Main {
	public static void main(String[] args) {		
		String input = "tests/file.txt";
		String output = "tests/output.txt";
		
		//bruteForce(input, output, true);
		LibAPI.dynamicProgramming(input, output, true);
		//LibAPI.reductionBruteForce(input, output, true);
		//LibAPI.reductionDynamicProgramming(input, output, true);
	}
}
