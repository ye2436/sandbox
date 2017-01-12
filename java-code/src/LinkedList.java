public class LinkedList {

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

    public static ListNode reverse(ListNode head) {
        ListNode temp = new ListNode(0);
        temp.next = head;

        ListNode first = temp.next;
        ListNode last = temp.next;
        while (last.next != null) {
            ListNode next = last.next;
            temp.next = next;
            last.next = next.next;
            next.next = first;
            first = next;
        }

        return temp.next;
    }


    public static class ListNode {
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
