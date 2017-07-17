/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;
import java.util.*;
import java.io.*;

/**
 *
 * @author tony.tian
 */
public class CSV {
    
    private File x;
    private String input;
    private int rows;
    private int columns;
    private String csvFile[][];
    
    public CSV(File file)
    {
        x = file;
        initialize();
        rows = rows();
        columns = columns();
        
        csvFile = new String[rows][columns];
        Scanner sc = new Scanner(input);
        int c = 0;
        while(sc.hasNextLine()) 
        {
            String thisLine[] = sc.nextLine().split(","); 
            for (int n = 0; n < thisLine.length; n++)
            {
                csvFile[c][n] = thisLine[n];
            }
            c++;
        }
    }
    
    public void initialize()
    {
        try
        {
            FileReader fr = new FileReader(x);
            Scanner sc = new Scanner(fr);
            while(sc.hasNextLine())
            {
                input += sc.nextLine() + "\r\n";
            }
            fr.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    public int rows()
    {
        Scanner sc = new Scanner(input);
        int num_row = 0;
        while(sc.hasNextLine())
        {
            num_row++;
            sc.nextLine();
        }
        return num_row;
    }
    
    public int columns()
    {
        Scanner sc = new Scanner(input);
        int num_column = 0;
        if(sc.hasNextLine())
        {
            String str[] = sc.nextLine().split(","); 
            for(int n = 0; n < str.length; n++)
                num_column++;
        }
        return num_column;
    }
    
    public String getRow(String key)
    {
        String thisRow = new String();
        boolean found = false;
        int num_row = 0;
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                if(csvFile[r][c].equals(key) && !found)
                {
                    num_row = r;
                    found = true;
                }
            }
        }
        for(int n = 0; n < columns; n++)
        {
            thisRow += csvFile[num_row][n] + ", ";
        }
        System.out.println(thisRow);
        return thisRow;
    }
    
    public void exportTo(String path, int borderSize, String caption, String bgColor, String alignment)
    {
        try
        {
            FileWriter fw = new FileWriter(path);
            
            fw.write("<html lang=\"en\">\n");
            fw.write("<head>");
            fw.write("<title>Hi</title>");
            fw.write("</head>");
            fw.write("<body>");
            fw.write("<table border=\"" + borderSize + "\"" + " bgcolor=\"" + bgColor + "\"" 
                    + " align=\"" + alignment + "\"" +">");
            fw.write("<caption>" + caption + "</caption>");
            
            for(int r = 0; r < rows; r++)
            {
                String str = csvFile[r][0];
                fw.write("<tr><td>" + str + "</td>");
                for(int c = 1; c < columns; c++)
                {
                    String c_str = csvFile[r][c];
                    fw.write("<td>" + c_str + "</td>");
                }
                fw.write("</tr>");
            }

            fw.write("</table>");
            fw.write("</body>");
            fw.close();
            System.out.println("The file has been saved.");
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    public void test()
    {
        for(int i = 0; i < rows; i++)
        {
            for(int n = 0; n < columns; n++)
            {
                System.out.println(csvFile[i][n] + " ");
            }
        }
    }
    
}
