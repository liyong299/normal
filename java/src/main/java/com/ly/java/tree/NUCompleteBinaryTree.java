/**
 * 项目名称：java
 * 文件包名：com.ly.java.tree
 * 文件名称：Tree.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年4月1日 上午10:00:07
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @功能描述：树对象。树的域变量，和一些其他操作：创建树，扩展树，查看树等
 * @文件名称：Tree.java
 * @author ly
 */
public class NUCompleteBinaryTree<K, V> {

	private int count;
	private TreeNode<K, V> root;
	private TreeNode<K, V>[] nodes;
	private final static int DEFAULT_SIZE = 16;

	public NUCompleteBinaryTree() {
		this.count = 0;
		nodes = new TreeNode[DEFAULT_SIZE];
	}

	public NUCompleteBinaryTree(TreeNode<K, V> root) {
		this(root, DEFAULT_SIZE);
	}

	public NUCompleteBinaryTree(TreeNode<K, V> root, int size) {
		nodes = new TreeNode[size];
		this.root = root;
		nodes[0] = root;
		this.count = 1;
	}

	private ReentrantLock lock = new ReentrantLock();

	public void addTreeNode(TreeNode<K, V> node) {
		lock.lock();
		if (count < nodes.length) {
			if (count > 0) {
				TreeNode<K, V> parent = getParent(count);
				if ((count & 1) == 0) {
					parent.setRightNode(node);
				} else {
					parent.setLeftNode(node);
				}
				parent.setLeaf(false);
				node.setParent(parent);
			} else {
				this.root = node;
			}
			this.nodes[count] = node;
			node.setPosition(count);
			count++;
		}
		lock.unlock();
	}

	public void print() {
		print(nodes, count);
	}

	public void print(TreeNode[] nodes, int count) {
		for (int i = 0; i < count; i++) {
			System.out.print(nodes[i].getContent() + "   ");
			if (i % 10 == 9) {
				System.out.println();
			}
		}
	}
	public TreeNode<K, V> getParent(int position) {
		if (position < 1) {
			return null;
		}
		int parentPosition = position / 2;
		return this.nodes[parentPosition];
	}

	public boolean delTreeNode(TreeNode<K, V> node) {
		return true;
	}
	
	public TreeNode<K, V>[] inorderTraversal() {
		List<TreeNode<K, V>> list = new LinkedList<TreeNode<K, V>>();
		inorderTraversal(root, list);
		TreeNode[] tmp = new TreeNode[list.size()];
		return list.toArray(tmp);
	}

	private void inorderTraversal(TreeNode node, List<TreeNode<K, V>> list) {
		if (node != null) {
			inorderTraversal(node.getLeftNode(), list);
			list.add(node);
			inorderTraversal(node.getRightNode(), list);
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
