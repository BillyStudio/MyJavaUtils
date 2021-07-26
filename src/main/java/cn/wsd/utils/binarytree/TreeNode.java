package cn.wsd.utils.binarytree;

public class TreeNode<T> {
	private T val;

	public TreeNode<T> left;
	public TreeNode<T> right;

	public TreeNode(T value) {
		this.val = value;
	}

	public T getValue() {
		return val;
	}

	@Override
	public String toString() {
		return "TreeNode{" +
				"value=" + val +
				'}';
	}
}
