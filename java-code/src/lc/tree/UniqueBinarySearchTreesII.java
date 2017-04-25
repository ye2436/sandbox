package lc.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * #95. Unique Binary Search Trees II
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 *  1         3     3      2      1
 *   \       /     /      / \      \
 *    3     2     1      1   3      2
 *   /     /       \                 \
 *  2     1         2                 3
 */
public class UniqueBinarySearchTreesII {

    // 在循环中调用递归函数求解子问题。
    // 思路是每次一次选取一个结点为根，然后递归求解左右子树的所有结果，
    // 最后根据左右子树的返回的所有子树，依次选取然后接上
    // （每个左边的子树跟所有右边的子树匹配，而每个右边的子树也要跟所有的左边子树匹配，总共有左右子树数量的乘积种情况），构造好之后作为当前树的结果返回。
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return helper(1, n);
    }

    // 返回从left到right之间的所有可能的二叉查找树
    public static List<TreeNode> helper(int left, int right) {
        List<TreeNode> res = new ArrayList<>();
        if (left > right) {
            res.add(null); // add empty lc.tree node
            return res;
        }

        for (int i=left; i<=right; i++) { // let i be root node
            List<TreeNode> leftSubtrees = helper(left, i-1); // all possible left BST's
            List<TreeNode> rightSubtrees = helper(i+1, right); // all possible right BST's
            for (int j=0; j<leftSubtrees.size(); j++) {
                for (int k=0; k<rightSubtrees.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftSubtrees.get(j);
                    root.right = rightSubtrees.get(k);
                    res.add(root);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<TreeNode> result = generateTrees(3);
        for (TreeNode node : result) {
            System.out.println(inorder(node));
        }
    }

    private static List<Integer> inorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            res.addAll(inorder(root.left));
            res.add(root.val);
            res.addAll(inorder(root.right));
        }
        return res;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
