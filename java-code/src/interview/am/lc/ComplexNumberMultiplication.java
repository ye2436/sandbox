package interview.am.lc;

/**
 * 537. Complex Number Multiplication

 Given two strings representing two complex numbers.

 You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

 Example 1:
 Input: "1+1i", "1+1i"
 Output: "0+2i"
 Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 Example 2:
 Input: "1+-1i", "1+-1i"
 Output: "0+-2i"
 Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 Note:

 The input strings will not have extra blank.
 The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.


 */
public class ComplexNumberMultiplication {

    // Let a = x1 + y1 i
    //     b = x2 + y2 i
    // then a*b = (x1x2 - y1y2) + (x1y2 + x2y1) i
    public String complexNumberMultiply(String a, String b) {
        if (a == null || b == null) return null;
        String[] p1 = a.split("\\+");
        int x1 = Integer.valueOf(p1[0]);
        int y1 = Integer.valueOf(p1[1].substring(0, p1[1].length()-1));
        String[] p2 = b.split("\\+");
        int x2 = Integer.valueOf(p2[0]);
        int y2 = Integer.valueOf(p2[1].substring(0, p2[1].length()-1));

        int x = x1*x2 - y1*y2;
        int y = x1*y2 + x2*y1;
        StringBuilder sb = new StringBuilder();
        sb.append(x).append('+').append(y).append('i');

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(Integer.valueOf("-1"));
    }
}
