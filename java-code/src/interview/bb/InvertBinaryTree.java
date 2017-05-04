package interview.bb;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. Invert Binary Tree
 * Invert a binary tree.
 *          4
 *        /   \
 *       2     7
 *      / \   / \
 *     1   3 6   9
 * to
 *          4
 *        /   \
 *       7     2
 *      / \   / \
 *     9   6 3   1
 */
public class InvertBinaryTree {

    // Recursive - but it is also bound to the application stack, may cause stack overflow
    public TreeNode invertTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return root;

        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = invertTree(root.right);
        newRoot.right = invertTree(root.left);
        return newRoot;
    }

    // Iterative - level order traverse. swap left & right.
    public TreeNode invertTree_2(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // swap left & right
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
