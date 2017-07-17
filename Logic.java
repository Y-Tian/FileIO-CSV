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
public class UtilityLogic {

    /**
     * @param args the command line arguments
     */
    private File x;
    private String memoryStamp;
    int capitalBegin = 65;
    int capitalEnd = 90;
    int lowerBegin = 97;
    int lowerEnd = 122;
    int num_letter = 26;
    int BUFFER = 13;
    int BUFFER_ = 19;
    
    public UtilityLogic(String file)
    {
        x = new File(file);
        memoryStamp = "";
    }
    
    public int wordCount()
    {
        int count = 0;
        try
        {
            FileReader fr = new FileReader(x);
            Scanner sc = new Scanner(fr);
            while(sc.hasNext())
            {
                sc.next();
                count++;
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        System.out.println(count);
        return count;
    }
    
    public int lineCount()
    {
        int count = 0;
        try
        {
            FileReader fr = new FileReader(x);
            Scanner sc = new Scanner(fr);
            while(sc.hasNextLine())
            {
                sc.nextLine();
                count++;
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        System.out.println(count);
        return count;
    }
    
    public int count(String word)//wat
    {
        int count = 0;
        try
        {
            Scanner sc = new Scanner(x);
            while(sc.hasNext(word))
            {
                count++;
                sc.next(word);  
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        System.out.println(count);
        return count;
    }

    public void replace(String target, String replacement)
    {
        try
        {
            FileReader fr = new FileReader(x);  
            Scanner sc = new Scanner(fr);
            String file = new String(); 
            while(sc.hasNextLine())
            {
                file += sc.nextLine() + "\r\n";
            }
            fr.close();
            
            file = file.replaceAll(target, replacement);
            FileWriter fw = new FileWriter(x);
            fw.write(file);
            fw.close();
            System.out.println(file); 
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    public void append(String data)
    {
        try
        {
            FileReader fr = new FileReader(x);  
            Scanner sc = new Scanner(fr);
            String file = new String(); 
            while(sc.hasNextLine())
            {
                file += sc.nextLine() + "\r\n";
            }
            fr.close();
            
            file = file += data;
            FileWriter fw = new FileWriter(x);
            fw.write(file);
            fw.close();
            System.out.println(file); 
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    public void cipherEncrypt(int key)
    {       
        try
        {
            String input = new String();       
            String output = new String();
            FileReader fr = new FileReader(x);
            Scanner sc = new Scanner(fr);     
            while(sc.hasNextLine())
            {
                input += sc.nextLine() + "\r\n";
            }
            System.out.println(input);
            fr.close();
            
            int count = 0;
            while(count < input.length())
            {
                char c = 0;
                c += input.charAt(count);
                if(c >= capitalBegin && c <= capitalEnd)//if the character is a capital letter
                {
                    c += key;          
                    char a = 0;
                    a += ((c - BUFFER)%num_letter + capitalBegin);
                    output += Character.toString(a);
                    
                }
                else if(c >= lowerBegin && c <= lowerEnd)//if the character is a lowercase letter
                {
                    c += key;
                    char b = 0;
                    b += ((c - BUFFER_)%num_letter + lowerBegin);
                    output += Character.toString(b);
                }
                else//if the character isn't a letter
                {
                    output += Character.toString(c);
                } 
                count++;
            }
            FileWriter fw = new FileWriter(x);
            fw.write(output);
            fw.close();
            System.out.println(output);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    public void cipherDecrypt(int key)
    {
        try
        {
            String input = new String();       
            String output = new String();
            FileReader fr = new FileReader(x);
            Scanner sc = new Scanner(fr);            
            while(sc.hasNextLine())
            {
                input += sc.nextLine() + "\r\n";
            }
            System.out.println(input);
            fr.close();
            
            int count = 0;
            while(count < input.length())
            {
                char c = 0;
                c += input.charAt(count);
                if(c >= capitalBegin && c <= capitalEnd)
                {
                    c -= key;
                    char a = 0;
                    a += ((c - BUFFER)%num_letter + capitalBegin);
                    output += Character.toString(a);
                }
                else if(c >= lowerBegin && c <= lowerEnd)
                {
                    c -= key;
                    char b = 0;
                    b += ((c - BUFFER_)%num_letter + lowerBegin);
                    output += Character.toString(b);
                }
                else
                {
                    output += Character.toString(c); 
                }
                count++;
            }
            
            memoryStamp = output;
            System.out.println(output);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    public String plsDecrypt(int n)
    {
        cipherDecrypt(n);
        return memoryStamp;
    }
    
    public void getDecrypt(String file)
    {
        try
        {
            String input = new String();       
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);            
            while(sc.hasNextLine())
            {
                input += sc.nextLine() + "\r\n";
            }
            fr.close();
            
            int count = 0;
            while(!memoryStamp.equals(input))
            {
                plsDecrypt(count);
                System.out.println(memoryStamp);
                count++;
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
}
