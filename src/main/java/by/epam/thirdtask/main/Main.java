package by.epam.thirdtask.main;

import by.epam.thirdtask.composite.Composite;
import by.epam.thirdtask.exception.IncorrectDataException;
import by.epam.thirdtask.parser.RowParser;
import by.epam.thirdtask.parser.TableHeaderRowParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main
{
    private static final Logger logger= LogManager.getLogger(Main.class);

    public static void main(String[]args)
    {
        Composite composite2 =new Composite();
        try
        {
            RowParser parser=new TableHeaderRowParser(0);
            parser.parse(composite2);
            composite2.operation();
            System.out.println(composite2.toString());
        }
        catch(IncorrectDataException e)
        {
            logger.error(e);
        }
/*        try
        {
            //String str=new MathAction().calculateReversePolishNotation("( 8 + 2 + 3 * 6 ) * 15 - 10 / 2");
            String str=new MathAction().calculateReversePolishNotation("( 70 / 2 + 7 ) / 3 + 8 - 100 * -1");
            //System.out.println(new MathAction().calculate(str));
        }
        catch (IncorrectDataException e)
        {
            logger.error(e);
        }*/
        //new TableHeaderRowParser().;
        /*new ExcelReader().read(0);
        System.out.println("///");
        new ExcelReader().read(1);
        System.out.println("///");
        new ExcelReader().read(2);
        System.out.println("///");
        new ExcelReader().read(3);*/
    }
}
