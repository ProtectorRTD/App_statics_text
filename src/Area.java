import java.security.KeyStore.Entry;
import java.util.ArrayList;
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
    public Area(App app)
    {
        this.app = app;
    }
    public void calculation(JTextArea area)
    {
        // 1) Три самых популярных слова
        // 2) Самая часта буква
        // 3) Количество слов
        // 4) Самая редкая буква
        // 5) Самое редкое слово
        // 6) Самое короткое слово
        // 7) Самое длинное слово
        // 8) Подсчет каждой буквы
        // 9) Количество абзаце
        // 10) Количество матов
        // 11) Подсчёт по группам : диеслово имэныкы
        // 12) Поиск по тексту
        // 13) Количество родов слов
        // 14) Количество символов

        this.area = area;
        firstText = app.getFirst();
        secondText = app.getSecond();
        popularWord();
        
        

        
        //12398456 123456 должен выделить 98
    }
    private void popularWord()
    {
        //привет как дела фиксить
        //чтобы ещё выводил сколько раз его использовали хм
        HashMap<String, Integer> hash_map = new HashMap<String, Integer>(); //создается карта 
        int count = 0; // счетчик для того чтобы считать какой раз уже записывалось 
        firstText = firstText.replaceAll("[^a-zA-Z0-9]"," ");
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
                    hash_map.put(word[i], 1);
            }                       
        }
        Map<String, Integer> hash_map_after_sorting = sortByValue(hash_map); // спизженая функция которая сортирует по ключам 
        String [] arr = hash_map_after_sorting.keySet().toArray(new String [0]); // из хэш мапы делаем в массив
        // String[] values =hash_map_after_sorting.values().toArray(new String[0]);
        Integer[] values = (hash_map_after_sorting.values().toArray(new Integer[hash_map.values().size()]));
        int start = arr.length - 1;
        this.area.setText("Топ 3 слова - ");
        for(int i  = arr.length-1; i>=0; i--) //вывод наше все!
        {
            if(start+4 == arr.length) break;
            if(i < 0 ) break;
            if(arr[i] != " ")
            {                
                this.area.append(arr[i] + " ");
                this.area.append(values[i] + " ");
            }
            start--;
        }
        //System.out.println(hash_map);
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
        Set<String> dictionary = new HashSet<String>();
        dictionary.add("И");
        dictionary.add("Ни");
        dictionary.add("да");
        dictionary.add("тоже");
        dictionary.add("также");
        dictionary.add("а"); //
        dictionary.add("Но");
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
        String[] word = firstText.split(" ");
        int count = 0;
        for(int i = 0; i < firstText.length(); i++)
        {
            if(dictionary.contains(word[i]))
            {
                count++;
            }
        }
        this.area.append("\n "+ count + "- Количество союзов \n");
    }

}
