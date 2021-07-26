package cn.wsd.utils.binarytree;

import java.util.*;
import java.util.stream.Collectors;

public class TestPostTravel {
	public List<Integer> postTraverse(TreeNode<Integer> root) {
		List<Integer> list=new ArrayList<>();
		Stack<TreeNode<Integer>> stack=new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode<Integer> node = stack.pop();
			list.add(node.getValue());
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
		Integer[] input = {1, null, 2, 3};
		TreeGenerator<Integer> treeGenerator = new TreeGenerator<>(input);
		TestPostTravel test = new TestPostTravel();
		List<Integer> result = test.postTraverse(treeGenerator.getRootNode());
		System.out.println(result.stream().map(Objects::toString).collect(Collectors.joining(",")));
	}
}
