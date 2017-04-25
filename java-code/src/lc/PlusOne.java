package lc;

/**
 * #66. Plus One
 * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class PlusOne {
    public static int[] plusOne(int[] digits) {
        if (digits==null || digits.length ==0) return null;
        int carry = 1;
        for (int i=digits.length-1; i>=0; i--) {
            int temp = (digits[i]+carry) % 10;
            carry = (digits[i]+carry)/10;
            digits[i] = temp;
            if (carry == 0) return digits;
        }
        int[] newDigits = new int[digits.length+1];
        newDigits[0] = 1; // 后续位均为0
        return newDigits;
    }

    public static void main(String[] args) {
        int[] digits = new int[]{7,8,9};
        System.out.println(plusOne(digits));
    }
}
