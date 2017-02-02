/**
 * #24. Swap Nodes in Pairs
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 */
public class SwapNodesInPairs {

    public static ListNode swapPairs(ListNode head) {
        ListNode temp = new ListNode(0);
        temp.next = head;
        head = temp;

        while (head.next != null && head.next.next != null) {
            ListNode first = head.next;
            ListNode second = head.next.next;

            head.next = second;
            first.next = second.next;
            second.next = first;

            head = first;
        }

        return temp.next;
    }


    public static void main(String[] args) {
        ListNode l1 = getListNode(5, 1);
        System.out.println(print(l1));
        System.out.println(print(swapPairs(l1)));
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
