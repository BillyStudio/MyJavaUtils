package cn.wsd.utils.combinatorics;

import java.util.ArrayList;
import java.util.List;

public class Combination {

	public List<List<Integer>> traverse(int[] nums, int k) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> seq = new ArrayList<>();
		boolean[] used = new boolean[nums.length];
		visit(nums, used, 0, k, seq, result);
		return result;
	}

	void visit(int[] nums, boolean[] used, int start, int remain, List<Integer> seq, List<List<Integer>> result) {
		if (remain == 0) {
			if (seq.get(0) + seq.get(1) > seq.get(2)
					&& seq.get(0) + seq.get(2) > seq.get(1)
					&& seq.get(1) + seq.get(2) > seq.get(0)) {
				result.add(new ArrayList<>(seq));
			}
			return ;
		}
		for (int i=start; i<=nums.length-remain; ++i) {
			seq.add(nums[i]);
			visit(nums, used, i+1, remain-1, seq, result);
			seq.remove(seq.size()-1);
		}
	}

	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> combinations = new ArrayList<>();
		List<Integer> combineList = new ArrayList<>();
		backtracking(combineList, combinations, 1, k, n);
		return combinations;
	}

	private void backtracking(List<Integer> combineList, List<List<Integer>> combinations, int start, int k, final int n) {
		if (k == 0) {
			combinations.add(new ArrayList<>(combineList));
			return;
		}
		for (int i = start; i <= n - k + 1; i++) {  // 剪枝
			combineList.add(i);
			backtracking(combineList, combinations, i + 1, k - 1, n);
			combineList.remove(combineList.size() - 1);
		}
	}
}
