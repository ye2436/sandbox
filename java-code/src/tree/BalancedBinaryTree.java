package tree;

/**
 * #110. Balanced Binary Tree
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of
 * the two subtrees of every node never differ by more than 1.
 */
public class BalancedBinaryTree {
    public static boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return isBalanced(root.left) && isBalanced(root.right)
                && Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1;
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }

    public static void main(String[] args) {
        TreeNode root = generate();
        System.out.println(isBalanced(root));
    }
    private static TreeNode generate() {
        /*
        *       1
        *      / \
        *     2   3
        *    / \
        *   4   5
        *
        * Pre-order (NLR):  1 2 4 5 3
        * In-order (LNR):   4 2 5 1 3
        * Post-order (LRN): 4 5 2 3 1
        * */

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;

        return t1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
