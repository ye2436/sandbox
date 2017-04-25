package lc.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * #102. Binary Tree Level Order Traversal
 * Given a binary lc.tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * For example:
 * Given binary lc.tree [3,9,20,null,null,15,7],
 *      3
 *     / \
 *    9  20
 *      /  \
 *     15   7
 * return its level order traversal as:
 * [
 *  [3],
 *  [9,20],
 *  [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {

    /**
     * Listed below is pseudocode for a simple queue based level order traversal, and will require space proportional to the maximum number of nodes at a given depth.
     * This can be as much as the total number of nodes/2. A more space-efficient approach for this type of traversal can be implemented using
     * an iterative deepening depth-first search.
     *  levelorder(root)
     *      q ← empty queue
     *      q.enqueue(root)
     *      while (not q.isEmpty())
     *          node ← q.dequeue()
     *          visit(node)
     *          if (node.left ≠ null)
     *              q.enqueue(node.left)
     *          if (node.right ≠ null)
     *              q.enqueue(node.right)
     * Source: https://en.wikipedia.org/wiki/Tree_traversal#Breadth-first_search
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> levelResult = new ArrayList<>();
        TreeNode curr = root;
        Queue<TreeNode> queue = new LinkedList<>();// list max size: 2^n
        queue.offer(curr); // or add
        // 这是BFS中常用的两个变量，curNum表示当前层数扫描到的元素个数，lastNum是上一层的元素个数，维护这两个变量可以统计出当前所在的层数
        // （相同的用法参见 Word Ladder）
        int currNum = 0; // number of nodes from the current level
        int lastNum = 1; // number of nodes from the last level (we already added the root node to the queue, so num is 1)

        while (!queue.isEmpty()) {
            curr = queue.poll(); // or pop
            levelResult.add(curr.val);
            lastNum--;

            if (curr.left != null) {
                queue.add(curr.left);
                currNum++;
            }
            if (curr.right != null) {
                queue.add(curr.right);
                currNum++;
            }

            if (lastNum == 0) {
                res.add(levelResult);
                lastNum = currNum;
                currNum = 0;
                levelResult = new ArrayList<>();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = generate();
        System.out.println(levelOrder(root));
    }

    private static TreeNode generate() {
        /*
        *       1
        *      / \
        *     2   3
        *    / \
        *   4   5
        *  /
        * 6
        * level order: 1 2 3 4 5 6
        *
        *       1
        *      / \
        *     2
        *    / \
        *   3
        *  /
        * 4
        * */

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        //TreeNode t6 = new TreeNode(6);
        //t1.left = t2;
        //t1.right = t3;
        //t2.left = t4;
        //t2.right = t5;
        //t4.left = t6;
        t1.left = t2;
        t2.left = t3;
        t3.left = t4;
        t4.left = t5;
        return t1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
