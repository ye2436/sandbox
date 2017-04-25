package lc.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * #109. Convert Sorted List to Binary Search Tree
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedListToBinarySearchTree {

    // use two pointers to locate medium
    public static TreeNode sortedListToBST_2(ListNode head) {
        if (head == null) return null;
        return helper_2(head, null);
    }

    // head inclusive, tail exclusive, because tail could be null
    private static TreeNode helper_2(ListNode head, ListNode tail) {
        if (head == tail) return null;
        ListNode fast = head;
        ListNode slow = head;
        // fast_node goes twice as fast as slow_node
        // so when fast reaches tail, slow is at the middle point between head and tail
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = helper_2(head, slow);
        root.right = helper_2(slow.next, tail);
        return root;
    }

    // convert lc.linkedlist to arraylist
    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        List<Integer> nums = new ArrayList<>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }

        return helper(nums, 0, nums.size()-1);
    }

    private static TreeNode helper(List<Integer> nums, int l, int r) {
        if (l > r) return null;
        int m = (l+r)/2;
        TreeNode root = new TreeNode(nums.get(m));
        root.left = helper(nums, l, m-1);
        root.right = helper(nums, m+1, r);
        return root;
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
