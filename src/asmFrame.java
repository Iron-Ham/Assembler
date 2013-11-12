import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;


public class asmFrame extends JFrame {
	
	/*
	 * This class is the GUI of the assembler. 
	 */
	
	JButton browse;
	JTextField inFile;
	JButton assemble;
	JList<String> input; 
	JList<String> output;
	JLabel arrow;
	DefaultListModel<String> data; 
	DefaultListModel<String> outData;
	
	
	//List model & JList implementation adapted from a tutorial on Java2S.com 
	
	public asmFrame() { 
		//The three lines in every GUI constructor
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Assembler v2");
		setSize(800, 640);
		
		setLayout(new BorderLayout()); //The primary layout of the application is a border layout. 
		//All panels nested within it can have a different layout. 
		
		//Initialization
		File picture = new File("Extras/Arrow.png");
		inFile = new JTextField(60); 
		data = new DefaultListModel<String>();
		browse = new JButton("Browse...");
		assemble = new JButton("Assemble!");
		input = new JList<String>(data);
		arrow = new JLabel(); 
		outData = new DefaultListModel<String>();
		output = new JList<String>(outData);
		ImageIcon icon = new ImageIcon(picture.toString());
		arrow.setIcon(icon);
		
		JScrollPane scrollPane = new JScrollPane(input); //The list view of data being parsed now has a scroll bar
		JScrollPane scrollPane2 = new JScrollPane(output);
		
		//Bottom Panel; nested
		JPanel filePanel = new JPanel(new FlowLayout()); 
		filePanel.add(inFile);
		JPanel bottomButtonPanel = new JPanel(new FlowLayout()); 
		JPanel bottom = new JPanel(new BorderLayout());
		bottomButtonPanel.add(browse);
		bottomButtonPanel.add(assemble);
		bottom.add(bottomButtonPanel, BorderLayout.SOUTH);
		bottom.add(filePanel, BorderLayout.CENTER);
		
		//Middle Panel; nested
		JPanel middle = new JPanel(new BorderLayout()); //The primary center panel
		
		JPanel midLeft = new JPanel(new FlowLayout()); 	//The panel for the data input
		JPanel midRight = new JPanel(new FlowLayout());	//Panel for data output
		
		Dimension size = new Dimension(350, 450);		//The size of the input and output panels
		scrollPane.setPreferredSize(size);
		midLeft.add(scrollPane); 						
		
		scrollPane2.setPreferredSize(size);
		midRight.add(scrollPane2);
		
		//Adding all things to the middle panel
		middle.add(midRight, BorderLayout.EAST);
		middle.add(midLeft, BorderLayout.WEST);
		middle.add(arrow, BorderLayout.CENTER);
		
		//Adding the panels onto the frame. 
		add(bottom, BorderLayout.SOUTH);
		add(middle, BorderLayout.CENTER);
	}
	
	public JList<String> getOutput() {
		return output;
	}

	public void setOutput(JList<String> output) {
		this.output = output;
	}

	public DefaultListModel<String> getOutData() {
		return outData;
	}

	public void setOutData(DefaultListModel<String> outData) {
		this.outData = outData;
	}

	public void registerListener(ActionListener e) { 
		browse.addActionListener(e);
		assemble.addActionListener(e);
	}

	
	//Generated setters and getters for all variables. 

	public JButton getBrowse() {
		return browse;
	}

	public void setBrowse(JButton browse) {
		this.browse = browse;
	}

	public JTextField getInFile() {
		return inFile;
	}

	public void setInFile(JTextField inFile) {
		this.inFile = inFile;
	}

	public JButton getAssemble() {
		return assemble;
	}

	public void setAssemble(JButton assemble) {
		this.assemble = assemble;
	}

	public JList<String> getInput() {
		return input;
	}

	public void setInput(JList<String> input) {
		this.input = input;
	}

	public DefaultListModel<String> getData() {
		return data;
	}

	public void setData(DefaultListModel<String> data) {
		this.data = data;
	}
	
	public static void infoBox(String infoMessage, String location) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + location, JOptionPane.INFORMATION_MESSAGE);
    }
}
