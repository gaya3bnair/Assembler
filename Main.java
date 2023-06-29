import java.io.*;
public class Main{
    public static void main(String[] args) {
         try {
            File inputFile = new File("Assembly.asm");
            File outputFile = new File("hack.txt");
            File actualInput=new File("nowhite.asm");
            ACInstruction main=new ACInstruction();
            main.Ainst(inputFile, outputFile,actualInput);
         }
         catch (Exception e) {
            System.out.println("Error in main");
        }
    }
}