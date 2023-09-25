/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
public class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner scan=new Scanner(System.in);
		long x=scan.nextLong();
		long k=scan.nextLong();
		long MOD=1000000007;
		if(x==0){System.out.println(0);return;}
		x%=MOD;
		long a=(new Num()).pow(2L,k+1);
		long b=(new Num()).pow(2L,k);
		long res=(a*x)%MOD-b+1;
		if(res<0){res+=MOD;}
		System.out.println(res%MOD);
	}
}

class Num{
	long MOD=1000000007;
	long pow(long x,long k){
		long base=x%MOD;
		long res=1;
		while(k>0){
			if((k&1)==1){
				res=(res*base)%MOD;
			}
			base=(base*base)%MOD;
			k>>=1;
		}
		return res;
	}
}