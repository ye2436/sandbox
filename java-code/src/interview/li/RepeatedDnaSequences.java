package interview.li;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * #187 Repeated DNA Sequences
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

 Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

 For example,

 Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

 Return:
 ["AAAAACCCCC", "CCCCCAAAAA"].


 */
public class RepeatedDnaSequences {

    // 因为字母只有四种可能，我们可以用 2 bits 表示任意字母；同时因为 sequence 长度为 10，所以我们只需要 20 bits 就可以 Uniquely represent 一个 sequence，
    // 即自己实现无 collision 的 hashing. 简便起见，一个 32-bit int 就够了。
    // encode 时，对于每一个给定的 substring，遍历每个字母，然后根据字母决定其数字，给最后两位赋值之后 << 2.
    // decode 时，每次把当前数字除以 4 看余数，根据余数决定字母，而后 >> 2.
    // 这样对于每一个 string / int ，其 encode / decode 都是唯一的。
    // 面试官一定要最优解法，也就是必须encoding，并且是1-A, 2-C, 3-G, 4-T这样
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();

        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);

        int[] hash = new int[1048576]; // 空间占用为 2^20 = 1048576 个 int = 4.194304 MB ，代表可能的 hash 值数量。
        for (int i=0; i<s.length()-9; i++) {
            int val = 0;
            for (int j=i; j<i+10; j++) {
                val = val << 2;
                val += map.get(s.charAt(j));
            }
            //System.out.println("Processing " + s.substring(i, i+10) + " - " + val );
            hash[val]++;
            //if(hash[val] == 2) res.add(s.substring(i, i+10));
        }

        // print alphabetically
        for (int i=0; i<hash.length; i++) {
            if (hash[i] > 1) {
                res.add(decode(i));
            }
        }

        return res;
    }



    public static String decode(int val) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<10; i++) {
            int n = val % 4;
            if (n == 0) {
                sb.append('A');
            } else if (n==1) {
                sb.append('C');
            } else if (n==2) {
                sb.append('G');
            } else {
                sb.append('T');
            }
            val = val >> 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
}
