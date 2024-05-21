package main.java.com.github.graduateworkknapsack.algorithms;

import main.java.com.github.graduateworkknapsack.util.Constant;

public class AlgorithmTSPDynProg extends AlgorithmTSP {
	private float[][] dp;
	private int[][] parent;
	
	public AlgorithmTSPDynProg(int startNode, float[][] distance) {
		super(startNode, distance);
		this.dp = new float[1 << this.nodesAmount][this.nodesAmount];
        this.parent = new int[1 << this.nodesAmount][this.nodesAmount];
	}
	
    public void run() {
        // Инициализация таблицы
        for (int i = 0; i < (1 << this.nodesAmount); i++) {
            for (int j = 0; j < this.nodesAmount; j++) {
            	this.dp[i][j] = Constant.INFINITY;
            }
        }

        // Задание начальной вершины
        this.dp[1 << this.startNode][this.startNode] = 0;

        // Заполнение таблицы
        for (int mask = 1; mask < (1 << this.nodesAmount); mask++) {
            for (int i = 0; i < this.nodesAmount; i++) {
                if ((mask & (1 << i)) !=0) {
                    for (int j = 0; j < this.nodesAmount; j++) {
                        if (i != j && (mask & (1 << j)) != 0) {
                            float newCost = this.dp[mask ^ (1 << i)][j] + this.distance[j][i];
                            if (newCost < this.dp[mask][i]) {
                            	this.dp[mask][i] = newCost;
                            	this.parent[mask][i] = j;
                            }
                        }
                    }
                }
            }
        }
        
        // Поиск оптимального маршрута
        int finalMask = (1 << this.nodesAmount) -1;
        for (int i = 0; i < this.nodesAmount; i++) {
            if (i != this.startNode && this.dp[finalMask][i] + this.distance[i][this.startNode] < this.minTourCost) {
            	this.minTourCost = this.dp[finalMask][i] + this.distance[i][this.startNode];
            	this.minTour.clear();
                // this.minTour.add(startNode);
            	this.reconstructPath(finalMask, i);
            	this.minTour.add(this.startNode);
            }
        }
    }

    private void reconstructPath(int mask, int current) {
        int stack[] = new int[this.nodesAmount];
        int top = 0;
        stack[top] = current;
        while (mask != (1 << this.startNode)) {
            int prev = this.parent[mask][current];
            stack[++top] = prev;
            mask ^= (1 << current);
            current = prev;
        }
        while (top >= 0) {
        	this.minTour.add(stack[top--]);
        }
    }
}
