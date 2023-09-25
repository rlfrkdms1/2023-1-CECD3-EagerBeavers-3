import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author beginner1010
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskF2 solver = new TaskF2();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskF2 {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            HashMap<Long, ArrayList<Interval>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                long sum = 0;
                for (int j = i; j < n; j++) {
                    sum += a[j];
                    if (map.containsKey(sum) == false) {
                        map.put(sum, new ArrayList<>());
                    }
                    ArrayList<Interval> arr = map.get(sum);
                    if (arr.isEmpty() || arr.get(arr.size() - 1).r < i) {
                        arr.add(new Interval(i, j));
                    } else if (arr.get(arr.size() - 1).r >= j) {
                        arr.set(arr.size() - 1, new Interval(i, j));
                    }
                }
            }

            ArrayList<Interval> best = new ArrayList<>();
            for (ArrayList<Interval> arr : map.values()) {
                if (best.size() < arr.size()) {
                    best = arr;
                }
            }

            out.println(best.size());
            for (Interval i : best) {
                out.println((i.l + 1) + " " + (i.r + 1));
            }
        }

        class Interval {
            int l;
            int r;

            Interval(int l, int r) {
                this.l = l;
                this.r = r;
            }

        }

    }

    static class InputReader {
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputStream stream;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isWhitespace(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isWhitespace(c));
            return res * sgn;
        }

    }
}

