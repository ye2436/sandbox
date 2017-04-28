package interview.li;

/**
 * 245. Shortest Word Distance III
 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * word1 and word2 may be the same and they represent two individual words in the list.
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.
 * Note: You may assume word1 and word2 are both in the list.
 */
public class ShortestWordDistanceIII {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int dist = Integer.MAX_VALUE;
        int index1 = -1;
        int index2 = -1;

        for (int i=0; i<words.length; i++) {
            if (words[i].equals(word1)) {
                index1 = i;
            }
            if (words[i].equals(word2)) {
                if (word1.equals(word2) && index2 >= 0) {
                    dist = Math.min(dist, index1-index2);
                }
                index2 = i;
            }
            if (index1 >=0 && index2 >= 0 && index1 != index2) {
                dist = Math.min(dist, Math.abs(index1-index2));
            }
        }

        return dist;
    }
}
