import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  30. Substring with Concatenation of All Words
 *  You are given a string, s, and a list of words, words, that are all of the same length.
 *  Find all starting indices of substring(s) in s that is a concatenation of each word in words
 *  exactly once and without any intervening characters.
 *  For example, given:
 *  s: "barfoothefoobarman"
 *  words: ["foo", "bar"]
 *  You should return the indices: [0,9]. (order does not matter).
 */
public class SubstringWithConcatOfAllWords {

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> results = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return results;

        Map<String, Integer> map = new HashMap<>(); // frequency of words
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word)+1);
            } else {
                map.put(word, 1);
            }
        }

        int len = words[0].length();
        int num = words.length;
        for (int j=0; j<len; j++) {
            Map<String, Integer> currMap = new HashMap<>();
            int start = j;//start index of start
            int count = 0;//count total qualified words so far

            for (int i=j; i<=s.length()-len; i+=len) {
                String sub = s.substring(i, i+len);
                if (map.containsKey(sub)) {
                    if (currMap.containsKey(sub)) {
                        currMap.put(sub, currMap.get(sub)+1);
                    } else {
                        currMap.put(sub, 1);
                    }
                    count++;

                    // if the current word's count is more than it is supposed to be, find the left most word and shift right
                    while (currMap.get(sub) > map.get(sub)) {
                        String left = s.substring(start, start+len);
                        currMap.put(left, currMap.get(left)-1);
                        start+=len;
                        count--;
                    }


                    if (count == num) {
                        results.add(start);

                        // find left most word, remove it from the currMap, reset count, and shift right
                        String left = s.substring(start, start+len);
                        currMap.put(left, currMap.get(left)-1);
                        start += len;
                        count--;
                    }

                } else { // if the substring is not found in map, reset counter and shift the window to the right by length
                    currMap.clear();
                    start = i+len;
                    count = 0;
                }
            }
        }

        return results;
    }



    public static void main(String[] args) {
        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good","best","good"}));
    }
}
