package interview.bb;

/**
 * Given a linked list which is sorted based on absolute values. Sort the list based on actual values.
 * Examples:
 * Input :  1 -> -10
 * output: -10 -> 1
 *
 * Input : 1 -> -2 -> -3 -> 4 -> -5
 * output: -5 -> -3 -> -2 -> 1 -> 4
 *
 * Input : -5 -> -10
 * Output: -10 -> -5
 *
 * Input : 5 -> 10
 * output: 5 -> 10
 */
public class SortedListByAbsoluteValue {

    // Solution 1 - create two lists, one for positive numbers, one for negative numbers
    // if positive, add to p list's tail; otherwise, add to n list's head
    // in the end, link negative list's tail to positive list's head

    // Solution 2 - O(1) space, in place sort.
    // if positive, do nothing; otherwise, add to the head
    public ListNode sortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val < 0) {
                prev.next = curr.next;
                curr.next = dummy.next;
                dummy.next = curr;

                // prev doesn't change. prev.next is the new current
                curr = prev.next;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        SortedListByAbsoluteValue solution = new SortedListByAbsoluteValue();
        ListNode head = solution.generate(new int[]{1,-2,-3,4,-5});
        System.out.println(solution.print(head));
        System.out.println(solution.print(solution.sortList(head)));
    }

    public ListNode generate(int[] vals) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : vals) {
            ListNode node = new ListNode(val);
            curr.next = node;
            curr = curr.next;
        }
        return dummy.next;
    }

    public String print(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(" --> ");
            head = head.next;
        }
        return sb.toString();
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
