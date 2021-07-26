package cn.wsd.utils.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeGenerator<T> {

	TreeNode<T>[] nodes;

	public TreeGenerator(T[] inputArray) {
		if (inputArray == null)
			throw new NullPointerException();
		if (inputArray.length == 0)
			return ;

		nodes = new TreeNode[inputArray.length];
		for (int i = 0; i < inputArray.length; ++i) if (inputArray[i] != null){
			nodes[i] = new TreeNode<T>(inputArray[i]);
		}

		Queue<TreeNode<T>> bfs = new LinkedList<>();
		int i = 0;
		bfs.offer(nodes[i++]);
		while (! bfs.isEmpty() && i < inputArray.length) {
			TreeNode<T> node = bfs.poll();
			node.left = nodes[i++];
			if (node.left != null) {
				bfs.offer(node.left);
			}
			if (i < inputArray.length) {
				node.right = nodes[i++];
				if (node.right != null) {
					bfs.offer(node.right);
				}
			}
		}
	}

	public TreeNode<T> getRootNode() {
		if (nodes.length == 0)
			throw new NullPointerException();
		return nodes[0];
	}
}
