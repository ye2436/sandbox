package interview.li;

import java.util.*;

/**
 * Given a list of nodes, each with a left child and a right child (they can be null),
 * determine if all nodes belong in a single valid binary tree. The root is not given.
 */
public class SingleValidTree {
    public boolean isValid(List<TreeNode> nodes){

        // 1. found if child node has multiple parents. use a hashset to store children nodes
        Set<TreeNode> children = new HashSet<>();
        for (TreeNode node : nodes) {
            if (node.left != null) {
                if (children.contains(node.left)) { // left is already some other node's child
                    return false;
                }
                children.add(node.left);
            }
            if (node.right != null) {
                if (children.contains(node.right)) { // right is already some other node's child
                    return false;
                }
                children.add(node.right);
            }
        }

        // 2. find the root node. it should not be in children nodes set. it should be only 1 root.
        TreeNode root = null;
        int count = 0;
        for (TreeNode node : nodes) {
            if (children.contains(node)) {
                continue;
            } else {
                root = node;
                count++;
            }
        }
        if (root == null || count != 1) {
            return false; // can have only 1 root
        }

        // 3. use bfs to construct the binary tree. remove child node from hashset while traversing the tree.
        //    there should be no child node left afterwards.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                children.remove(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
                children.remove(node.right);
            }
        }

        return children.size() == 0;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
