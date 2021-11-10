import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.*;  

public class Area 
{
    //private JFrame f;
    private String firstText;
    private String secondText;
    private App app;
    private boolean to_the_end;
    private JTextArea area;
    private Set<String> dictionary;
    private Parser parser;
    public Area(App app)
    {
        this.app = app;
        dictionary = new HashSet<String>();
        putInDictionary();
    }
    public void calculation(JTextArea area)
    {
        // 1) Три самых популярных слова +- сделан функционал, вроде работае правильно
        // 2) Самая часта буква
        // 3) Количество слов + (сделано)
        // 4) Самая редкая буква + сделано + самая популярная буква точно правильно
        // 5) Самое редкое слово
        // 6) Самое короткое слово
        // 7) Самое длинное слово + (сделано) 
        // 8) Подсчет каждой буквы самая частая буква (сделано)
        // 9) Количество абзацев (не совсем понятно, что именно считается через \n + \t или другое)
        // 10) Количество матов
        // 11) Подсчёт по группам : диеслово имэныкы
        // 12) Поиск по тексту
        // 13) Количество родов слов
        // 14) Количество символов +
        // 15) Количество союзов + сделано (но нужно потом для англ )
        // 16) Количество воды
        // 17) Количество уникальных слоформ
        // 18) Количество каждого уникального слова
        // 19) Какие буквы вообще не юзали

        // считать слова через тхт файл
        // также проверять слова в тхт файле на большое и маленькое
        // подправить вывод троих слов
        //Там где написано Рефакторинг означает что придется работать с потоками
        // придумать метод в котором открывается поток передается буква и он ищет, начиная с этой буквы 
        this.area = area;
        firstText = app.getFirst();
        secondText = app.getSecond();
        popularWord();
        onlyWord();
        topCharacters();
        CounterSymbol();
        
        //12398456 123456 должен выделить 98
    }
    private void CounterSymbol()
    {
        this.area.append("\nКоличество символов - "+firstText.length()+".\n");
    }
    private void topCharacters()
    {
        int count = 0;
        int min = Integer.MAX_VALUE;
        HashMap<Character, Integer> hash_map = new HashMap<Character, Integer>();
        for(int i = 0; i < firstText.length(); i++) // пройтись циклом по всему массиву 
        { 
            if(firstText.charAt(i) >= 'A' && 'Z'<= firstText.charAt(i) || firstText.charAt(i) >= 'a' && 'z'<= firstText.charAt(i))
            {
                if(hash_map.containsKey(firstText.charAt(i)) == true) 
                {
                            //создать функцию которая не будет записывать какие-то частицы
                            count = hash_map.get(firstText.charAt(i));
                            count++;
                            hash_map.replace(firstText.charAt(i), count); // заменять с таким же на 1 больше, было 2 раза а станет 3
                }
                else 
                {
                            hash_map.put(firstText.charAt(i), 1);
                }                       
            }              
        }
        count = Integer.MIN_VALUE;
        for(int value : hash_map.values())
        {
            if(count < value)
            {
                count = value;                    
            }
            if(min > value)
            {
                min = value;
            }
        }
        Character result = getKey(hash_map, count);
        boolean more_than_one = false;
        if(result != null)this.area.append("\n"+result+" - самая популярная буква - " + count +", использований.\n");
        while(result != null)
        {
            result = getKey(hash_map, count);
            if(result != null)
            {
                if(more_than_one == true) this.area.append(", ");
                this.area.append(""+result);
                more_than_one = true;
                //printfUses(more_than_one);
            }
            if(result == null)
            {
                printfUses(more_than_one);
                more_than_one = false;
                break;
            }
        }
        result = getKey(hash_map, min);
        if(result != null)this.area.append("\n"+result+" - самая (не)популярная буква - " + min + ",использований.\n");
        while(result != null)
        {
            result = getKey(hash_map, min);
            if(result != null)
            {
                if(more_than_one == true) this.area.append(", ");
                this.area.append(""+result);
                more_than_one = true;
            }
            if(result == null)
            {
                printfUses(more_than_one);
                more_than_one = false;
            }
        }
        // printfUses(more_than_one);
    }
    private void printfUses(boolean more_than_one)
    {
        if(more_than_one == true)
        {
            this.area.append(" - буквы которые имеют столько же использований.");
        }
    }
    private <K, V> K getKey(Map<K, V> map, V value)
    {
        for (Map.Entry<K, V> entry: map.entrySet())
        {
            if (value.equals(entry.getValue())) 
            {
                K result = entry.getKey();
                //map.remove(entry.getValue());
                map.remove(entry.getKey());
                return result;// записать этот entry массив
            }
        }
        return null;
    }
    private void theBiggestWord(String word[]) //находить самое длинное слово а ещё и самое короткое нейминг для клоунов
    {
        int lenghtBiggest = Integer.MIN_VALUE;
        int lenghtSmallest = Integer.MAX_VALUE;
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
        }
        String[] longest_word_array = new String[15000];
        String[] smallest_word_array = new String[15000];
        int count = 0;
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
                    this.area.append("\n"+longest_word_array[0] + " - Самое длинное слово, ");   
                }
                else
                {
                    if(longest_word_array[i] != null)
                    {
                        this.area.append(longest_word_array[i] + "");
                        if(i+1 < count) this.area.append(",");   
                    }
                }
        }
        this.area.append(" - Слова с такой же длиной");
        for(int i = 0; i <= count_v2; i++)
        {
                if(i == 0)
                {
                    this.area.append("\n"+smallest_word_array[0] + " - Самое короткое слово, ");   
                }
                else
                {
                    if(smallest_word_array[i] != null)
                    {
                        this.area.append(smallest_word_array[i] + "");
                        if(i+1 < count) this.area.append(",");   
                    }
                }
        }
        this.area.append(" - Слова с такой же длиной");
        
    }
    private void count_word(int lenght) //сколько слов в тексте
    {
        //refactoring 
        this.area.append("\n"+lenght + "-Количество слов в тексте");
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
                this.area.append(arr[i] + "-");
                this.area.append(values[i] + ", ");
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
                        this.area.append(arr[j]+",");
                        first = true;
                        start = 0;
                    }
                }
                if(first == false)
                {
                    this.area.append("\n"+arr[j] + " - Самое редкое слово, ");
                    count = values[j];
                }
                first = true;
            }
        }
        if(start == 0) //больше чем 1 слово
        {
           this.area.append(" - Слова с такой же редкостью");
        }
        count_word(word.length);
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
    private void onlyWord() //функция которая будет считать количество союзных слов
    {
        String[] word = firstText.toLowerCase().split(" ");
        int count = 0;
        for(int i = 0; i < word.length; i++)
        {
            if(dictionary.contains(word[i]))
            {
                count++;
            }
        }
        this.area.append("\n"+ count + " - Количество союзов в тексе");
    }
    private void putInDictionary()
    {
        dictionary.add("и");
        dictionary.add("ни");
        dictionary.add("да");
        dictionary.add("тоже");
        dictionary.add("также");
        dictionary.add("а"); //
        dictionary.add("но");
        dictionary.add("однако");
        dictionary.add("зато");
        dictionary.add("же");
        dictionary.add("да"); //
        dictionary.add("или");
        dictionary.add("либо");
        dictionary.add("та");
        dictionary.add("то");
        dictionary.add("или"); // --
        dictionary.add("что");
        dictionary.add("будто");
        dictionary.add("чтобы");
        dictionary.add("вследствие"); // --
        dictionary.add("хотя");
        dictionary.add("как");
        dictionary.add("ибо");
        dictionary.add("оттого");
        dictionary.add("словно");
        dictionary.add("точно");
        dictionary.add("несмотря");
    }
}
