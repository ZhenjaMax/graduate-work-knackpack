package test.java.com.github.graduateworkknapsack.algorithms;

import static org.junit.Assert.fail;

import org.junit.Test;

import main.java.com.github.graduateworkknapsack.algorithms.AlgorithmTSPBnB;

public class AlgorithmTSPBnBTest extends AlgorithmTSPTest {
	@Override
	@Test
	public void testAlgorithm() {
		for(int i = 0; i < this.distanceInputs.length; i++) {
			AlgorithmTSPBnB tsp = new AlgorithmTSPBnB(0, this.distanceInputs[i]);
			tsp.run();
			
			for(int j = 0; j < tsp.minTour.size(); j++) {
				if(this.answers.get(i).get(j) != tsp.minTour.get(j)) {
					fail("Incorrect answer for test #" + (i+1) + ".");
				}
			}
		}
	}
}
