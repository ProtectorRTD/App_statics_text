import javax.swing.*;  
public class App 
{
    private JFrame f;
    public static void main(String[] args) throws Exception 
    {
        new App();

    }
    private App()
    {
        frame();
        initArea();
        settings();
    }
    private void frame()
    {
        f = new JFrame("TextField Example");  
    }   
    private void initArea()
    {
        JTextArea area = new JTextArea("Welcome to javatpoint");  
        area.setBounds(10,30, 200,200);  
        f.add(area);  
    }
    private void settings()
    {
        f.setSize(300,300);  
        f.setLayout(null);  
        f.setVisible(true);  
    }
}
