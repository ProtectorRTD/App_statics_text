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
    private String value;
    private JTextArea area, area_search;
    private JFrame frame;
    private JScrollPane scroll;

    private JButton searchButton;
    private int lastMatch;
    private Object highlightTag;
    private DefaultHighlighter.DefaultHighlightPainter highlightPainter;
    public SearchText(App app) 
    {
        super(app); 
        this.app = app;    
        lastMatch = 0;
        area = app.getArea();
        String first_area = app.getFirst();           
    }
    public void CreateTextArea()
    {
        frame = app.getFrame();   

        area_search = new JTextArea("Find");
        area_search.setBounds(0,480,100,60);



        scroll = new JScrollPane(area_search);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getViewport().setBackground(Color.white);
        scroll.getViewport().add(area_search);
        scroll.setBounds(0,480,100,60); 
        scroll.setVisible(true);

        area_search.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent ke)
            {
                checkTextArea();
            }
        });
        frame.add(scroll);
        app.text();

    }
    private void startCheckArea() throws BadLocationException 
    {
        Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( Color.cyan );
        area.getHighlighter().removeAllHighlights();
        String textArea = app.getFirst();
        String searchWord = area_search.getText();
        int offset = textArea.indexOf(searchWord); //textarea
        int length = searchWord.length();
        while(offset != -1)
        {
            area.getHighlighter().addHighlight(offset, length, painter);
            offset = textArea.indexOf(searchWord, offset+1);
        }
    }
    //он запускает лишь когда кнопка начинает нажимать грубо говоря у меня есть а нажимает б и б ещё не записалась и считает а
    public void checkTextArea() //если что-то написал в первое поле то запускается
    {
       if(area_search != null && area_search.getText() != null)
        {
            try 
            {
                startCheckArea();
            } 
            catch (BadLocationException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       }
    }
    public Highlighter getHiglighter()
    {
        return highlighter;
    }
}
