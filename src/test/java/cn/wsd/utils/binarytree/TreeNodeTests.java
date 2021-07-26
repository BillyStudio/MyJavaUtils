package cn.wsd.utils.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;


class TreeNodeTests {
	// 左、右、根
	public List<Integer> postTraverse(TreeNode root) {
		List<Integer> result = new LinkedList<>();
		if(root == null) {
			return result;
		}
		Stack<TreeNode> stack = new Stack<>();
		TreeNode currentNode = root;
		stack.push(currentNode);
		TreeNode previousNode = null;
		while(!stack.isEmpty()) {
			previousNode = currentNode;
			currentNode = stack.pop();
			/* 两种情况： 1. 左右子树均未访问；2.左子树访问完，右子树没访问；3. 左右子树均已访问
			对第1种情况，currentNode在下，previousNode在上
			对第2种情况，previousNode在下，为currentNode的左子树
			对第3种情况，previousNode在下，为currentNode的右子树
			 */
			if(currentNode.left != previousNode && currentNode.right != previousNode) {
				// 第1种情况，开始不断访问左子树，直至叶子节点
				while(currentNode.left != null) {
					stack.push(currentNode);
					previousNode = currentNode;
					currentNode = currentNode.left;
				}
				// 此时currentNode即左节点
			} else if (currentNode.right == previousNode){ // 第3种情况
				result.add((Integer) currentNode.getValue());
				continue;
			}
			/* 两种情况： 1.currentNode为左节点，存在还没访问的右子树； 2.左子树访问完，无右子树,currentNode均不在栈中
			对第1种情况，previousNode在上，currentNode在下，previousNode.left = currentNode
			对第2种情况，currentNode.left = previousNode
			 */
			if(currentNode.right != null && currentNode.right != previousNode) { // 右子树存在且还没访问
				stack.push(currentNode);
				// 右子树的根节点压栈
				stack.push(currentNode.right);
			} else { // 左右子树均已访问
				result.add((Integer) currentNode.getValue());
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Integer[] input = {5, 4, 7, 0, 3, 6, null, null, null, 1, 2};
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
		List<Integer> result = test.postTraverse(nodes[0]);
		System.out.println(result.stream().map(Objects::toString).collect(Collectors.joining(",")));
	}
}
