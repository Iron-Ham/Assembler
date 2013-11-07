README

Running My Program:
There are several ways of running my program. 
1) Load the project into Eclipse and run the Assembler.java file.

2) A more elegant solution would be to compile my project into a .jar executable file. In order to do this, go to the Eclipse "File" menu and select Export. Select Java, and then Runnable Jar File. Specify the launch configuration to be Assembler.java, and select your export destination. Select "Finish".

Translating .ASM Files into .Hack files:
This program does not take arguments from the command line. Instead, it uses a GUI file browser to select a file by selecting the "Browse..." button. 
Once you've selected a file, clicking the assemble button will generate a .hack file in the same directory as the .asm file. 