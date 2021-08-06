package cn.wsd.utils.graph;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {
	class Pair {
		public int target;	// end node
		public int path;
		public Pair(int t, int p) {
			target = t; path = p;
		}
	}

	// graph is a n*n matrix
	public int[] findShortestPath(int[][] graph, int n, int k) {
		int[] dist = new int[n];	// distances from source to targets
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[k] = 0;
		Queue<Pair> priorityQueue = new PriorityQueue<>((t1,t2) -> t1.path-t2.path);
		boolean[] used = new boolean[n];
		used[k] = true;
		priorityQueue.offer(new Pair(k, 0));
		while (! priorityQueue.isEmpty()) {
			Pair arrow = priorityQueue.poll();
			for(int i = 0; i < n; ++i) { // travel all adjacents
				if (! used[i] && graph[arrow.target][i] >= 0) {
					if (dist[arrow.target] + graph[arrow.target][i] < dist[i]) {
						dist[i] = dist[arrow.target] + graph[arrow.target][i];
					}
				}
			}
			// find next point
			int nextPoint = -1;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i<n; ++i) {
				if (!used[i] && dist[i] < min) {
					min = dist[i];
					nextPoint = i;
				}
			}
			if (nextPoint >= 0) {
				priorityQueue.offer(new Pair(nextPoint, dist[nextPoint]));
				used[nextPoint] = true;
			}
		}
		return dist;
	}
}
