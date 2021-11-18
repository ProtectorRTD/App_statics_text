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
        System.out.println("Start");
        area_search = new JTextArea("Find");
        frame = app.getFrame();        
        area_search.setBounds(0,480,100,60);
        checkTextArea();
        scroll = new JScrollPane(area_search);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getViewport().setBackground(Color.white);
        scroll.getViewport().add(area_search);
        scroll.setBounds(0,480,100,60); 
        
        // private void init_ch_b_text1(){
        //     ch_b_text1=new JLabel("Text test1");
        //     ch_b_text1.setBounds(0,480,100,60);
        // }
        // private void init_ch_b_text2(){
        //     ch_b_text2=new JLabel("Text test2");
        //     ch_b_text2.setBounds(0,480,100,60);
        // }
        // private void init_check_box1(){
        //     check_box1=new JCheckBox();
        //     check_box1.setBounds(0,480,100,60);
        // }
        // private void init_check_box2(){
        //     check_box2=new JCheckBox();
        //     check_box2.setBounds(100,480,10,60);
        // }
        searchButton = new JButton("Search");
        searchButton.setBounds(100,480,90,60);
        searchButton.setVisible(true);
        scroll.setVisible(true);
        // searchButton.setEnabled(true);
        // frame.add(searchButton);
        frame.add(scroll);
        app.text();


        //сделать так чтобы сразу отрисовалась и подумать над алгоритмом неправильно он работает
        searchButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                String text = area_search.getText();
                if (!text.equals("Find")) 
                {
                    value = text;
                    lastMatch = 0;
                }
                try 
                {
                    if(highlightTag != null)area.getHighlighter().removeHighlight(highlightTag);                
                    startCheckArea();
                } catch (BadLocationException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        // frame.add(app.p_bottom);
    }
    private void startCheckArea() throws BadLocationException 
    {
        String first_area = app.getFirst();
        if (lastMatch + value.length() >= first_area.length()) 
        {
            lastMatch = 0;
        }
        for (; lastMatch + value.length() <= first_area.length(); lastMatch++) //find value
        {
            String match = area.getText(lastMatch, value.length());
            if (value.equalsIgnoreCase(match)) 
            {
                if (highlightTag != null) 
                {
                    area.getHighlighter().removeHighlight(highlightTag);
                }
                if (highlightPainter == null)
                {
                        highlightPainter = new javax.swing.text.DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                }
                highlightTag = area.getHighlighter().addHighlight(lastMatch, lastMatch + value.length(), highlightPainter);
                lastMatch += value.length();
                break;
            }
        }      
    }
    public void checkTextArea() //если что-то написал в первое поле то запускается
    {
        if(highlightTag != null)area.getHighlighter().removeHighlight(highlightTag);
    }
    public Highlighter getHiglighter()
    {
        return highlighter;
    }
}
