package lc.linkedlist;

/**
 * #61. Rotate List
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 */
public class RotateList {
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        k = k%len;
        if (k==0) return head;

        ListNode runner = head;
        ListNode walker = head;
        int count = k;
        while (count>0) {
            runner = runner.next;
            count--;
        }
        while (runner.next != null) {
            runner = runner.next;
            walker = walker.next;
        }
        ListNode newHead = walker.next;
        walker.next = null;
        runner.next = head;

        return newHead;
    }

    public static void main(String[] args) {
        ListNode l1 = getListNode(5);
        System.out.println(print(l1));
        System.out.println(print(rotateRight(l1, 2)));
    }


    // helper
    private static String print(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null){
            sb.append(head.val + " --> ");
            head = head.next;
        }
        return sb.toString();
    }

    // helper
    private static ListNode getListNode(int n) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        for (int i=1; i<=n; i++) {
            current.next = new ListNode(i);
            current = current.next;
        }
        return head.next;
    }

    /////////////////////////////////
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
