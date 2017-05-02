package interview.ms;

/**
 * 用两种方式实现circular buffer，linkedlist和array，tradeoff。
 * http://www.geeksforgeeks.org/linked-list-vs-array/
 * Linked list provides following two advantages over arrays
 * 1) Dynamic size
 * 2) Ease of insertion/deletion
 * Linked lists have following drawbacks:
 * 1) Random access is not allowed. We have to access elements sequentially starting from the first node. So we cannot do binary search with linked lists.
 * 2) Extra memory space for a pointer is required with each element of the list.
 * 3) Arrays have better cache locality that can make a pretty big difference in performance.
 */
public class CircularBuffer {

    public class CircularBufferListImpl { // fill count
        public Object[] elements = null;

        private int capacity  = 0;
        private int writePos  = 0;
        private int available = 0;

        public CircularBufferListImpl(int capacity) {
            this.capacity = capacity;
            this.elements = new Object[capacity];
        }

        public void reset() {
            this.writePos = 0;
            this.available = 0;
        }

        public int capacity() { return this.capacity; }
        public int available(){ return this.available; }

        public int remainingCapacity() {
            return this.capacity - this.available;
        }

        public boolean put(Object element){

            if(available < capacity){
                if(writePos >= capacity){
                    writePos = 0;
                }
                elements[writePos] = element;
                writePos++;
                available++;
                return true;
            }

            return false;
        }

        public Object take() {
            if(available == 0){
                return null;
            }
            int nextSlot = writePos - available;
            if(nextSlot < 0){
                nextSlot += capacity;
            }
            Object nextObj = elements[nextSlot];
            available--;
            return nextObj;
        }
    }
}
