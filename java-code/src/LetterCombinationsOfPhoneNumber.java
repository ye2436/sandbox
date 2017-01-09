import java.util.*;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationsOfPhoneNumber {

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        String[] letters = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        for (int i=0; i<digits.length();i++) {
            String letter = letters[digits.charAt(i) - '0'];
            String[] characters = letter.split("(?!^)");
            if (res.size() == 0) {
                res = new ArrayList<>(Arrays.asList(characters));
            } else {
                List<String> temp = new ArrayList<>();
                for (String e: res) {
                    for (String c : characters) {
                        temp.add(e+c);
                    }
                }
                res = temp;
            }

        }

        return res;
    }

    public static List<String> letterCombinations_dfs(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        Map<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a','b','c'});
        map.put('3', new char[]{'d','e','f'});
        map.put('4', new char[]{'g','h','i'});
        map.put('5', new char[]{'j','k','l'});
        map.put('6', new char[]{'m','n','o'});
        map.put('7', new char[]{'p','q','r','s'});
        map.put('8', new char[]{'t','u','v'});
        map.put('9', new char[]{'w','x','y','z'});

        helper(digits, 0, new StringBuilder(), res, map);

        return res;
    }

    public static void helper(String digits, int depth, StringBuilder sb, List<String> result, Map<Character, char[]> map) {
        if (depth == digits.length()) {
            result.add(sb.toString());
            return;
        }
        char curr = digits.charAt(depth);
        for (char c : map.get(curr)) {
            sb.append(c);
            helper(digits, depth+1, sb, result, map);
            sb.deleteCharAt(sb.length()-1); // why remove the last character?
            // when depth equals digits.length, the string is added to result list.
            // after helper returns, we need to move up one step to keep searching.
        }
    }


    public static void main(String[] args) {

        System.out.println(letterCombinations("23"));
        System.out.println(letterCombinations_dfs("23"));
    }
}
