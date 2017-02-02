import java.util.*;

/**
 * #36. Valid Sudoku
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 *
 * A partially filled sudoku which is valid.
 *
 * Note:
 * A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 */
public class ValidSudoku {
    public static boolean isValidSudoku(char[][] board) { // board[i][j]
        if (board == null || board.length == 0) return false;

        //Map<Integer, Set<Character>> hMap = new HashMap<>(); // i -> numbers
        Map<Integer, Set<Character>> vMap = new HashMap<>(); // j -> numbers
        //Map<Integer, Map<Integer, Set<Character>>> cMap = new HashMap<>(); // i/3 -> j/3 -> numbers
        Map<Integer, Set<Character>> cMap = new HashMap<>(); // j/3 -> numbers

        for (int i=0; i<board.length; i++) { // row
            Set<Character> currRow = new HashSet<>();

            if (i % 3 == 0) { // clear the cube map every 3 rows
                cMap.clear();
            }

            for(int j=0; j<board[i].length; j++) { // column
                char c = board[i][j];
                if (c == '.') continue;

                if (currRow.contains(c)) {
                    return false;
                } else {
                    currRow.add(c);
                }

                Set<Character> currCol;
                if (vMap.containsKey(j)) {
                    currCol = vMap.get(j);
                } else {
                    currCol = new HashSet<>();
                    vMap.put(j, currCol);
                }
                if (currCol.contains(c)) {
                    return false;
                } else {
                    currCol.add(c);
                }

                Set<Character> currCube;
                if (cMap.containsKey(j/3)) {
                    currCube = cMap.get(j/3);
                } else {
                    currCube = new HashSet<>();
                    cMap.put(j/3, currCube);
                }
                if (currCube.contains(c)) {
                    return false;
                } else {
                    currCube.add(c);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        System.out.println(isValidSudoku(board));
        System.out.println(Arrays.deepToString(board));
    }
}
