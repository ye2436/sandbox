package interview.bb;

import interview.tb.EncodeAndDecodeStrings;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 297. Serialize and Deserialize Binary Tree
 * Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection link
 * to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on
 * how your serialization/deserialization algorithm should work. You just need to ensure that
 * a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * For example, you may serialize the following tree
 *      1
 *     / \
 *    2   3
 *       / \
 *      4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states.
 * Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // level order traversal
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int lastNum = 1; // # of not null nodes from last level
        int currNum = 0;
        while (!queue.isEmpty() && (lastNum != 0 || currNum != 0)) {
            TreeNode node = queue.poll();
            if (node != null) {
                lastNum--;
                sb.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
                if (node.left != null) {
                    currNum++;
                }
                if (node.right != null) {
                    currNum++;
                }
            } else {
                sb.append("#,");
                queue.offer(null);
                queue.offer(null);
            }

            if (lastNum == 0) {
                lastNum = currNum;
                currNum = 0;
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] values = data.split(",");
        return null;
    }

    public static void main(String[] args) {
        // Your Codec object will be instantiated and called as such:
        SerializeAndDeserializeBinaryTree codec = new SerializeAndDeserializeBinaryTree();
        TreeNode root = codec.generate();
        System.out.println(codec.serialize(root));
        System.out.println(codec.deserialize(codec.serialize(root)));
    }

    private TreeNode generate() {
        /*
        *       1
        *      / \
        *     2   3
        *        / \
        *       4   5
        * In-order (LNR):   2 1 4 3 5
        * */

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        t5.left = t6;

        return t1;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
