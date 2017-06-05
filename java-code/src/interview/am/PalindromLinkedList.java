package interview.am;

/**
 * 234. Palindrome Linked List

 Given a singly linked list, determine if it is a palindrome.

 Follow up:
 Could you do it in O(n) time?
 */
public class PalindromLinkedList {

    // This can be solved by reversing the 2nd half and compare the two halves.
    // 1. Use Walker-Runner 2 pointers to get to the mid point and the end point
    // 2. reverse the 2nd half
    // 3.
    public boolean isPalindrome(ListNode head) {
        return false;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
