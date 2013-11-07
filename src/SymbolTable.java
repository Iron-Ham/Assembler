import java.util.HashMap;
import java.util.Map;


public class SymbolTable {
	Map<String, Integer> table;
	public SymbolTable() { 
		//initialize all variables
		//Add all pre-defined symbols
		String s = new String();
		table = new HashMap<String, Integer>();
		table.put("SP", 0);
		table.put("LCL", 1);
		table.put("ARG", 2);
		table.put("THIS", 3);
		table.put("THAT", 4);
		for (int i = 0; i < 16; i++) { 
			s = "R" + i;
			table.put(s, i);
		}
		table.put("SCREEN", 16384);
		table.put("KBD", 24576);
	}
	
	public void addEntry(String symbol, int address) {
		//allows for addition of new symbol
		table.put(symbol, address);
	}
	
	public boolean contains(String symbol) {
		//checks to see if symbol exists in table
		return table.containsKey(symbol);
	}
	
	public int getAddress(String symbol) { 
		//returns address of symbol if symbol exists
		if (this.contains(symbol)) { 
			return table.get(symbol);
		}
		else return -1; //if symbol doesn't exist, return -1
	}
}
