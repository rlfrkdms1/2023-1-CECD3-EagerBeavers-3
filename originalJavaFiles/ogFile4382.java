import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        FastScanner in = new FastScanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        F solver = new F();
        solver.solve(1, in, out);
        out.close();
    }

    static class F {
        public void solve(int testNumber, FastScanner in, PrintWriter out) {
            int n = in.ni(), m = in.ni();
            int[][] a = new int[n][m];
            for (int i = 0; i < n; i++) {
                a[i] = in.na(m);
            }
            if (n == 1) {
                int ans = Integer.MAX_VALUE;
                for (int i = 1; i < m; i++) {
                    ans = Math.min(ans, Math.abs(a[0][i] - a[0][i - 1]));
                }
                out.println(ans);
                return;
            }
            int[][] mk = new int[n][n];
            int[][] mk1 = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int minK = Integer.MAX_VALUE;
                    int minK1 = Integer.MAX_VALUE;
                    int minK2 = Integer.MAX_VALUE;
                    for (int l = 0; l < m; l++) {
                        minK = Math.min(minK, Math.abs(a[i][l] - a[j][l]));
                        if (l > 0) {
                            minK1 = Math.min(minK1, Math.abs(a[i][l] - a[j][l - 1]));
                            minK2 = Math.min(minK2, Math.abs(a[i][l - 1] - a[j][l]));
                        }
                    }
                    mk[i][j] = mk[j][i] = minK;
                    mk1[i][j] = minK1;
                    mk1[j][i] = minK2;
                }
            }

            int ans = 0;
            for (int first = 0; first < n; first++) {
                int[][] dp = new int[1 << n][n];
                for (int mask = 1; mask < (1 << n) - 1; mask++) {
                    int bc = Integer.bitCount(mask);
                    if ((mask & (1 << first)) != 0) {
                        if (bc == 1) {
                            dp[mask][first] = Integer.MAX_VALUE;
                        }
                        for (int i = 0; i < n; i++) {
                            if ((mask & (1 << i)) != 0) {
                                for (int j = 0; j < n; j++) {
                                    if ((mask & (1 << j)) == 0) {
                                        dp[mask | (1 << j)][j] = Math.max(dp[mask | (1 << j)][j], Math.min(dp[mask][i], mk[i][j]));
                                    }
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    if (i != first) {
                        ans = Math.max(ans, Math.min(dp[(1 << n) - 1][i], mk1[first][i]));
                    }
                }
            }
            out.println(ans);
        }

    }

    static class FastScanner {
        private BufferedReader in;
        private StringTokenizer st;

        public FastScanner(InputStream stream) {
            in = new BufferedReader(new InputStreamReader(stream));
        }

        public String ns() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String rl = in.readLine();
                    if (rl == null) {
                        return null;
                    }
                    st = new StringTokenizer(rl);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        public int ni() {
            return Integer.parseInt(ns());
        }

        public int[] na(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = ni();
            return a;
        }

    }
}

