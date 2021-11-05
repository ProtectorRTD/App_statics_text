import java.security.KeyStore.Entry;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.*;  

public class Area 
{
    //private JFrame f;
    private String firstText;
    private String secondText;
    private App app;
    private boolean to_the_end;
    public Area(App app)
    {
        this.app = app;
    }
    public void calculation()
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


        firstText = app.getFirst();
        secondText = app.getSecond();
        popularWord();
        
        

        
        //12398456 123456 должен выделить 98
    }
    private void popularWord()
    {
        HashMap<String, Integer> hash_map = new HashMap<String, Integer>(); //создается карта 
        int count = 0; // счетчик для того чтобы считать какой раз уже записывалось 
        String[] word = firstText.split(" "); //разделить весь инпут по пробелам 
        for(int i = 0; i < word.length; i++) // пройтись циклом по всему массиву
        {                
            if(hash_map.containsKey(word[i]) == true) 
            {
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
        int start = arr.length - 1;
        for(int i  = arr.length-1; i>=0; i--) //вывод наше все!
        {
            if(start+4 == arr.length) break;
            if(i < 0 ) break;
            if(arr[i] != " ")System.out.println(arr[i]);
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
}
