package lc;

import java.util.*;

/**
 * #126. Word Ladder II
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
 * from beginWord to endWord, such that:
 *  Only one letter can be changed at a time.
 *  Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * For example,
 *  Given:
 *      beginWord = "hit"
 *      endWord = "cog"
 *      wordList = ["hot","dot","dog","lot","log","cog"]
 *  As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *  return
 *  [
 *      ["hit","hot","dot","dog","cog"],
 *      ["hit","hot","lot","log","cog"]
 *  ]
 * Note:
 *  Return an empty list if there is no such transformation sequence.
 *  All words have the same length.
 *  All words contain only lowercase alphabetic characters.
 *  You may assume no duplicates in the word list.
 *  You may assume beginWord and endWord are non-empty and are not the same.
 */
public class WordLadderII {
    // 思路上和Word Ladder是比较类似的，但是因为是要求出所有路径，仅仅保存路径长度是不够的，而且这里还有更多的问题，那就是为了得到所有路径，
    // 不是每个结点访问一次就可以标记为visited了，因为有些访问过的结点也会是别的路径上的结点，所以访问的集合要进行回溯（也就是标记回未访问）。
    // 所以时间上不再是一次广度优先搜索的复杂度了，取决于结果路径的数量。同样空间上也是相当高的复杂度，因为我们要保存过程中满足的中间路径到某个数据结构中，
    // 以便最后可以获取路径，这里我们维护一个HashMap，把一个结点前驱结点都进行保存。
    // 在LeetCode中用Java实现上述算法非常容易超时。为了提高算法效率，需要注意一下两点：
    // 1）在替换String的某一位的字符时，先转换成char数组再操作；
    // 2）如果按照正常的方法从start找end，然后根据这个来构造路径，代价会比较高，因为保存前驱结点容易，而保存后驱结点则比较困难。
    //    所以我们在广度优先搜索时反过来先从end找start，最后再根据生成的前驱结点映射从start往end构造路径，这样算法效率会有明显提高。
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0
                || beginWord.length() != endWord.length()  || !dict.contains(endWord)) return res;


        return res;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(findLadders(beginWord, endWord, wordList));
    }
}
