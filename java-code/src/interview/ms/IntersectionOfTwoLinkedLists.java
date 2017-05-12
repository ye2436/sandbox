package interview.ms;

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

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
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


    // Solution 2: go thru 2 lists at the same time. if a list reaches the end, redirect pointer to the other list's head.
    // if length of list A is m, length of list B is n, intersection length is k
    // both will go through m+n-k and meet at the start of the intersection.
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }

        return a;
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
