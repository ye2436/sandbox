package interview.ms;

import interview.li.SingleValidTree;

/**
 * Given preorder traversal of a binary search tree, construct the BST.
 */
public class ConstructBSTFromPreorder {

    /*
    * Method 1 ( O(n^2) time complexity )
The first element of preorder traversal is always root. We first construct the root. Then we find the index of first element which is greater than root. Let the index be ‘i’. The values between root and ‘i’ will be part of left subtree, and the values between ‘i+1’ and ‘n-1’ will be part of right subtree. Divide given pre[] at index “i” and recur for left and right sub-trees.
For example in {10, 5, 1, 7, 40, 50}, 10 is the first element, so we make it root. Now we look for the first element greater than 10, we find 40. So we know the structure of BST is as following.

             10
           /    \
          /      \
  {5, 1, 7}       {40, 50}
We recursively follow above steps for subarrays {5, 1, 7} and {40, 50}, and get the complete tree.
    * */

    /*
    * Method 2 ( O(n) time complexity )
The idea used here is inspired from method 3 of this post. The trick is to set a range {min .. max} for every node.
Initialize the range as {INT_MIN .. INT_MAX}. The first node will definitely be in range, so create root node.
To construct the left subtree, set the range as {INT_MIN …root->data}. If a values is in the range {INT_MIN .. root->data},
the values is part part of left subtree. To construct the right subtree, set the range as {root->data..max .. INT_MAX}.
    * */

    // The main function to construct BST from given preorder traversal.
    // This function mainly uses constructTreeUtil()
    public TreeNode constructTree(int pre[], int size) {
        int preIndex = 0;
        return constructTreeUtil(pre, new Index(), pre[0], Integer.MIN_VALUE,
                Integer.MAX_VALUE, size);
    }

    // A recursive function to construct BST from pre[]. preIndex is used
    // to keep track of index in pre[].
    TreeNode constructTreeUtil(int pre[], Index preIndex, int key,
                           int min, int max, int size) {

        // Base case
        if (preIndex.index >= size) {
            return null;
        }

        TreeNode root = null;

        // If current element of pre[] is in range, then
        // only it is part of current subtree
        if (key > min && key < max) {

            // Allocate memory for root of this subtree and increment *preIndex
            root = new TreeNode(key);
            preIndex.index = preIndex.index + 1;

            if (preIndex.index < size) {

                // Contruct the subtree under root
                // All nodes which are in range {min .. key} will go in left
                // subtree, and first such node will be root of left subtree.
                root.left = constructTreeUtil(pre, preIndex, pre[preIndex.index],
                        min, key, size);

                // All nodes which are in range {key..max} will go in right
                // subtree, and first such node will be root of right subtree.
                root.right = constructTreeUtil(pre, preIndex, pre[preIndex.index],
                        key, max, size);
            }
        }

        return root;
    }

    class Index {
        int index = 0;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
