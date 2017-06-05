package interview.am;

/**
 * #160. Intersection of Two Linked Lists
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 *  A:          a1 → a2
 *                      ↘
 *                       c1 → c2 → c3
 *                      ↗
 *  B:     b1 → b2 → b3
 *  begin to intersect at node c1.
 * Notes:
 *  If the two linked lists have no intersection at all, return null.
 *  The linked lists must retain their original structure after the function returns.
 *  You may assume there are no cycles anywhere in the entire linked structure.
 *  Your code should preferably run in O(n) time and use only O(1) memory.
 */
public class IntersectionOfTwoLinkedLists {

    // Leetcode 160
    // 分情况讨论：
    // 两个没有环，不想交
    // 两个没有环，相交
    // 两个有环，不想交
    // 两个有环，相交
    // 如果是有环的情况下找到环的的点，然后照样计算长度同样的算法。


    // The trick is to use 2 pointers starting from 2 heads, once it reaches the tail,
    // redirect it to the other list's head.
    // * But this will not work if they do not intersect.
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pointerA = headA;
        ListNode pointerB = headB;

        while (pointerA != pointerB) {
            pointerA = pointerA.next == null ? headB : pointerA.next;
            pointerB = pointerB.next == null ? headA : pointerB.next;
        }

        return pointerA;
    }

    // No Circle, May not intersect
    // 1, Get the length of the two lists.
    // 2, Align them to the same start point.
    // 3, Move them together until finding the intersection point, or the end null
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int lenA = getLength(headA);
        int lenB = getLength(headB);

        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenB > lenA) {
            headB = headB.next;
            lenB--;
        }

        while (headA != headB ) { // either end when find intersect or both reached the end null
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    private static int getLength(ListNode node){
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }



    public static ListNode getIntersectionNode_old(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // get A length
        // get B length
        // get difference and set longer one's pointer to diff
        // move both together.
        ListNode tempA = headA;
        ListNode tempB = headB;
        int lenA = 0;
        int lenB = 0;
        while (tempA != null || tempB != null) {
            if (tempA != null) {
                lenA++;
                tempA = tempA.next;
            }
            if (tempB != null) {
                lenB ++;
                tempB = tempB.next;
            }
        }

        // reset pointers
        tempA = headA;
        tempB = headB;
        if (lenA > lenB) {
            int diff = lenA - lenB;
            while (diff > 0) {
                tempA = tempA.next;
                diff--;
            }
        } else if (lenA < lenB) {
            int diff = lenB - lenA;
            while (diff > 0) {
                tempB = tempB.next;
                diff--;
            }
        } // else, length equals, do nothing

        while (tempA != tempB) {
            tempA = tempA.next;
            tempB = tempB.next;
        }

        return tempA;
    }

    public static void main(String[] args) {
        ListNode[] array = getListNodes();
        System.out.println(print(array[0]));
        System.out.println(print(array[1]));
        System.out.println(print(getIntersectionNode(array[0], array[1])));
    }

    /* helper */
    private static ListNode[] getListNodes() {
        ListNode temp = new ListNode(0);
        ListNode current = temp;
        int[] nums = {7,8,9};
        for (int i=0; i<nums.length; i++) {
            current.next = new ListNode(nums[i]);
            current = current.next;
        } // temp.next is the start of intersection

        ListNode tempA = new ListNode(0);
        ListNode currentA = tempA;
        nums = new int[]{0,1,2,3};
        for (int i=0; i<nums.length; i++) {
            currentA.next = new ListNode(nums[i]);
            currentA = currentA.next;
        }
        //currentA.next = temp.next;

        ListNode tempB = new ListNode(0);
        ListNode currentB = tempB;
        nums = new int[]{4,5,6};
        for (int i=0; i<nums.length; i++) {
            currentB.next = new ListNode(nums[i]);
            currentB = currentB.next;
        }
        currentB.next = temp.next;

        return new ListNode[] {tempA.next, tempB.next};
    }

    private static String print(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null){
            sb.append(head.val + " --> ");
            head = head.next;
        }
        return sb.toString();
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
