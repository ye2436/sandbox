package interview.fb;

/**
 * 157. Read N Characters Given Read4
 *
 The API: int read4(char *buf) reads 4 characters at a time from a file.

 The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

 By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 Note:
 The read function will only be called once for each test case.
 */
public class ReadNCharactersGivenRead4 {

    // because read4 reads characters into its input buf, we want to use a tmp buff for read4
    // and then merge it back to read function's buf.
    public class Solution extends Reader4 {
        /**
         * @param buf Destination buffer
         * @param n   Maximum number of characters to read
         * @return    The number of characters read
         */
        public int read(char[] buf, int n) {
            int total = 0;
            char[] tmp = new char[4];
            boolean eof = false; // end of file

            while (!eof && total < n) { // while file not ended and n characters not reached
                // read 4 to tmp array
                int cnt = read4(tmp);
                eof = cnt < 4;

                // overall cnt should not exceed n
                cnt = Math.min(cnt, n - total);

                // then copy all from tmp to buf at index total
                for (int i=0; i<cnt; i++) {
                    buf[total++] = tmp[i];
                }

            }
            return total;
        }
    }

    /* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */
    public class Reader4 {
        int read4(char[] buf) {
            return 0;
        }
    }

}
