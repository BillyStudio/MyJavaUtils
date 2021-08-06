package cn.wsd.utils.backtracking;

import java.util.List;

public class TestNQueens {

	public static void main(String[] args) {
		NQueens nQueens = new NQueens();
		List<List<String>> result = nQueens.solveNQueens(4);
		result.stream().forEach(list -> {
			System.out.printf("[");
			for (String str:list) {
				System.out.println(str);
			}
			System.out.println("]");
		});
	}
}
