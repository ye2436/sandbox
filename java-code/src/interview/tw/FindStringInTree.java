package interview.tw;

import java.util.*;

public class FindStringInTree {
    // find in a tree if a string value exists
    // 1. binary tree?
    // 2. have cycles?
    // 3. what is the input and what to return?
    // i.e. ---- traversal of a graph using BST
    // so we need a queue and a set (to store visited nodes)

    /**
     * will require space proportional to the maximum number of nodes at a given depth.
     * This can be as much as the total number of nodes/2.
     * A more space-efficient approach for this type of traversal can be implemented using
     * an iterative deepening depth-first search.
     * */
    public boolean findInTree(TreeNode root, String word) {
        if (root == null || word == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                continue;
            }
            if (word.equals(curr.val)) {
                return true;
            }
            for (TreeNode child : curr.children) {
                queue.offer(child);
            }
        }

        return false;
    }

    public static boolean findInGraph(TreeNode root, String word) {
        if (root == null || word == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Set<TreeNode> visitedNodes = new HashSet<>();
        visitedNodes.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                continue;
            }
            if (word.equals(curr.val)) {
                return true;
            }
            for (TreeNode child : curr.children) {
                if (!visitedNodes.contains(child)) {
                    queue.offer(child);
                    visitedNodes.add(child);
                }
            }
        }

        return false;
    }



    public static void main(String[] args) {
        FindStringInTree instance = new FindStringInTree();
        TreeNode root = instance.generate(false);
        System.out.println(instance.findInTree(root, "3"));

        root = instance.generate(true);
        System.out.println(instance.findInGraph(root, "6"));
    }

    private TreeNode generate(boolean hasCycle) {
        /*
        *       1
        *      / \
        *     2   3
        *    / \
        *   4   5
        *  /
        * 6
        * */

        TreeNode t1 = new TreeNode("1");
        TreeNode t2 = new TreeNode("2");
        TreeNode t3 = new TreeNode("3");
        TreeNode t4 = new TreeNode("4");
        TreeNode t5 = new TreeNode("5");
        TreeNode t6 = new TreeNode("6");
        t1.children = Arrays.asList(t2, t3);
        t2.children = Arrays.asList(t4, t5);
        t4.children = Arrays.asList(t6);
        if (hasCycle) {
            t3.children = Arrays.asList(t4);
            t4.children = Arrays.asList(t5, t6);
            t5.children = Arrays.asList(t3);
        }

        return t1;
    }


    private class TreeNode {
        String val;
        List<TreeNode> children;

        TreeNode(String val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }
}
