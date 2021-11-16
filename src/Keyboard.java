import java.awt.Color;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.event.*;
import java.awt.Toolkit; 
public class Keyboard extends Area
{
    private App app;
    private JTextArea area;
    private JLabel language;
    private int count_language;
    public Keyboard(App app) 
    {
        super(app); 
        this.app = app;    
        area = app.getArea(); 
        createCombination();
    }
    private void createCombination()
    {
        combinationRun();
        combinationSelectAll();
        language();
        ChangeMenuItem();
        CombinationForSearch();
    }
    private void CombinationForSearch()
    {
        KeyStroke ctrlF = KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
        app.search.setAccelerator(ctrlF);
    }
    private void combinationRun()
    {
        KeyStroke f5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
        app.run.setAccelerator(f5);
    }
    private void combinationSelectAll()
    {
        KeyStroke ctrlA = KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
        app.selectAll.setAccelerator(ctrlA);
        app.selectAll.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                System.out.println("Proverka");
                area.selectAll();
            }
        });        
    }
    private void language()
    {
        count_language = 0;
        language = app.GetLanguage();
        language.addMouseListener(new MouseInputAdapter() 
        {
            public void mouseClicked(MouseEvent e) 
            {
                count_language++;
                ChangeLabel(count_language);
                if(count_language == 2) count_language = 0;
            }
        });
    }
    private void ChangeLabel(int count_language)
    {
        if(count_language == 0)
        {
            language.setText("Russian");
        }
        else if (count_language == 1)
        {
            language.setText("English");
        }
        else 
        {
            language.setText("Ukrainian");
        }
    }
    private void ChangeMenuItem()
    {
        KeyStroke shiftminus =  KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,KeyEvent.SHIFT_MASK | KeyEvent.CTRL_MASK);
        app.changeLanguage.setAccelerator(shiftminus);
    }
    public int getCountLanguage()
    {
        return count_language;
    }
    public void SetCountLanguage(int count)
    {
        count_language = count;
        ChangeLabel(count_language);
    }
}
