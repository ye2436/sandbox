package interview.li;

import java.util.*;

/**
 * 366. Find Leaves of Binary Tree
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
 * Example:
 * Given binary tree
 *      1
 *     / \
 *    2   3
 *   / \
 *  4   5
 * Returns [4, 5, 3], [2], [1].
 * Explanation:
 * 1. Removing the leaves [4, 5, 3] would result in this tree:
 *      1
 *     /
 *    2
 * 2. Now removing the leaf [2] would result in this tree:
 *      1
 * 3. Now removing the leaf [1] would result in the empty tree:
 *      []
 * Returns [4, 5, 3], [2], [1].
 */
public class FindLeavesOfBinaryTree {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        helper(root, result);
        return result;
    }

    // The height of a node is the number of edges on the longest path between that node and a leaf.
    // The depth of a node is the number of edges from the tree's root node to the node.
    private int helper(TreeNode node, List<List<Integer>> result) { // returns height
        if (node == null) {
            return -1;
        }
        // if both left and right is null, height is 0
        int height = Math.max(helper(node.left, result), helper(node.right, result)) + 1;
        /*while (result.size() < height) {
            result.add(new ArrayList<>());
        }*/ // not necessary because it is done recursively, the list is filled from the bottom of the tree and up
        if (result.size() == height) {
            result.add(new ArrayList<>());
        }
        result.get(height).add(node.val);
        return height;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
