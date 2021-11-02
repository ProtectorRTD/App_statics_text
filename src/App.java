import java.awt.Color;

import javax.swing.*;  
public class App 
{
    private JFrame f;
    JTextArea area;
    JTextArea area_v2;
    JScrollPane scroll;
    JScrollPane scroll_v2;
    public static void main(String[] args) throws Exception 
    {
        new App();

    }
    private App()
    {
        initArea();
        initScroll();
        frame();
    }  
    private void initArea()
    {
        area = new JTextArea(""); 
        area.setLineWrap(true);  //для переноса на следующую строку
        area.setBounds(100,200, 200,200);  


        area_v2 = new JTextArea(""); 
        area_v2.setLineWrap(true);  //для переноса на следующую строку
        area_v2.setBounds(400,200, 200,200); 
    }
    private void initScroll() 
    {
        scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scroll.getViewport().setBackground(Color.white);
        scroll.getViewport().add(area);
        scroll.setBounds(100, 200, 200, 200);


        scroll_v2 = new JScrollPane(area_v2);
        scroll_v2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scroll_v2.getViewport().setBackground(Color.white);
        scroll_v2.getViewport().add(area_v2);
        scroll_v2.setBounds(400,200, 200,200);
    }
    private void frame()
    {
        f = new JFrame("TextField Example");  
        f.setSize(800,800);  
        f.setResizable(false);
        f.setTitle("Text Compare version 1.0");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(null);  
        

        f.add(scroll);        
        f.add(scroll_v2);
        
        //f.pack();
        f.setVisible(true);  
    }
}

