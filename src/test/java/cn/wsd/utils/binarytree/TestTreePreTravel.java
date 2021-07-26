package cn.wsd.utils.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;


public class TestTreePreTravel {

	/*
	前序遍历的非递归写法
	 */
	public List<Integer> preTravel1(TreeNode<Integer> root) {
		List<Integer> result = new LinkedList<>();
		if (root == null) {
			return result;
		}
		result.add(root.getValue());
		result.addAll(preTravel1(root.left));
		result.addAll(preTravel1(root.right));
		return result;
	}

	/*
	前序遍历的非递归写法
	 */
	public List<Integer> preTravel2(TreeNode<Integer> root) {
		List<Integer> result = new LinkedList<>();
		if (root == null) {
			return result;
		}
		Stack<TreeNode<Integer>> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode<Integer> node = stack.pop();
			result.add(node.getValue());
			// 先压右子树入栈
			if (node.right != null) {
				stack.push(node.right);
			}
			// 后压左子树，这样左子树就能先出了
			if (node.left != null) {
				stack.push(node.left);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Integer[] inputs = new Integer[]{5,4,6,0,3,null,7,null,null,1,2};
		TreeGenerator<Integer> treeGenerator = new TreeGenerator<>(inputs);
		TestTreePreTravel obj = new TestTreePreTravel();
		List<Integer> result = obj.preTravel2(treeGenerator.getRootNode());
		System.out.println(result.stream().map(Objects::toString).collect(Collectors.joining(",")));
		System.out.println("5,4,0,3,1,2,6,7");
	}
}
