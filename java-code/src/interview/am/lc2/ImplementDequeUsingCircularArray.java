package interview.am.lc2;

/**
 * http://www.geeksforgeeks.org/implementation-deque-using-circular-array/
 * Operations on Deque:
 Mainly the following four basic operations are performed on queue:
 insertFront(): Adds an item at the front of Deque.
 insertRear(): Adds an item at the rear of Deque.
 deleteFront(): Deletes an item from front of Deque.
 deleteRear(): Deletes an item from rear of Deque.

 In addition to above operations, following operations are also supported
 getFront(): Gets the front item from queue.
 getRear(): Gets the last item from queue.
 isEmpty(): Checks whether Deque is empty or not.
 isFull(): Checks whether Deque is full or not.
 */
public class ImplementDequeUsingCircularArray {

    // Similar to queue. See other impl for detailed explanation
    // Here we want to use just one pointer (head for example), so we don't run into the situation
    // where tail and head are fighting for the last available slot. and we won't be able to tell tail from head apart.
    // tail = (head + size) % n
    // * head is the next insert point, head+1 is the first element, tail is the last element
    public static class MyDeque<T> {
        T[] queue;
        int head;
        int size;

        public MyDeque(int capacity) {
            queue = (T[]) new Object[capacity];
        }

        public void insertFront(T t) {
            if (isFull()) {
                System.out.println("reached capacity");
                return;
            }
            queue[head] = t;
            head = head == 0? queue.length -1 : head-1;
            size++;
        }

        public void insertRear(T t) {
            if (isFull()) {
                System.out.println("reached capacity");
                return;
            }
            queue[(head + size) % queue.length + 1] = t;
            size++;
        }

        public T deleteFront() {
            if (isEmpty()) {
                System.out.println("empty queue");
                return null;
            }
            T val = queue[(head+1) % queue.length];
            head = (head+1) % queue.length;
            size--;
            return val;
        }

        public T deleteRear() {
            if (isEmpty()) {
                System.out.println("empty queue");
                return null;
            }
            T val = queue[(head + size) % queue.length];
            size--;
            return val;
        }

        public T peekFront() {
            if (isEmpty()) {
                System.out.println("empty queue");
                return null;
            }
            return queue[(head+1) % queue.length];
        }

        public T peekRear() {
            if (isEmpty()) {
                System.out.println("empty queue");
                return null;
            }
            return queue[(head + size) % queue.length];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == queue.length;
        }
    }

    public static void main(String[] args) {
        MyDeque<Integer> queue = new MyDeque<>(5);
        queue.insertRear(1);
        System.out.println("First: " + queue.peekFront());
        System.out.println("Last: " + queue.peekRear());
        queue.insertFront(2);
        queue.insertFront(3);
        System.out.println("First: " + queue.peekFront());
        System.out.println("Last: " + queue.peekRear());

        while (!queue.isEmpty()) {
            System.out.println(queue.deleteRear());
        }
    }
}
