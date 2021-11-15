import java.awt.Color;

import javax.swing.*;  
import java.awt.event.*;
public class App implements ActionListener, KeyListener
{
    private static JFrame f;
    private JLabel a_labe1,a_labe2;
    private JTextArea area,area_v2;
    private JScrollPane scroll,scroll_v2;
//        Читать причину ниже
//
    // private JButton button_size;
//    Доделать______________________________
//
//    private JPanel p_left;
//    private JPanel p_right;
//    private JPanel p_bottom;
    private JMenuBar mb;
    private JMenu file,edit,help,help_sub_menu;
    private JMenuItem selectAll,open,lang,eng,ru, run; //cut,copy, paste
    private String textfirst,textsecond;


    private Area area_class;
    //private Area area_class; типо так сделать Денис?
    public JFrame giveframe()
    {
        return f;
    }
    public App()
    {
        area_class = new Area(this);
        initArea();
        initScroll();
        init_a_labe1();
        init_a_labe2();

//        Читать причину ниже
//
//        init_button();

//        Доделать_________________________
//
//        init_p_left();
//        init_p_right();
//        init_p_bottom();
        init_mb();
        init_edit();
        init_file();
        init_open();
        // init_copy();
        // init_cut();
        // init_paste();
        init_selectAll();
        init_run();
        init_help();
        help_sub_menu();
        init_lang_chose();
        init_eng();
        init_ru();
        frame();
    }
    private void frame()
    {
        f = new JFrame("TextField Example");
        f.setSize(800,600);
        f.setResizable(false);
        f.setTitle("Text static information 1.0");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
//        Читать причину ниже
//
        // f.add(button_size);
        f.add(scroll);f.add(scroll_v2);
        f.add(a_labe1);f.add(a_labe2);

        //для считывание комбинации клавиш

        f.addKeyListener(this);



//         Доделать____________________________
//
//        f.add(p_left);f.add(p_right);f.add(p_bottom);
        // edit.add(cut);edit.add(copy);
        // edit.add(paste);
        edit.add(selectAll);
        file.add(open);
        help.add(help_sub_menu);
        help_sub_menu.add(lang);
        lang.add(eng);lang.add(ru);
        edit.add(run);
        mb.add(file);mb.add(edit);mb.add(help);
        f.add(mb);
        f.setJMenuBar(mb);
        //f.pack();
        f.setVisible(true);
    }
    //Розобратсяь с Jpanel и JScrollPane, JTextAre<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    private void initArea()
    {
        area = new JTextArea("");
        area.setLineWrap(true);  //для переноса на следующую строку
        area.setWrapStyleWord(true); // чтобы переносил лишь слова а не чарактерсы(буквы)
        area.setBounds(50,50, 315,420);

        area_v2 = new JTextArea("");
        area_v2.setLineWrap(true);  //для переноса на следующую строку
        area_v2.setWrapStyleWord(true); // чтобы переносил лишь слова а не чарактерсы(буквы)
        area_v2.setBounds(450,50, 315,420);

        text();
    }
    private void initScroll()
    {
        scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scroll.getViewport().setBackground(Color.white);
        scroll.getViewport().add(area);
        scroll.setBounds(50,50, 315,420);


        scroll_v2 = new JScrollPane(area_v2);
        scroll_v2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scroll_v2.getViewport().setBackground(Color.white);
        scroll_v2.getViewport().add(area_v2);
        scroll_v2.setBounds(450,50, 315,420);
    }
//    Просто отключил кнопку вместо нее будет кнопка в меню ________________________
//
//    private void init_button()
//    {
//        button_size = new JButton("Stats");
//        button_size.setBounds(300,50,100,50);
//        button_size.setEnabled(true);
//        button_size.addActionListener(new ActionListener()
//        {
//            public void actionPerformed(ActionEvent e)
//            {
//                //вызов из другого класса
//                text();
//                area_class.calculation(area_v2);
//            }
//        });
//    }
    private void init_a_labe1()
    {
        a_labe1=new JLabel("Insert text here");
        a_labe1.setBounds(50,20,400,20);

    }
    private void init_a_labe2()
    {
        a_labe2=new JLabel("Text statistics here");
        a_labe2.setBounds(450,20,400,20);

    }

//    Доделать________________________________
//
//    private void init_p_left(){
//        p_left=new JPanel();
//        p_left.setBounds(0,0,800,20);
//
//    }
//    private void init_p_right(){
//        p_right=new JPanel();
//        p_right.setBounds(0,0,800,20);
//
//    }
//    private void init_p_bottom(){
//        p_bottom=new JPanel();
//        p_bottom.setBounds(0,0,800,20);
//    }


//  Это меню покачто без функций
//    Меню
    private void init_mb()
    {
        mb=new JMenuBar();
        mb.setBounds(0,0,800,20);

    }
    //Вкладка файл
    private void init_file()
    {
        file=new JMenu("File");

    }
    private void init_open()
    {
        open=new JMenuItem("Open File");
    }
// Вкладка едит
    private void init_edit()
    {
        edit=new JMenu("Edit");
    }
    // private void init_copy()
    // {
    //     copy=new JMenuItem("copy");
    // }
    // private void init_cut()
    // {
    //             cut=new JMenuItem("cut");
    // }
    // private void init_paste()
    // {
    //     paste=new JMenuItem("paste");
    // }
    private void init_selectAll()
    {
        selectAll=new JMenuItem("selectAll          CTRL+A");
        selectAll.addActionListener(this);    
    }
        //Вкладка ран
    private void init_run()
    {
        run = new JMenuItem("Run");
        run.addActionListener(this);
        //area_class.calculation(area_v2);
        
    }
    //Вкладка хелп
    private void init_help()
    {
        help=new JMenu("Help");
    }
    //Вложеная вкладка языков
    private void help_sub_menu()
    {
        help_sub_menu=new JMenu("Help");
    }
    private void init_lang_chose()
    {
        lang=new JMenu("Language");
    }
    private void init_eng()
    {
        eng=new JMenu("English");
    }
    private void init_ru()
    {
        ru=new JMenu("Russian");
    }
// Все что ниже магия егора я еще не дорос пойму потом (((((((
    private void text()
    {
        textfirst = area.getText();
        textsecond = area_v2.getText();
    }
    public String getFirst()
    {
        return textfirst;
    }
    public String getSecond()
    {
        return textsecond;
    }
    //Никита функция которую передаешь действие например e.getSourse == cut
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == run)
        {
            text();
            area_class.calculation(area_v2);
        }
        if(e.getSource() == selectAll)
        {
            area.selectAll();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) 
    {        
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyPressed(KeyEvent e) 
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyReleased(KeyEvent e) 
    {
        // TODO Auto-generated method stub        
    }    
}
