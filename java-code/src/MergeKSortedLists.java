/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        return null;
    }



    public static void main(String[] args) {
        ListNode l1 = getListNode(5,false);
        ListNode l2 = getListNode(5,true);
        System.out.println(print(l1));
        System.out.println(print(l2));
        System.out.println(print(mergeKLists(new ListNode[]{l1, l2})));
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

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
