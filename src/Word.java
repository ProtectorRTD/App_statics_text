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

//сделать когда все слова использовались лишь 1 раз
public class Word 
{
    private Area area_class;
    private String firstText;
    private JTextArea area;
    private Set<String> dictionary;
    private HashSet<String> checking;
    private waterText waterText;
    public Word(JTextArea area, Area area_class)
    {
        this.area = area;
        firstText = area_class.getField();//geter
        dictionary = area_class.getDictionary();
        checking = area_class.getDictionaryword();
        waterText = new waterText(area, area_class);
        start();
    }
    private void start()
    {
        popularWord();
    }
    private void popularWord()
    {
        // Если у всех трех слов 1 количество использований, то сказать что все слова уникальные, 
        // сказать что слова не повторяются
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
                if(checking.contains(word[i]) && word[i].length() > 2) hash_map.put(word[i], 1); // проверку на буквы
            }                       
        }
        Map<String, Integer> hash_map_after_sorting = sortByValue(hash_map); // спизженая функция которая сортирует по ключам 
        String [] arr = hash_map_after_sorting.keySet().toArray(new String [0]); // из хэш мапы делаем в массив
        Integer[] values = (hash_map_after_sorting.values().toArray(new Integer[hash_map.values().size()]));
        
        int start = arr.length - 1;
        boolean no_print = false;
        if(start >= 0 && values[start] == 1) 
        {
            // this.area.append("\n————————————————————————");
            this.area.setText("Все слова являются уникальными");
            no_print = true;
        }
        else
        {
            count = 0;
            int array_for_printf[] = new int[3]; 
            String string_for_printf[] = new String[3]; 
            for(int i  = arr.length-1; i>=0; i--) //вывод наше все!
            {
                if(start+3 == arr.length -1) break;
                else
                {
                    array_for_printf[count] = values[i];
                    string_for_printf[count] = arr[i];
                    count++;
                    start--;
                }
            }

        }
        if(no_print == false)print_for_word(values, arr, count);
        theBiggestWord(word); 
        waterText.countWater(word);
    }

    private void theBiggestWord(String word[]) //находить самое длинное слово а ещё и самое короткое нейминг для клоунов
    {
        int lenghtBiggest = Integer.MIN_VALUE;
        int lenghtSmallest = Integer.MAX_VALUE;
        int count = 0, count_v2 = 0;
        for(int i = 0; i < word.length; i++)
        {
            if(!dictionary.contains(word[i]) && lenghtBiggest < word[i].length() && checking.contains(word[i]))
            {
                lenghtBiggest = word[i].length();
            }
            if(!dictionary.contains(word[i]) && word[i].length() > 2 && lenghtSmallest >= word[i].length() && checking.contains(word[i]) )
            {
                lenghtSmallest = word[i].length();
                System.out.println(word[i]);
            }
            if(checking.contains(word[i].toLowerCase()))
            {
                count++;
            }
        }
        // this.area.append("\n————————————————————————");
        this.area.append("\nКоличество слов в тексте — " + count);

        String[] longest_word_array = new String[15000];
        String[] smallest_word_array = new String[15000];
        count = 0;
        for(int i = 0; i < word.length; i++)
        {
            if(word[i].length() == lenghtBiggest)
            {
                if(checking.contains(word[i]))
                {
                    longest_word_array[count] = word[i];
                    count++;
                }
            }
            if(word[i].length() == lenghtSmallest)
            {
                if(checking.contains(word[i]))
                {
                    // с короткими ошибка
                    smallest_word_array[count_v2] = word[i];
                    count_v2++;
                    System.out.println(word[i]);
                }
            }
        }
        print_for_big_smal(longest_word_array, smallest_word_array, count, count_v2);
    }
    private void print_for_big_smal(String[] longest_word_array, String[] smallest_word_array, int count, int count_v2) 
    {
        if(count == 1)
        {
            // this.area.append("\n————————————————————————");
            this.area.append("\nСамое длинное слово — "+longest_word_array[0]); 
        }
        else
        {
            if(count != 0)
            {
                // this.area.append("\n————————————————————————");
                this.area.append("\nСамые длинные слова — ");
                for(int i = 0; i < count; i++)
                {
                    this.area.append(longest_word_array[i]);
                    if(i+1 < count) this.area.append(", ");   
                }                
            }
        }
        if(count_v2 == 1)
        {
            // this.area.append("\n————————————————————————");
            this.area.append("\nСамое короткое слово — "+smallest_word_array[0]); 
        }
        else
        {
            if(count_v2 != 0)
            {         
                // this.area.append("\n————————————————————————");
                this.area.append("\nСамые короткие слова — ");
                for(int i = 0; i < count_v2; i++)
                {
                    this.area.append(smallest_word_array[i]);
                    if(i+1 < count_v2) this.area.append(", ");   
                }
                 // возможно давать сколько выводить 
            }
        }
    }


    private void print_for_word(Integer[] values, String[] words, int count) //принт для топ 3 слов
    {
        // самые большие слова в контексте использования написаны в конце массива
        if(words.length > 1)
        {
            this.area.setText("Топ " + count + " слова : ");
        }
        else 
        {
            this.area.setText("Топ слово : ");
        }
        for(int i = words.length-1; i >= 0; i--)
        {
            this.area.append(words[i]+" — " + values[i]);
            if(i+count > words.length) 
            {
                this.area.append(", ");
            }
            if(i+count-1 < words.length) break;
        }

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
