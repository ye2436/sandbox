/**
 * #2. Add Two Numbers
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */

public class AddTwoNumbers {

    public static void main(String[] args) {
        // write your code here
        AddTwoNumbers a = new AddTwoNumbers();
        ListNode l1 = new ListNode(2);
        ListNode l1m = new ListNode(4);
        ListNode l1n = new ListNode(3);
        l1.next = l1m;
        l1m.next = l1n;

        ListNode l2 = new ListNode(5);
        ListNode l2m = new ListNode(6);
        ListNode l2n = new ListNode(5);
        l2.next = l2m;
        l2m.next = l2n;

        ListNode l3 = a.addTwoNumbers(l1, l2);
        System.out.println(l3.val);
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        ListNode p = l1;
        ListNode q = l2;
        int carry = 0;
        while (p != null || q!= null) {
            int x = p==null ? 0 : p.val;
            int y = q==null ? 0 : q.val;
            curr.next = new ListNode((x+y+carry) % 10);
            carry = (x + y + carry)/10;
            curr = curr.next;

            if (p!=null) {
                p = p.next;
            }
            if (q!=null){
                q = q.next;
            }
        }
        if (carry>0) {
            curr.next = new ListNode(carry);
        }
        return head.next;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


}
