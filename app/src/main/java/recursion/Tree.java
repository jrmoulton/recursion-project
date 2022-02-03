
package recursion;

import java.util.*;

public class Tree<E extends Comparable<? super E>> {
	private BinaryTreeNode root; // Root of tree
	private String name; // Name of tree

	/**
	 * Create an empty tree
	 *
	 * @param label Name of tree
	 */
	public Tree(String label) {
		this.name = label;
	}

	/**
	 * Create BST from ArrayList
	 *
	 * @param arr   List of elements to be added
	 * @param label Name of tree
	 */
	public Tree(ArrayList<E> arr, String label) {
		this.name = label;
		for (E key : arr) {
			insert(key);
		}
	}

	/**
	 * Create BST from Array
	 *
	 * @param arr   List of elements to be added
	 * @param label Name of tree
	 */
	public Tree(E[] arr, String label) {
		this.name = label;
		for (E key : arr) {
			insert(key);
		}
	}

	/**
	 * Return a string containing the tree contents as a tree with one node per line
	 */
	@Override
	public String toString() {
		// TODO:
		var sortedElements = new ArrayList<BinaryTreeNode>();
		furthestLeft(this.root, sortedElements);
		var sb = new StringBuilder();
		sb.append(this.name);
		for (int i = sortedElements.size()-1; i >= 0; i--) {
			var node = sortedElements.get(i);
			var localDepth = getDepthFromParent(this.root, node, 0); 
			sb.append("\n");
			for (int j = 0; j < localDepth; j++) {
				sb.append("  ");
			}
			String parent;
			if (node.parent == null) {
			 parent = "no parent";
			} else {
				parent = node.parent.element.toString();
			}
			String formatString = String.format("%d [%s]", node.element, parent);
			sb.append(formatString);
		}
		return sb.toString();
	}
	private int getDepthFromParent(BinaryTreeNode root, BinaryTreeNode target, int count) {
		if (root.equals(target)) {
			return count;
		}
		int leftCount = 0; 
		int rightCount = 0;

		if (root.left != null) {
			leftCount = getDepthFromParent(root.left, target, count+1);
		}
		if (root.right != null) {
			rightCount = getDepthFromParent(root.right, target, count+1);
		}
		return Math.max(leftCount, rightCount);
	}

	
	public int getDepth() {
		 return recurseDepth(this.root, 1);
	}
	private int recurseDepth(BinaryTreeNode root, int count) {
		if (root.left == null && root.right == null)  {
			 return count;
		}

		int leftCount = 0; 
		int rightCount = 0;

		if (root.left != null) {
			leftCount = recurseDepth(root.left, count + 1);
		}
		if (root.right != null) {
			rightCount = recurseDepth(root.right, count + 1);
		}
		return Math.max(leftCount, rightCount);
	}

