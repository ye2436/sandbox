package lc.linkedlist;

/**
 * #25. Reverse Nodes in K Group
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * Only constant memory is allowed.
 * For example,
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 */
public class ReverseNodesInKGroup {

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k==1 || head == null) return head;

        ListNode temp = new ListNode(0);
        temp.next = head;

        int count = 0;
        ListNode pre = temp;
        ListNode curr = head;
        while (curr != null) {
            count++;

            if (count % k == 0) {
                pre = reverse(pre, curr.next); // the new tail is now pre
                // curr was the one before next, now pointing to head of the reversed
                curr = pre.next; // curr reset to pre.next
            } else {
                curr = curr.next;
            }
        }

        return temp.next;
    }

    /*
     * Reverse a link list between pre and next exclusively and return the last node of the reversed list
     * Example:
     * 0->1->2->3->4->5->6
     * |           |
     * pre        next
     *
     * after calling pre = reverse(pre, next)
     *
     * 0->3->2->1->4->5->6
     *          |  |
     *          pre next
    */
    public static ListNode reverse(ListNode pre, ListNode next) {
        ListNode last = pre.next; // where first will be doomed "last"
        ListNode curr = last.next;

        while (curr != next) {
            last.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
            curr = last.next;
        }
        return last;
    }

    public static void main(String[] args) {
        ListNode dummy = new ListNode(0);
        ListNode l1 = getListNode(5, 1);
        ListNode l4 = l1.next.next.next;
        dummy.next = l1;
        System.out.println(print(l1));
        //System.out.println(print(reverse(dummy, l4)));
        System.out.println(print(reverseKGroup(l1,3)));
    }


    /* helper */
    public static String print(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null){
            sb.append(head.val + " --> ");
            head = head.next;
        }
        return sb.toString();
    }

    /* helper */
    public static ListNode getListNode(int n, int factor) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        for (int i=factor; i<=n*factor; i+=factor) {
            current.next = new ListNode(i);
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
