package lc.linkedlist;

/**
 * #143. Reorder List
 *  Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 *  reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *  You must do this in-place without altering the nodes' values.
 *  For example,
 *  Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */
public class ReorderList {

    // take off 2nd half, reverse, and merge
    public static void reorderList(ListNode head) {
        if (head == null) return;
        ListNode walker = head;
        ListNode runner = head;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
        }
        // take off 2nd half
        ListNode head2 = walker.next;
        walker.next = null;

        // reverse
        ListNode curr = head2;
        ListNode prev = null;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        head2 = prev;

        // merge
        ListNode head1 = head;
        while (head1 != null && head2 != null) {
            ListNode temp = head1.next;
            head1.next = head2;
            head2 = head2.next;
            head1.next.next = temp;
            head1 = temp;
        }
    }



    public static void main(String[] args) {
        ListNode l1 = getListNode();
        System.out.println(print(l1));
        reorderList(l1);
        // [1-6] :
        // 1 2 3 4 5 6
        // 1 6 2 5 3 4
        // [1-7] :
        // 1 2 3 4 5 6 7
        // 1 7 2 6 3 5 4
        System.out.println(print(l1));
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
        ListNode temp = new ListNode(0);
        ListNode current = temp;
        int[] nums = {1,2,3,4,5,6,7};
        for (int i=0; i<nums.length; i++) {
            current.next = new ListNode(nums[i]);
            current = current.next;
        }
        return temp.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
