package interview.am;

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



    /*
    * The solution contains two steps 1 Use BFS to construct a graph. 2. Use DFS to construct the paths from end to start.Both solutions got AC within 1s.

    The first step BFS is quite important. I summarized three tricks

    - Using a MAP to store the min ladder of each word, or use a SET to store the words visited in current ladder, when the current ladder was completed, delete the visited words from unvisited. That's why I have two similar solutions.
    - Use Character iteration to find all possible paths. Do not compare one word to all the other words and check if they only differ by one character.
    - One word is allowed to be inserted into the queue only ONCE. See my comments.

    * */
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        if (wordList.size() == 0)
            return results;
        Set<String> dict = new HashSet<>(wordList);

        int min=Integer.MAX_VALUE;

        Queue<String> queue= new ArrayDeque<>();
        queue.add(beginWord);

        Map<String,List<String>> map = new HashMap<>();

        Map<String,Integer> ladder = new HashMap<>();
        for (String string:dict)
            ladder.put(string, Integer.MAX_VALUE);
        ladder.put(beginWord, 0);

        dict.add(endWord);
        //BFS: Dijisktra search
        while (!queue.isEmpty()) {

            String word = queue.poll();

            int step = ladder.get(word)+1;//'step' indicates how many steps are needed to travel to one word.

            if (step>min) break;

            for (int i = 0; i < word.length(); i++){
                StringBuilder builder = new StringBuilder(word);
                for (char ch='a';  ch <= 'z'; ch++){
                    builder.setCharAt(i,ch);
                    String new_word=builder.toString();
                    if (ladder.containsKey(new_word)) {

                        if (step>ladder.get(new_word))//Check if it is the shortest path to one word.
                            continue;
                        else if (step<ladder.get(new_word)){
                            queue.add(new_word);
                            ladder.put(new_word, step);
                        }else;// It is a KEY line. If one word already appeared in one ladder,
                        // Do not insert the same word inside the queue twice. Otherwise it gets TLE.

                        if (map.containsKey(new_word)) //Build adjacent Graph
                            map.get(new_word).add(word);
                        else{
                            List<String> list= new LinkedList<String>();
                            list.add(word);
                            map.put(new_word,list);
                            //It is possible to write three lines in one:
                            //map.put(new_word,new LinkedList<String>(Arrays.asList(new String[]{word})));
                            //Which one is better?
                        }

                        if (new_word.equals(endWord))
                            min=step;

                    }//End if dict contains new_word
                }//End:Iteration from 'a' to 'z'
            }//End:Iteration from the first to the last
        }//End While

        //BackTracking
        LinkedList<String> result = new LinkedList<>();
        backTrace(endWord,beginWord,result, results, map);

        return results;
    }

    private static void backTrace(String word,String start,List<String> list, List<List<String>> results, Map<String,List<String>> map){
        if (word.equals(start)){
            list.add(0,start);
            results.add(new ArrayList<String>(list));
            list.remove(0);
            return;
        }
        list.add(0,word);
        if (map.get(word)!=null)
            for (String s:map.get(word))
                backTrace(s,start,list, results, map);
        list.remove(0);
    }


    // only print the shortest path
    // Using BFS, the first path we found is the shortest path
    // Here for efficiency, we use two-end bfs
    // 如果是只要一条最短路径，只需要一次bfs。具体来说在bfs的过程中，在把下一层节点i加入queue的时候，用一个map记录，key是下一层节点i的id, value是本层的节点id。
    // 这样到最后，就可以从结束点开始往前找parent，一直找到开头的点。因为加入queue的过程就是确定下一层点的parent的过程，所以每个点只进入queue一次，这个用set来保证。
    public static List<String> findLadders15(String beginWord, String endWord, List<String> wordList) {
        List<String> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        if (dict.contains(endWord)) {
            endSet.add(endWord); // assumption: endWord not necessarily in the dict
        }

        int strLen = beginWord.length();
        Set<String> visited = new HashSet<>();
        Map<String, String> map = new HashMap<>(); // node -> to its predecessor
        boolean reversed = false; // to ensure the node path are added in the correct direction

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) { // swap to the smaller set
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
                reversed = !reversed;
            }

            Set<String> next = new HashSet<>();
            for (String word : beginSet) {
                char[] array = word.toCharArray();

                for (int i=0; i<strLen; i++) {
                    char old = array[i];

                    for (char c = 'a'; c<='z'; c++) {
                        array[i] = c;
                        String newWord = new String(array);

                        String key = reversed ? word : newWord;
                        String value = reversed ? newWord : word;

                        if (endSet.contains(newWord)) {
                            map.put(key, value); // the last key value pair to be added
                            // return word ladders from newWord to beginWord
                            return constructResult(map, beginWord, endWord);
                        }

                        if (dict.contains(newWord) && !visited.contains(newWord)) {
                            next.add(newWord);
                            visited.add(newWord);
                            map.put(key, value);
                        }
                    }

                    array[i] = old;
                }
            }
            beginSet = next;
        }
        return res;
    }

    public static List<String> constructResult(Map<String, String> map, String beginWord, String endWord) {
        List<String> res = new ArrayList<>();
        res.add(endWord);
        while (res.get(0) != beginWord) {
            endWord = map.get(endWord);
            res.add(0, endWord);
        }
        return res;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(findLadders(beginWord, endWord, wordList));
        System.out.println(findLadders15(beginWord, endWord, wordList));
    }
}
