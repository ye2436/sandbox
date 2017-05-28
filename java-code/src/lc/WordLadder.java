package lc;

import java.util.*;
import java.util.LinkedList;

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
    // 这道题看似一个关于字符串操作的题目，其实要解决这个问题得用图的方法。我们先给题目进行图的映射，顶点则是每个字符串，
    // 然后两个字符串如果相差一个字符则我们进行连边。接下来看看这个方法的优势，注意到我们的字符集只有小写字母，而且字符串长度固定，假设是L。
    // 那么可以注意到每一个字符可以对应的边则有25个（26个小写字母减去自己），那么一个字符串可能存在的边是25*L条。
    // 接下来就是检测这些边对应的字符串是否在字典里，就可以得到一个完整的图的结构了。
    // 根据题目的要求，等价于求这个图一个顶点到另一个顶点的最短路径，一般我们用广度优先搜索（不熟悉搜索的朋友可以看看Clone Graph）即可。
    // 这个算法中最坏情况是把所有长度为L的字符串都看一下，或者把字典中的字符串都看一下，而长度为L的字符串总共有26^L，
    // 所以时间复杂度是O(min(26^L, size(dict))，空间上需要存储访问情况，也是O(min(26^L, size(dict))。
    /**
     * Pseudocode:
     *  - Input: A graph G and a vertex v of G.
     *  - Output: The closest vertex to v satisfying some conditions, or null if no such a vertex exists.
     *
     *  procedure BFS(G, v):
     *      create a queue Q
     *      enqueue v onto Q
     *      mark v
     *      while Q is not empty:
     *          t ← Q.dequeue()
     *          if t is what we are looking for:
     *              return t
     *          for all edges e in G.adjacentEdges(t) do
     *              o ← G.adjacentVertex(t, e)
     *              if o is not marked:
     *                  mark o
     *                  enqueue o onto Q
     *      return null
     * Source: https://en.wikipedia.org/wiki/Graph_traversal#Breadth-first_search
     * BFS visits the neighbor vertices before visiting the child vertices, and a queue is used in the search process.
     * This algorithm is often used to find the shortest path from one vertex to another.
     */

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0
                || beginWord.length() != endWord.length()  || !wordList.contains(endWord)) return 0;

        Set<String> dict = new HashSet<>(wordList);
        int len = beginWord.length();
        LinkedList<String> queue = new LinkedList();
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        visited.add(beginWord);
        int step = 1; // 等同于树的层数(level)，在这里也代表ladder的长度，已经放了第一个字符串了，所以就是1
        // BFS常用的两个变量，lastNum表示上一层还有几个结点，curNum表示当前一层有几个结点。
        // 当lastNum是0的时候，说明上一层结点已经遍历结束了，所以把curNum赋给lastNum因为进入下一层了。
        // （相同的用法参见 Binary Tree Level Order Traversal）
        int lastNum = 1;
        int currNum = 0;
        while(!queue.isEmpty()) {
            String curr = queue.pop();
            lastNum--;
            // 如果和 endWord只差一个字符就找到了
            // replace 1 character in curr at a time, check if the new word is in wordList and unvisited,
            // add it to the queue and mark visited
            // check 25 * L times at most
            for (int i=0; i<len; i++) {
                char[] charArray = curr.toCharArray();
                for (char c='a'; c<='z'; c++) {
                    // replace character at length i with a to z
                    charArray[i] = c;
                    String newWord = new String(charArray);
                    if (newWord.equals(endWord)) {
                        return step+1;
                    }
                    // in the wordList and not visited (excludes the startWord)
                    if (dict.contains(newWord) && !visited.contains(newWord)) {
                        queue.add(newWord);
                        visited.add(newWord);
                        currNum++;
                    }
                }
            }

            if (lastNum == 0) { // last level all visited
                lastNum = currNum;
                currNum = 0;
                step++;
            }
        }

        return 0; // not found
    }

    // two-end BFS
    public static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        if (dict.contains(endWord)) {
            endSet.add(endWord); // assumption: endWord not necessarily in the dict
        }

        int len = 1; // ladder length
        int strLen = beginWord.length();
        Set<String> visited = new HashSet<>();

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // alternate 2 sets to perform two-end search
            // always search from the set with fewer nodes
            if (beginSet.size() > endSet.size()) {
                Set<String> tmpSet = beginSet;
                beginSet = endSet;
                endSet = tmpSet;
            }

            Set<String> next = new HashSet<>();
            for (String word : beginSet) {
                char[] array = word.toCharArray();

                for (int i=0; i<strLen; i++) {
                    char old = array[i];  // original char at index i
                    for (char c='a'; c<='z'; c++) {
                        array[i] = c;
                        String newWord = new String(array);
                        if (endSet.contains(newWord)) {
                            return len+1;
                        }
                        if (dict.contains(newWord) && !visited.contains(newWord)) {
                            next.add(newWord);
                            visited.add(newWord);
                        }
                    }
                    array[i] = old; // revert back index i to its original before moving to next index
                }
            }
            // after visiting all in the curr begin set
            len++;
            beginSet = next;
        }

        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log");
        System.out.println(ladderLength(beginWord, endWord, wordList));
        System.out.println(ladderLength2(beginWord, endWord, wordList));
    }
}
