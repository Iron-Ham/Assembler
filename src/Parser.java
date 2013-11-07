import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Parser {

	File data;
	Scanner stdin;
	String currentCommand;
	
	//Constructor
	public Parser(File data) {
		this.data = data;
		currentCommand = new String(); 
		try {
			stdin = new Scanner(data);
		} catch (FileNotFoundException e) {
			System.out.println("File not found - Error");
			e.printStackTrace();
		}
	}
	
	//Seems a bit redundant, no? 
	public boolean hasMoreCommands() {
		if (stdin.hasNext())  
			return true;
		else 
			return false;
	}
	
	//Advance will skip comments and empty lines, as well as one-char lines
	public void advance() { 
		if (this.hasMoreCommands()) { 
			String tmp = stdin.nextLine();
			tmp = tmp.trim();
			while (this.hasMoreCommands() && (tmp.equals("") || tmp.equals("\n") || tmp.startsWith("//"))) {
				tmp = stdin.nextLine();
				tmp = tmp.trim();
			}
			
			currentCommand = tmp;
		}
	}
	
	public String commandType() { 
		boolean numCheck;
		String a = "A_COMMAND";
		String c = "C_COMMAND";
		String l = "L_COMMAND";
		if (currentCommand.startsWith("@")) {
			return a;
		}
		if (currentCommand.startsWith("(")) { 
			return l;
		}
		else 
			return c;
		
	}
	
	public String symbol() {
		if (this.commandType() != "C_COMMAND") {
			return currentCommand.substring(1); 
		}
		else return "Nope";
	}
	
	public String dest() { 
		if (this.commandType() == "C_COMMAND") {
			if (currentCommand.contains("=")) {
				String[] split = currentCommand.split("[=|;]+");
				return split[0];
			}
			else return "null";
		}
		else return "null";
	}
	
	public String comp() { 
		if (this.commandType() == "C_COMMAND") { 
			if (currentCommand.contains("="))  {
				String tmp = currentCommand.substring(currentCommand.lastIndexOf("=") + 1, currentCommand.length());
				if (tmp.contains(";")) { 
					return tmp.substring(0, tmp.lastIndexOf(";"));
				}
				else return tmp;
			}
			else {
				if (currentCommand.contains(";"))  { 
					return currentCommand.substring(0, currentCommand.lastIndexOf(";")); 
				}
				else 
					return currentCommand;
			}
		}
		else return "Nope";
	}
	
	public String jump() { 
		if (this.commandType() == "C_COMMAND") { 
			if (currentCommand.contains(";")) {
				String[] split = currentCommand.split(";");
				return split[1];
//				return currentCommand.substring(currentCommand.lastIndexOf(";") + 1, currentCommand.length());
			}
			else return "null";
		}
		else return "Nope";
	}
	
	public void reset() { 
		this.stdin = new Parser(data).stdin;
	}
	
	
	
}

