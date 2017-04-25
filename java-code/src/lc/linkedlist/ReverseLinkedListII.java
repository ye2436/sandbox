package lc.linkedlist;

/**
 * #92. Reverse Linked List II
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * return 1->4->3->2->5->NULL.
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 */
public class ReverseLinkedListII {
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i=0; i<m-1; i++) {
            pre = pre.next;
        }

        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed

        for (int i=0; i<n-m; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next; // moves to the next one needs processing
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = getListNode();
        System.out.println(print(l1));
        System.out.println(print(reverseBetween(l1,3,6)));
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
