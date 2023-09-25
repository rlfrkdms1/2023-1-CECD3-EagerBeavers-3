import java.util.InputMismatchException;
import java.io.*;
import java.util.Vector;
import java.util.Collections;
import java.util.Arrays;

/**
 * Generated by Contest helper plug-in
 * Actual solution is at the bottom
 */
public class Main {
    public static void main(String[] args) {
        InputReader in = new StreamInputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        run(in, out);
    }

    public static void run(InputReader in, PrintWriter out) {
        Solver solver = new Sellerman();
        solver.solve(1, in, out);
        Exit.exit(in, out);
    }
}

class StreamInputReader extends InputReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar, numChars;

    public StreamInputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
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

    @Override
    public void close() {
        try {
            stream.close();
        } catch (IOException ignored) {
        }
    }
}

abstract class InputReader {
    private boolean finished = false;

    public abstract int read();

    public int nextInt() {
        return Integer.parseInt(nextToken());
    }


    public String nextToken() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuffer res = new StringBuffer();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }
    private boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public abstract void close();
}

interface Solver {
    public void solve(int testNumber, InputReader in, PrintWriter out);
}

class Exit {
    private Exit() {
    }

    public static void exit(InputReader in, PrintWriter out) {
        in.setFinished(true);
        in.close();
        out.close();
    }
}

class Sellerman implements Solver {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int x0 = in.nextInt();
        int y0 = in.nextInt();
        int n = in.nextInt() + 1;
        int[] x = new int[n];
        int[] y = new int[n];
        x[n - 1] = x0;
        y[n - 1] = y0;
        for (int i = 0; i < n - 1; ++i) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        int [][] g = new int[n][n];
        for(int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j) {
                g[i][j] = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
            }
        -- n;
        int[] dm = new int[1 << n];
        int[] prev = new int[1 << n];
        byte [] bit = new byte[1 << n];
        byte [] item0 = new byte[1 << n];
        byte [] item1 = new byte[1 << n];
        for (int i = 0; i < n; ++i) {
            bit[1 << i] = (byte) i;
        }
        Arrays.fill(dm, -1);
        dm[0] = 0;
        int tt[][] = new int[n][n];
        for (int i = 0; i < n; ++i)
            for (int j =0 ; j < n; ++j) {
                tt[i][j] = Math.min(g[n][i] + g[i][j] + g[j][n],
                                    g[n][j] + g[i][j] + g[i][n]);
            }
        for (int i = 0; i < (1 << n); ++i) {
            if (dm[i] == -1)continue;
            int t = (i ^ ((1 << n) - 1));
            int left = bit[t - (t & (t - 1))];
            for (int j = left; j < n; ++j) {
                if ((i & (1 << j)) > 0) continue;
                int nm = i | (1 << left) | (1 << j);
                if (dm[nm] == -1 || dm[nm] > dm[i] + tt[left][j]) {
                    dm[nm] = dm[i] + tt[left][j];
                    prev[nm] = i;
                    item0[nm] = (byte)left;
                    item1[nm] = (byte)j;
                }
            }
        }
        out.println(dm[(1 << n) - 1]);
        Vector<Vector<Integer>> path = new Vector<Vector<Integer>> () ;
        int cmask = (1 << n) - 1;
        while (cmask > 0) {
            int p = prev[cmask];
            Vector<Integer> tmp = new Vector<Integer> () ;
            tmp.add(0);
            tmp.add(item0[cmask] + 1);
            tmp.add(item1[cmask] + 1);
            cmask = prev[cmask];
            path.add(tmp);
        }
        Collections.reverse(path);
        int len = 0;
        for (Vector<Integer> vec : path)
            len += vec.size();
        int ans[] = new int[len];
        boolean[] valid = new boolean[len];
        Arrays.fill(valid, true);
        len = 0;
        for (Vector<Integer> vec : path) {
            for (int ttt : vec) {
                ans[len ++] = ttt;
            }
        }
        for (int i = 0; i < len - 1; ++i) {
            if (ans[i] == ans[i + 1])
                valid[i] = false;
        }
        for (int i = 0; i < len; ++i) {
            if (valid[i]) {
                out.print(ans[i] + " ");
            }
        }
        out.print("0");

    }
}
