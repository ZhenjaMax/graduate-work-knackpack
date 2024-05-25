package main.java.com.github.graduateworkknapsack.elements;

import java.util.ArrayList;
import java.util.Collections;

import main.java.com.github.graduateworkknapsack.util.Constant;

public class PathDataBnB implements Comparable<PathDataBnB> {
	public float[][] adjacencyMatrix;
	public ArrayList<ArrayList<Integer>> currentPath;
	public int baseCost;
	
	public float maxPenalty = -1;
	public int penaltyI = 0;
	public int penaltyJ = 0;
	
	private int nodesAmount;

	public void reduce() {
		for (int i = 0; i < this.nodesAmount; i++) {
            float min = Constant.INFINITY;
            for (int j = 0; j < this.nodesAmount; j++) {
                if (this.adjacencyMatrix[i][j] < min && i != j) {
                    min = this.adjacencyMatrix[i][j];
                }
            }
            if(min != Constant.INFINITY) {
                this.baseCost += min;
                for (int j = 0; j < this.nodesAmount; j++) {
                    if (i != j && this.adjacencyMatrix[i][j] != Constant.INFINITY) {
                        this.adjacencyMatrix[i][j] -= min;
                    }
                }
            }
        }
        
        for (int i = 0; i < this.nodesAmount; i++) {
            float min = Constant.INFINITY;
            for (int j = 0; j < this.nodesAmount; j++) {
                if (this.adjacencyMatrix[j][i] < min && i != j) {
                    min = this.adjacencyMatrix[j][i];
                }
            }
            if(min != Constant.INFINITY) {
            	this.baseCost += min;
                for (int j = 0; j < this.nodesAmount; j++) {
                    if (i != j && this.adjacencyMatrix[j][i] != Constant.INFINITY) {
                        this.adjacencyMatrix[j][i] -= min;
                    }
                }
            }
        }
	}
	
	public void setPenalty() {
		maxPenalty = -1;
		penaltyI = -1;
		penaltyJ = -1;
		
		for(int i = 0; i < this.nodesAmount; i++) {
			for(int j = 0; j < this.nodesAmount; j++) {
				if(this.adjacencyMatrix[i][j] != 0) {
					continue;
				}
				
				float penalty = 0;
				float min = Constant.INFINITY;
				for(int k = 0; k < this.nodesAmount; k++) {
					if(k == i) {
						continue;
					} else if(this.adjacencyMatrix[k][j] == 0) {
						min = 0;
						break;
					} else if(this.adjacencyMatrix[k][j] < min) {
						min = this.adjacencyMatrix[k][j];
					}
				}

				penalty += min;
				min = Constant.INFINITY;
				for(int k = 0; k < this.nodesAmount; k++) {
					if(k == j) {
						continue;
					} else if(this.adjacencyMatrix[i][k] == 0) {
						min = 0;
						break;
					} else if(this.adjacencyMatrix[i][k] < min) {
						min = this.adjacencyMatrix[i][k];
					}
				}
				penalty += min;
				
				if(penalty > maxPenalty) {
					maxPenalty = penalty;
					penaltyI = i;
					penaltyJ = j;
				}
			}
		}
	}
	
	// no cycles
	public boolean isPathFull(int startNode) {
		ArrayList<Integer> restoredPath = this.restorePath(startNode);
		restoredPath.remove(0);
		for(int i = 0; i < this.nodesAmount; i++) {
			if(Collections.frequency(restoredPath, i) != 1) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<Integer> restorePath(int startNode) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		path.add(startNode);
		int currentNode = startNode;
		int size = this.currentPath.size();
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(this.currentPath.get(j).get(0) == currentNode) {
					currentNode = this.currentPath.get(j).get(1);
					path.add(currentNode);
					break;
				}
			}
		}
		
		return path;
	}
	
	public void setPathEdge(int indexI, int indexJ) {
		for(int k = 0; k < this.nodesAmount; k++) {
			this.adjacencyMatrix[indexI][k] = Constant.INFINITY;
			this.adjacencyMatrix[k][indexJ] = Constant.INFINITY;
		}
		this.adjacencyMatrix[indexJ][indexI] = Constant.INFINITY;
		
		ArrayList<Integer> edge = new ArrayList<Integer>();
		edge.add(indexI);
		edge.add(indexJ);
		this.currentPath.add(edge);
		
		for(int i = 0; i < this.currentPath.size()-1; i++) {
			ArrayList<Integer> oldEdge = this.currentPath.get(i);
			if(oldEdge.get(1) == indexI) {
				this.adjacencyMatrix[indexJ][oldEdge.get(0)] = Constant.INFINITY;
			}
			if(oldEdge.get(0) == indexJ) {
				this.adjacencyMatrix[oldEdge.get(1)][indexI] = Constant.INFINITY;
			}
		}
	}
	
	public PathDataBnB(float[][] adjacencyMatrix) {
		this.nodesAmount = adjacencyMatrix.length;
		this.adjacencyMatrix = new float[this.nodesAmount][this.nodesAmount];
		for(int i = 0; i < this.nodesAmount; i++) {
			this.adjacencyMatrix[i] = adjacencyMatrix[i].clone();
		}
		this.baseCost = 0;
		this.currentPath = new ArrayList<ArrayList<Integer>>();
	}
	
	public PathDataBnB(float[][] adjacencyMatrix, ArrayList<ArrayList<Integer>> currentPath, int baseCost) {
		this(adjacencyMatrix);
		this.baseCost = baseCost;
		for(ArrayList<Integer> list: currentPath) {
			this.currentPath.add(new ArrayList<Integer>(list));
		}
	}

	public PathDataBnB clone() {
		return new PathDataBnB(this.adjacencyMatrix, this.currentPath, this.baseCost);
	}
	
	@Override
	public int compareTo(PathDataBnB o) {
		if(this.baseCost != o.baseCost) {
			return this.baseCost - o.baseCost;
		}
		if(this.currentPath.size() != o.currentPath.size()) {
			return this.currentPath.size() - o.currentPath.size();
		}
		if(this != o) {
			return -1;
		}
		return 0;
	}
}
