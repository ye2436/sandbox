package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * #99. Recover Binary Search Tree
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */
public class RecoverBinarySearchTree {
    public static void recoverTree(TreeNode root) {

    }

    public static void main(String[] args) {
        TreeNode root = generate();
        System.out.println(inorder(root));
        recoverTree(root);
        System.out.println(inorder(root));
    }

    private static TreeNode generate() {
        /*
        * Original:
        *       4
        *      / \
        *     2   5
        *    / \
        *   1   3
        * Inorder: 1 2 3 4 5
        *
        * Swapped: 3 <-> 5
        *       4
        *      / \
        *     2   3
        *    / \
        *   1   5
        * Inorder : 1 2 5 4 3
        * */

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t4.left = t2;
        t4.right = t3;
        t2.left = t1;
        t2.right = t5;
        return t4;
    }

    private static List<Integer> inorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            res.addAll(inorder(root.left));
            res.add(root.val);
            res.addAll(inorder(root.right));
        }
        return res;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
