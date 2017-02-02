/**
 * #82. Remove Duplicates from Sorted List II
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 */
public class RemoveDuplicatesFromSortedListII {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode pre = temp;
        ListNode curr = head;
        while (curr != null) {
            while (curr.next != null && pre.next.val == curr.next.val) {
                curr = curr.next;
            } // get all duplicates after pre. now curr is different from curr.next

            if (pre.next == curr) { // no duplicates found
                pre = pre.next;
            } else {
                pre.next = curr.next;
            }

            curr = curr.next;
        }
        return temp.next;
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
            if (i==3 || i==4) {
                current.next = new ListNode(i);
                current = current.next;
            }
        }
        return head.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
