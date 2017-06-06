package interview.am;

/**
 * 234. Palindrome Linked List

 Given a singly linked list, determine if it is a palindrome.

 Follow up:
 Could you do it in O(n) time?
 */
public class PalindromeLinkedList {

    // This can be solved by reversing the 2nd half and compare the two halves.
    // 1. Use Walker-Runner 2 pointers to get to the mid point and the end point
    // 2. reverse the 2nd half
    // 3.
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;

        ListNode walker = head;
        ListNode runner = head;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
        }

        // now walker is at the head of the 2nd half of the list, reverse the half list
        ListNode right = reverse(walker);
        ListNode left = head;
        // when there are odd number of nodes, left list is smaller than right list. we want to stop before the mid point node(walker)
        while (right != null && left != walker) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }

        return true;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
