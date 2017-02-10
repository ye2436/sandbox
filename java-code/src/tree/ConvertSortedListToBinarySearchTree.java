package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * #109. Convert Sorted List to Binary Search Tree
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedListToBinarySearchTree {

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;

        return null;
    }

    public static void main(String[] args) {
        ListNode head = getListNode();
        TreeNode root = sortedListToBST(head);
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

    private static ListNode getListNode() {
        ListNode head = new ListNode(0);
        ListNode current = head;
        int[] nums = {1,2,3,4,5,6};
        for (int i=0; i<nums.length; i++) {
            current.next = new ListNode(nums[i]);
            current = current.next;
        }
        return head.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
