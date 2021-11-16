
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;  

public class Area 
{
    private String firstText;
    private App app;
    private JTextArea area;
    private Set<String> dictionary;
    private HashSet<String> checking;
    private Parser parser;
    private Characters character_class;
    private Word word;
    public Area(App app)
    {
        this.app = app;
        dictionary = new HashSet<String>();
        putInDictionary();
    }
    public String getField()
    {
        return firstText;
    }
    public Set<String> getDictionary()
    {
        return dictionary;
    }
    public HashSet<String> getDictionaryword()
    {
        return checking;
    }
    public HashSet<String> getStopWords()
    {
        return parser.getstopWords();
    }
    public void calculation(JTextArea area)
    {
        // подправить просто вывод и переключение на разные языки
        // 1) Три самых популярных слова ++ сделан функционал, вроде работае правильно
        // 2) Самая часта буква ++
        // 3) Количество слов + (сделано)
        // 4) Самая редкая буква ++ 
        // 5) Самое редкое слово ++
        // 6) Самое короткое слово ++
        // 7) Самое длинное слово ++ 
        // 8) Подсчет каждой буквы самая частая буква (сделано) ++ 
        // 14) Количество символов ++
        // 16) Количество воды ++
        // 15) Количество союзов + сделано (но нужно потом для англ )

        // 9) Количество абзацев (не совсем понятно, что именно считается через \n + \t или другое)
        // 10) Количество матов
        // 11) Подсчёт по группам : диеслово имэныкы
        // 12) Поиск по тексту
        // 13) Количество родов слов
        // 17) Количество уникальных слоформ
        // 18) Количество каждого уникального слова
        // 19) Какие буквы вообще не юзали
        // 20) Выделить какие (знаки не являются словами) типо абоба)
        // 21) галочка чтобы выделить стоп слова в тексте


        this.area = area;
        firstText = app.getFirst();
        if(firstText != null)
        {
            parser = new Parser();
            checking = parser.getHashSet();
            word = new Word(area,this);
            character_class = new Characters(area, this);
            onlyWord();
            
        }
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
        this.area.append("\n————————————————————————");
        this.area.append("\nКоличество союзов в тексе — "+ count);
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
