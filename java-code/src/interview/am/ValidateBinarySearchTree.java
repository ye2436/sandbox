package interview.am;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * #98. Validate Binary Search Tree
 * Given a binary lc.tree, determine if it is a valid binary search lc.tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 *      2
 *     / \
 *    1   3
 * Binary lc.tree [2,1,3], return true.
 * Example 2:
 *      1
 *     / \
 *    2   3
 * Binary lc.tree [1,2,3], return false.
 */
public class ValidateBinarySearchTree {
    // inorder traverse, if node smaller than previous, return false
    public static boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        TreeNode curr = root;
        Integer pre = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                if (pre != null && curr.val <= pre) return false;
                pre = curr.val;
                curr = curr.right;
            }
        }
        return true;
    }

    // recursive
    public static boolean isValidBST_2(TreeNode root) {
        List<Integer> pre = new ArrayList<>();
        pre.add(null);
        return helper(root, pre);
    }

    private static boolean helper(TreeNode root, List<Integer> pre) {
        if (root == null) return true;

        if (!helper(root.left, pre)) { // left subtree invalid
            return false;
        }
        if (pre.get(0) != null && root.val <= pre.get(0)) {
            return false;
        }
        pre.set(0, root.val);
        return helper(root.right, pre); // if right subtree is valid
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.right = n2;
        n2.right = n3;
        System.out.println(isValidBST(n1));
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
