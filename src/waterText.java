import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JTextArea;

public class waterText 
{
    private String firstText;
    private JTextArea area;
    private HashSet <String> dictionary_stop;
    public waterText(JTextArea area, Area area_class)
    {
        this.area = area;
        firstText = area_class.getField();//geter
        dictionary_stop = area_class.getStopWords();
    }
    public void countWater(String[] word) //выводит процетность воды
    {
        if(word == null) return ;
        int count_water = 0, all_word = word.length, result = 0;
        for(int i = 0; i <word.length; i++)
        {
            if(dictionary_stop.contains(word[i]))
            {
                count_water++;
            }
        }
        result = (100 * count_water)/all_word;
        printfWater(result);
    }
    private void printfWater(int result)
    {
        this.area.append("\n"+result+"% - процент воды в тексте");
    }
}
