package interview.ms;

/**
 * #28. Implement strStr().
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
public class StrStr {

    // Solution 1: brute force.
    // Time: O(m*n) --- needle length m, haystack length: n
    // n-m+1 (# of substrings) * m (for each substring length )

    public static int strStr(String haystack, String needle) {

        for (int i=0; ; i++) {
            for (int j=0; ; j++) {
                if (j == needle.length()) return i;
                if (i+j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i+j)) break;
            }
        }
    }

    // Solution 2: Rolling Hash (Rabin-Karp Algorithm)
    // Use a string hash to perform the pattern matching.
    // requirement for the hashing function: next hash must be efficiently computable from the current hash value and next character in text
    // H = c_1 a^{k-1} + c_2 a^{k-2} + c_3 a^{k-3} + ... + c_k a^{0}
    // where a is a constant and c_1, ..., c_k are the input characters.
    // NOTICE that k is in descending order

    // After we calculate the current hash H, the next hash will be
    // H shifting to the right - remove the first term and add the new right term

    //Hashing是一个constant的操作，所以检测所有子串的时间复杂度只需要O(m+n-m)=O(n)
    //这个方法的hashcode比较容易越界，因为以素数为底的幂会很大，解决的办法可以用BigInteger,或者如同Rabin–Karp algorithm - Wikipedia
    // 一样对于hashcode进行取余，但是可能存在多个字符串映射到同一hashcode的问题，尽管是很少数的情况。

    public static int strStr2(String haystack, String needle) {
        if(haystack==null || needle==null) return -1;
        if(haystack.length()==0){
            return needle.length()==0? 0 :-1;
        }
        if(needle.length()==0) return 0;
        if(haystack.length()<needle.length()) return -1;

        int base = 29; // choose a prime number larger than the size of the character set
        long patternHash = 0;
        long tempBase = 1;

        // descending i, so power of k is largest for the first term.
        for(int i=needle.length()-1; i>=0; i--){
            System.out.println("a - " + (int)needle.charAt(i));
            System.out.println("t - " + tempBase);
            patternHash += (int)needle.charAt(i)*tempBase;
            System.out.println("h - " + patternHash);
            tempBase *= base;
        }

        long hayHash = 0;
        tempBase = 1;
        for(int i=needle.length()-1; i>=0; i--){
            hayHash += (int)haystack.charAt(i)*tempBase;
            tempBase *= base;
        }
        tempBase /= base;

        if(hayHash == patternHash){
            return 0;
        }

        for(int i=needle.length(); i<haystack.length(); i++){
            // hash - leftmost character * base^(m-1)
            // remainder part * base
            // add new last term * (base^0)
            hayHash = (hayHash - (int)haystack.charAt(i-needle.length())*tempBase)*base+(int)haystack.charAt(i);
            if(hayHash == patternHash){
                return i-needle.length()+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("mississippi","issi"));
        System.out.println(strStr("a","a"));

        System.out.println(strStr2("mississippi","abcd"));
        System.out.println(strStr2("a","a"));
    }
}
