package lc.linkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * #142. Linked List Cycle II
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Note: Do not modify the linked list.
 * Follow up:
 * Can you solve it without using extra space?
 */
public class LinkedListCycleII {

    // 假设linked list有环，环长Y，环以外的长度是X。
    // 现在有两个指针，第一个指针，每走一次走一步，第二个指针每走一次走两步，如果他们走了t次之后相遇在K点
    // 那么，指针一  走的路是      t = X + nY + K        ①
    //      指针二  走的路是     2t = X + mY+ K       ②          m,n为未知数
    // 把等式一代入到等式二中, 有
    // 2X + 2nY + 2K = X + mY + K    =>   X+K  =  (m-2n)Y    ③
    // 这就清晰了，X和K的关系是基于Y互补的。等于说，两个指针相遇以后，再往下走X步就回到Cycle的起点了。这就可以有O(n)的实现了。
    public static ListNode detectCycle(ListNode head) {
        if (head == null) return null;

        // 先使用Linked List Cycle I的办法，判断是否有cycle。
        ListNode walker = head;
        ListNode runner = head;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (runner == walker) {
               break;
            }
        }
        if (runner == null || runner.next == null) return null; // 没有cycle

        // 一起往下走X步，就找到节点了。
        walker = head;
        while (walker != runner) {
            walker = walker.next;
            runner = runner.next;
        }
        return runner;
    }

    public static ListNode detectCycle_withExtraSpace(ListNode head) {
        if (head == null) return null;
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) {
                return node;
            }
            set.add(node);
            node = node.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode l1 = getListNode();
        System.out.println(detectCycle_withExtraSpace(l1).val);
        System.out.println(detectCycle(l1).val);
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
