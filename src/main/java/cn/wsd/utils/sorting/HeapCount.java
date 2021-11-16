package cn.wsd.utils.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import cn.wsd.utils.Pair;

/**
 * 统计出现频率最高的3个数
 */
public class HeapCount {

	long[] countTop3(long[] nums) {
		if (nums.length < 3) {
			return new long[0];
		}
		HashMap<Long, Integer> map = new HashMap<>();
		for (long num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<Pair<Long, Integer>>(
				(p1, p2) -> p2.second() - p1.second()
		);
		for (long key : map.keySet()) {
			pq.offer(new Pair<>(key, map.get(key)));
		}
		List<Long> top3 = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			top3.add(pq.poll().first());
		}
		return top3.stream().mapToLong(Long::longValue).toArray();
	}

	public static void main(String[] args) {
		long[] input = new long[]{4,2,2,2,1,1,5,5,3,3,5,5};
		HeapCount test = new HeapCount();
		long[] top3 = test.countTop3(input);
		System.out.println(Arrays.stream(top3).mapToObj(Objects::toString).collect(Collectors.joining(",")));
	}
}
