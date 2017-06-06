package interview.am;

import java.util.ArrayList;
import java.util.List;

/**
 * #89. Gray Code
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
 * A gray code sequence must begin with 0.
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 *  00 - 0
 *  01 - 1
 *  11 - 3
 *  10 - 2
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 */
public class GrayCode {

    public List<Integer> grayCode_3(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 1<<n; i++) result.add(i ^ i>>1);
        return result;
    }

    // from n-1 bits to n bits
    // first add 0 to the highest bit, these are half of our gray code
    // then mirror them, replace 0 with 1 to the highest bit. now we have our other half
    public List<Integer> grayCode_2(int n) {
        List<Integer> res = new ArrayList<Integer>();
        if(n<0)
            return res;
        if(n==0)
        {
            res.add(0);
            return res;
        }
        res.add(0);
        res.add(1);
        for(int i=2;i<=n;i++)
        {
            int size = res.size();
            for(int j=size-1;j>=0;j--)
            {
                res.add(res.get(j)+(1<<(i-1)));
            }
        }
        return res;
    }

    public static List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        if (n==0) {
            res.add(0);
        } else {
            helper(n, new StringBuilder(), res);
        }

        return res;
    }

    private static void helper(int n, StringBuilder sb, List<Integer> res){
        if (sb.length() == n) {
            res.add(Integer.parseInt(sb.toString(), 2));
            return;
        }
        for (int i=0; i<=1; i++) {
            sb.append(i);
            helper(n, sb, res);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(grayCode(0));
    }
}
