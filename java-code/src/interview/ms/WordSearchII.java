package interview.ms;

import java.util.*;

/**
 * 212. Word Search II
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are
 * those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 *  [
 *      ['o','a','a','n'],
 *      ['e','t','a','e'],
 *      ['i','h','k','r'],
 *      ['i','f','l','v']
 *  ]
 * Return ["eat","oath"].
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 *
 * Hint:
 * You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
 * If the current candidate does not exist in all words' prefix, you could stop backtracking immediately.
 * What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not?
 * How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.
 */
public class WordSearchII {

    // Solution 1: brute force, backtracking & dfs
    public static List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) return new ArrayList<>();

        Set<String> foundWords = new HashSet<>(); // there might be duplicates in words
        for (String word : words) {
            // if exists on board
            if (foundWords.contains(word)) {
                continue;
            }
            for (int i=0; i<board.length; i++) {
                for (int j=0; j<board[0].length; j++) {
                    if (exists(board, i, j, word, 0)) {
                        foundWords.add(word);
                    }
                }
            }
        }
        return new ArrayList<>(foundWords);
    }

    // if word exists on board, starting from (i,j), index is the current index in word
    private static boolean exists(char[][] board, int i, int j, String word, int index) {
        if (index == word.length()) {
            return true;
        }
        if (i<0 || j<0 || i>=board.length || j>=board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }
        char c = board[i][j];
        board[i][j] = '*'; // mask the cell so it won't be used again
        boolean exists = false;
        if (exists(board, i+1, j, word, index+1)
                || exists(board, i-1, j, word, index+1)
                || exists(board, i, j+1, word, index+1)
                || exists(board, i, j-1, word, index+1)) {
            exists = true;
        }
        board[i][j] = c; // revert back char
        return exists;
    }

    // Solution 2: Trie
    public List<String> findWords2(char[][] board, String[] words) {

        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) return new ArrayList<>();

        TrieNode root = buildTrie(words);
        Set<String> foundWords = new HashSet<>();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                helper(board, i, j, root, new StringBuilder(), foundWords);
            }
        }
        return new ArrayList<>(foundWords);
    }

    // starting from board(i,j), check if could find a word with length of len in node
    public void helper(char[][] board, int i, int j, TrieNode node, StringBuilder sb, Set<String> res) {
        if (node.isLeaf) { // found to the end of the word. add result but DO NOT return
            res.add(sb.toString());
            // DO NOT RETURN here. so we can continue to check the words with same prefix.
        }

        if (i<0 || j<0 || i>=board.length || j>=board[0].length || board[i][j] == '*'
                || node.children[board[i][j]-'a'] == null) {
            return; // out of boundary OR used char OR char not found, return
        }

        char c = board[i][j];
        board[i][j] = '*'; // mask the cell so it won't be used again
        sb.append(c);
        helper(board, i+1, j, node.children[c-'a'], sb, res); // check all 4 direction
        helper(board, i-1, j, node.children[c-'a'], sb, res);
        helper(board, i, j+1, node.children[c-'a'], sb, res);
        helper(board, i, j-1, node.children[c-'a'], sb, res);
        board[i][j] = c; // revert back char
        sb.deleteCharAt(sb.length()-1);
        return;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode current = root;
            for (int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if (current.children[c-'a'] == null) {
                    TrieNode child = new TrieNode();
                    current.children[c-'a'] = child;
                }
                current = current.children[c-'a'];
            }
            // Set isLeaf/isEnd at the end
            current.isLeaf = true;
        }
        return root;
    }

    class TrieNode {
        // no need for a value. it can be inferred from its parent's index
        TrieNode[] children; // index 0: a, index 25: z
        boolean isLeaf; // true if the node represents end of a word
        public TrieNode() {
            children = new TrieNode[26];
        }
    }


    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        System.out.println(findWords(board, new String[]{"oath","pea","eat","rain"}));
        WordSearchII solution = new WordSearchII();
        System.out.println(solution.findWords2(board, new String[]{"oath","pea","eat","rain"}));

        board = new char[][] {
                {'a','b'},
                {'a','a'},
        };
        System.out.println(solution.findWords2(board, new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"}));
    }
}
