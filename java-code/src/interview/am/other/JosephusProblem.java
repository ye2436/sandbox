package interview.am.other;

/**
 * There are n people standing in a circle waiting to be executed. The counting out begins at some point in the circle and
 * proceeds around the circle in a fixed direction. In each step, a certain number of people are skipped and the next person
 * is executed. The elimination proceeds around the circle (which is becoming smaller and smaller as the executed people are removed),
 * until only the last person remains, who is given freedom. Given the total number of persons n and a number k which indicates
 * that k-1 persons are skipped and kth person is killed in circle. The task is to choose the place in the initial circle
 * so that you are the last one remaining and so survive.
 */
public class JosephusProblem {

    // The problem has following recursive structure.
    // josephus(n, k) = (josephus(n - 1, k) + k-1) % n + 1
    // josephus(1, k) = 1
    // After the first person (kth from beginning) is killed, n-1 persons are left. So we call josephus(n – 1, k)
    // to get the position with n-1 persons. But the position returned by josephus(n – 1, k) will consider the position
    // starting from k%n + 1. So, we must make adjustments to the position returned by josephus(n – 1, k).
    public static int josephus(int n, int k) {
        if (n == 1) return 1;
        return (josephus(n - 1, k) + k-1) % n + 1;
    }


    // Another way to solve this is to simulate the situation with Circular Linked List
    // Use 1 variable to record the how many step walked
    // 1 prev node to help delete nodes
    public static int josephus2(int n, int k) {
        ListNode curr = createCircularList(n);
        ListNode prev = null;
        int counter = 1;

        while (curr != null && curr.next != null) { // not the last one
            if (counter == k) { // need to kill this one
                if (k == 1) {
                    ListNode tmp = curr.next;
                    curr.next = null;
                    curr = tmp;
                } else {
                    prev.next = curr.next;
                    curr.next = null;
                    curr = prev.next;
                }

                counter = 1; // reset counter
            } else {
                prev = curr;
                curr = curr.next;
                counter++;
            }
        }

        return prev.val;
    }

    private static ListNode createCircularList(int n) {
        int i=1;
        ListNode head = new ListNode(i++);
        ListNode prev = head;
        ListNode curr = null;
        while(i<=n) {
            curr = new ListNode(i++);
            prev.next = curr;
            prev = curr;
        }
        if (curr != null) {
            curr.next = head;
        }
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        //ListNode head = createCircularList(7);
        System.out.println(josephus(14,3));
        System.out.println(josephus2(14,3));
    }

}
