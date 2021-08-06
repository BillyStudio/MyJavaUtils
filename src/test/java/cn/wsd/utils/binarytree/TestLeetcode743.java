package cn.wsd.utils.binarytree;

import java.util.Arrays;

import cn.wsd.utils.graph.Dijkstra;

public class TestLeetcode743 {

	public static int networkDelayTime(int[][] times, int n, int k, Dijkstra test) {
		int[][] graph = new int[n][n];
		// init graph
		for(int i=0; i<n; ++i) {
			Arrays.fill(graph[i], -1);
			graph[i][i] = 0;
		}
		for (int[] item : times) {
			graph[item[0]-1][item[1]-1] = item[2];
		}
		int[] dist = test.findShortestPath(graph, n, k-1);
		int maxTime = -1;
		for (int time : dist) {
			if (time == Integer.MAX_VALUE) return -1;
			if (time > maxTime) maxTime = time;
		}
		return maxTime;
	}

	public static void main(String[] args) {
		int[][] times = {
				{3,5,78},
				{2,1,1},
				{1,3,0},
				{4,3,59},
				{5,3,85},
				{5,2,22},
				{2,4,23},
				{1,4,43},
				{4,5,75},
				{5,1,15},
				{1,5,91},
				{4,1,16},
				{3,2,98},
				{3,4,22},
				{5,4,31},
				{1,2,0},
				{2,5,4},
				{4,2,51},{3,1,36},{2,3,59}};
		Dijkstra test = new Dijkstra();
		System.out.println(networkDelayTime(times, 5, 5, test));
	}
}
