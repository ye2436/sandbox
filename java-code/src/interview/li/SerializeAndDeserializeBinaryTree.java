package interview.li;

import java.util.Arrays;
import java.util.LinkedList;
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

    // use 2 null(#) to denote a leaf. iterative pre-order traversal

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sb.toString();
    }

    public void helper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,");
            return;
        }
        sb.append(node.val + ",");
        helper(node.left, sb);
        helper(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        Queue<String> vals = new LinkedList();
        vals.addAll(Arrays.asList(data.split(",")));
        return helper(vals);
    }

    public TreeNode helper(Queue<String> vals) {
        String val = vals.poll();
        if (val.equals("#")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = helper(vals);
        node.right = helper(vals);
        return node;
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
