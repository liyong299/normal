package com.ly.java.tree;

public class TreeTest {

	public static void main(String[] args) {
		TreeTest.createTree();
	}

	public static void createTree() {
		CompleteBinaryTree tree = new CompleteBinaryTree<>();
		for (int i = 0; i < 10; i++) {
			tree.addTreeNode(createTreeNode(i));
		}
		tree.print();
		System.out.println("--------1--------");
		TreeNode[] nodes = tree.inorderTraversal();
		System.out.println("--------3--------");
		tree.print(nodes, nodes.length);
		nodes = tree.preorderTraversal();
		System.out.println("--------4--------");
		tree.print(nodes, nodes.length);
		nodes = tree.suforderTraversal();
		System.out.println("--------5--------");
		tree.print(nodes, nodes.length);
	}

	public static TreeNode createTreeNode(int idx) {
		TreeNode node = new TreeNode();
		node.setContent("user", "liyong" + idx);
		return node;
	}
}
