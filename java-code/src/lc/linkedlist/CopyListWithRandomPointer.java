package lc.linkedlist;

import java.util.*;

/**
 * #138. Copy List with Random Pointer
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 */
public class CopyListWithRandomPointer {

    // 主要的问题就是有可能这个random指向的位置还没有被copy到，所以解决方法都是多次扫描list。

    // 第一种方法，就是使用HashMap来做，HashMap的key存原始pointer，value存新的pointer。
    // 第一遍，先不copy random的值，只copy数值建立好新的链表。并把新旧pointer存在HashMap中。
    // 第二遍，遍历旧表，复制random的值，因为第一遍已经把链表复制好了并且也存在HashMap里了，
    // 所以只需从HashMap中，把当前旧的node.random作为key值，得到新的value的值，并把其赋给新node.random就好。
    public static RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;

        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        map.put(head, new RandomListNode(head.label));
        RandomListNode pre = map.get(head); // previous copied node
        RandomListNode curr = head.next; // the next node to copy
        while (curr != null) {
            map.put(curr, new RandomListNode(curr.label));
            pre.next = map.get(curr);
            curr = curr.next;
            pre = pre.next;
        }
        /**
         * for (RandomListNode old : map.keySet()) {
         *     map.get(old).random = old.random;
         * }
         * the above code is wrong, because the random from new node is pointing to old node
         */
        for (RandomListNode old : map.keySet()) {
            map.get(old).random = map.get(old.random);
        }
        /**
         * Or use while loop. this is slightly faster
         */
        /*curr = head;
        RandomListNode newHead = map.get(head);
        while (curr != null) {
            newHead.random = map.get(curr.random);
            curr = curr.next;
            newHead = newHead.next;
        }*/

        return map.get(head);
    }

    // 第二种方法不使用HashMap来做，使空间复杂度降为O(1)，不过需要3次遍历list，时间复杂度为O(3n)=O(n)。
    // 第一遍，对每个node进行复制，并插入其原始node的后面，新旧交替，变成重复链表。如：原始：1->2->3->null，复制后：1->1->2->2->3->3->null
    // 第二遍，遍历每个旧node，把旧node的random的复制给新node的random，因为链表已经是新旧交替的。所以复制方法为：
    //          node.next.random = node.random.next
    // 前面是说旧node的next的random，就是新node的random，后面是旧node的random的next，正好是新node，是从旧random复制来的。
    // 第三遍，则是把新旧两个表拆开，返回新的表即可。
    public static RandomListNode copyRandomList_2(RandomListNode head) {
        if (head == null) return null;
        RandomListNode curr = head;
        while (curr != null) {
            RandomListNode copy = new RandomListNode(curr.label);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        curr = head;
        RandomListNode newHead = curr.next;
        while (curr != null) {
            RandomListNode newNode = curr.next;
            curr.next = newNode.next;
            if (curr.next != null) {
                newNode.next = curr.next.next;
            }
            curr = curr.next;
        }

        return newHead;
    }

    public static void main(String[] args) {
        RandomListNode node = generate();
        System.out.println(print(node));
        System.out.println(print(copyRandomList(node)));
        System.out.println(print(copyRandomList_2(node)));
    }

    private static RandomListNode generate() {
        RandomListNode n1 = new RandomListNode(1);
        RandomListNode n2 = new RandomListNode(2);
        RandomListNode n3 = new RandomListNode(3);
        RandomListNode n4 = new RandomListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n1.random = n4;
        n2.random = n4;
        n3.random = n4;
        n4.random = n2;
        return n1;
    }

    private static String print(RandomListNode node) {
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append("(" + node.label + ", random: " + node.random.label + ") --> ");
            node = node.next;
        }
        return sb.toString();
    }

    private static class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) {
            this.label = x;
        }
    }
}