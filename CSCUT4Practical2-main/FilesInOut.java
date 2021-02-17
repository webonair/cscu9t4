import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;
import java.text.SimpleDateFormat;  
import java.util.Date;  

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
    	
    	// Searching arguments for the -u flag
    	boolean upper = false;
    	for (String s: args) {
    		if (s.equals("-u")) {
    			upper = true;
    		}
    	}
    	
    	
    	// Reading and altering inFile with regexes
        try {
           FileReader fr = new FileReader (inFile);
           BufferedReader br = new BufferedReader(fr);
           
           String line;

           while ( (line = br.readLine()) != null ) {
        	   line = line.replaceAll(" [0-9]", "\t"); // Adds white space
        	   line = line.replaceAll(" (.) ", " $1. "); // Adds middle initial dot
        	   
        	   // Date format for end-of-line substring.  Not good
        	   String bdaysub = line.substring((line.length() - 8), line.length());
        	   bdaysub = bDayBuilder(bdaysub);
        	   line = line.replaceAll("........$", bdaysub);
        	   
        	   
        	   if (upper) {
        	   		line = line.toUpperCase();
        	   } else {
        	   		line = toTitleCase(line);
        	   }
        	   
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
    
    // Converts to title case
    public static String toTitleCase(String line) {
    	StringBuilder titlec = new StringBuilder();

        boolean convertNext = true;
        for (char ch : line.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            titlec.append(ch);
        }

        return titlec.toString();
    }
    
    public static String bDayBuilder(String line) {
    	String result;
    	
    	StringBuilder bdayb = new StringBuilder(line);
    	result = bdayb.insert(line.length()-4, "/").toString();
    	result = bdayb.insert(line.length()-6, "/").toString();
    	
    	return result;
    }
    
    // Basic but obligatory error handling
    public static void ioe (IOException e) {
    	System.out.println("IO exception!");
    	e.printStackTrace();
    }

} // FilesInOut
