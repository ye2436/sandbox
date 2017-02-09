package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #105. Construct Binary Tree from Preorder and Inorder Traversal
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 */
public class ConstructBinaryTreeFromPreAndInTraversal {
    // preorder : root [0] | left subtree [j] | right subtree
    // inorder : left subtree [i-1] | root [i] | right subtree
    // also, inorder[i-1], aka, the node before root is right most node of left subtree
    // the last node of left subtree in preorder will be the same
    // because no matter preorder (NLR) or inorder (LNR) ends with R node
    // So:
    // 1. get root.val from preorder[0]
    // 2. find root.val index i in inorder
    // 3. find the inorder[i-1]
    // 4. in preorder find j where preorder[j] == inorder[i-1]
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0
                || preorder.length != inorder.length) return null;

        TreeNode root = new TreeNode(preorder[0]);
        int i=0;
        for (; i<inorder.length; i++) {
            if (inorder[i] == root.val) break;
        }
        if (i > 0) {
            // left not null
        }
        int j=1;
        for (; j<preorder.length && i>0; j++) {
            if (preorder[j] == inorder[i-1]);
        }

        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {1,2,4,5,3};
        int[] inorder = {4,2,5,1,3};
        System.out.println(Arrays.toString(preorder));
        System.out.println(Arrays.toString(Arrays.copyOfRange(preorder, 1,2)));
        //TreeNode root = buildTree(preorder, inorder);
        //System.out.println(preorder(root));
        //System.out.println(inorder(root));
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
