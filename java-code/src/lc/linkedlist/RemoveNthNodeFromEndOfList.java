package lc.linkedlist;

/**
 * #19. Remove Nth Node from End of List
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example,
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 */
public class RemoveNthNodeFromEndOfList {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = new ListNode(0);
        temp.next = head;
        int size = 0;
        ListNode first = head;
        while (first!=null) {
            first = first.next;
            size++;
        }

        first = temp;
        // from front: size-n+1
        int count = size-n;
        while (count>0) {
            first = first.next;
            count--;
        } // now at the node before the one we want to remove
        first.next = first.next.next;
        return temp.next;
    }

    public static ListNode removeNthFromEnd_2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode first = dummy;
        ListNode second = dummy;

        int i=n+1;
        while (i>0) {
            first = first.next;
            i--;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;

        return dummy.next;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        //ListNode head = getListNode(5);
        //System.out.println(print(head));
        //System.out.println(print(removeNthFromEnd(head,2)));


        System.out.println(print(removeNthFromEnd(getListNode(5),2)));
        System.out.println(print(removeNthFromEnd_2(getListNode(1),1)));
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
}
