package linkedlist;

public class LinkedList {

    private static ListNode reverse_new(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = head;
        while (tail != null && tail.next != null) {
            ListNode newHead = tail.next;
            tail.next = newHead.next;
            newHead.next = dummy.next;
            dummy.next = newHead;
        }
        return dummy.next;
    }


    // null -> 1 -> 2 -> 3
    // prev   curr
    // 1) save off next (node 2)
    // 2) curr.next is prev (reverse the arrow so that null <- 1)
    // 3) prev & curr move right
    // null <- 1 -> 2 -> 3
    //       prev  curr
    // 1) save off node 3
    // 2) the arrow between prev and curr
    // 3) prev and curr move right
    // ..... until curr reaches null
    // null <- 1 <- 2 <- 3 -> null
    //                  prev  curr
    public static ListNode reverseIterative(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        return prev;
    }

    public static ListNode reverseRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode tail = reverseRecursive(head.next);
        // head.next is also the reversed list's tail. e.g., 1 --> 2 <-- 3 <-- 4 <-- 5
        // 2345 is reversed to 5432, now 2 is the tail. at the same time, 2 is also 1.next
        // set 1.next.next to current head, then everything is reversed.
        head.next.next = head;
        head.next = null; // set the head.next to avoid cycle

        return tail;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }

    public static String print(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null){
            sb.append(head.val + " --> ");
            head = head.next;
        }
        return sb.toString();
    }

    public static ListNode getListNode(int n) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        for (int i=1; i<=n; i++) {
            current.next = new ListNode(i);
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {

        System.out.println(print(reverseIterative(getListNode(5))));
        System.out.println(print(reverseRecursive(getListNode(6))));
    }
}
