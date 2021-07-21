package edu.ustb.utils.binarytree;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTests {
	// 左、右、根
	public void postTraverse(TreeNode root) {
		if(root == null) {
			return ;
		}
		Stack<TreeNode> path = new Stack<>();
		TreeNode currentNode = root;
		path.push(currentNode);
		TreeNode previousNode = null;
		while(!path.isEmpty()) {
			previousNode = currentNode;
			currentNode = path.pop();
			if(currentNode.left != previousNode && currentNode.right != previousNode) {
				while(currentNode != null) {
					path.push(currentNode);
					previousNode = currentNode;
					currentNode = currentNode.left;
				}
				previousNode = currentNode;
				currentNode = path.peek();
			}
			if(currentNode.right == null || currentNode.right == previousNode) { // 右子树为空或访问完右子树了
				// 打印根节点
				print(currentNode);
				path.pop();
				continue;
			}
			if(currentNode.right != null) { // 右子树存在且还没访问
				// 右子树的根节点压栈
				path.push(currentNode.right);
			}
		}
		return ;
	}

	void print(TreeNode node) {
		System.out.println(node.value);
	}

	public static void main(String[] args) {
		Integer[] input = {5, 4, 7, 0, 3, 6, null, null, 1, 2};
		TreeNode[] nodes = new TreeNode[input.length];
		for(int i = input.length-1; i >= 0; --i) {
			if(input[i] != null) {
				nodes[i] = new TreeNode(input[i]);
				if(2*i+1 < input.length) {
					nodes[i].left = nodes[2*i+1];
				}
				if(2*i+2 < input.length) {
					nodes[i].right = nodes[2*i+2];
				}
			}
		}
		TreeNodeTests test = new TreeNodeTests();
		test.postTraverse(nodes[0]);
	}
}
