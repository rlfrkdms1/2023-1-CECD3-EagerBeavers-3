import java.awt.List;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;


public final class CF_524_D2_D {

	static boolean verb=true;
	static void log(Object X){if (verb) System.err.println(X);}
	static void log(Object[] X){if (verb) {for (Object U:X) System.err.print(U+" ");System.err.println("");}}
	static void log(int[] X){if (verb) {for (int U:X) System.err.print(U+" ");System.err.println("");}}
	static void log(int[] X,int L){if (verb) {for (int i=0;i<L;i++) System.err.print(X[i]+" ");System.err.println("");}}
	static void log(long[] X){if (verb) {for (long U:X) System.err.print(U+" ");System.err.println("");}}

	static void logWln(Object X){if (verb) System.err.print(X);}
	static void info(Object o){	System.out.println(o);}
	static void output(Object o){outputWln(""+o+"\n");	}
	static void outputWln(Object o){try {out.write(""+ o);} catch (Exception e) {}}





	// Global vars
	static BufferedWriter out;
	static InputReader reader;


	static long mod=1000000007;




	static void process() throws Exception {


		out = new BufferedWriter(new OutputStreamWriter(System.out));
		reader=new InputReader(System.in);

		long mymax=1L<<62;
		log(mymax);


		long cut=0;
		int it=0;
		long squares=1;
		int GX=32;
		long[] maxgen=new long[GX];
		while (cut<2000000000000000000L){
			maxgen[it]=cut;
			//log("squares:"+squares+" cut:"+cut+" it:"+it+" size:"+(1L<<it));
			it++;
			cut=1+4*cut;
			squares*=4;
			
		}
		//log(maxgen);
		
		
		int T=reader.readInt();
		for (int t=0;t<T;t++){
			int n=reader.readInt();
			long k=reader.readLong();
	
			if (n>=GX){
				output("YES "+(n-1));
			} else {
				// do first cut
				long pieces=3;
				long minc=1;
				int size=n-1;
				long maxc=1+maxgen[size];
				while (size>0 && maxc<k){
					minc+=pieces;
					maxc+=pieces+maxgen[size-1]*(2*pieces-1);
					size--;
					pieces=2*pieces+1;
					
				}
				if (minc<=k && maxc>=k){
					output("YES "+size);
				} else {
					output("NO");
					//log("//check:"+maxc+" "+maxgen[n]);
				}
				//log("size:"+size+" minc:"+minc+" maxc:"+maxc);
			}




		}



		try {
			out.close();
		}
		catch (Exception EX){}


	}


	public static void main(String[] args) throws Exception {
		process();

	}

	static final class InputReader {
		private final InputStream stream;
		private final byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		private int read() throws IOException {
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read(buf);
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}


		public final String readString() throws IOException {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			StringBuilder res=new StringBuilder();
			do {
				res.append((char)c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public final int readInt() throws IOException {
			int c = read();
			boolean neg=false;
			while (isSpaceChar(c)) {
				c = read();
			}
			char d=(char)c;
			//log("d:"+d);
			if (d=='-') {
				neg=true;
				c = read();
			}
			int res = 0;
			do {
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			//log("res:"+res);
			if (neg)
				return -res;
			return res;

		}

		public final long readLong() throws IOException {
			int c = read();
			boolean neg=false;
			while (isSpaceChar(c)) {
				c = read();
			}
			char d=(char)c;
			//log("d:"+d);
			if (d=='-') {
				neg=true;
				c = read();
			}
			long res = 0;
			do {
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			//log("res:"+res);
			if (neg)
				return -res;
			return res;

		}




		private boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}


}