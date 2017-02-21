package linkedlist;

import java.util.List;

/**
 * #147. Insertion Sort List
 * Sort a linked list using insertion sort.
 */
public class InsertionSortList {

    // 插入排序（英语：Insertion Sort）是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，
    // 找到相应位置并插入。插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），
    // 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
    // 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
    // 1. 从第一个元素开始，该元素可以认为已经被排序
    // 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
    // 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
    // 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
    // 5. 将新元素插入到该位置后
    // 6. 重复步骤2~5

    public static ListNode insertionSortList(ListNode head) {
        if (head == null) return null;

        // starting from head, get curr node, comparing from head to curr (can't compare from curr to head because of link direction)
        // find the first node > curr, insert before the node (so need a prev pointer)
        // set curr to curr.next (saved off as temp before comparing)
        ListNode curr = head;
        ListNode prev; // the one before the first node that is > curr
        ListNode next;
        ListNode dummy = new ListNode(0); // do not link dummy.next to head. it will be set after sort.
        while (curr != null) {
            next = curr.next;
            prev = dummy;

            while (prev.next != null && prev.next.val <= curr.val) {
                prev = prev.next;
            }

            // insert between pre and pre.next
            // we need to do this part even if prev is already right before curr (seemingly)
            // the sorted part is actually detached from the unsorted part after the first round of outer while loop
            // the first round - pre: dummy, curr: head
            // so pre.next is actually null
            curr.next = prev.next;
            prev.next = curr;
            curr = next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = getListNode();
        System.out.println(print(l1));
        System.out.println(print(insertionSortList(l1)));
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
        ListNode temp = new ListNode(0);
        ListNode current = temp;
        int[] nums = {3,8,2,4,9,1,5,6};
        for (int i=0; i<nums.length; i++) {
            current.next = new ListNode(nums[i]);
            current = current.next;
        }
        return temp.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
