package interview.am.lc;

import java.util.List;

/**
 * 206. Reverse Linked List

 Reverse a singly linked list.

 Hint:
 A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {

    // recursive
    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // iterative
    // null -> 1 -> 2 -> 3
    // prev   curr
    // 1) save off next (node 2)
    // 2) reverse the arrow between prev and curr
    // 3) prev & curr move to the right
    public static ListNode reverseList2(ListNode head) {
        if (head == null) return null;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode l1 = getListNode();
        System.out.println(print(l1));
        System.out.println(print(reverseList(l1)));
    }


    /* helper */
    private static String print(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null){
            sb.append(head.val + " --> ");
            head = head.next;
        }
        return sb.toString();
    }

    /* helper */
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
