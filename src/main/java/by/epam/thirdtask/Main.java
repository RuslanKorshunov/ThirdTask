package by.epam.thirdtask;

import by.epam.thirdtask.composite.Component;
import by.epam.thirdtask.parser.DataRowParser;
import by.epam.thirdtask.parser.RowParser;
import by.epam.thirdtask.parser.TableHeaderRowParser;

import java.io.IOException;

public class Main
{
    public static void main(String[]args) throws IOException
    {
        Component component=new Component();
        RowParser dataRowParserSecond=new DataRowParser(5);
        RowParser dataRowParserFirst=new DataRowParser(4, dataRowParserSecond);
        RowParser tableHeaderRowParserFouth =new TableHeaderRowParser(3, dataRowParserFirst);
        RowParser tableHeaderRowParserThird =new TableHeaderRowParser(2, tableHeaderRowParserFouth);
        RowParser tableHeaderRowParserSecond =new TableHeaderRowParser(1, tableHeaderRowParserThird);
        RowParser tableHeaderRowParserFirst =new TableHeaderRowParser(0, tableHeaderRowParserSecond);
        tableHeaderRowParserFirst.parse(component);
        component.operation();
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
