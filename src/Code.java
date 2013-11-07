
public class Code {
	public static String dest(String in) { 
		//This translates the parser's dest() method to binary
		String out = null;
		//Values taken from page 110
		switch (in) { 
		case "null":
			out = "000";
			break;
		case "": 
			out = "000";
			break;
		case "M":
			out = "001";
			break;
		case "D":
			out = "010";
			break;
		case "MD":
			out = "011";
			break;
		case "A":
			out = "100";
			break;
		case "AM":
			out = "101";
			break;
		case "AD":
			out = "110";
			break;
		case "AMD":
			out = "111";
			break;
		}
		return out;
	}
	
	public static String jump(String in) { 
		//Translates the parser's jump() method to binary
		String out = null;
		//Values taken from page 110
		switch(in) { 
		case "null":
			out = "000";
			break;
		case "JGT":
			out = "001";
			break;
		case "JEQ":
			out = "010";
			break;
		case "JGE":
			out = "011";
			break;
		case "JLT":
			out = "100";
			break;
		case "JNE":
			out = "101";
			break;
		case "JLE":
			out = "110";
			break;
		case "JMP":
			out = "111";
			break;
		}
		return out;
	}
	
	static public String comp(String d) {
		String out = new String();
		
		switch(d) {
		case "null":
			out = "";
			break;
		case "0":
			out = "0101010";
			break;
		case "1":
			out = "0111111";
			break;
		case "-1":
			out = "0111010";
			break;
		case "!D":
			out = "0001101";
			break;
		case "!A":
			out = "0110001";
			break;
		case "-D":
			out = "0001111";
			break;
		case "-A":
			out = "0110011";
			break;
		case "D+1":
			out = "0011111";
			break;
		case "A+1":
			out = "0110111";
			break;
		case "D-1":
			out = "0001110";
			break;
		case "A-1":
			out = "0110010";
			break;
		case "D+A":
			out = "0000010";
			break;
		case "D-A":
			out = "0010011";
			break;
		case "A-D":
			out = "0000111";
			break;
		case "D&A":
			out = "0000000";
			break;
		case "D|A":
			out = "0010101";
			break;
		case "!M":
			out = "1110001";
			break;
		case "-M":
			out = "1110011";
			break;
		case "M+1":
			out = "1110111";
			break;
		case "M-1":
			out = "1110010";
			break;
		case "D+M":
			out = "1000010";
			break;
		case "D-M":
			out = "1010011";
			break;
		case "M-D":
			out = "1000111";
			break;
		case "D&M":
			out = "1000000";
			break;
		case "D|M":
			out = "1010101";
			break;
		case "M":
			out = "1110000";
			break;
		case "A":
			out = "0110000";
			break;
		case "D":
			out = "0001100";
			break;
		}
		return out;
	}
}

