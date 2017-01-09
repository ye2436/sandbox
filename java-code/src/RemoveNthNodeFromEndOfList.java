/**
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
        ListNode temp = head;
        int size = 1;
        while (head.next !=null) {
            head = head.next;
            size++;
        }
        if (size == 1) {
            if (n==0) {
                return temp;
            } else {
                return null;
            }
        }

        head = temp;
        temp = new ListNode(0);
        temp.next = head;

        //System.out.println(size);
        //System.out.println("current head " + print(head));
        //System.out.println("current temp " + print(temp));

        // from front: size-n+1
        int count = 1;
        while (head.next!=null) {
            if (size - n == 0) { // remove head
                return head.next;
            } else if (size-n == count) { // prev is size-n
                head.next = head.next.next;
                break;
            } else {
                count++;
                head = head.next;
            }
        }
        return temp.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    public static void main(String[] args) {
        //ListNode head = getListNode(5);
        //System.out.println(print(head));
        //System.out.println(print(removeNthFromEnd(head,2)));

        System.out.println(print(removeNthFromEnd(getListNode(2),2)));
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
