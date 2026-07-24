// DO NOT ADD import STATEMENTS
// Talk with Prof. Reilly if your code requires additional imports.
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
* Assembler for the CS318 simple computer simulation
* Starter code: complete the methods as instructed.
*
* Student name(s):
*/
public class Assembler_P1 {

    /**
    * DO NOT ALTER THIS METHOD
    * 
    * Pass 1 of assembling the code file. When this method is finished, the 
    * dataFile and codeFile contain the size of the assembled data segment
    * and code segment, respectively. The labels list contains a list of the
    * code segment labels and their associated offsets.
    *
    * @param inFile The pathname to the assembly language file to be assembled.
    * @param dataFile The pathname where the data segment file should be written.
    * @param codeFile The pathname where the code segment file should be written.
    * @throws FileNotFoundException if a file cannot be opened.
    * @throws IOException if errors occur while processing a file.
    * @return The array list of code segment lables and associated offsets (for testing and debugging purposes)
    */
    public static ArrayList<LabelOffset> assemble(String inFile, String dataFile, String codeFile)
                                    throws FileNotFoundException, IOException {

        ArrayList<LabelOffset> labels = pass1(inFile, dataFile, codeFile);
        return labels;
    }

    /**
    * PROJECT 2A: COMPLETE THIS METHOD
    * 
    * First pass of the assembler. Writes the number of bytes in the data segment
    * and code segment to their respective output files. Returns a list of
    * code segment labels and thier relative offsets.
    *
    * @param inFile The pathname of the file containing assembly language code.
    * @param dataFile The pathname for the data segment binary file.
    * @param codeFile The pathname for the code segment binary file.
    * @return List of the code segment labels and relative offsets.
    * @throws RuntimeException if the assembly code file does not have the
    * correct format, or an error occurs while processing the assembly code file.
    * @throws FileNotFoundException if a file cannot be opened.
    */
    private static ArrayList<LabelOffset> pass1(String inFile, String dataFile, String codeFile)
                                    throws FileNotFoundException, RuntimeException {

        // Placeholder return statement.
        // Replace with the code for this method.
        ArrayList<LabelOffset> retrunlist = new ArrayList<LabelOffset>();

        
       FileReader in = null;
       BufferedReader br = null;
       //System.out.println("before ");
       try {
           //System.out.println("try works");
           in = new FileReader(inFile);
           br = new BufferedReader(in);
           //System.out.println("here ");
           int datasegment = 0;
           int codesize = 0;
           String line;  
           // varaibles for me to us.
          // System.out.println("variables works");// fails here
          line = br.readLine().trim();
           if (!(line.equals(".align 2"))){
               throw new RuntimeException("is not .align 2 thus exception");//first line
           }
           line = br.readLine().trim();
           if (!((line.equals(".data")))){
               throw new RuntimeException("second line is not .data thus exception");
           }  // second line


           // should test the two runtimeExceptions.
           while ((line = br.readLine().trim()) != null){// this genuanly goeas on forever.
               if (line.startsWith(".word")){
                   String[] list = line.split(",");
                   System.err.println(list);//test
                   datasegment = datasegment + list.length;
               }
               if(line.equals(".text")){
                   break;
               }
           }
           datasegment = datasegment *4; // the number of bits in .word to .txt


           //datawriter = new filewriter;
           //File data = new File(dataFile);
           //System.out.println("Here");
           FileWriter data = new FileWriter(dataFile);
           data.write(datasegment +"\n");//should write a the file hopefully
           data.close();//closes it




           // test the code
           int linecount = 0;
           codesize = -8;
           while ((line = br.readLine().trim()) != null){
               if (((line.equals(".end")) == false) || ((line.endsWith(":")) == false)){
                   linecount = linecount+1;
                   codesize = codesize + 4;
               }
               if (line.endsWith(":")){
                   LabelOffset cLabelOffset = new LabelOffset();
                   line = line.substring(0, line.length() - 1);
                   cLabelOffset.label = line;
                   cLabelOffset.offset = codesize;
                   retrunlist.add(cLabelOffset);
                   codesize = codesize -4;
               }
               if (line.equals(".end")){
                   codesize = codesize+4;
                   break;// so should count the amount of lines untill it hits end then the lines will be multiplyed by 4 for 4 bits and add 4 bits to.
               }
           }


           FileWriter code = new FileWriter(codeFile);
           code.write(codesize +"\n");//should write a the file hopefully
           code.close();




       } catch (IOException e){
           throw new FileNotFoundException("could not read.");
       }   


       // ok return it to a list  and write two files
       // retrun a list that is a offsetlable object contains the amount  of bits in codesize and line






       // Placeholder return statement.
       // Replace with the code for this method.
       return retrunlist;
    } // end of pass1


} // end of class
