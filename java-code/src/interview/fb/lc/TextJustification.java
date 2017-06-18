package interview.fb.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * #68. Text Justification
 *  Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
 *  You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 *  Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *  For the last line of text, it should be left justified and no extra space is inserted between words.
 *  For example,
 *  words: ["This", "is", "an", "example", "of", "text", "justification."]
 *  L: 16.
 *  Return the formatted lines as:
 *  [
 *      "This    is    an",
 *      "example  of text",
 *      "justification.  "
 *  ]
 *  Note: Each word is guaranteed not to exceed L in length.
 *  Corner Cases:
 *  A line other than the last line might contain only one word. What should you do in this case?
 *  In this case, that line should be left-justified.
 */
public class TextJustification {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        if (words==null || words.length == 0) return new ArrayList<>();

        List<String> res = new ArrayList<>();
        List<String> row = new ArrayList<>();
        int wordslength = 0;
        for (int i=0; i<words.length; i++) {
            int len = words[i].length();
            if (len + wordslength + row.size() > maxWidth) { // curr word length, prev words length, plus n spaces
                // justify row and add to res
                if (row.size() == 1) {
                    // left justify
                    StringBuilder sb = new StringBuilder();
                    sb.append(row.get(0));
                    int diff = maxWidth-sb.length();
                    while (diff>0) {
                        sb.append(" ");
                        diff--;
                    }
                    res.add(sb.toString());

                } else {
                    int spaceSize = (maxWidth - wordslength) / (row.size()-1);
                    int extraSpaces = (maxWidth - wordslength) % (row.size()-1);
                    StringBuilder sb = new StringBuilder();
                    for (int j=0; j<row.size()-1; j++) {
                        sb.append(row.get(j));
                        for (int k=spaceSize; k>0; k--) {
                            sb.append(" ");
                        }
                        if (extraSpaces>0) {
                            sb.append(" ");
                            extraSpaces--;
                        }
                    }
                    sb.append(row.get(row.size()-1));
                    res.add(sb.toString());
                }

                // create new row and add curr word to it
                row = new ArrayList<>();
                wordslength=0;
            }
            row.add(words[i]);
            wordslength += len;
        }
        // last row, left justify
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<row.size(); i++) {
            sb.append(row.get(i));
            if (i!=row.size()-1) {
                sb.append(" ");
            }
        }
        int diff = maxWidth-sb.length();
        while (diff>0) {
            sb.append(" ");
            diff--;
        }
        res.add(sb.toString());
        return res;
    }

    public static void main(String[] args) {
        //String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        String[] words = new String[]{"What","must","be","shall","be."};
        System.out.println(fullJustify(words, 12));
    }
}
