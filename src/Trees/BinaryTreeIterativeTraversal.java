package Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeIterativeTraversal {
	Node root;

	public static void main(String args[]) {

		/*
		 * creating a binary tree and entering the nodes
		 */
		BinaryTreeIterativeTraversal tree = new BinaryTreeIterativeTraversal();

		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		List<Integer> res = tree.inorder();
		System.out.println("InOrder Traversal");
		res.forEach(item -> System.out.print(item + "   "));
		System.out.println("\nPreOrder Traversal");
		res = tree.preorder();
		res.forEach(item -> System.out.print(item + "   "));
		System.out.println("\nPostOrder Traversal");
		res = tree.postorder();
		res.forEach(item -> System.out.print(item + "   "));

	}

	public List<Integer> inorder() {
		List<Integer> res = new ArrayList<>();
		if (root == null)
			return res;
		Stack<Node> st = new Stack<>();

		Node node = root;
		while (node != null) {
			st.push(node);
			node = node.left;
		}
		while (!st.isEmpty()) {
			node = st.pop();
			res.add(node.data);
			if (node.right != null) {
				node = node.right;
				while (node != null) {
					st.push(node);
					node = node.left;
				}
			}
		}
		return res;
	}

	public List<Integer> preorder() {
		List<Integer> res = new ArrayList<>();
		if (root == null)
			return res;
		Stack<Node> st = new Stack<>();
		st.push(root);
		Node node = root;
		while (!st.isEmpty()) {
			node = st.pop();
			res.add(node.data);
			if (node.right != null)
				st.push(node.right);
			if (node.left != null)
				st.push(node.left);

		}
		return res;
	}

	public List<Integer> postorder() {
		List<Integer> res = new ArrayList<>();
		if (root == null)
			return res;
		Stack<Node> st = new Stack<>();
        st.push(root);
		Node node = null;
		while (!st.isEmpty()) {
			node = st.peek();
			if(node.left ==null && node.right == null) {
				st.pop();
				res.add(node.data);
			} else {
				if(node.right != null) {
					st.push(node.right);
					node.right = null;
				}
				if(node.left != null) {
					st.push(node.left);
					node.left = null;
				}
			}

		}
		return res;
	}

}

class Node {

	int data;
	Node left, right;

	public Node(int item) {
		data = item;
		left = right = null;
	}
}
