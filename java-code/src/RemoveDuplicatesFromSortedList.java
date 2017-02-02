/**
 * #83. Remove Duplicates from Sorted List
 *  Given a sorted linked list, delete all duplicates such that each element appear only once.
 *  For example,
 *  Given 1->1->2, return 1->2.
 *  Given 1->1->2->3->3, return 1->2->3.
 */
public class RemoveDuplicatesFromSortedList {
    public static ListNode deleteDuplicates(ListNode head) {
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = getListNode(5);
        System.out.println(print(l1));
        System.out.println(print(deleteDuplicates(l1)));
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
    private static ListNode getListNode(int n) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        for (int i=1; i<=n; i++) {
            current.next = new ListNode(i);
            current = current.next;
        }
        return head.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
