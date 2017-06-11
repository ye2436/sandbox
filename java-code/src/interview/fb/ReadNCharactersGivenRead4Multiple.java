package interview.fb;

import java.util.Arrays;

/**
 *  158. Read N Characters Given Read4 II - Call multiple times

 The API: int read4(char *buf) reads 4 characters at a time from a file.

 The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

 By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 Note:
 The read function may be called multiple times.
 */
public class ReadNCharactersGivenRead4Multiple {

    // For example, if we have source with "abcde". when we call read(1), it returns us buf = "a".
    // In the background, "bcd" was read out by function read4 as well. The next time, if we call read(2),
    // we want to read from "bcd" instead of calling read4 again.
    // To achieve that, we want to save off the characters in an array as global variable

    public static class Solution extends Reader4 {
        int bufPtr = 0; // points to index of the next character in buffer
        int bufCnt = 0; // count of total characters in buffer
        char[] buffer = new char[4]; // internal buffer saved from last read4 function

        /**
         * @param buf Destination buffer
         * @param n   Maximum number of characters to read
         * @return    The number of characters read
         */
        public int read(char[] buf, int n) {
            int total = 0;

            while (total < n) {
                if (bufPtr == 0) { // there is no remaining character in buffer
                    bufCnt = read4(buffer);
                }
                if (bufCnt == 0) break; // end of file

                // while we haven't read enough characters and we haven't reach the end of buffer, copy from buffer to buf
                while (total < n && bufPtr < bufCnt) {
                    buf[total++] = buffer[bufPtr++];
                }
                // all characters in buffer have been read, reset pointer (no need to reset count, because it's not used to
                // determine if the buffer is empty, buffer pointer is used)
                if (bufPtr == bufCnt) {
                    bufPtr = 0;
                }
            }
            return total;
        }
    }

    /* The read4 API is defined in the parent class Reader4.
     int read4(char[] buf); */
    public static class Reader4 {
        int read4(char[] buf) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[] buf = new char[10];
        solution.read(buf, 1);
        System.out.println(Arrays.toString(buf));
        solution.read(buf, 2);
        System.out.println(Arrays.toString(buf));
    }

}
