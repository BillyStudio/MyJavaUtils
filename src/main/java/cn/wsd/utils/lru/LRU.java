package cn.wsd.utils.lru;

import java.util.*;

public class LRU {
	class Node {
		int key;
		int val;
	}

	public LinkedList<Node> data;

	public int[] testLRU(int[][] operators, int k) {
		List<Integer> result = new LinkedList<>();
		result.add(1);
		result.add(2);
		result.add(3);
		result.remove(0);
		return result.stream().mapToInt(Integer::intValue).toArray();
	}
}
