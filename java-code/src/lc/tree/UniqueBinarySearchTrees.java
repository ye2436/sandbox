package lc.tree;

/**
 * #96. Unique Binary Search Trees
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 *  1         3     3      2      1
 *   \       /     /      / \      \
 *    3     2     1      1   3      2
 *   /     /       \                 \
 *  2     1         2                 3
 */
public class UniqueBinarySearchTrees {


    // 其实二叉查找树可以任意取根，只要满足中序遍历有序的要求就可以。
    // 从处理子问题的角度来看，选取一个结点为根，就把结点切成左右子树，
    // 以这个结点为根的可行二叉树数量就是左右子树可行二叉树数量的乘积，所以总的数量是将以所有结点为根的可行结果累加起来。
    public static int numTrees(int n) {
        if (n <= 0) return 0;
        int[] res = new int[n+1]; // res[i]表示含有i个结点的二叉查找树的数量
        res[0] = 1;
        res[1] = 1;
        for (int i=2; i<=n; i++) {
            for (int j=0; j<i; j++) {
                res[i] += res[j] * res[i-j-1]; // 减掉一个根节点
            }
        }

        return res[n];
    }

    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }
}
