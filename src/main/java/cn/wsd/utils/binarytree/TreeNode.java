package cn.wsd.utils.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

	public List<String> printAsTree() {
		List<String> outList = new LinkedList<>();
		Queue<TreeNode<T>> queue = new LinkedList<>();
		TreeNode<T> node = this;
		queue.offer(node);
		while(! queue.isEmpty()) {
			TreeNode<T> obj = queue.poll();
			if (obj != null) {
				outList.add(obj.val == null ? "null" : obj.val.toString());
				queue.offer(obj.left);
				queue.offer(obj.right);
			} else {
				outList.add("null");
			}
		}
		for (int i=outList.size()-1; outList.get(i).equals("null"); --i) {
			outList.remove(i);
		}
		return outList;
	}

	@Override
	public String toString() {
		return "TreeNode{" +
				"value=" + val +
				'}';
	}
}
