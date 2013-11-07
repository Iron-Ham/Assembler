
public class Assembler {

	public static void main(String[] args) {
		
		asmFrame frame = new asmFrame();
		asmListener listener = new asmListener(frame); 
		frame.registerListener(listener); 
		if (args != null) { 
			listener.passFile(args[0]);
		}
		
		frame.setVisible(true);
	}

}
