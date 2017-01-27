/**
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 */
public class AddBinary {
    public static String addBinary(String a, String b) {
        if (a==null||a.length()==0){
            if (b==null||b.length()==0) {
                return null;
            }
            return b;
        }
        if (b==null||b.length()==0) return a;

        int len = Math.max(a.length(), b.length());
        int diff = Math.abs(a.length() - b.length());
        String shorter;
        String longer;
        if (a.length() == len) {
            longer = a;
            shorter = b;
        } else {
            longer = b;
            shorter = a;
        }
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int i=len-1;
        for (; i>=diff; i--) {
            int d1 = longer.charAt(i) - '0';
            int d2 = shorter.charAt(i-diff) - '0';
            sb.append((carry + d1 + d2) % 2);
            carry = (carry + d1 + d2) / 2;
        }
        for (; i>=0; i--) {
            int d = longer.charAt(i) - '0';
            sb.append((carry + d) % 2);
            carry = (carry + d) / 2;
        }
        if(carry>0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("1010","1011"));
    }
}
