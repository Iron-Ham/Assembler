import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class asmListener implements ActionListener {
	Parser p;
	asmFrame frame;
	JFileChooser fc; 
	SymbolTable st;
	
	public asmListener(asmFrame f, SymbolTable st) {
		//Constructor
		frame = f;
		fc = new JFileChooser();
		this.st = st;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		Object source = e.getSource(); 
		if (source instanceof JButton) {
			JButton button = (JButton) source;
			String buttonText = button.getActionCommand();
			
			switch(buttonText) {
			case "Browse...":
				//Adapted from a tutorial on Java2S.com
				int returnVal = fc.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) { 
					File file = fc.getSelectedFile();
					String path = file.getAbsolutePath();
					frame.getInFile().setText(path);
					
					p = new Parser(file);
					frame.getData().clear();
					frame.getOutData().clear();
					while(p.hasMoreCommands()) {
						p.advance();
						frame.getData().addElement(p.currentCommand);
						//Display contents of .asm file in the JList
					}
				}
				break;
			case "Assemble!":
				System.out.println("Assemble!");
				FileWriter fw; 
				p.reset(); //Nothing gets printed without this method
				String fileName = fc.getSelectedFile().getAbsolutePath();
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
				fileName = fileName + ".hack";
				firstPass();
				secondPass(fileName);
				
			}
		}
	}
	
	public void firstPass() { 
		p.reset();
		int count = -1;
		String symbol = null;
		while (p.hasMoreCommands()) {
			p.advance();
			if (p.commandType() == "L_COMMAND") { 
				symbol = p.symbol();
				st.addEntry(symbol, count +1);
			}
			else 
				count++;
		}
		p.reset();
	}
	
	public void secondPass(String fileName) { 
		p.reset();
		FileWriter fw;
		BufferedWriter writer;
		try {
			fw = new FileWriter(new File(fileName));
			writer = new BufferedWriter(fw);
			while(p.hasMoreCommands()) { 
				p.advance();
				if(p.commandType() == "C_COMMAND") {
					writer.write("111" + Code.comp(p.comp()) + Code.dest(p.dest()) + Code.jump(p.jump()) + "\n");
					frame.getOutData().addElement("111" + Code.comp(p.comp()) + Code.dest(p.dest()) + Code.jump(p.jump()));
				}
				if(p.commandType() == "A_COMMAND") {
					String binary = new String();
					binary = Integer.toBinaryString(Integer.valueOf(p.symbol()));
					String out = new String();
					for(int i = 0; i < 16-binary.length(); i++)
						out = out + "0";
					writer.write(out + binary + "\n");
					frame.getOutData().addElement(out + binary);
				}
			}
			writer.flush();
			writer.close();
		} catch (IOException e){ 
			e.printStackTrace();
		}
		p.reset();
		asmFrame.infoBox("Assembly Complete!", "Assembler v2");
	}
	
	public void passFile(String s) { 
		//This method is just used in order to pass files from the command line. 
		//It is largely a copy of the actionPerformed method.
		frame.getInFile().setText(s);
		File file = new File(s);
		p = new Parser(file);
		frame.getData().clear();
		while(p.hasMoreCommands()){
			p.advance();
			frame.getData().addElement(p.currentCommand);
		}
		
		FileWriter fw;
		p.reset();
		String fileName = s;
		fileName = fileName.substring(0, fileName.lastIndexOf("."));
		fileName = fileName + ".hack";
		firstPass();
		secondPass(fileName);
	}
	
}
