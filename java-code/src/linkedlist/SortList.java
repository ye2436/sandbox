package linkedlist;

/**
 * #148. Sort List
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class SortList {

    // merge sort
    // 归并操作（merge），也叫归并算法，指的是将两个已经排序的序列合并成一个序列的操作。归并排序算法依赖归并操作。
    // 迭代法
    //  1. 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
    //  2. 设定两个指针，最初位置分别为两个已经排序序列的起始位置
    //  3. 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
    //  4. 重复步骤3直到某一指针到达序列尾
    //  5. 将另一序列剩下的所有元素直接复制到合并序列尾
    // 递归法
    // 原理如下（假设序列共有n个元素）：
    //  1. 将序列每相邻两个数字进行归并操作，形成 floor(n/2)个序列，排序后每个序列包含两个元素
    //  2. 将上述序列再次归并，形成 floor(n/4)个序列，每个序列包含四个元素
    //  3. 重复步骤2，直到所有元素排序完毕

    public static ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    private static ListNode mergeSort(ListNode head) {
        // divide list into half, stop when there is no more than one node in the list.
        // when there is no more than one node in each list, the list is ordered.
        if (head == null || head.next == null) return head;
        ListNode walker = head;
        ListNode runner = head.next;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
        }
        ListNode head1 = head;
        ListNode head2 = walker.next;
        walker.next = null;
        ListNode left = mergeSort(head1);
        ListNode right = mergeSort(head2);
        return merge(left, right);
    }

    // merge two sorted list
    private static ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (head1 != null || head2 != null) {
            if (head1 == null) {
                curr.next = head2;
                break;
            }
            if (head2 == null) {
                curr.next = head1;
                break;
            }
            if (head1.val < head2.val) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = getListNode();
        System.out.println(print(l1));
        System.out.println(print(sortList(l1)));
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
