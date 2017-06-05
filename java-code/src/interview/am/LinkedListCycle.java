package interview.am;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * #141. Linked List Cycle
 *  Given a linked list, determine if it has a cycle in it.
 *  Follow up:
 *  Can you solve it without using extra space?
 */
public class LinkedListCycle {
    public static boolean hasCycle_withExtraSpace(ListNode head) {
        if (head == null) return false;
        Set<ListNode> set = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (set.contains(curr)) return true;
            set.add(curr);
            curr = curr.next;
        }
        return false;
    }

    // two pointer: walker-runner
    // runner goes twice as fast, if there is a cycle in list, runner will meet walker
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode walker = head;
        ListNode runner = head;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (runner == walker) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode l1 = getListNode();
        System.out.println(hasCycle_withExtraSpace(l1));
        System.out.println(hasCycle(l1));
    }

    /* helper */
    private static ListNode getListNode() {
        ListNode temp = new ListNode(0);
        ListNode current = temp;
        int[] nums = {1,4,3,2,5,2};
        Map<Integer, ListNode> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ListNode(nums[i]));
            }
            current.next = map.get(nums[i]);
            current = current.next;
        }
        return temp.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
