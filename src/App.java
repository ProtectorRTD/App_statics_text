import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.*;  
import java.awt.event.*;
public class App 
{
    private static JFrame f;
    private JTextArea area;
    private JTextArea area_v2;
    private JScrollPane scroll;
    private JScrollPane scroll_v2;
    private JButton button_size;
    public static void main(String[] args) throws Exception 
    {
        new App();

    }
    public static JFrame giveframe()
    {
        return f;
    }
    private App()
    {
        initArea();
        initScroll();
        initbutton();
        frame();
    }  
    private void initbutton()
    {
        button_size = new JButton("Compare");
        button_size.setBounds(300,100,100,50);
        button_size.setEnabled(true);
        button_size.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //вызов из другого класса
                Area area = new Area();
                area.calculation();
            }
        });
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
        
        f.add(button_size);
        f.add(scroll);        
        f.add(scroll_v2);
        
        //f.pack();
        f.setVisible(true);  
    }
}
