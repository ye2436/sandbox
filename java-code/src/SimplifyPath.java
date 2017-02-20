import java.util.LinkedList;

/**
 * #71. Simplify Path
 * Given an absolute path for a file (Unix-style), simplify it.
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 *
 * The single period . means current working directory
 * The double period .. means parent of the current working directory
 *
 * / -> root
 * /a -> in (a)
 * . -> THIS dir path
 * /a/./ -> still in /a
 * /a/./b -> in /a/b
 * .. -> go "up" one level
 * /a/./b/.. -> /a/b/.. -> /a
 * /a/./b/../.. -> /a/.. -> /
 * /a/./b/../../c -> /c
 *
 *
 * Corner Cases:
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class SimplifyPath {

    public static String simplifyPath(String path) {
        if (path == null || path.length() == 0) return null;
        LinkedList<String> stack = new LinkedList<>();
        String[] array = path.split("/");
        for (String s : array) {
            if (".".equals(s) || "".equals(s)) {
                continue;
            } else if ("..".equals(s)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(s);
            }
        }
        if (stack.isEmpty()) return "/";
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            if (sb.length()==0 || sb.charAt(sb.length()-1) != '/') {
                sb.append("/");
            }
            sb.append(stack.removeLast());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(simplifyPath("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///"));
        /*linkedlist.LinkedList<Integer> stack = new linkedlist.LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        while(!stack.isEmpty()) {
            System.out.println(stack.removeLast());
        }*/
    }
}
