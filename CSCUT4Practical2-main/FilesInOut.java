import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

/**
 * 
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FilesInOut {

    public static void main(String[] args) {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("Enter filename of input:");
    	File inFile = new File(scanner.nextLine());
    	System.out.println("Enter filename of output:");
    	File outFile = new File(scanner.nextLine());
    	
    	System.out.print("Using input " + inFile.getAbsolutePath() + "\n");
    	scanner.close();
    	
    	// This declaration belongs outside the try statement because I'll use it later on.
    	StringBuffer sb = new StringBuffer();
    	
    	// Reading and altering inFile with regex
        try {
           FileReader fr = new FileReader (inFile);
           BufferedReader br = new BufferedReader(fr);
           
           String line;

           // I don't know what the "output format" is exactly.
           // I'm assuming it's to remove middle initials from students' names to match input.txt.
           // I'm also assuming it should be a table, so I did that too.
           while ( (line = br.readLine()) != null ) {
        	   line = line.replaceAll(" . ", " ");  // Removes initials
        	   line = line.replaceAll(" [0-9]", "\t"); // Adds white space
        	   sb.append(line);
        	   sb.append("\n");
           }
           
           fr.close();
           System.out.println(sb.toString());
        } catch (IOException e) {
        	ioe(e);
        }
        
        // Writing to outFile
        try {
            if (outFile.createNewFile()) {
              System.out.println("File created: " + outFile.getAbsolutePath());
              PrintWriter wri = new PrintWriter(outFile);
              wri.write(sb.toString());
              wri.close();
            } else {
              System.out.println("File already exists.");
            }
          } catch (IOException e) {
        	  ioe(e);
          }

    } // main
    
    // Basic but obligatory error handling
    public static void ioe (IOException e) {
    	System.out.println("IO exception!");
    	e.printStackTrace();
    }

} // FilesInOut
