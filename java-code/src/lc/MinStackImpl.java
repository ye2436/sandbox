package lc;

import java.util.ArrayList;
import java.util.List;

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

    public static class MinStack {

        List<Integer> stack;
        List<Integer> minStack;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new ArrayList<>();
            minStack = new ArrayList<>();
        }

        public void push(int x) {
            stack.add(x);
            if (minStack.isEmpty() || minStack.get(minStack.size()-1) >= x) {
                minStack.add(x);
            }
        }

        public void pop() {
            int topElem = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            if (!minStack.isEmpty() && minStack.get(minStack.size()-1) == topElem) {
                minStack.remove(minStack.size()-1);
            }
        }

        public int top() {
            if (stack.isEmpty()) return 0;
            return stack.get(stack.size()-1);
        }

        public int getMin() {
            if(minStack.isEmpty()) return 0;
            return minStack.get(minStack.size()-1);
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
