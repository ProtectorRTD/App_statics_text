import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;


public class SearchText extends Area
{
    private App app;
    private JTextArea area, area_search;
    private JFrame frame;
    private JScrollPane scroll;
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
        scroll = new JScrollPane(area_search);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getViewport().setBackground(Color.white);
        scroll.getViewport().add(area_search);
        scroll.setBounds(580,5, 140,25); 
        frame.add(scroll);
    }
}
