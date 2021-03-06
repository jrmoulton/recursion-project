/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package recursion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
	private Integer[] v1 = {25, 10, 60, 55, 58, 56, 14, 63, 8, 50, 6, 9};
	private Integer[] v2 = {200, 15, 3, 65, 83, 70, 90};
	private Integer[] v3 = {25, 10, 60, 14, 55, 63, 58, 56};

    private Tree<Integer> tree1 = new Tree<>(this.v1, "Tree 1");
    private Tree<Integer> tree2 = new Tree<>(this.v2, "Tree 2");
    private Tree<Integer> tree3 = new Tree<>(this.v1, "Tree 3");
    private Tree<Integer> tree4 = new Tree<>(this.v3, "Tree 4");

    @Test void treeOneToString() {
		String valid =
"""
Tree 1
    63 [60]
  60 [25]
      58 [55]
        56 [58]
    55 [60]
      50 [55]
25 [no parent]
    14 [10]
  10 [25]
      9 [8]
    8 [10]
      6 [8]""";
		assertEquals(valid, this.tree1.toString());
    }

    @Test void treeTwoToString() {
	String valid = 
"""
Tree 2
200 [no parent]
        90 [83]
      83 [65]
        70 [83]
    65 [15]
  15 [200]
    3 [15]""";
		assertEquals(valid, this.tree2.toString());
    }

	@Test void treeOneInOrderToString() {
		assertEquals("Tree 4: 10 14 25 55 56 58 60 63", tree4.inOrderToString());
	}

	@Test void testBalanceTree() {
		Tree<Integer> treeToBalance = new Tree<>(this.v2, "Tree 4");
		String valid = 
"""
Tree 4
    200 [90]
  90 [70]
    83 [90]
70 [no parent]
    65 [15]
  15 [70]
    3 [15]""";
		treeToBalance.balanceTree();
		assertEquals(valid, treeToBalance.toString());
	}

	@Test void testGetByKey() {
		assertEquals(14, tree1.getByKey(14).element);
	}

	@Test void testNodesInLevelZero() {
		assertEquals(1, tree4.nodesInLevel(0));
	}

	@Test void testNodesInLevelTwo() {
		assertEquals(3, tree4.nodesInLevel(2));
	}

	@Test void testNodesInLevelDNE() {
		assertEquals(0, tree4.nodesInLevel(7), "If the specified level does not exist in the tree 0 should be returned");
	}

	@Test void treeOneMatches() {
		assertEquals(tree1, tree3);
	}

	@Test void testGetAllPaths() {
		String valid = 
"""
25 10 8 6
25 10 8 9
25 10 14
25 60 55 50
25 60 55 58 56
25 60 63""";
		assertEquals(valid, tree1.getAllPaths());
	}

	@Test void testGetAllPathsTwo() {
		String valid = 
"""
200 15 3
200 15 65 83 70
200 15 65 83 90""";
		assertEquals(valid, tree2.getAllPaths());
	}

	@Test void testTreeOneInOrderSuccessor() {
		assertEquals(tree1.getByKey(10), tree1.inOrderSuccessor(tree1.getByKey(9)));
	}

	@Test void testGetDepth() {
		assertEquals(5, tree4.getDepth());
	}

	@Test void testCountBST() {
		assertEquals(12,tree1.countBST());
	}
	@Test void testFlipWithCountBST() {
		var tree5 = new Tree<>(this.v1, "Tree 5");
		tree5.flip();
		assertEquals(6, tree5.countBST());
	}
}
