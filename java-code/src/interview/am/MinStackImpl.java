package interview.am;

import java.util.Stack;

/**
 * #155. Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *  push(x) -- Push element x onto stack.
 *  pop() -- Removes the element on top of the stack.
 *  top() -- Get the top element.
 *  getMin() -- Retrieve the minimum element in the stack.
 * Example:
 *  MinStack minStack = new MinStack();
 *  minStack.push(-2);
 *  minStack.push(0);
 *  minStack.push(-3);
 *  minStack.getMin();   --> Returns -3.
 *  minStack.pop();
 *  minStack.top();      --> Returns 0.
 *  minStack.getMin();   --> Returns -2.
 */
public class MinStackImpl {

    /**
     * The question is ask to construct One stack. So I am using one stack.

     The idea is to store the gap between the min value and the current value;

     The problem for my solution is the cast. I have no idea to avoid the cast. Since the possible gap between the current value and the min value could be Integer.MAX_VALUE-Integer.MIN_VALUE;
     */
    public static class MinStack {

        long min;
        Stack<Long> stack;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int x) {
            if (stack.isEmpty()) {
                stack.push(0l);
                min = x;
            } else {
                stack.push(x-min); // could be negative if x is the new min
                min = Math.min(min, x);
            }
        }

        public void pop() {
            if (!stack.isEmpty()) {
                long n = stack.pop();
                if (n<0) {
                    min = min-n; // update min if the current min is popped
                }
            }
        }

        public int top() {
            if (stack.isEmpty()) return 0;
            long top = stack.peek();
            if (top<0) {
                return (int)min; // if top is negative, it is the min. (DO NOT add top with min, that's incorrect)
            } else {
                return (int)(top + min);
            }
        }

        public int getMin() {
            if(stack.isEmpty()) return 0;
            return (int) min;
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        obj.push(4);
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());
    }
}
