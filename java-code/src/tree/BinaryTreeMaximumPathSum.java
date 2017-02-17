package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * #124. Binary Tree Maximum Path Sum
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree
 * along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * For example:
 * Given the below binary tree,
 *      1
 *     / \
 *    2   3
 * Return 6.
 */
public class BinaryTreeMaximumPathSum {
    public static int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        List<Integer> maxSoFar = new ArrayList<>();
        maxSoFar.add(Integer.MIN_VALUE);
        helper(root, maxSoFar);
        return maxSoFar.get(0);
    }

    // simplified from helper_v1
    private static int helper(TreeNode node, List<Integer> maxSoFar) {
        if (node == null) return 0;
        int leftMax = helper(node.left, maxSoFar);
        int rightMax = helper(node.right, maxSoFar);

        int sum = node.val;
        if (leftMax > 0) {
            sum += leftMax;
        }
        if (rightMax > 0) {
            sum += rightMax;
        }
        if (sum > maxSoFar.get(0)) {
            maxSoFar.set(0, sum);
        }

        return node.val + Math.max(0, Math.max(leftMax, rightMax));
    }

    // returns [leftMax, rightMax]
    private static int[] helper_v1(TreeNode node, List<Integer> maxSoFar) { // node not null
        int[] max = new int[2];
        if (node.left != null) {
            int[] leftMax = helper_v1(node.left, maxSoFar);
            max[0] = Math.max(leftMax[0], Math.max(leftMax[1], 0)) + node.left.val;
        }
        if (node.right != null) {
            int[] rightMax = helper_v1(node.right, maxSoFar);
            max[1] = Math.max(rightMax[0], Math.max(rightMax[1], 0)) + node.right.val;
        }

        int sum = node.val;
        if (max[0] > 0) {
            sum += max[0];
        }
        if (max[1] > 0) {
            sum += max[1];
        }
        if (sum > maxSoFar.get(0)) {
            maxSoFar.set(0, sum);
        }

        return max;
    }


    public static void main(String[] args) {
        TreeNode root = generate();
        System.out.println(maxPathSum(root));
    }

    private static TreeNode generate() {

        /**
         *      1
         *     / \
         *    2   5
         *   / \   \
         *  3   4   6
         */
        /*TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        t1.left = t2;
        t1.right = t5;
        t2.left = t3;
        t2.right = t4;
        t5.right = t6;*/
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(-2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        return t1;
    }
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
