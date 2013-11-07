
public class Assembler {

	public static void main(String[] args) {
		
		asmFrame frame = new asmFrame();
		SymbolTable table = new SymbolTable();
		asmListener listener = new asmListener(frame, table); 
		frame.registerListener(listener); 
		if (args.length != 0) { 
			listener.passFile(args[0]);
		}
		frame.setVisible(true);
	}

}
