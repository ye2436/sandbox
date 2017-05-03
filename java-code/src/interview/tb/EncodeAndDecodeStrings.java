package interview.tb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 271. Encode and Decode Strings
 *  Design an algorithm to encode a list of strings to a string. The encoded string is
 *  then sent over the network and is decoded back to the original list of strings.
 *
 * Machine 1 (sender) has the function:
 *  string encode(vector<string> strs) {
 *      // ... your code
 *      return encoded_string;
 *  }
 * Machine 2 (receiver) has the function:
 *  vector<string> decode(string s) {
 *      //... your code
 *      return strs;
 *  }
 * So Machine 1 does:
 *  string encoded_string = encode(strs);
 * and Machine 2 does:
 *  vector<string> strs2 = decode(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 * Implement the encode and decode methods.
 * Note:
 *  The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
 *  Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 *  Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 */
public class EncodeAndDecodeStrings {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        // we need some sort of the separator between each string
        // but using any single character is not safe because it can be contained in string as well
        // 1)what if we use the length of the string as separator? then we know where is the start and the end of the string.
        // Problem: the str length could be 10 or above, then the separator's length will be 2
        // 2) use length + a character
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append("|").append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> strs = new ArrayList<>();
        for (int i=0; i<s.length(); i++) {
            int strLength = 0;
            while (s.charAt(i) != '|') {
                strLength = strLength * 10 + (s.charAt(i) - '0');
                i++;
            } // now i is at character '|'
            String str = s.substring(i+1, i+1+strLength);
            strs.add(str);
            i += strLength; // set i to the end of the str, next i++ will set it to the start of the next str
        }
        return strs;
    }

    public static void main(String[] args) {
        // Your Codec object will be instantiated and called as such:
        EncodeAndDecodeStrings codec = new EncodeAndDecodeStrings();
        List<String> strs = Arrays.asList("123","a0bc","xyz43","987gckj");
        strs = Arrays.asList("63/Rc","h","BmI3FS~J9#vmk","7uBZ?7*/","24h+X","O ");
        System.out.println(codec.decode(codec.encode(strs)));
    }
}
