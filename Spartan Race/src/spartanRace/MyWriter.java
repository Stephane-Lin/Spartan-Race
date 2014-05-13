/*  Name(s) and ID(s): Stéphane Lin, ID: 6980716
 *                     Awais Ali, ID: 6849040
 *  COMP249
 *  Assignment #2
 *  Due Date: Friday, March 21, 2014
 */

package spartanRace;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * PrintWriter object; allows printing on screen and in output file
 */
public class MyWriter{
    //Instance variables
    PrintWriter writer;
    /**
     * Constructor
     * @param path location of output file
     * @throws FileNotFoundException 
     */
    public MyWriter(String path) throws FileNotFoundException{
        writer = new PrintWriter(path);
    }
    /**
     * Controls println method in PrintWriter object
     * @param str String to be printed
     */
    public void println(String str){
        writer.println(str);
    }
    /**
     * Controls print method in PrintWriter object
     * @param str String to be printed
     */
    public void print(String str){
        writer.print(str);
    }
    /**
     * Method to close PrintWriter object
     */
    public void close(){
        writer.close();
    }
    /**
     * Method to print on screen and in output file simultaneously
     * @param msg String to be printed
     * @param newLine Decides between print and println
     */
    public void printBoth(String msg, boolean newLine){
        if(newLine){
            System.out.println(msg);
            println(msg);
        }else{
            System.out.print(msg);
            print(msg);           
        }
    }
}
