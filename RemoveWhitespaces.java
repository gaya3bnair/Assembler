import java.io.*;
public class RemoveWhitespaces {
    public void RemoveWhiteSpaces(File inputFile,File outputFile){
      try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
            String Line;
            while ((Line = br.readLine()) != null) {

                Line = Line.split("//")[0].trim();
                if (Line.isEmpty() || Line.trim().equals("") || Line.trim().equals("\n")) {
                    continue;
                }
                bw.write(Line);
                bw.newLine();
            }
            bw.close();
            br.close();
        } catch (IOException e) {
            System.out.println("Error");

        }  
    }
}
