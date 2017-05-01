package interview.li;

import apple.laf.JRSUIUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * #101. Symmetric Tree
 * Given a binary lc.tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary lc.tree [1,2,2,3,4,4,3] is symmetric:
 *      1
 *     / \
 *    2   2
 *   / \ / \
 *  3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 *      1
 *     / \
 *    2   2
 *     \   \
 *     3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */
public class SymmetricTree {
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        TreeNode l = root;
        TreeNode r = root;
        return helper(l, r);
    }

    // pre-order traverse & reversed pre-order traverse
    private static boolean helper(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;
        if (l.val != r.val) return false;
        return helper(l.left, r.right) && helper(l.right, r.left);
    }

    // iterative - use bfs, level order traversal
    //             create 2 queues for track left subtree and right subtree
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (root.left == null || root.right == null) return false;

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(root.left);
        queue2.offer(root.right);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();

            if (node1.val != node2.val) return false;

            if (node1.left != null && node2.right != null) { // being not null at same time
                queue1.offer(node1.left);
                queue2.offer(node2.right);
            } else if (!(node1.left == null && node2.right == null)) { // not being null at the same time
                return false;
            }

            if (node1.right != null && node2.left != null) {
                queue1.offer(node1.right);
                queue2.offer(node2.left);
            } else if (!(node1.right == null && node2.left == null)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode p = generate(true);
        System.out.println(isSymmetric(p));
        TreeNode q = generate(false);
        System.out.println(isSymmetric(q));
    }

    private static TreeNode generate(boolean isSymmetric) {
        /*
        * true:
        *      1
        *     / \
        *    2   2
        *   / \ / \
        *  3  4 4  3
        * */
        if (isSymmetric) {
            TreeNode t1 = new TreeNode(1);
            TreeNode t2 = new TreeNode(2);
            TreeNode t3 = new TreeNode(2);
            TreeNode t4 = new TreeNode(3);
            TreeNode t5 = new TreeNode(4);
            TreeNode t6 = new TreeNode(4);
            TreeNode t7 = new TreeNode(3);
            t1.left = t2;
            t1.right = t3;
            t2.left = t4;
            t2.right = t5;
            t3.left = t6;
            t3.right = t7;
            return t1;

        } else {
                /*
                * false:
                *      1
                *     / \
                *    2   2
                *     \   \
                *     3    3
                *
                * */
            TreeNode t1 = new TreeNode(1);
            TreeNode t2 = new TreeNode(2);
            TreeNode t3 = new TreeNode(2);
            TreeNode t4 = new TreeNode(3);
            TreeNode t5 = new TreeNode(3);
            t1.left = t2;
            t1.right = t3;
            t2.right = t4;
            t3.right = t5;
            return t1;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
