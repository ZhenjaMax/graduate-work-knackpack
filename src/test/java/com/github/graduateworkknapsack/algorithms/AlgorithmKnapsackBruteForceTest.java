package test.java.com.github.graduateworkknapsack.algorithms;

import org.junit.Assert;
import org.junit.Test;

import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsackBruteForce;

public final class AlgorithmKnapsackBruteForceTest extends AlgorithmKnapsackTest {
	@Override
	@Test
	public void testAlgorithm() {
		int flagValue = this.runAlgorithm(new AlgorithmKnapsackBruteForce());
		Assert.assertTrue(
			"Incorrect answer for test #" + flagValue + ".",
			(flagValue == 0)
		);
	}
}
