package interview.fb.lc;

/**
 * #125. Valid Palindrome
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * For the purpose of this problem, we define empty string as valid palindrome.
 */
public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        if (s ==  null || s.length() == 0) return true;

        int l = 0;
        int r = s.length()-1;
        while (l < r) {
            while (l<=r && !Character.isLetterOrDigit(s.codePointAt(l))) {
                l++;
            }
            while (l<=r && !Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }
            if (l > r) { // no valid char between l and r
                break;
            }
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    private boolean isValid(char c) {
        if(c>='a' && c<='z' || c>='A' && c<='Z' || c>='0' && c<='9')
            return true;
        return false;
    }

    private boolean isSame(char c1, char c2) {
        if(c1>='A' && c1<='Z')
            c1 = (char)(c1-'A'+'a');
        if(c2>='A' && c2<='Z')
            c2 = (char)(c2-'A'+'a');
        return c1==c2;

    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
    }
}
