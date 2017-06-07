package interview.am;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 508. Most Frequent Subtree Sum

 Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

 Examples 1
 Input:

    5
  /  \
 2   -3
 return [2, -3, 4], since all the values happen only once, return all of them in any order.
 Examples 2
 Input:

    5
  /  \
 2   -5
 return [2], since 2 happens twice, however -5 only occur once.
 Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 */
public class MostFrequentSubtreeSum {

    // post order traversal
    // calculate subtree sum first, and then the parent node
    // and then use a hashmap to keep frequency counts
    // * How do we convert the map with counts to an array of numbers with highest counts?
    //   We could store maxCount while we are traversing the tree. And when we have the entire frequency map, we get all the
    //   keys with value = maxCount. add keys to list, then convert to array;

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return new int[]{};
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> maxCount = new ArrayList<>();
        maxCount.add(0);
        subTreeSum(root, map, maxCount);

        List<Integer> list = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key) == maxCount.get(0)) {
                list.add(key);
            }
        }

        int[] array = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    private int subTreeSum(TreeNode node, Map<Integer, Integer> map, List<Integer> maxCount) {
        if (node == null) return 0;
        int left = subTreeSum(node.left, map, maxCount);
        int right = subTreeSum(node.right, map, maxCount);
        int sum = left+right+node.val;
        if (map.containsKey(sum)) {
            map.put(sum, map.get(sum)+1);
        } else {
            map.put(sum, 1);
        }
        if (map.get(sum) > maxCount.get(0)) {
            maxCount.set(0, map.get(sum));
        }
        return sum;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
