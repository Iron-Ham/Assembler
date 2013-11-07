import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class asmListener implements ActionListener {
	//Bringing up a standard open dialog only requires two lines of code
	//final JFileChooser fc = new JFileChooser();
	//int returnVal = fc.showOpenDialog(aComponent);
	
	Parser p;
	asmFrame frame;
	JFileChooser fc; 
	
	public asmListener(asmFrame f) {
		frame = f;
		fc = new JFileChooser();
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
				try {
					fw = new FileWriter(new File(fileName));
					BufferedWriter writer = new BufferedWriter(fw);
					while(p.hasMoreCommands()) { 
						p.advance();
						if(p.commandType() == "C_COMMAND") {
							writer.write("111" + Code.comp(p.comp()) + Code.dest(p.dest()) + Code.jump(p.jump()) + "\n");
						}
						if(p.commandType() == "A_COMMAND") {
							String binary = new String();
							binary = Integer.toBinaryString(Integer.valueOf(p.symbol()));
							String out = new String();
							for(int i = 0; i < 16-binary.length(); i++)
								out = out + "0";
							writer.write(out + binary + "\n");
						}
						
						//I'm not quite sure what to do with L_Commands? 
					}
					writer.flush();
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
	}
	public void passFile(String s) { 
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
		try {
			fw = new FileWriter(new File(fileName));
			BufferedWriter writer = new BufferedWriter(fw);
			while(p.hasMoreCommands()) { 
				p.advance();
				if(p.commandType() == "C_COMMAND") {
					writer.write("111" + Code.comp(p.comp()) + Code.dest(p.dest()) + Code.jump(p.jump()) + "\n");
				}
				if(p.commandType() == "A_COMMAND") {
					String binary = new String();
					binary = Integer.toBinaryString(Integer.valueOf(p.symbol()));
					String out = new String();
					for(int i = 0; i < 16-binary.length(); i++)
						out = out + "0";
					writer.write(out + binary + "\n");
				}
				
				//I'm not quite sure what to do with L_Commands? 
			}
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
