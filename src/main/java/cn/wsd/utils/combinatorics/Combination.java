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
				result.add(new ArrayList<>(seq));
			return ;
		}
		for (int i=start; i<=nums.length-remain; ++i) {
			seq.add(nums[i]);
			visit(nums, used, start+1, remain-1, seq, result);
			seq.remove(seq.size()-1);
		}
	}
}
