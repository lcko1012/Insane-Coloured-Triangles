package Input_Output;

import java.util.Date;

public class In_Out {
	private String input;
	private String output;
	private Date dateTime;
	public In_Out() {
//		this.dateTime = new Date();
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
}
