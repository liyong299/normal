/**
 * 项目名称：java
 * 文件包名：com.ly.java.tree
 * 文件名称：TreeNode.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月24日 上午11:48:53
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.tree;

import java.util.Map;
import java.util.Objects;

/**
 * @功能描述：树的节点
 * @文件名称：TreeNode.java
 * @author ly
 */
public class TreeNode<K, V> {

	private int position;
	private boolean isLeaf;
	private boolean isRoot;
	private TreeNode<K, V> parent;
	private TreeNode<K, V> leftNode;
	private TreeNode<K, V> rightNode;

	private Entry<K, V> content;

	public void setContent(K key, V value) {
		content = new Entry<>(key, value);
	}

	public static class Entry<K, V> {
		private K key;
		private V value;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		/**
		 * @return the key
		 */
		public K getKey() {
			return key;
		}

		/**
		 * @param key the key to set
		 */
		public void setKey(K key) {
			this.key = key;
		}

		/**
		 * @return the value
		 */
		public V getValue() {
			return value;
		}

		/**
		 * @param value the value to set
		 */
		public void setValue(V value) {
			this.value = value;
		}


		public final boolean equals(Object o) {
			if (!(o instanceof Map.Entry))
				return false;
			Entry e = (Entry) o;
			Object k1 = getKey();
			Object k2 = e.getKey();
			if (k1 == k2 || (k1 != null && k1.equals(k2))) {
				Object v1 = getValue();
				Object v2 = e.getValue();
				if (v1 == v2 || (v1 != null && v1.equals(v2)))
					return true;
			}
			return false;
		}

		public final int hashCode() {
			return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
		}

		public final String toString() {
			return getKey() + "=" + getValue();
		}
	}

	/**
	 * @return the isLeaf
	 */
	public boolean isLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf the isLeaf to set
	 */
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * @return the isRoot
	 */
	public boolean isRoot() {
		return isRoot;
	}

	/**
	 * @param isRoot the isRoot to set
	 */
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	/**
	 * @return the leftNode
	 */
	public TreeNode<K, V> getLeftNode() {
		return leftNode;
	}

	/**
	 * @param leftNode the leftNode to set
	 */
	public void setLeftNode(TreeNode<K, V> leftNode) {
		this.leftNode = leftNode;
	}

	/**
	 * @return the rightNode
	 */
	public TreeNode<K, V> getRightNode() {
		return rightNode;
	}

	/**
	 * @param rightNode the rightNode to set
	 */
	public void setRightNode(TreeNode<K, V> rightNode) {
		this.rightNode = rightNode;
	}

	/**
	 * @return the content
	 */
	public Entry<K, V> getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(Entry<K, V> content) {
		this.content = content;
	}

	/**
	 * @return the parent
	 */
	public TreeNode<K, V> getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(TreeNode<K, V> parent) {
		this.parent = parent;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
}
