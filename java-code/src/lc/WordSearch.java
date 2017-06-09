package lc;

/**
 * #79. Word Search
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * For example,
 * Given board =
 * [
 *  ['A','B','C','E'],
 *  ['S','F','C','S'],
 *  ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */
public class WordSearch {

    boolean[][] used; // global variable - we only need one copy. because after each search, it will be reverted back.

    public boolean exist2(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        used = new boolean[board.length][board[0].length];
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (helper(word, 0, board, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean helper(String word, int index, char[][] board, int i, int j) {
        if (index == word.length()) return true;
        if (i<0 || i>=board.length || j<0 || j>=board[0].length || used[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }
        used[i][j] = true;
        boolean exists = helper(word, index+1, board, i+1, j) || helper(word, index+1, board, i-1, j)
                || helper(word, index+1, board, i, j+1) || helper(word, index+1, board, i, j-1);
        used[i][j] = false;
        return exists;
    }

    public static boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) return true;
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        //boolean[][] used = new boolean[board.length][board[0].length];
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if(helper(board, i, j, word, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean helper(char[][] board, int i, int j, String word, int index) {
        if (index == word.length()) {
            return true;
        }

        if (i<0 || j< 0 || i>=board.length || j>=board[0].length || word.charAt(index) != board[i][j]) {
            return false; // if not found, return false and move on to the next one on board
        }
        //used[i][j] = true;
        board[i][j] ^= 256; // bit mask the char at (i,j)
        boolean result =  helper(board, i+1, j, word, index+1)
                || helper(board, i, j+1, word, index+1)
                || helper(board, i, j-1, word, index+1)
                || helper(board, i-1, j, word, index+1);
        //used[i][j] = false; // revert back; make sure if will not interfere next calculation if curr result = false
        board[i][j] ^= 256; // toggle it back
        return result;
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        System.out.println(exist(board, "ABCCED"));
    }
}
