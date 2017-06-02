package interview.am;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * 460. LFU Cache

 Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LFUCache cache = new LFUCache( 2 ); // capacity

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
 */
public class LFUCacheImpl {

    // We want to keep track of frequency. And at the same time, with the ones having the same frequency, we want to maintain their order of least recently used.
    // In that case, we could group the ones with same frequency to 1 doubly linked list node, and within the node, we use a linkedhashset to track their order
    // doubly linked list - store less frequent nodes first
    // linked hash set - store least recent key first

    public static class LFUCache {

        class Node {
            int frequency;
            // store only keys here, and use a separate hashmap to store key/value pair
            // alternatively, we could use a pair here? No - that way, we won't get value in O(1)
            LinkedHashSet<Integer> keys;
            Node prev;
            Node next;

            public Node(int frequency) {
                this.frequency = frequency;
            }
        }

        int capacity;
        Node head;
        Map<Integer, Integer> valueMap;
        Map<Integer, Node> nodeMap;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            valueMap = new HashMap<>();
            nodeMap = new HashMap<>();
        }

        // if not exists return -1
        // if exists, find the key in node and move it to next freq node
        public int get(int key) {
            if(!valueMap.containsKey(key)) return -1;
            incrementFreq(key);
            return valueMap.get(key);
        }

        // if exists, similar to get(), update value, get the key in node and move it to next freq node
        // if not exists, check capacity (remove as needed), add to valueMap, add to list head,
        public void put(int key, int value) {
            if (valueMap.containsKey(key)) {
                valueMap.put(key, value);
            } else {
                if (valueMap.size() == capacity) {
                    removeOld();
                }
                valueMap.put(key, value);
                addToHead(key);
            }

            incrementFreq(key);
        }

        // remove key from its current node, and move it to the next node (higher freq)
        private void incrementFreq(int key) {
            // first remove key from the node
            Node node = nodeMap.get(key);
            node.keys.remove(key);

            // move key to the next node
            if (node.next == null) {
                Node next = new Node(node.frequency + 1);
                next.keys.add(key);
                node.next = next;
                next.prev = node;
            } else if (node.next.frequency == node.frequency + 1) {
                node.next.keys.add(key);
            } else {
                Node next = new Node(node.frequency + 1);
                next.keys.add(key);
                next.next = node.next;
                node.next = next;
                next.prev = node;
                node.next.prev = node;
            }

            // update map reference
            nodeMap.put(key, node.next);
            if (node.keys.size() == 0) {
                removeNode(node);
            }
        }

        // when the node has 0 key, remove node from list
        private void removeNode(Node node) {
            if (node == head) {
                head = head.next;
                //head.prev = null;
            } else {
                node.prev.next = node.next;
            }
            if (node.next != null) { // includes head not null
                node.next.prev = node.prev;
            }
        }

        // remove the least freq from doubly linked first
        // if multiple keys exist in the node, remove the least recent ( the first in the linked hash set)
        private void removeOld() {
            if (head == null) return;
            int oldKey = 0;
            for (int n : head.keys) {
                oldKey = n;
                break;
            }
            head.keys.remove(oldKey);
            if (head.keys.size() == 0) removeNode(head);
            valueMap.remove(oldKey);
            nodeMap.remove(oldKey);
        }

        private void addToHead(int key) {
            if (head == null) {
                head = new Node(1);
                head.keys.add(key);
            } else if (head.frequency == 1){
                head.keys.add(key);
            } else {
                Node newHead = new Node(1);
                newHead.keys.add(key);
                newHead.next = head;
                head.prev = newHead;
                head = newHead;
            }
            nodeMap.put(key, head);
        }
    }

    /**
     * Your LFUCache object will be instantiated and called as such:
     * LFUCache obj = new LFUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }

}
