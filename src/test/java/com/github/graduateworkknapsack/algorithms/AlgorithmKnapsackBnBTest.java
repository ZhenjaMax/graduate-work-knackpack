package test.java.com.github.graduateworkknapsack.algorithms;

import org.junit.Assert;
import org.junit.Test;

import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmKnapsackBnB;

public class AlgorithmKnapsackBnBTest extends AlgorithmKnapsackTest {
	@Override
	@Test
	public void testAlgorithm() {
		int flagValue = this.runAlgorithm(new AlgorithmKnapsackBnB());
		Assert.assertTrue(
			"Incorrect answer for test #" + flagValue + ".",
			(flagValue == 0)
		);
	}
}
