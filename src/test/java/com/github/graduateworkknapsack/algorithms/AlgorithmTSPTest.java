package test.java.com.github.graduateworkknapsack.algorithms;

import java.util.ArrayList;
import main.java.com.github.graduateworkknapsack.util.Constant;

public abstract class AlgorithmTSPTest {
	protected float[][][] distanceInputs;
	protected ArrayList<ArrayList<Integer>> answers = new ArrayList<>();
	
	protected AlgorithmTSPTest() {
		float[][] matrix1 = {
			{Constant.INFINITY, 1, Constant.INFINITY, Constant.INFINITY},
			{Constant.INFINITY, Constant.INFINITY, 1, Constant.INFINITY},
			{Constant.INFINITY, Constant.INFINITY, Constant.INFINITY, 1},
			{1, Constant.INFINITY, Constant.INFINITY, Constant.INFINITY},
		};
		int[] answer1 = {0, 1, 2, 3, 0};
		
		float[][] matrix2 = {
			{Constant.INFINITY, 27, 43, 16, 30, 26},
			{7, Constant.INFINITY, 16, 1, 30, 25},
			{20, 13, Constant.INFINITY, 35, 5, 0},
			{21, 16, 25, Constant.INFINITY, 18, 18},
			{12, 46, 27, 48, Constant.INFINITY, 5},
			{23, 5, 5, 9, 5, Constant.INFINITY},
		};
		int[] answer2 = {0, 3, 2, 4, 5, 1, 0};
		
		float[][] matrix3 = {
			{Constant.INFINITY, 0, 1, 19, 7, 19, 18, 6, 2, 18}, 
			{5, Constant.INFINITY, 16, 18, 13, 14, 5, 6, 10, 13}, 
			{4, 9, Constant.INFINITY, 16, 11, 17, 5, 18, 8, 16}, 
			{2, 15, 4, Constant.INFINITY, 6, 3, 14, 7, 6, 20}, 
			{5, 11, 15, 8, Constant.INFINITY, 8, 12, 17, 8, 8}, 
			{6, 5, 20, 16, 12, Constant.INFINITY, 17, 2, 6, 5}, 
			{13, 8, 19, 6, 0, 1, Constant.INFINITY, 16, 3, 1}, 
			{17, 7, 7, 2, 0, 9, 0, Constant.INFINITY, 10, 6}, 
			{0, 5, 7, 2, 10, 19, 12, 16, Constant.INFINITY, 20}, 
			{12, 11, 15, 3, 8, 17, 17, 13, 12, Constant.INFINITY}
		};
		int[] answer3 = {0, 1, 6, 5, 7, 4, 9, 3, 2, 8, 0};
		
		float[][] matrix4 = {
			{Constant.INFINITY, 5, 16, 19, 12, 0, 30, 14, 14, 7, 13, 4},
			{15, Constant.INFINITY, 18, 23, 20, 15, 17, 2, 8, 29, 15, 5},
			{3, 0, Constant.INFINITY, 7, 13, 18, 28, 1, 10, 22, 2, 9},
			{26, 2, 9, Constant.INFINITY, 24, 6, 8, 14, 21, 10, 27, 5},
			{0, 25, 29, 12, Constant.INFINITY, 1, 23, 16, 16, 12, 8, 30},
			{19, 27, 22, 27, 17, Constant.INFINITY, 9, 29, 18, 29, 26, 13},
			{10, 22, 8, 21, 0, 16, Constant.INFINITY, 19, 26, 3, 15, 12},
			{7, 9, 12, 5, 21, 2, 11, Constant.INFINITY, 5, 3, 12, 20},
			{19, 22, 26, 4, 1, 0, 14, 24, Constant.INFINITY, 19, 4, 23},
			{26, 26, 14, 25, 3, 21, 18, 26, 19, Constant.INFINITY, 16, 13},
			{12, 19, 29, 16, 23, 26, 4, 23, 0, 21, Constant.INFINITY, 16},
			{2, 25, 17, 24, 10, 15, 14, 23, 3, 6, 7, Constant.INFINITY},
		};
		int[] answer4 = {0, 11, 10, 8, 3, 2, 1, 7, 5, 6, 9, 4, 0};
		
		this.distanceInputs = new float[][][]	{matrix1, matrix2, matrix3, matrix4};
		int[][] rawAnswers 	= 					{answer1, answer2, answer3, answer4};
		for(int i = 0; i < rawAnswers.length; i++) {
			ArrayList<Integer> listAnswers = new ArrayList<Integer>();
			for(int j = 0; j < rawAnswers[i].length; j++) {
				listAnswers.add(rawAnswers[i][j]);
			}
			this.answers.add(listAnswers);
		}
		
	}
	
	public abstract void testAlgorithm();
}
