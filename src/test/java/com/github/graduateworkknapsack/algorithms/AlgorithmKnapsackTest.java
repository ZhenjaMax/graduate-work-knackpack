package test.java.com.github.graduateworkknapsack.algorithms;

import java.util.ArrayList;

import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsack;
import main.java.com.github.graduateworkknapsack.elements.Item;
import main.java.com.github.graduateworkknapsack.elements.Knapsack;
import main.java.com.github.graduateworkknapsack.fileio.FileIO;
import main.java.com.github.graduateworkknapsack.util.Pair;

public abstract class AlgorithmKnapsackTest {
	protected final String resourceFilesPath = "src/test/resources/com/github/graduateworkknapsack/algorithms/";
	protected String[] filesInput = {
		"knapsack_1.txt",
		"knapsack_2.txt",
		"knapsack_3.txt",
		"knapsack_4.txt",
	};
	protected String[] filesAnswersInput = {
		"knapsack_1_answer.txt",
		"knapsack_2_answer.txt",
		"knapsack_3_answer.txt",
		"knapsack_4_answer.txt",
	};
	
	protected int runAlgorithm(AlgorithmKnapsack algorithmKP) {
		for(int i = 0; i < this.filesInput.length; i++) {
			Pair<Knapsack, ArrayList<Item>> pair = FileIO.readDataKP(this.resourceFilesPath + this.filesInput[i]);
			Pair<Knapsack, ArrayList<Item>> pairAnswer = FileIO.readDataKP(this.resourceFilesPath + this.filesAnswersInput[i]);
			
			algorithmKP.run(pair.t, pair.u);
			
			pairAnswer.t.items = pairAnswer.u;
			if(!pair.t.equals(pairAnswer.t)) {
				return i+1;
			}
		}
		
		return 0;
	}
	
	public abstract void testAlgorithm();
}
