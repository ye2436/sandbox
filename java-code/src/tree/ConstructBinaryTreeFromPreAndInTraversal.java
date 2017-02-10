package tree;

import java.util.*;

/**
 * #105. Construct Binary Tree from Preorder and Inorder Traversal
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 */
public class ConstructBinaryTreeFromPreAndInTraversal {
    // preorder : root [0] | left subtree  | right subtree
    // inorder : left subtree | root [i] | right subtree
    // So:
    // 1. get root.val from preorder[0]
    // 2. find root.val index i in inorder
    // 3. locate left subtree in preorder by its len (the length is the same for a subtree in 2 different order)
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0
                || preorder.length != inorder.length) return null;

        int len = preorder.length;
        TreeNode root = new TreeNode(preorder[0]);
        int i=0;
        for (; i<len; i++) {
            if (inorder[i] == root.val) break;
        }
        // i represents the index of the root in preorder, and also the length of left subtree
        // then the length of right subtree is len-1-i
        TreeNode left = buildTree(Arrays.copyOfRange(preorder, 1, 1+i), Arrays.copyOfRange(inorder, 0, i));
        TreeNode right = buildTree(Arrays.copyOfRange(preorder, 1+i, len), Arrays.copyOfRange(inorder, i+1, len));
        root.left = left;
        root.right = right;
        return root;
    }

    public static TreeNode buildTree_2(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0
                || preorder.length != inorder.length) return null;

        // 可以先建立一个HashMap，以免后面需要进行线行搜索，这样每次递归中就只需要常量操作就可以完成对根的确定和左右子树的分割。
        // 因为每次都会从preorder中取root然后在inorder中找index，所以先遍历一次将每个节点对应的inorder的index存起来
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, map);
    }

    // preL, preR, inL, inR - all inclusive
    private static TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR, Map<Integer, Integer> map) {
        if (preL > preR || inL > inR) return null;
        TreeNode root = new TreeNode(preorder[preL]);
        int index = map.get(root.val);
        // left length : index - inL
        root.left = helper(preorder, preL+1, preL+index-inL, inorder, inL, index-1, map);
        root.right = helper(preorder, preL+1+index-inL, preR, inorder, index+1, inR, map);

        return root;
    }

    public static void main(String[] args) {
        //int[] preorder = {1,2,4,5,3};
        //int[] inorder = {4,2,5,1,3};
        int[] preorder = {3,2,1,4};
        int[] inorder = {1,2,3,4};
        TreeNode root = buildTree_2(preorder, inorder);
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
