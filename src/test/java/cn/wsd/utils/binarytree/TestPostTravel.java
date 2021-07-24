package cn.wsd.utils.binarytree;

import java.util.*;
import java.util.stream.Collectors;

public class TestPostTravel {
	public List<Integer> postTraverse(TreeNode root) {
		List<Integer> list=new ArrayList<>();
		Stack<TreeNode> stack=new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode node = stack.pop();
			list.add(node.val);
			if(node.left!=null){
				stack.push(node.left);
			}
			if(node.right!=null){
				stack.push(node.right);
			}
		}
		Collections.reverse(list);
	   	return list;
	}

	public static void main(String[] args) {
		Integer[] input = {5, 4, 7, 0, 3, 6, null, null, null, 1, 2};
		TreeNode[] nodes = new TreeNode[input.length];
		for (int i = input.length - 1; i >= 0; --i) {
			if (input[i] != null) {
				nodes[i] = new TreeNode(input[i]);
				if (2 * i + 1 < input.length) {
					nodes[i].left = nodes[2 * i + 1];
				}
				if (2 * i + 2 < input.length) {
					nodes[i].right = nodes[2 * i + 2];
				}
			}
		}
		TestPostTravel test = new TestPostTravel();
		List<Integer> result = test.postTraverse(nodes[0]);
		System.out.println(result.stream().map(Objects::toString).collect(Collectors.joining(",")));
	}
}
