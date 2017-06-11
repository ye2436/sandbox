package interview.am.lc2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Given inorder and postorder traversal of a lc.tree, construct the binary lc.tree.
 * Note:
 * You may assume that duplicates do not exist in the lc.tree.
 */
public class ConstructBinaryTreeFromInAndPostTraversal {


    // Similar as build tree from pre and in order. We can locate root from the last element of the post order array
    //          | left subtree | right sub tree | root | ---- post order
    //          | left subtree | root | right sub tree | ---- in order
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null || postorder.length == 0 || inorder.length == 0
                || postorder.length != inorder.length) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1, map);
    }

    // preL, preR, inL, inR - all inclusive
    private static TreeNode helper(int[] inorder, int inL, int inR, int[] postorder, int postL, int postR, Map<Integer, Integer> map) {
        if (postL > postR || inL > inR) return null;
        TreeNode root = new TreeNode(postorder[postR]);
        int index = map.get(root.val);
        // left length : index - inL
        root.left = helper(inorder, inL, index-1, postorder, postL, postL+index-inL-1, map);
        root.right = helper(inorder, index+1, inR, postorder, postL+index-inL, postR-1, map);

        return root;
    }

    public static void main(String[] args) {
        int[] inorder = {1,2,3,4};
        int[] postorder = {1,2,4,3};
        TreeNode root = buildTree(inorder, postorder);
        System.out.println(inorder(root));
        System.out.println(postorder(root));
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

    private static List<Integer> postorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            res.addAll(postorder(root.left));
            res.addAll(postorder(root.right));
            res.add(root.val);
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
