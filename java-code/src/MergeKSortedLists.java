import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * #23. Merge K Sorted Lists
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return helper(lists,0,lists.length-1);
    }

    public static ListNode helper (ListNode[] lists, int l, int r) {
        if (l < r) {
            int m = (l+r)/2;
            return merge(helper(lists, l, m), helper(lists,m+1, r));
        }
        return lists[l]; // when l==r, returns the only one in lists
    }

    public static ListNode merge(ListNode l1, ListNode l2) { // merge two sorted lists
        ListNode head = new ListNode(0);
        ListNode temp = head;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                temp.next = l2;
                break;
            }
            if (l2 == null) {
                temp.next = l1;
                break;
            }
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        return head.next;
    }


    public static ListNode mergeKLists_2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        ListNode head = new ListNode(0);
        ListNode temp = head;

        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
            }
        }

        while (!heap.isEmpty()) {
            ListNode curr = heap.poll();
            temp.next = curr;
            temp = temp.next;

            if (curr.next != null) {
                heap.offer(curr.next);
            }
        }

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
        l1 = getListNode(5, 1);
        l2 = getListNode(5, 2);
        l3 = getListNode(5, 3);
        l4 = getListNode(5, 4);
        System.out.println(print(mergeKLists_2(new ListNode[]{l1, l2, l3, l4})));
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
