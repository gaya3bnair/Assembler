import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
public class SymbolTable {
    public void Symbol(File inputFile,File symbolFile,File actualInput){
        RemoveWhitespaces rws=new RemoveWhitespaces();
        rws.RemoveWhiteSpaces(inputFile, actualInput);
        try {
            BufferedReader br = new BufferedReader(new FileReader(actualInput));
            BufferedWriter bw = new BufferedWriter(new FileWriter(symbolFile));
            String Line;
            // USED Linked Hashmap because the order was being messed up otherwise
            LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
            for (int i = 0; i < 16; i++) {
                String R = "R" + i;
                map.put(R, i);
            }
            map.put("SCREEN", 16384);
            map.put("KBD", 24576);
            map.put("SP", 0);
            map.put("LCL", 1);
            map.put("ARG", 2);
            map.put("THIS", 3);
            map.put("THAT", 4);
            // Predefined Variables have been added to the HashMAp
            int n = 0, m = 16;
            while ((Line = br.readLine()) != null) {
                n++;
                if (Line.startsWith("(") && Line.endsWith(")")) {
                    n--;
                    Line = Line.substring(Line.indexOf("(") + 1, Line.lastIndexOf(")"));
                    map.put(Line, n);
                }
            }
            br.close();
            BufferedReader br2 = new BufferedReader(new FileReader(inputFile));
            while ((Line = br2.readLine()) != null) {
                if (Line.startsWith("@")) {
                    Line = Line.split("@")[1].trim();
                    Boolean flag = Character.isLowerCase(Line.charAt(0));
                    if (flag) {
                        map.put(Line, m);
                        m++;
                    }
                }
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {

                // put key and value separated by a colon
                bw.write(entry.getKey() + ":" + entry.getValue());
                bw.newLine();
            }
            bw.flush();
            bw.close();
            br2.close();
        } catch (IOException e) {
            System.out.println("Error");

        }
    }
}
