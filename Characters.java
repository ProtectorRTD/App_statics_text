import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;

public class Characters 
{
    private JTextArea area;
    private String firstText;
    private Area area_class;
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
    private void CounterSymbol() //++
    {
        this.area.append("\nКоличество символов - "+firstText.length()+".\n");
    }
    private void topCharacters() //++
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
    private void printfUses(boolean more_than_one) //++
    {
        if(more_than_one == true)
        {
            this.area.append(" - буквы которые имеют столько же использований.");
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
