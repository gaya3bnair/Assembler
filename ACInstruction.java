import java.io.*;
import java.util.LinkedHashMap;

public class ACInstruction {
    public InstructionSet sc = new InstructionSet();

    public String destin(String dest) {
        return sc.destMap.get(dest);
    }

    public String comput(String comp) {
        return sc.compMap.get(comp);
    }

    public String jumpin(String jump) {
        return sc.jumpMap.get(jump);
    }

    public String cInstruction(String dest, String comp, String jump) {
        return "111" + comput(comp) + destin(dest) + jumpin(jump);
    }

    public void Ainst(File inputFile, File outputFile,File actualInputFile) {
        ACInstruction cinst = new ACInstruction();
        RemoveWhitespaces rw=new RemoveWhitespaces();
        File Symbol = new File("Symboltable.asm");
        SymbolTable sym = new SymbolTable();
        rw.RemoveWhiteSpaces(inputFile, actualInputFile);
        sym.Symbol(inputFile, Symbol, actualInputFile);
        try {
            BufferedReader br = new BufferedReader(new FileReader(actualInputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
            BufferedReader sy = new BufferedReader(new FileReader(Symbol));
            LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
            String assem_Line, sym_line, bin;
            String output, dest, comp, jump;
            while ((sym_line = sy.readLine()) != null) {
                String sym_line_0 = sym_line.split(":")[0].trim();
                int sym_line_1 = Integer.parseInt(sym_line.split(":")[1].trim());
                map.put(sym_line_0, sym_line_1);
            }
            while ((assem_Line = br.readLine()) != null) {
                if (assem_Line.startsWith("@")) {
                    assem_Line = assem_Line.split("@")[1].trim();
                    if (Character.isDigit(assem_Line.charAt(0))) {
                        bin = String.format("%15s", Integer.toBinaryString(Integer.parseInt(assem_Line)))
                                .replaceAll(" ", "0");
                        bw.write("0" + bin + " ");
                    } else {
                        if (map.containsKey(assem_Line)) {
                            bin = String.format("%15s", Integer.toBinaryString(map.get(assem_Line)))
                                    .replaceAll(" ", "0");
                            bw.write("0" + bin + " ");
                        }
                    }
                }

                else if ((!(assem_Line.startsWith("@"))) && (!(assem_Line.startsWith("(")))) {
                    dest = "NULL";
                    comp = "NULL";
                    jump = "NULL";
                    if (assem_Line.contains("=")) {
                        dest = assem_Line.split("=")[0].trim();
                        String assem_Line_1 = assem_Line.split("=")[1].trim();
                        if (assem_Line_1.contains(";")) {
                            comp = assem_Line_1.split(";")[0].trim();
                            jump = assem_Line_1.split(";")[1].trim();
                        } else {
                            comp = assem_Line_1;
                        }
                    } else if ((!(assem_Line.contains("="))) && (assem_Line.contains(";"))) {
                        comp = assem_Line.split(";")[0].trim();
                        jump = assem_Line.split(";")[1].trim();
                    } else {
                        comp = assem_Line;
                    }
                    output = cinst.cInstruction(dest, comp, jump);
                    bw.write(output);
                    bw.newLine();
                }

            }
            bw.close();
            br.close();
            sy.close();
        }

        catch (IOException e) {
            System.out.println("ERROR In final");
        }
    }
}
