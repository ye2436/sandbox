package interview.li;

import java.util.*;

/**
 * #244. Shortest Word Distance II
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and
 * your method will be called repeatedly many times with different parameters. How would you optimize it?
 * Design a class which receives a list of words in the constructor, and implements a method that takes
 * two words word1 and word2 and return the shortest distance between these two words in the list.
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 * Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 * */
public class ShortestWordDistanceII {
    public class WordDistance {

        Map<String, List<Integer>> map = new HashMap<>();

        public WordDistance(String[] words) {
            for (int i=0; i<words.length; i++) {
                if (!map.containsKey(words[i])) {
                    map.put(words[i], new ArrayList<>(Arrays.asList(i)));
                } else {
                    map.get(words[i]).add(i);
                }
            }
        }

        // finding the smallest diff out of 2 index lists
        // the index lists are in ascending ordering because the way they are created.
        // every time move the smaller index to the next, because moving the larger index will make the diff even bigger
        public int shortest(String word1, String word2) {
            int dist = Integer.MAX_VALUE;
            List<Integer> indexList1 = map.get(word1);
            List<Integer> indexList2 = map.get(word2);
            int i=0;
            int j=0;
            while (i < indexList1.size() && j < indexList2.size()) {
                int index1 = indexList1.get(i);
                int index2 = indexList2.get(j);
                dist = Math.min(dist, Math.abs(index1-index2));
                if (index1 < index2) {
                    i++;
                } else {
                    j++;
                }
            }

            return dist;
        }
    }

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
}
