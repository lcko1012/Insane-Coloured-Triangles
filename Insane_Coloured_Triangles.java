package Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


/*
 * Gỉa sử các màu là số nguyên modulo 3
  	Hàng đầu tiên ( a1, a2, a3... an)
  	Hàng 2 (-a1 - a2, -a2 - a3, ... ,-an-1 - an)
	Hàng 3    (a1+2a2+a3, a2+2a3+a4,.... )
  
 * Ta tính toán được màu của hàng cuối cùng sẽ là :
  	   ((−1)^(n−1) n∑k=1 C(n-1,k-1) *ak ) modulo 3   (1)
  
 * Ta sử dụng công thức modulo, để tránh việc n quá lớn
  	(a * b) mod c = ((a mod c) * (b mod c)) mod c
 	(a ± b) mod c = ((a mod c) ± (b mod c)) mod c
  
 * 	(1) => ((-1)^(n-1)  n∑k=1 ak*[(C(n-1,k-1) mod3] ) mod3 (2)
  
 * Áp dụng Lucas's theorem để tính C(n-1,k-1) mod3
  		C(N, K) % MOD = (C(n0, k0) * C(n1, k1) * … * C(nm-1, km-1)) % MOD
  	N = n0 * MOD^0 + n1 * MOD^1 + … + nm-1 * MOD^m-1
  	K = k0 * MOD^0 + k1 * MOD^1 + … + km-1 * MOD^m-1
 * */

public class Insane_Coloured_Triangles {
	
	//Biểu diễn số n dưới dạng cơ số 3
	public static ArrayList<Integer>  getRepresentation(int N){
		ArrayList<Integer> res = new ArrayList<Integer>();
		while(N > 0) {
			res.add(N%3);
			N /= 3;
		}
		return res;
	}
	
	//n0,k0 luôn nằm trong khoảng [0,2], ta dễ dàng tính được C(n,k)
	public static int C(int n, int k) {
		if(n<k) {
			return 0;
		}
		switch(n) {
		case 0:
		case 1:
			return 1;
		case 2:
			if(k==1) {
				return 2;
			}
			else {
				return 1;
			}
		case 3:
			return 1;
		default:
			return 0;
		}
	}
	
	//Công thức Lucas
	public static int Lucas(ArrayList<Integer> dig_n, ArrayList<Integer> dig_k) {
	int res = 1;
	for(int i=0;i< dig_k.size(); i++)
		{
		//C(N, K) % 3 = (C(n0, k0) * C(n1, k1) * … * C(nm-1, km-1)) % 3
		res = (res * C(dig_n.get(i),dig_k.get(i))) % 3;
		}
	return res;
	}
	
	//Chuyển RGB thành 012
	public static int char_2_int(char c) {
		switch(c) {
		case 'R':
			return 0;
		case 'G':
			return 1;
		case 'B' :
			return 2;
		default:
			return 3;
		}
	}
	
	//Chuyển 012 thành RGB
	public static char int_2_char(int i) {
		switch(i) {
		case 0:
			return 'R';
		case 1: 
			return 'G';
		case 2:
			return 'B';
		default:
			return '\0';
		}
	}
	
	public static char Triangle (String row) {
		char[] input = row.toCharArray();
		int n = input.length;
		//Mảng chứa các số nguyên modulo 3 của n: n0,n1..
		ArrayList<Integer> dig_n = getRepresentation(n-1);
		int sum = 0;
		
		
		for(int k = 0; k < n; k++) {
			//Tính k0,k1..
			ArrayList<Integer> dig_k = getRepresentation(k);
			//Tính C(n-1,k-1) mod 3
			int Cnk_mod3 = Lucas(dig_n,dig_k);
			int a = char_2_int(input[k]);
		
			sum = (sum + Cnk_mod3 * a) %3;
		}
	
		int sign = (n%2)*2-1;
		//Sử dụng công thức (2)
		int sum_mod3 = (3+(sign*(int)(sum%3))) %3;
		return int_2_char(sum_mod3);
	}
	
	
}
