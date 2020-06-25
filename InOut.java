package InOut;

import java.util.ArrayList;
import java.util.Date;

public class InOut {
	private String input;
	private String output;
	private Date dateTime;
	public InOut() {
	}
	
	public void setInput(String input) {
		this.input = input;
	}
	
	public String getInput() {
		return this.input;
	}
	
	public void setOutput(String output) {
		this.output = output;
	}
	
	public String getOutput() {
		return this.output;
	}
	
	public void setDTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	public Date getDTime() {
		return this.dateTime;
	}
	
	/**
	 * Thuật toán 1
	 */
	
	static char[] values = new char[255];
	static {
		values['R' + 'R'] = 'R';
		values['G' + 'G'] = 'G';
		values['B' + 'B'] = 'B';
		values['R' + 'G'] = 'B';
		values['R' + 'B'] = 'G';
		values['B' + 'G'] = 'R';
	}
	public void Triangle() {
		char[] result = this.input.toCharArray();
		
		for(int size=result.length; size>1; size--) {
			for(int i=0; i<size-1; i++) {
				result[i] = values[result[i] + result[i+1]];
			}
		}
		this.output = Character.toString(result[0]);
	}
	
	/**
	 * Thuật toán 2
	 */
	
	public ArrayList<Integer>  getRepresentation(int N){
		ArrayList<Integer> res = new ArrayList<Integer>();
		while(N > 0) {
			res.add(N%3);
			N /= 3;
		}
		return res;
	}
	
	//n0,k0 luôn nằm trong khoảng [0,2], ta dễ dàng tính được C(n,k)
	public int C(int n, int k) {
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
	public int Lucas(ArrayList<Integer> dig_n, ArrayList<Integer> dig_k) {
	int res = 1;
	for(int i=0;i< dig_k.size(); i++)
		{
		//C(N, K) % 3 = (C(n0, k0) * C(n1, k1) * … * C(nm-1, km-1)) % 3
		res = (res * C(dig_n.get(i),dig_k.get(i))) % 3;
		}
	return res;
	}
	
	//Chuyển RGB thành 012
	public int char_2_int(char c) {
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
	public char int_2_char(int i) {
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
	
	public void Triangle2 () {
		char[] input = this.input.toCharArray();
		int n = input.length;
		//Mảng chứa các số nguyên modulo 3 của n: n0,n1..
		ArrayList<Integer> dig_n = getRepresentation(n-1);
		int sum = 0;
		
		//Biến kiểm tra kí tự nhập sai
//		int kt = 1;
		
		for(int k = 0; k < n; k++) {
			//Tính k0,k1..
			ArrayList<Integer> dig_k = getRepresentation(k);
			//Tính C(n-1,k-1) mod 3
			int Cnk_mod3 = Lucas(dig_n,dig_k);
			int a = char_2_int(input[k]);
			
			sum = (sum + Cnk_mod3 * a) %3;
		}

		//kiểm tra (-1)^n-1 
		int sign = (n%2)*2-1;
		//Sử dụng công thức (2)
		int sum_mod3 = (3+(sign*(int)(sum%3))) %3;
		//return int_2_char(sum_mod3);
		this.output = Character.toString(int_2_char(sum_mod3));
	}
}
