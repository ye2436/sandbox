package interview.fb.lc;

import java.util.*;

/**
 * 314. Binary Tree Vertical Order Traversal Add to List

 Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

 If two nodes are in the same row and column, the order should be from left to right.

 Examples:

 Given binary tree [3,9,20,null,null,15,7],
         3
        /\
       /  \
      9  20
         /\
        /  \
       15   7
 return its vertical order traversal as:
 [
 [9],
 [3,15],
 [20],
 [7]
 ]
 Given binary tree [3,9,8,4,0,1,7],
        3
       /\
      /  \
     9   8
    /\  /\
   /  \/  \
   4  01   7
 return its vertical order traversal as:
 [
 [4],
 [9],
 [3,0,1],
 [8],
 [7]
 ]
 Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
        3
       /\
      /  \
     9   8
    /\  /\
   /  \/  \
   4  01   7
      /\
     /  \
    5   2
 return its vertical order traversal as:
 [
 [4],
 [9,5],
 [3,0,1],
 [8,2],
 [7]
 ]
 */
public class BinaryTreeVerticalOrderTraversal {

    // Take root as center point, give it a column value 0. And let its left sub tree be -1, right sub tree be 1.
    // Apply this rule to all nodes. For each node, its left subtree's column should be the node's column-1, and right be column+1.
    // This way, we will group all nodes correctly with their vertical columns.
    // Use BFS to traverse the tree, and group the nodes with same column # to the same list, and use column # as key.
    // And then convert the map to result list ( smallest column number first )
    // To convert, we could keep a min and max column will traversing, and use min/max to get the list from map.
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>(); // column -> nodes
        Queue<TreeNode> queue = new LinkedList<>(); // nodes for BFS traversal
        Queue<Integer> columns = new LinkedList<>(); // columns for each node in the same order as in BFS queue
        queue.offer(root);
        columns.offer(0);
        int min = 0;
        int max = 0;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int column = columns.poll();
            if (node.left != null) {
                queue.offer(node.left);
                columns.offer(column-1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                columns.offer(column+1);
            }

            if (!map.containsKey(column)) {
                map.put(column, new ArrayList<>());
            }
            map.get(column).add(node.val);

            // update min & max column numbers. to get the range of columns for later use.
            min = Math.min(min, column);
            max = Math.max(max, column);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i=min; i<=max; i++) { // because the column numbers must be continuous
            res.add(map.get(i));
        }
        return res;
    }

    int min = 0;
    int max = 0;
    public List<List<Integer>> verticalOrder_dfs(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        dfs(root, 0, map);
        for (int i=min; i<=max; i++) {
            res.add(map.get(i));
        }
        return res;
    }

    private void dfs(TreeNode node, int col, Map<Integer, List<Integer>> map) { // map: col -> list of node vals
        if(node == null) return;
        if (!map.containsKey(col)) {
            map.put(col, new ArrayList<>());
            min = Math.min(min, col);
            max = Math.max(max, col);
        }
        map.get(col).add(node.val);
        dfs(node.left, col-1, map);
        dfs(node.right, col-2, map);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
