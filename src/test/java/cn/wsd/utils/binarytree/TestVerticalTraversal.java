package cn.wsd.utils.binarytree;

import java.util.*;
import java.util.stream.*;

public class TestVerticalTraversal {
	public List<List<Integer>> verticalTraversal(TreeNode<Integer> root) {
		LinkedHashMap<Integer, LinkedHashMap<Integer, List<Integer>>> grid = new LinkedHashMap<>();
		visitNode(root, grid, 0, 0);
		List<List<Integer>> result = grid.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(entry -> {
					LinkedHashMap<Integer, List<Integer>> row2Check = entry.getValue();
					List<List<Integer>> listOflist = row2Check.entrySet().stream()
							.sorted((entry1, entry2) -> entry1.getKey() - entry2.getKey())
							.map(item -> {
								List<Integer> in1Check = item.getValue();
								in1Check.sort(Comparator.comparingInt(o -> o));
								return in1Check;
							})
							.collect(Collectors.toList());
					List<Integer> singleCol = new ArrayList<>();
					listOflist.stream().forEach(l -> singleCol.addAll(l));
					return singleCol;
				})
				.collect(Collectors.toList());
		return result;
	}

	void visitNode(TreeNode<Integer> node, LinkedHashMap<Integer, LinkedHashMap<Integer, List<Integer>>> grid, int row, int col) {
		if (node == null) {
			return;
		}
		LinkedHashMap<Integer, List<Integer>> vert = grid.getOrDefault(col, new LinkedHashMap<Integer, List<Integer>>());
		List<Integer> check = vert.getOrDefault(row, new ArrayList<Integer>());
		check.add(node.getValue());
		vert.put(row, check);
		grid.put(col, vert);
		visitNode(node.left, grid, row+1, col-1);
		visitNode(node.right, grid, row+1, col+1);
	}

	public static void main(String[] args) {
		TestVerticalTraversal test = new TestVerticalTraversal();
		TreeGenerator<Integer> treeGenerator = new TreeGenerator<>(new Integer[]{1,2,3,4,5,6,7});
		List<List<Integer>> result = test.verticalTraversal(treeGenerator.getRootNode());
		Stream<String> stream = result.stream().map(list -> {
			String linkedStr = list.stream().map(Objects::toString).collect(Collectors.joining(","));
			linkedStr = "[" + linkedStr + "]";
			return linkedStr;
		});
		String output = stream.map(Objects::toString).collect(Collectors.joining(","));
		System.out.println(output);
	}
}
