import java.util.HashMap;
import java.util.Map;

/**
 * #146. LRU Cache
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 *                   it should invalidate the least recently used item before inserting a new item.
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * Example:
 *  LRUCache cache = new LRUCache( 2 ); // capacity
 *  cache.put(1, 1);
 *  cache.put(2, 2);
 *  cache.get(1);       // returns 1
 *  cache.put(3, 3);    // evicts key 2
 *  cache.get(2);       // returns -1 (not found)
 *  cache.put(4, 4);    // evicts key 1
 *  cache.get(1);       // returns -1 (not found)
 *  cache.get(3);       // returns 3
 *  cache.get(4);       // returns 4
 */
public class LRUCacheImpl {

    // 按照题目要求，要实现get和set功能，为了满足随机存储的需求我们首先想到的一般是用数组，如果用链表会有O(n)的访问时间。
    // 然而他又有另一个条件就是要维护least used的队列，也就是说经常用的放在前面，用的少的放在后面。这样当资源超过cache的容积的时候就可以把用得最少的资源删掉。
    // 这就要求我们要对节点有好的删除和插入操作，这个要求又会让我们想到用链表，因为数组的删除和插入是O(n)复杂度的。
    // 那么我们能不能维护一个数据结构使得访问操作和插入删除操作都是O(1)复杂度的呢？答案是肯定的。
    // 这个数据结构比较复杂，是用一个hash表加上一个双向链表来实现。基本思路是这样的，用一个hash表来维护结点的位置关系，
    // 也就是hash表的key就是资源本身的key，value是资源的结点（包含key和value的信息）。
    // 然后把结点维护成一个双向链表构成的队列，这样子如果我们要访问某一个结点，那么可以通过hash表和key来找到结点，从而取到相应的value。
    // 而当我们想要删除或者插入结点时，我们还是通过hash表找到结点，然后通过双向链表和队列的尾结点把自己删除同时插入到队尾。
    // 通过hash表访问结点我们可以认为是O(1)的操作（假设hash函数足够好），然后双向链表的插入删除操作也是O(1)的操作。
    // 如此我们便实现了用O(1)时间来完成所有LRU cache的操作。空间上就是对于每一个资源有一个hash表的的项以及一个对应的结点（包含前后指针和资源的<key, value>）。
    public static class LRUCache {


        private int capacity;
        private int num;
        private Map<Integer, Node> map;
        private Node first;
        private Node last;


        public LRUCache(int capacity) {
            this.capacity = capacity;
            num = 0;
            map = new HashMap<>();
            first = null;
            last = null;
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node curr = map.get(key);
            // 刚刚用到的应该放在队尾，然后当需要踢掉元素的时候从队头踢出
            if (curr != last) { //reorder list
                if (curr == first) {
                    first = first.next;
                    first.pre = null; // can be combined with curr.next.pre = curr.pre;
                } else {
                    curr.pre.next = curr.next;
                    curr.next.pre = curr.pre;
                }
                last.next = curr;
                curr.pre = last;
                curr.next = null;
                last = curr;
            }
            return curr.value;
        }

        public void put(int key, int value) {
            if (map.get(key) == null) { // node does not exist
                Node node = new Node(key, value);
                map.put(key, node);
                if (num >= capacity) { // delete first node
                    map.remove(first.key);
                    first = first.next;
                    if (first != null) {
                        first.pre = null;
                    } else { // 这种情况是capacity只有1，删了一个以后就空了，那last也设为空
                        last = null;
                    }
                    num--;
                }

                if (first != null && last != null) { // 链表不为空
                    last.next = node;
                } else {
                    first = node;
                }
                node.pre = last;
                last = node;
                num++;

            } else { // node already exists
                Node node = map.get(key);
                node.value = value;
                if (node != last) { //reorder list
                    if (node == first) {
                        first = first.next;
                        first.pre = null; // can be combined with curr.next.pre = curr.pre;
                    } else {
                        node.pre.next = node.next;
                        node.next.pre = node.pre;
                    }
                    last.next = node;
                    node.pre = last;
                    node.next = null;
                    last = node;
                }
            }
        }

        class Node {
            int key;
            int value;
            Node pre;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
    }
}


