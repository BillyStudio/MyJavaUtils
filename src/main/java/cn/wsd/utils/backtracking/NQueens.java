package cn.wsd.utils.backtracking;

import java.util.*;

public class NQueens {
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> result = new ArrayList<>();
		int[] queens = new int[n];	// 皇后在每一行的位置
		Arrays.fill(queens, -1);
		Set<Integer> columns = new HashSet<>(); // 皇后占用的列位置
		Set<Integer> diagnose1 = new HashSet<>(); // 皇后占用的左斜对角
		Set<Integer> diagnose2 = new HashSet<>(); // 皇后占用的右斜对角
		backtrack(0, queens, result, columns, diagnose1, diagnose2, n);
		return result;
	}

	void backtrack(int row, int[] queens, List<List<String>> result, Set<Integer> columns, Set<Integer> diagnose1, Set<Integer> diagnose2, int n) {
		if(row == n) {
			result.add(drawQueen(queens));
			return ;
		}
		for(int j = 0; j < n; ++j) {
			if(columns.contains(j)) {
				continue;	// 列被占了
			}
			if (diagnose1.contains(row-j)) {
				continue;
			}
			if (diagnose2.contains(row+j)) {
				continue;
			}
			columns.add(j);
			diagnose1.add(row - j);
			diagnose2.add(row + j);
			queens[row] = j;
			backtrack(row+1, queens, result, columns, diagnose1, diagnose2, n);
			queens[row] = -1;
			columns.remove(j);
			diagnose1.remove(row - j);
			diagnose2.remove(row + j);
		}
	}

	List<String> drawQueen(int[] queens) {
		List<String> board = new ArrayList<>();
		for (int i = 0; i < queens.length; i++) {
			char[] row = new char[queens.length];
			Arrays.fill(row, '.');
			row[queens[i]] = 'Q';
			board.add(String.valueOf(row));
		}
		return board;
	}
}
