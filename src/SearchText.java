import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import java.util.Arrays;

public class SearchText extends Area
{
    private App app;
    private Highlighter highlighter;
    private char[] value;
    private JTextArea area, area_search;
    private JFrame frame;
    private JScrollPane scroll;
    private Object last;
    public SearchText(App app) 
    {
        super(app); 
        this.app = app;    
        area = app.getArea();           
    }
    public void CreateTextArea()
    {
        System.out.println("Start");
        area_search = new JTextArea("Find");
        frame = app.getFrame();        
        area_search.setBounds(580,5, 140,25);
        checkTextArea();
        scroll = new JScrollPane(area_search);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getViewport().setBackground(Color.white);
        scroll.getViewport().add(area_search);
        scroll.setBounds(580,5, 140,25); 
        frame.add(scroll);
    }
    private void startCheckArea() throws BadLocationException
    {
        HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
        Highlighter highlighter = area.getHighlighter();
        app.text();
        char[] first_area = app.getFirst().toCharArray();
        int p0 = 0;
        int p1 = 0;
        int j = 0;
        // boolean first = false;
        for(int i = 0; i < first_area.length; i++)
        {
            if(value.length > 0 && first_area[i] == value[0])
            {
                p0 = i;
                // first = true;
                p1 = 0;
                for(j = i; j <area_search.getText().length(); j++)
                {
                    if(first_area[j] == value[p1])
                    {
                        p1++;
                    }
                }
                p1++;
                last = highlighter.addHighlight(p0, i+p1, painter );                
            }
            // i += j;          
        }
    }
    public void checkTextArea() 
    {
        char[] find = new char[]{'F','i','n','d'};
        area_search.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent ke)
            {
                if(area_search.getText().equals("Find")) area_search.setText("");
                value = area_search.getText().toCharArray();
                try 
                {
                    startCheckArea();
                } catch (BadLocationException e) 
                {
                    e.printStackTrace();
                }                                
            }
        });
        if(last != null)
        {
            area.getHighlighter().removeHighlight(highlighter);
        }
        //одно решение вырезать тот текст что одинаковые а другой поделить на 2 части принтануть до него и потом наш выделить и второй также 
        //создать бул в кейцбоарде чтобы тем же нажатие 
 
        // если добавилось что-то в первое поле то тоже сразу же удалить хайлатер
    }
}
