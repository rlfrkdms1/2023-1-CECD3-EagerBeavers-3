import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author anand.oza
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        APaintTheNumbers solver = new APaintTheNumbers();
        solver.solve(1, in, out);
        out.close();
    }

    static class APaintTheNumbers {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();

            int[] a = in.readIntArray(n);

            Arrays.sort(a);

            int answer = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] == 0)
                    continue;

                answer++;
                for (int j = 0; j < n; j++) {
                    if (j == i)
                        continue;
                    if (a[j] % a[i] == 0) {
                        a[j] = 0;
                    }
                }

                a[i] = 0;
            }

            out.println(answer);
        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public int[] readIntArray(int n) {
            int[] x = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = nextInt();
            }
            return x;
        }

    }
}

