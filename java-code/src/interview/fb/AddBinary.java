package interview.fb;

/**
 * Created by yechen on 5/28/17.
 */
public class AddBinary {

    public static String addBinary(String a, String b) {

        StringBuilder sb = new StringBuilder();
        int len1 = a.length();
        int len2 = b.length();
        int i = 0;
        int carry = 0;

        while (i < len1 || i < len2) {
            int num = 0;
            if (i >= len1) {
                num += carry + (b.charAt(len2-1-i) - '0');
            } else if (i >= len2) {
                num += carry + (a.charAt(len1-1-i) - '0');
            } else {
                num += carry + (a.charAt(len1-1-i) - '0') + (b.charAt(len2-1-i) - '0');
            }
            carry = num / 2;
            sb.append(num % 2);
            i++;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
    }
}