	/**
	 * Return a string containing the tree contents as a single line
	 */
	public String inOrderToString() {
		// Done:
		var sortedElements = new ArrayList<BinaryTreeNode>();
		var sb = new StringBuilder();
		sb.append(this.name);
		sb.append(": ");
		furthestLeft(root, sortedElements);
		for (var node : sortedElements) {
			sb.append(node.element.toString());
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	private void furthestLeft(BinaryTreeNode node, ArrayList<BinaryTreeNode> sortedElements) {
		if (node == null) {
			return;
		}
		furthestLeft(node.left, sortedElements);
		sortedElements.add(node);
		furthestLeft(node.right, sortedElements);
	}

	/**
	 * reverse left and right children recursively
	 */
	public void flip() {
		// Done:
		swapRecurse(this.root);
	}

	private void swapRecurse(BinaryTreeNode node) {
		if (node == null) {
			return;
		}
		var temp = node.left;
		node.left = node.right;
		node.right = temp;
		swapRecurse(node.left);
		swapRecurse(node.right);
	}

	/**
	 * Returns the in-order successor of the specified node
	 * 
	 * @param node node from which to find the in-order successor
	 */
	public BinaryTreeNode inOrderSuccessor(BinaryTreeNode node) {
		// TODO:
		return null;
	}

	/**
	 * Counts number of nodes in specified level
	 *
	 * @param level Level in tree, root is zero
	 * @return count of number of nodes at specified level
	 */
	public int nodesInLevel(int level) {
		// Done:
		return getLevelCount(0, level, this.root);

	}
	private int getLevelCount(int currLevel, int targetLevel, BinaryTreeNode node) {
		if (node == null) {
			return 0;
		}
		if (currLevel == targetLevel) {
			return 1;
		}
		return getLevelCount(currLevel + 1, targetLevel, node.left)
				+ getLevelCount(currLevel + 1, targetLevel, node.right);
	}

	/**
	 * Print all paths from root to leaves
	 */
	public void printAllPaths() {
		// TODO:
	}

	/**
	 * Counts all non-null binary search trees embedded in tree
	 *
	 * @return Count of embedded binary search trees
	 */
	public int countBST() {
		// TODO:
		return 0;
	}

	/**
	 * Insert into a bst tree; duplicates are allowed
	 *
	 * @param x the item to insert.
	 */
	public void insert(E x) {
		root = insert(x, root, null);
	}

	public BinaryTreeNode getByKey(E key) {
		// TODO:
		return findKey(key, this.root);
	}

	private BinaryTreeNode findKey(E key, BinaryTreeNode node) {
		if (node.element.equals(key)) {
			return node;
		}
		if (key.compareTo(node.element) < 0) {
			return findKey(key, node.left);
		} else {
			return findKey(key, node.right);
		}
	}

	/**
	 * Balance the tree
	 */
	public void balanceTree() {
		// Done
		var extractedElements = new ArrayList<BinaryTreeNode>();
		furthestLeft(this.root, extractedElements);
		this.root = new BinaryTreeNode(extractedElements.get((extractedElements.size() / 2)).element);
		insertFromMiddle(0, extractedElements.size(), root, extractedElements);
	}
	private void insertFromMiddle(int start, int finish, BinaryTreeNode root, ArrayList<BinaryTreeNode> elements) {
		if (Math.abs(finish - start) < 1) {
			return;
		}
		var midPoint = (start + finish) / 2;
		var middle = elements.get(midPoint).element;
		if (!middle.equals(root.element)) {
			insert(middle, root, null);
		}
		insertFromMiddle(start, midPoint, root, elements);
		if (midPoint != start) {
			insertFromMiddle(midPoint+1, finish, root, elements);
		}
	}

	/**
	 * Internal method to insert into a subtree.
	 * In tree is balanced, this routine runs in O(log n)
	 *
	 * @param x the item to insert.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryTreeNode insert(E value, BinaryTreeNode root, BinaryTreeNode parent) {
		if (root == null)
			return new BinaryTreeNode(value, null, null, parent);

		int compareResult = value.compareTo(root.element);
		if (compareResult < 0) {
			root.left = insert(value, root.left, root);
		} else {
			root.right = insert(value, root.right, root);
		}

		return root;
	}

	/**
	 * Internal method to find an item in a subtree.
	 * This routine runs in O(log n) as there is only one recursive call that is
	 * executed and the work
	 * associated with a single call is independent of the size of the tree: a=1,
	 * b=2, k=0
	 *
	 * @param x is item to search for.
	 * @param t the node that roots the subtree.
	 *          SIDE EFFECT: Sets local variable curr to be the node that is found
	 * @return node containing the matched item.
	 */
	private boolean contains(E x, BinaryTreeNode t) {
		if (t == null)
			return false;

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			return contains(x, t.left);
		else if (compareResult > 0)
			return contains(x, t.right);
		else {
			return true; // Match
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Tree)) {
			return false;
		}
		Tree outside = (Tree<E>) o;
		if (!recurseEquals(this.root, outside.root)) {
			return false;
		}
		return true;

	}

	private boolean recurseEquals(BinaryTreeNode lhs, BinaryTreeNode rhs) {
		if (lhs == null) {
			return rhs == null;
		}
		if (rhs == null) {
			return lhs == null;
		}
		if (!lhs.equals(rhs)) {
			return false;
		}
		var temp = recurseEquals(lhs.left, rhs.left);
		return recurseEquals(lhs.right, rhs.right) && temp;
	}

	// Basic node stored in unbalanced binary trees
	public class BinaryTreeNode {
		E element; // The data in the node
		BinaryTreeNode left; // Left child
		BinaryTreeNode right; // Right child
		BinaryTreeNode parent; // Parent node

		// Constructors
		BinaryTreeNode(E theElement) {
			this(theElement, null, null, null);
		}

		BinaryTreeNode(E theElement, BinaryTreeNode lt, BinaryTreeNode rt, BinaryTreeNode pt) {
			element = theElement;
			left = lt;
			right = rt;
			parent = pt;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Node:");
			sb.append(element);
			if (parent == null) {
				sb.append("<>");
			} else {
				sb.append("<");
				sb.append(parent.element);
				sb.append(">");
			}

			return sb.toString();
		}

		/**
		 * A {@link BinaryTreeNode} is equal when the vaues match. It does not
		 * depend on the children elements. But the {@link Tree} is only equal
		 * when the children also match
		 */
		public boolean equals(BinaryTreeNode outside) {
			if (outside == null) {
				return false;
			}
			if (outside == this) {
				return true;
			}
			return (this.element.equals(outside.element));
		}
	}
}
