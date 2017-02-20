package linkedlist;

/**
 * #86. Partition List
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 */
public class PartitionList {

    public static ListNode partition(ListNode head, int x) {
        if (head == null) return head;

        ListNode temp1 = new ListNode(0);
        ListNode temp2 = new ListNode(0);
        ListNode curr1 = temp1;
        ListNode curr2 = temp2;

        while(head != null) {
            if (head.val < x) {
                curr1.next = head;
                curr1 = curr1.next;
            } else {
                curr2.next = head;
                curr2 = curr2.next;
            }
            head = head.next;
        }
        curr1.next = temp2.next;
        curr2.next = null;

        return temp1.next;
    }

    public static void main(String[] args) {
        ListNode l1 = getListNode();
        System.out.println(print(l1));
        System.out.println(print(partition(l1,3)));
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
        ListNode head = new ListNode(0);
        ListNode current = head;
        // 1->4->3->2->5->2
        int[] nums = {1,4,3,2,5,2};
        for (int i=0; i<nums.length; i++) {
            current.next = new ListNode(nums[i]);
            current = current.next;
        }
        return head.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
