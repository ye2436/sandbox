package interview.am.other;

import java.util.Stack;

/**
 * 232. Implement Queue using Stacks
 * Implement the following operations of a queue using stacks.
 *  push(x) -- Push element x to the back of queue.
 *  pop() -- Removes the element from in front of queue.
 *  peek() -- Get the front element.
 *  empty() -- Return whether the queue is empty.
 *  Notes:
 *  You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size,
 *  and is empty operations are valid.
 *  Depending on your language, stack may not be supported natively. You may simulate a stack by using a list
 *  or deque (double-ended queue), as long as you use only standard operations of a stack.
 *  You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */
public class ImplementQueueUsingStacks {

    // http://www.geeksforgeeks.org/queue-set-1introduction-and-array-implementation/
    // http://www.geeksforgeeks.org/queue-using-stacks/

    // Method 1 (By making enQueue operation costly) This method makes sure that oldest entered element is always
    // at the top of stack 1, so that deQueue operation just pops from stack1. To put the element at top of stack1, stack2 is used.
    // enQueue(q, x)
    // 1) While stack1 is not empty, push everything from stack1 to stack2.
    // 2) Push x to stack1 (assuming size of stacks is unlimited).
    // 3) Push everything back to stack1.
    // dnQueue(q)
    // 1) If stack1 is empty then error
    // 2) Pop an item from stack1 and return it

    // Method 2 (By making deQueue operation costly)
    // In this method, in en-queue operation, the new element is entered at the top of stack1.
    // In de-queue operation, if stack2 is empty then all the elements are moved to stack2 and finally top of stack2 is returned.
    // enQueue(q,  x)
    // 1) Push x to stack1 (assuming size of stacks is unlimited).
    // deQueue(q)
    // 1) If both stacks are empty then error.
    // 2) If stack2 is empty
    //      While stack1 is not empty, push everything from stack1 to stack2.
    // 3) Pop the element from stack2 and return it.
    //
    // Method 2 is definitely better than method 1.
    // Method 1 moves all the elements twice in enQueue operation, while method 2 (in deQueue operation)
    // moves the elements once and moves elements only if stack2 empty.


    // Time: Amortized O(1)
    // Each element only ever gets moved like that once, though, and only after we already spent time pushing it,
    // so the overall amortized cost for each operation is O(1).

    public class MyQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;

        /** Initialize your data structure here. */
        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (stack1.empty() && stack2.empty()) {
                // throw new Exception
            }
            if (!stack2.empty()) {
                return stack2.pop();
            }
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (stack1.empty() && stack2.empty()) {
                // throw new Exception
            }
            if (!stack2.empty()) {
                return stack2.peek();
            }
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
            return stack2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.empty() && stack2.empty();
        }
    }

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */
}
