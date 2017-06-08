package interview.am.other;

/**
 * http://www.geeksforgeeks.org/queue-set-1introduction-and-array-implementation/
 *
 * 4 actions:
 * Enqueue: Adds an item to the queue. If the queue is full, then it is said to be an Overflow condition.
 * Dequeue: Removes an item from the queue. The items are popped in the same order in which they are pushed. If the queue is empty, then it is said to be an Underflow condition.
 * Front: Get the front item from queue.
 * Rear: Get the last item from queue.
 */
public class ImplementQueueUsingArray {

    public static class MyQueue<T> {

        T[] queue;
        // use 2 variables to track the start and the end of the queue.
        // * We can't use the following statement to check if it's full: (tail+1) % capacity == head
        // because in the case of capacity = 1, this does not work.
        // We can use a variable size to keep current # of elements in the queue
        // * The tail points at the next insert point, head points at the head element
        // * when incrementing the head/tail index, need to check boundary
        // * This can be further improved by removing head ( can be implied as (n + tail - size) % n )
        int head;
        int tail;
        int size;

        public MyQueue(int capacity) {
            queue = (T[]) new Object[capacity];
        }

        public void enqueue(T t) {
            if (isFull()) {
                // reached capacity, throw exception
                System.out.println("reached capacity");
                return;
            }
            queue[tail] = t; // set tail to t
            tail = (tail+1) % queue.length; //  increment tail, check boundary
            size++;
        }

        public T dequeue() {
            if (isEmpty()) {
                // empty queue, throw exception
                System.out.println("empty queue");
                return null;
            }
            T val = queue[head];
            head = (head+1) % queue.length; // increment head index
            size--;
            return val; // return the head value
        }

        public T peekFirst() {
            if (isEmpty()) {
                // empty queue, throw exception
                System.out.println("empty queue");
                return null;
            }
            return queue[head];
        }

        // This is not a usual method for Queue. It's used for Deque, but not usually for queue. but we implement it anyway
        public T peekLast() {
            if (isEmpty()) {
                // empty queue, throw exception
                System.out.println("empty queue");
                return null;
            }
            // tail is pointed at next empty slot, so to return last element, we need to decrease it
            if (tail == 0) return queue[queue.length-1];
            return queue[tail-1];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == queue.length;
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>(3);
        System.out.println(queue.dequeue());
        queue.enqueue(1);
        System.out.println("First: " + queue.peekFirst());
        System.out.println("Last: " + queue.peekLast());
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        queue.enqueue(4);
        System.out.println("First: " + queue.peekFirst());
        System.out.println("Last: " + queue.peekLast());
        queue.enqueue(5);
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
