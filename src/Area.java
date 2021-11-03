
public class Area 
{
    //private JFrame f;
    private String firstText;
    private String secondText;
    private App app;
    private boolean to_the_end;
    public Area(App app)
    {
        this.app = app;
    }
    public void calculation()
    {
        //if(app.getFirst() == null || app.getSecond() == null) return;
        //функция которая будет сравнивать 1-ое и второе текстовое поле
        //должен быть под метод который в свою очередь будет иметь два стринга, 1-ый отвечает за 1 поле, второй за второе (вынести в метод)
        //calculation считает и говорит какое неправильно выделяя цветом
        //подумать или посмотреть про статик что-то выглядит странно
        firstText = app.getFirst();
        secondText = app.getSecond();

        System.out.println(firstText);
        System.out.println(secondText);
        System.out.println();
        int max = secondText.length();
        if(firstText.length() > secondText.length()) max = firstText.length();
        
        //12398456 123456 должен выделить 98

        
        for(int i = 0; i < max; i++) // пишет что во втором неправильно 
        {
            
        }
    }
}
