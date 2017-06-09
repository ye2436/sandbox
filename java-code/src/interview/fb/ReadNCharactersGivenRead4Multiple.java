package interview.fb;

/**
 *  158. Read N Characters Given Read4 II - Call multiple times

 The API: int read4(char *buf) reads 4 characters at a time from a file.

 The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

 By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 Note:
 The read function may be called multiple times.
 */
public class ReadNCharactersGivenRead4Multiple {

    // We need a global variable to store last index of buf, so that when read are called multiple times
    // the data won't be overwritten.
    int bufPtr = 0;

    public class Solution extends Reader4 {
        /**
         * @param buf Destination buffer
         * @param n   Maximum number of characters to read
         * @return    The number of characters read
         */
        public int read(char[] buf, int n) {
            char[] tmp = new char[4];
            int total = 0;
            boolean eof = false;

            while (!eof && total < n) {
                int cnt = read4(tmp);
                eof = cnt < 4;

                cnt = Math.min(cnt, n - total);
                for (int i=0; i<cnt; i++) {
                    buf[bufPtr++] = tmp[i];
                    total++;
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
