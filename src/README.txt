README

Running My Program:
There are several ways of running my program. 
1) Load the project into Eclipse and run the Assembler.java file.

2) A more elegant solution would be to compile my project into a .jar executable file. In order to do this, go to the Eclipse "File" menu and select Export. Select Java, and then Runnable Jar File. Specify the launch configuration to be Assembler.java, and select your export destination. Select "Finish".

3) If you’d like to use the CLI instead of a GUI, you must first compile the program and then run it with your argument. There’s a requirement that the file you wish to assemble must be in the same directory as the assembler if done this way. 
	i) Compile the code by going to the directory of the .java files provided, and typing “javac Assembler.java”
	ii) run the program by typing “java Assembler [argument]”, where [argument] is your argument without the brackets. 
	iii) This will produce an output file in the same directory as the assembler, and will also launch a GUI.

Translating .ASM Files into .Hack files:
This program does not take arguments from the command line. Instead, it uses a GUI file browser to select a file by selecting the "Browse..." button. 
Once you've selected a file, clicking the assemble button will generate a .hack file in the same directory as the .asm file. 
