package interview.am.lc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 449. Serialize and Deserialize BST

 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

 The encoded string should be as compact as possible.

 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeAndDeserializeBST {

    // Use pre-order traversal to serialize the BST. The encoded string will be simply nodes val divided by separator
    // Because of the nature of BST, we know everything larger root is on the right, every smaller is on the left.
    // We can use that to decode


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return preorder(root);
    }

    private String preorder(TreeNode node) {
        if (node == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(node.val).append(",");
        if (node.left != null) {
            sb.append(preorder(node.left)).append(",");
        }
        if (node.right != null) {
            sb.append(preorder(node.right)).append(",");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) return null;
        String[] array = data.split(",");
        Queue<Integer> queue = new LinkedList<>();
        for (String val : array) {
            if (val.length()>0) {
                queue.offer(Integer.parseInt(val));
            }
        }
        return createNode(queue);
    }

    private TreeNode createNode(Queue<Integer> queue) {
        int val = queue.poll();
        TreeNode node = new TreeNode(val);
        Queue<Integer> leftQueue = new LinkedList<>();
        while (!queue.isEmpty() && queue.peek() < val) {
            leftQueue.offer(queue.poll());
        }
        if (!leftQueue.isEmpty()) {
            node.left = createNode(leftQueue);
        }
        if (!queue.isEmpty()) {
            node.right = createNode(queue);
        }
        return node;
    }

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
