/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.
 * Note:
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings {
    public static String multiply(String num1, String num2) {
        if (num1==null || num1.length()==0 || num2==null ||num2.length() == 0) return "";
        if (num1.equals("0") || num2.equals("0")) return "0";


        StringBuilder res = new StringBuilder();
        int num = 0;
        // 从低位到高位对每一位进行计算，假设第一个数长度是n，第二个数长度是m，我们知道结果长度为m+n或者m+n-1（没有进位的情况）。
        // 对于某一位i，要计算这个位上的数字，我们需要对所有能组合出这一位结果的位进行乘法，即第1位和第i位，第2位和第i-1位，... ，
        // 然后累加起来，最后我们取个位上的数值，然后剩下的作为进位放到下一轮循环中。
        for (int i=num1.length()+num2.length(); i>0; i--) { // i: from m+n to 1
            // 取i-1与num1长度的小值，避免运算超过num1长度时高位为空的情况
            for (int j=Math.min(i-1, num1.length()); j>0; j--) { //why i-1? 因为如果没进位最长是m+n-1，那第m+n位是进位来的，不需要多计算
                if (i-j < num2.length()) { // why?
                    num += (num1.charAt(j-1)-'0') * (num2.charAt(i-1-j)-'0');
                }

            }
            if(i!=1 || num>0)
                res.append(num%10);
            num = num/10; // carry
        }

        return res.reverse().toString(); //是由低位到高位放入结果串的，所以最后要进行一次reverse
    }

    public static void main(String[] args) {
        System.out.println(multiply("", ""));
    }
}
