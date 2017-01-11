import java.util.ArrayList;
import java.util.List;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);



        return head.next;
    }


    public static void main(String[] args) {
        ListNode l1 = getListNode(5, 1);
        ListNode l2 = getListNode(5, 2);
        ListNode l3 = getListNode(5, 3);
        ListNode l4 = getListNode(5, 4);
        System.out.println(print(l1));
        System.out.println(print(l2));
        System.out.println(print(l3));
        System.out.println(print(l4));
        System.out.println(print(mergeKLists(new ListNode[]{l1, l2, l3, l4})));

        // test
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0,1);
        System.out.println(list);
        System.out.println(2 >> 1);
        System.out.println(3 >> 1);
        System.out.println(4 >> 1);
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

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
