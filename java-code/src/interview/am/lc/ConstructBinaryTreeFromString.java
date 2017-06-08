package interview.am.lc;

/**
 *  536. Construct Binary Tree from String

 You need to construct a binary tree from a string consisting of parenthesis and integers.

 The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

 You always start to construct the left child node of the parent first if it exists.

 Example:

 Input: "4(2(3)(1))(6(5))"
 Output: return the tree root node representing the following tree:

        4
      /   \
     2     6
    / \   /
   3   1 5

 Note:

 There will only be '(', ')', '-' and '0' ~ '9' in the input string.
 An empty tree is represented by "" instead of "()".

 */
public class ConstructBinaryTreeFromString {

    // Use 4(2(3)(1))(6(5)) as example. The first number 4 is the node value, (2(3)(1)) is the left subtree,
    // and (6(5)) is the right subtree. The main issue here is how we divide the 3 parts. And then we can
    // build the tree recursively.
    // 1. anything before the first paren is the node val.
    // 2. When paren counts match for the first time, we have our left subtree
    // 3. the rest is the right subtree
    // ** Note: we get the substring for subtree, do not include the outer parens. (2(3)(1)) should be 2(3)(1)

    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) return null;
        int firstParen = s.indexOf('(');
        int val = firstParen == -1 ? Integer.valueOf(s) :Integer.valueOf(s.substring(0, firstParen));
        TreeNode node = new TreeNode(val);
        if (firstParen == -1) return node;
        int start = firstParen;
        int parenCount = 0;
        for (int i=start; i<s.length(); i++) {
            if (s.charAt(i) == '(') {
                parenCount++;
            } else if (s.charAt(i) == ')') {
                parenCount--;
            }

            // if parentCount = 0, means currently the parens are matched. we are either at the end of left subtree or right subtree
            if (parenCount == 0 && start == firstParen) { // start at firstParen, this is the left subtree
                node.left = str2tree(s.substring(start+1, i));
                start = i+1;
            } else if (parenCount == 0) {
                node.right = str2tree(s.substring(start+1, i));
            }
        }
        return node;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
