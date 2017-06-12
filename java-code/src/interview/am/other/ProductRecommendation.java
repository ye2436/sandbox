package interview.am.other;

import java.util.*;

/**
 * - 给一堆purchase record，给买了同样商品的客户之间推荐rating超过4的商品。例如：Alice买了商品{A， B， C}，评价是{3, 4, 5},
 *   然后Bob买了商品{B, D, F}评价是{4, 4, 4},要给bob推荐C，因为alice跟bob都买了B，而且alice觉得B好，然后得把C推荐给Bob
 * - 设计一个根据朋友购买次数，返回recommend list的题
 * - 找出好友推荐的东西，给两个api，一个可以得到user的朋友list，一个是可以得到user买的东西
 * - 设计某个产品的关联产品功能
 */
public class ProductRecommendation {

    // Based on customer who purchased the same product and gave a good rating
    // 1) for each customer, find all purchases with rating >=4
    // 2) for each purchase, find other users who had purchased it (and gave good rating?)
    // 3) for each other user, find their purchases with good rating
    // 4) accumulate all those purchases, remove the ones the first customer already purchased, return

    // Based on friend's purchase
    public List<String> recommend(int userId) {
        List<String> res = new ArrayList<>();
        List<String> purchased = getPurchases(userId);
        Set<String> purchasedSet = new HashSet<>(purchased);
        for (int friend : getFriends(userId)) {
            for (String purchase : getPurchases(friend)) {
                if (!purchasedSet.contains(purchase)) {
                    // and some other conditions such as ratings
                    res.add(purchase);
                }
            }
        }
        return res;
    }

    private List<Integer> getFriends(int userId) {
        return new ArrayList<>();
    }

    private List<String> getPurchases(int userId) {
        return new ArrayList<>();
    }

    // 想想如果你用linkedin或者facebook， 给你一个人和他的朋友关系网，你会怎么给一个人推荐朋友
    // 一个例子就是A-B, A-C, B - D, B - E, C - D，这个时候问我应该推荐谁给A，我说D,因为他是BC的共同好友，而E只是B的好友,
    // 到这我才明白干啥，就是给一个图和里面的一个节点A,用bfs从A出发，找出第二层中indegree度数最大节点
    // 用HashMap<Character, HashSet<Character>>来建图
    // 用HashMap<Character, Integer> SndIndegree来表示第二层indegree数目
    // 用maxIndegree记录第二层Indegree最大值
    // 用res记录第二层Indegree最大者
    // BFS
    public static char recommend(char tar, char[][] arr) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> SndIndegree = new HashMap<>();

        //build graph
        for (char[] edge : arr) {
            if (!graph.containsKey(edge[0])) graph.put(edge[0], new HashSet<>());
            if (!graph.containsKey(edge[1])) graph.put(edge[1], new HashSet<>());
            graph.get(edge[0]).add(edge[1]);
            if (!SndIndegree.containsKey(edge[0])) SndIndegree.put(edge[0], 0);
            if (!SndIndegree.containsKey(edge[1])) SndIndegree.put(edge[1], 0);
        }

        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        int level = 0;
        queue.offer(tar);
        visited.add(tar);
        int PNum = 1;
        int CNum = 0;
        int maxIndegree = 0;
        char res = '\0';
        while (!queue.isEmpty()) {
            char cur = queue.poll();
            PNum--;
            for (Character neigh : graph.get(cur)) {
                if (level+1 == 2) {
                    if (neigh == tar) continue;
                    int curIndegree = SndIndegree.get(neigh)+1;
                    if (curIndegree > maxIndegree) res = neigh.charValue();
                    SndIndegree.put(neigh, curIndegree);
                }
                else { //not second level
                    if (!visited.contains(neigh)) {
                        queue.offer(neigh);
                        CNum++;
                        visited.add(neigh);
                    }
                }
            }
            if (PNum == 0) {
                PNum = CNum;
                CNum = 0;
                level++;
            }
        }
        return res;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {

        char res = recommend('A', new char[][]{{'A','B'},{'A','C'},{'B','D'},{'B','E'},{'C','D'},{'B','A'},{'C','A'}});
        System.out.println(res);
    }


}
