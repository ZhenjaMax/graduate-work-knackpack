package test.java.com.github.graduateworkknapsack.algorithms;

import org.junit.Assert;
import org.junit.Test;

import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsackDynProg;

public class AlgorithmKnapsackDynProgTest extends AlgorithmKnapsackTest {
	@Override
	@Test
	public void testAlgorithm() {
		int flagValue = this.runAlgorithm(new AlgorithmKnapsackDynProg());
		Assert.assertTrue(
			"Incorrect answer for test #" + flagValue + ".",
			(flagValue == 0)
		);
	}
}