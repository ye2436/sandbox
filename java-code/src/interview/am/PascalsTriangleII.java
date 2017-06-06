package interview.am;

import java.util.ArrayList;
import java.util.List;

/**
 * #119. Pascal's lc.Triangle II
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalsTriangleII {
    // 第 n 行数字和为 2^{n-1} (2的n-1次方)。
    // 第n行的数字个数为n个。
    // 第n行的第k個數字為組合數 C (n − 1, k − 1)。 (start from 1)
    // The entry in the nth row and kth column of Pascal's triangle is denoted ( n k ). (start from 0)
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        for (int i=0; i<=rowIndex; i++) {
            // for each row, add 1 to the head of the list
            // 1,2,1 becomes 1,1,2,1
            // add element with its next, starting from index 1
            res.add(0,1);
            for (int j=1; j<res.size()-1; j++) {
                res.set(j, res.get(j)+res.get(j+1));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getRow(6));
    }
}
