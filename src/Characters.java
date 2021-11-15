import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;

public class Characters 
{
    private JTextArea area;
    private String firstText;
    private Area area_class;
    private char maxCharacter;
    private char minCharacter;
    public Characters(JTextArea area, Area area_class)
    {
        this.area_class = area_class;
        this.area = area;
        firstText = area_class.getField();//geter
        start();
    }
    private void start()
    {
        CounterSymbol();
        topCharacters();   
        
    }
    private void Symbolnotuse(HashMap<Character, Integer> hash_map)
    {
        // если бул будет для англ то поменять алфавит
        String alphabetArray = "abcdefghijklmnopqrstuvwxyz";
        String alhabetRus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        Character [] result = new Character[100];
        boolean if_not = false;
        int count = 0;
        for(int i = 0; i < alhabetRus.length(); i++)
        {
            if(!hash_map.containsKey(alhabetRus.charAt(i)) && alhabetRus.charAt(i) != minCharacter && alhabetRus.charAt(i) != maxCharacter) //что-то не так с буквой о
            {
                if_not = true;
                result[count] = alhabetRus.charAt(i);
                count++;
            }
        }
        if(if_not == false)
        {
            this.area.append("\n————————————————————————");
            this.area.append("\nВсе буквы были использованы");
        }
        else
        {
            this.area.append("\n————————————————————————");
            // this.area.append("\n————————————————————————");
            this.area.append("\nБуквы, которые были не использованные — ");
            for(int i = 0; i < count; i++)
            {
                this.area.append(""+result[i]);
                if(i+1 < count) this.area.append(",");
            }

        }
    }
    private void CounterSymbol() //++
    {
        this.area.append("\n————————————————————————");
        // this.area.append("\n————————————————————————");
        this.area.append("\nКоличество символов — "+firstText.length()+".");
        // this.area.append("\n————————————");
    }
    private void topCharacters() //++
    {
        int count = 0;
        int min = Integer.MAX_VALUE;
        HashMap<Character, Integer> hash_map = new HashMap<Character, Integer>();
        firstText = firstText.toLowerCase(); //!
        for(int i = 0; i < firstText.length(); i++) // пройтись циклом по всему массиву 
        { 
            if(firstText.charAt(i) >= 'а' && 'я'<= firstText.charAt(i) || firstText.charAt(i) >= 'А' && 'Я'<= firstText.charAt(i))
            {
                if(hash_map.containsKey(firstText.charAt(i)) == true) 
                {
                            //создать функцию которая не будет записывать какие-то частицы
                    count = hash_map.get(firstText.charAt(i)); //превращать большую букву в маленькую
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
        maxCharacter = result;
        boolean more_than_one = false;
        
        if(result != null)
        {
            this.area.append("\n————————————————————————");
            this.area.append("\nСамая популярная буква — " + result+"," + count +" использований.");
        }
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
        minCharacter = result;
        
        // this.area.append("\n————————————————————————");
        if(result != null)
        {
            this.area.append("\n————————————————————————");
            this.area.append("\nСамая (не)популярная буква — " + result +"," + min + " использований.");
        }

        // this.area.append("\n————————————————————————");
        boolean one_print = false;
        while(result != null)
        {
            result = getKey(hash_map, min);
            if(result != null)
            {
                if(one_print == false)                 
                {
                    this.area.append("\n————————————————————————");
                    this.area.append("Буквы которые имеют столько же использований — ");
                    one_print = true;
                }
                this.area.append(""+result + " ");
                more_than_one = true;
            }
            if(result == null)
            {
                //printfUses(more_than_one);
                more_than_one = false;
            }
        }
        Symbolnotuse(hash_map);//!
        // printfUses(more_than_one);
    }
    private void printfUses(boolean more_than_one) //++
    {
        if(more_than_one == true)
        {
            this.area.append(" — буквы которые имеют столько же использований.");
            // this.area.append("\n————————————");
        }
    }
    private <K, V> K getKey(Map<K, V> map, V value) //++
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
}
