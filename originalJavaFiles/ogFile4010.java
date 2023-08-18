import java.util.*;
import java.io.*;
public class EdC {
	static long[] mods = {1000000007, 998244353, 1000000009};
	static long mod = mods[0];
	public static MyScanner sc;
    public static PrintWriter out;
    static char[][] grid;
    static int n;
    static int t;
    static int[][] dp;
    static int[] times;
    static int[] genre;
	public static void main(String[] omkar) throws Exception{
		// TODO Auto-generated method stub
 		sc = new MyScanner();
 		out = new PrintWriter(System.out);
 		n = sc.nextInt();
 		t = sc.nextInt();
 		times = new int[n];
 		genre = new int[n];
 		for(int j =0 ;j<n;j++){
 			times[j] = sc.nextInt();
 			genre[j] = sc.nextInt();		
 		}
 		dp = new int[1<<n][4];
 		for(int j = 0;j<1<<n;j++)
 			Arrays.fill(dp[j],  -1);
 		for(int j=0;j<1<<n;j++){
 			letsgodp(j, 1);
 			letsgodp(j, 2);
 			letsgodp(j, 3);
 		}
 		int ans = 0;
 		for(int j=0;j<1<<n;j++){
 			int time = 0;
 			for(int k = 0;k<n;k++){
 				if (((1<<k) & j) != 0){
 					time+=times[k];
 				}
 			}
 			if (time == t){
 				ans+=dp[j][1];
 				ans%=mod;
 				ans+=dp[j][2];
 				ans%=mod;
 				ans+=dp[j][3];
 				ans%=mod;
 			}
 		}
 		out.println(ans);
 		out.close();	
 	}
	public static void letsgodp(int mask, int dg){
		if (dp[mask][dg] != -1)
			return;
		dp[mask][dg] = 0;
		for(int j = 0;j<n;j++){
			if (((1<<j) & mask) != 0 && genre[j] == dg){
				int submask = mask - (1<<j);
				int og1 = genre[j]+1 > 3 ? genre[j]-2 : genre[j]+1;
				int og2 = genre[j]+2 > 3 ? genre[j]-1 : genre[j]+2;
				if (submask != 0){
					letsgodp(submask, og1);
					letsgodp(submask, og2);
					dp[mask][dg] +=(dp[submask][og1] + dp[submask][og2]);
					dp[mask][dg] %=mod;
				}
				else{
					dp[mask][dg] = 1;
				}
			}
		}
	}
	public static void sort(int[] array){
		ArrayList<Integer> copy = new ArrayList<Integer>();
		for (int i : array)
			copy.add(i);
		Collections.sort(copy);
		for(int i = 0;i<array.length;i++)
			array[i] = copy.get(i);
	}
	static String[] readArrayString(int n){
		String[] array = new String[n];
		for(int j =0 ;j<n;j++)
			array[j] = sc.next();
		return array;
	}
	static int[] readArrayInt(int n){
    	int[] array = new int[n];
    	for(int j = 0;j<n;j++)
    		array[j] = sc.nextInt();
    	return array;
    }
	static int[] readArrayInt1(int n){
		int[] array = new int[n+1];
		for(int j = 1;j<=n;j++){
			array[j] = sc.nextInt();
		}
		return array;
	}
	static long[] readArrayLong(int n){
		long[] array = new long[n];
		for(int j =0 ;j<n;j++)
			array[j] = sc.nextLong();
		return array;
	}
	static double[] readArrayDouble(int n){
		double[] array = new double[n];
		for(int j =0 ;j<n;j++)
			array[j] = sc.nextDouble();
		return array;
	}
	static int minIndex(int[] array){
		int minValue = Integer.MAX_VALUE;
		int minIndex = -1;
		for(int j = 0;j<array.length;j++){
			if (array[j] < minValue){
				minValue = array[j];
				minIndex = j;
			}
		}
		return minIndex;
	}
	static int minIndex(long[] array){
		long minValue = Long.MAX_VALUE;
		int minIndex = -1;
		for(int j = 0;j<array.length;j++){
			if (array[j] < minValue){
				minValue = array[j];
				minIndex = j;
			}
		}
		return minIndex;
	}
	static int minIndex(double[] array){
		double minValue = Double.MAX_VALUE;
		int minIndex = -1;
		for(int j = 0;j<array.length;j++){
			if (array[j] < minValue){
				minValue = array[j];
				minIndex = j;
			}
		}
		return minIndex;
	}
	static long power(long x, long y){
		if (y == 0)
			return 1;
		if (y%2 == 1)
			return (x*power(x, y-1))%mod;
		return power((x*x)%mod, y/2)%mod;
	}
	static void verdict(boolean a){
        out.println(a ? "YES" : "NO");
    }
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;
        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
        String nextLine() {
            String str = "";
            try{
                str = br.readLine();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
        
    }	
}

//StringJoiner sj = new StringJoiner(" "); 
//sj.add(strings)
//sj.toString() gives string of those stuff w spaces or whatever that sequence is