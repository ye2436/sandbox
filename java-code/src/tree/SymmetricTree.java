package tree;

/**
 * #101. Symmetric Tree
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
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

        return false;
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
