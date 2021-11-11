import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;


public class Parser 
{
    private HashSet<String> dictionary;
    private HashSet<String> stopWords;
    public Parser()
    {
        String filePath = "src/russian.txt"; //tut if na toolbar
        dictionary = readAllBytesJava7(filePath);
        filePath = "src/russian_stopwords.txt";
        stopWords = readAllBytesJava7(filePath);
        
        System.out.println("");
    }
    private static HashSet<String> readAllBytesJava7(String filePath) 
    {
        HashSet <String> result = new HashSet<>();
        //Name of the file
        String fileName=filePath;
        try{
           //Create object of FileReader
           FileReader inputFile = new FileReader(fileName);
           //Instantiate the BufferedReader Class
           BufferedReader bufferReader = new BufferedReader(inputFile);
           //Variable to hold the one line data
           String line;
           // Read file line by line and print on the console
           line = bufferReader.readLine();
           while ((line = bufferReader.readLine()) != null)
            {
                result.add(line);
            }
           //Close the buffer reader
           bufferReader.close();
        }catch(Exception e)
        {
           System.out.println("Error while reading file line by line:" + e.getMessage());                      
        }
        return result;
    }
    // public boolean check(String compare)
    // {
    //     isDone = false;
    //     if(compare != null && dictionary != null)
    //     {
    //         if(this.dictionary.contains(compare)) isDone = true;
    //         else
    //         {
    //             isDone = false;
    //         }
    //     }
    //     return isDone;
    // }
    public HashSet<String> getHashSet()
    {
        return this.dictionary;
    }
    public HashSet<String> getstopWords()
    {
        return this.stopWords;
    }

}
