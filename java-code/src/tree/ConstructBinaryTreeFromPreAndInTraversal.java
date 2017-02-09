package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * #105. Construct Binary Tree from Preorder and Inorder Traversal
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 */
public class ConstructBinaryTreeFromPreAndInTraversal {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return null;
    }

    public static void main(String[] args) {
        int[] preorder = {1,2,4,5,3};
        int[] inorder = {4,2,5,1,3};
        TreeNode root = buildTree(preorder, inorder);
        System.out.println(preorder(root));
        System.out.println(inorder(root));
    }

    private static List<Integer> preorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            res.add(root.val);
            res.addAll(preorder(root.left));
            res.addAll(preorder(root.right));
        }
        return res;
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
