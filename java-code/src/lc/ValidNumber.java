package lc;

/**
 * #65. Valid Number
 * Validate if a given string is numeric.
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 */
public class ValidNumber {
    public static boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        s = s.trim();
        if (s.length() == 0) return false;

        boolean hasDot = false;
        boolean hasE = false;
        for (int i=0; i<s.length(); i++) {
            switch (s.charAt(i)) {
                case '.':
                    if (hasDot || hasE || ((i==0 || s.charAt(i-1)<'0'|| s.charAt(i-1)>'9')) && (i==s.length()-1 || s.charAt(i+1)<'0' || s.charAt(i+1)>'9'))
                        return false;
                    hasDot = true;
                    break;
                case 'e':
                case 'E':
                    if (hasE || i==0 || i==s.length()-1)
                        return false;
                    hasE = true;
                    break;
                case '+':
                case '-':
                    if((i>0 && (s.charAt(i-1)!='e' && s.charAt(i-1)!='E'))
                            || (i==s.length()-1 || !(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9'||s.charAt(i+1)=='.')))
                        return false;
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isNumber(" +.1"));
    }
}
