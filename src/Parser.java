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
		//Determines what type of command the current command is.
		boolean numCheck;
		String a = "A_COMMAND";
		String c = "C_COMMAND";
		String l = "L_COMMAND";
		//A command start with @
		if (currentCommand.startsWith("@")) {
			return a;
		}
		//L commands start with (
		if (currentCommand.startsWith("(")) { 
			return l;
		}
		else 
			//All other commands are c commands
			return c;
		
	}
    public String symbol() {
        String symbol = null;
        if (this.commandType() == "A_COMMAND" ) {
            symbol = currentCommand.substring(1, currentCommand.length());
        } else if (this.commandType() == "L_COMMAND" ) {
            symbol = currentCommand.substring(1, currentCommand.length() - 1);
        }
        else { }
        return symbol;
    }
	
	public String dest() { 
		//returns the destination bits d1 d2 d3
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
		//This was the most difficult method to implement because I could not use a split as before. 
		//Using [=|;]+ as a split regex would cause a split on the "|" character, instead of that character being treated
		//as an or. 
		
		if (this.commandType() == "C_COMMAND") { 
			if (currentCommand.contains("="))  {
				String tmp = currentCommand.substring(currentCommand.lastIndexOf("=") + 1, currentCommand.length());
				if (tmp.contains(";")) {  //Added to pass the uberHack.asm test
					return tmp.substring(0, tmp.lastIndexOf(";"));
				}
				else return tmp;
			}
			else {
				if (currentCommand.contains(";"))  { 
					return currentCommand.substring(0, currentCommand.lastIndexOf(";")); 
				}
				else 
					//If it does not contain a semicolon, it is a direct command such as "0", "A", or "D|M"
					return currentCommand;
			}
		}
		else return "Nope";
	}
	
	public String jump() { 
		//Only for c commands
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
		/**
		 * This is my only method that is not in the original API. I added this method because:
		 * 			I am using a GUI and once I populate the JList, I need to reset the scanner
		 * 		 	so I can go through the file again in the assembly stage.
		 */
		this.stdin = new Parser(data).stdin;
	}
	
	
	
}

