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
            HashMap<Integer, Integer> lastIndex = new HashMap<>();
            HashMap<Integer, Integer> maxSize = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = i; j < n; j++) {
                    sum += a[j];
                    if (maxSize.containsKey(sum) == false) {
                        maxSize.put(sum, 0);
                    }
                    int curMaxSize = maxSize.get(sum);
                    int curLastIndex = curMaxSize == 0 ? -1 : lastIndex.get(sum);
                    if (curMaxSize == 0 || curLastIndex < i) {
                        curMaxSize++;
                        curLastIndex = j;
                    } else if (curLastIndex >= j) {
                        curLastIndex = j;
                    }
                    maxSize.put(sum, curMaxSize);
                    lastIndex.put(sum, curLastIndex);
                }
            }

            int bestSum = -1;
            int bestSize = -1;
            for (int sum : maxSize.keySet()) {
                if (maxSize.get(sum) > bestSize) {
                    bestSize = maxSize.get(sum);
                    bestSum = sum;
                }
            }

            ArrayList<Interval> best = new ArrayList<>();
            lastIndex = new HashMap<>();
            maxSize = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = i; j < n; j++) {
                    sum += a[j];
                    if (sum != bestSum)
                        continue; // consider only bestSums
                    if (maxSize.containsKey(sum) == false) {
                        maxSize.put(sum, 0);
                    }
                    int curMaxSize = maxSize.get(sum);
                    int curLastIndex = curMaxSize == 0 ? -1 : lastIndex.get(sum);
                    if (curMaxSize == 0 || curLastIndex < i) {
                        curMaxSize++;
                        curLastIndex = j;
                        best.add(new Interval(i, j));
                    } else if (curLastIndex >= j) {
                        curLastIndex = j;
                        best.set(best.size() - 1, new Interval(i, j));
                    }
                    maxSize.put(sum, curMaxSize);
                    lastIndex.put(sum, curLastIndex);
                }
            }

            out.println(bestSize);
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

