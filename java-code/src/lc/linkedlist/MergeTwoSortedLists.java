package lc.linkedlist;

/**
 * #21. Merge Two Sorted Lists
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 */
public class MergeTwoSortedLists {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;

        while (l1!=null || l2!=null) {
            if (l1 == null) {
                curr.next = l2;
                break;
            }
            if (l2 == null) {
                curr.next = l1;
                break;
            }

            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        return head.next;
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }



    public static void main(String[] args) {
        ListNode l1 = getListNode(5,false);
        ListNode l2 = getListNode(5,true);
        System.out.println(print(l1));
        System.out.println(print(l2));
        System.out.println(print(mergeTwoLists(l1, l2)));
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
    public static ListNode getListNode(int n, boolean even) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        int i = even ? 2 : 1;
        for (; i<=n*2; i+=2) {
            current.next = new ListNode(i);
            current = current.next;
        }
        return head.next;
    }
}
