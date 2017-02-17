import java.util.Arrays;
import java.util.List;

/**
 * #127. Word Ladder
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence
 * from beginWord to endWord, such that:
 *  Only one letter can be changed at a time.
 *  Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * For example,
 *  Given:
 *      beginWord = "hit"
 *      endWord = "cog"
 *      wordList = ["hot","dot","dog","lot","log","cog"]
 *  As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *  return its length 5.
 * Note:
 *  Return 0 if there is no such transformation sequence.
 *  All words have the same length.
 *  All words contain only lowercase alphabetic characters.
 *  You may assume no duplicates in the word list.
 *  You may assume beginWord and endWord are non-empty and are not the same.
 */
public class WordLadder {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(ladderLength(beginWord, endWord, wordList));
    }
}
