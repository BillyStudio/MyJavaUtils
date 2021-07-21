package edu.ustb.utils.binarytree;

public class TreeNode {
	int value;

	public TreeNode left;
	public TreeNode right;

	public TreeNode(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TreeNode{" +
				"value=" + value +
				'}';
	}
}
