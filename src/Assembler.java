
public class Assembler {

	public static void main(String[] args) {
		asmFrame frame = new asmFrame();
		asmListener listener = new asmListener(frame); 
		frame.registerListener(listener);
		frame.setVisible(true);
	}

}
