package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 108. Convert Sorted Array to Binary Search Tree
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedArrayToBinarySearchTree {
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return  null;
        return helper(nums, 0, nums.length-1);
    }

    private static TreeNode helper(int[] nums, int l, int r) {
        if (l>r) return null;
        int m = (l+r)/2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = helper(nums, l, m-1);
        root.right = helper(nums, m+1, r);
        return root;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,9};
        TreeNode root = sortedArrayToBST(nums);
        System.out.println(inorder(root));
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
