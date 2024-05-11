package graduateWorkKnapsack;

import java.util.ArrayList;
import java.util.List;

import graduateWorkKnapsack.algorithms.AlgorithmTSPDynProg;

public class Main {
	public static void main(String[] args) {		
		String input = "tests/file.txt";
		String output = "tests/output.txt";
		
		LibAPI.reductionDynamicProgramming(input, output, true);
	}
}
