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
public class Word 
{
    private Area area_class;
    private String firstText;
    private JTextArea area;
    private Set<String> dictionary;
    private HashSet<String> checking;
    public Word(JTextArea area, Area area_class)
    {
        this.area = area;
        firstText = area_class.getField();//geter
        dictionary = area_class.getDictionary();
        checking = area_class.getDictionaryword();
        start();
    }
    private void start()
    {
        popularWord();
    }
    private void theBiggestWord(String word[]) //находить самое длинное слово а ещё и самое короткое нейминг для клоунов
    {
        int lenghtBiggest = Integer.MIN_VALUE;
        int lenghtSmallest = Integer.MAX_VALUE;
        int count = 0;
        for(int i = 0; i < word.length; i++)
        {
            if(!dictionary.contains(word[i]) && lenghtBiggest < word[i].length())
            {
                lenghtBiggest = word[i].length();
            }
            if(!dictionary.contains(word[i]) && lenghtSmallest >= word[i].length())
            {
                lenghtSmallest = word[i].length();
            }
            if(checking.contains(word[i]))
            {
                count++;
            }
        }
        this.area.append("\n"+count+"- Количество слов в тексте");
        String[] longest_word_array = new String[15000];
        String[] smallest_word_array = new String[15000];
        count = 0;
        int count_v2 = 0;
        for(int i = 0; i < word.length; i++)
        {
            if(word[i].length() == lenghtBiggest)
            {
                longest_word_array[count] = word[i];
                count++;
            }
            if(word[i].length() == lenghtSmallest)
            {
                smallest_word_array[count_v2] = word[i];
                count_v2++;
            }
        }
        for(int i = 0; i <= count; i++)
        {
                if(i == 0)
                {
                    if( checking.contains(longest_word_array[0]) == true)
                    {
                        this.area.append("\n"+longest_word_array[0] + " - Самое длинное слово, "); 
                    }  
                }
                else
                {
                    if(longest_word_array[i] != null)
                    {
                        if( checking.contains(longest_word_array[i]) == true)
                        {
                            this.area.append(longest_word_array[i] + "");
                            if(i+1 < count) this.area.append(",");   
                        }
                    }
                }
        }
        this.area.append(" - Слова с такой же длиной");
        for(int i = 0; i <= count_v2; i++)
        {
                if(i == 0)
                {
                    if( checking.contains(smallest_word_array[0]) == true)
                    {
                        this.area.append("\n"+smallest_word_array[0] + " - Самое короткое слово, "); 
                    }  
                }
                else
                {
                    if(smallest_word_array[i] != null)
                    {
                        if( checking.contains(smallest_word_array[i]) == true)
                        {
                            this.area.append(smallest_word_array[i] + "");
                            if(i+1 < count) this.area.append(",");   
                        }
                    }
                }
        }
        this.area.append(" - Слова с такой же длиной");
        
    }
    private void popularWord()
    {
        HashMap<String, Integer> hash_map = new HashMap<String, Integer>(); //создается карта 
        int count = 0; // счетчик для того чтобы считать какой раз уже записывалось 
        firstText = firstText.replaceAll("[^a-zA-Zа-яА-Я0-9]"," ");
        firstText = firstText.replace("  ", " ");
        String[] word = firstText.split(" "); //разделить весь инпут по пробелам 
        for(int i = 0; i < word.length; i++) // пройтись циклом по всему массиву 
        {                
            if(hash_map.containsKey(word[i]) == true) 
            {
                    //создать функцию которая не будет записывать какие-то частицы
                    count = hash_map.get(word[i]);
                    count++;
                    hash_map.replace(word[i], count); // заменять с таким же на 1 больше, было 2 раза а станет 3
            }
            else 
            {
                    //refactoring 
                    hash_map.put(word[i], 1);
            }                       
        }
        Map<String, Integer> hash_map_after_sorting = sortByValue(hash_map); // спизженая функция которая сортирует по ключам 
        String [] arr = hash_map_after_sorting.keySet().toArray(new String [0]); // из хэш мапы делаем в массив
        Integer[] values = (hash_map_after_sorting.values().toArray(new Integer[hash_map.values().size()]));
        int start = arr.length - 1;
        this.area.setText("Топ 3 слова - ");
        for(int i  = arr.length-1; i>=0; i--) //вывод наше все!
        {
            if(start+4 == arr.length) break;
            if(i < 0 ) break;
            if(arr[i] != " ")
            {      
                if( checking.contains(arr[i]) == true)
                {
                    this.area.append(arr[i] + "-");
                    this.area.append(values[i] + ", ");
                }          
            }            
            start--;
        }
        boolean first = false;
        for(int j = 0; j < arr.length-1; j++)
        {
            if(arr[j] != " ")
            {
                if(first == true && values[j] == count)
                {
                    if(j+1 < arr.length-1 && values[j+1] == values[j])
                    {
                        if( checking.contains(arr[j]) == true)
                        {
                            this.area.append(arr[j]+",");
                            first = true;
                            start = 0;
                        }
                    }
                }
                if(first == false)
                {
                    if( checking.contains(arr[j]) == true) 
                    {
                        this.area.append("\n"+arr[j] + " - Самое редкое слово, ");
                        count = values[j];
                    }
                }
                first = true;
            }
        }
        if(start == 0) //больше чем 1 слово
        {
           this.area.append(" - Слова с такой же редкостью");
        }
        theBiggestWord(word);
        
    }
    private HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // creating list from elements of HashMap
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());
        // sorting list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<String, Integer> ha = new LinkedHashMap<String, Integer>();
        for(Map.Entry<String, Integer> me : list)
        {
            ha.put(me.getKey(), me.getValue());
        }
        return ha;
    }
}
