package cn.wsd.utils.combinatorics;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class CombinationTests {

	public static void main(String[] args) {
		Combination test = new Combination();
		List<List<Integer>> result = test.traverse(new int[]{1,2,3,4,5}, 3);
		for (List<Integer> list : result) {
			System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(",")));
		}
	}
}
