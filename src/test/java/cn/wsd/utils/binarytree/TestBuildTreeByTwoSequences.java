package cn.wsd.utils.binarytree;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TestBuildTreeByTwoSequences {

	// 前提：nodeVal之间互不相等
	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		if (pre == null) {
			return null;
		}
		return buildTreeByPreAndInOrder(Arrays.stream(pre).boxed().collect(Collectors.toList()),
				Arrays.stream(in).boxed().collect(Collectors.toList()));
	}

	public TreeNode buildTreeByPreAndInOrder(List<Integer> preOrder, List<Integer> inOrder) {
		if (preOrder.isEmpty()) {
			return null;
		}
		TreeNode root = new TreeNode(preOrder.get(0));
		if (preOrder.size() == 1) {
			return root;
		}
		int leftSize = 0;
		for (int i = 0; i < inOrder.size(); ++i) {
			if (root.getValue().equals(inOrder.get(i))) {
				leftSize = i;
				break;
			}
		}
		root.left = buildTreeByPreAndInOrder(preOrder.subList(1, 1+leftSize), inOrder.subList(0, leftSize));
		root.right = buildTreeByPreAndInOrder(preOrder.subList(1+leftSize, preOrder.size()), inOrder.subList(1+leftSize, inOrder.size()));
		return root;
	}

	public static void main(String[] args) {
		int[] pre = new int[]{5,4,0,3,1,2,6,7};
		int[] mid = new int[]{0,4,1,3,2,5,6,7};
		TestBuildTreeByTwoSequences test = new TestBuildTreeByTwoSequences();
		TreeNode<Integer> root = test.reConstructBinaryTree(pre, mid);
		System.out.println(root.printAsTree().stream().map(Objects::toString).collect(Collectors.joining(",")));
	}
}
