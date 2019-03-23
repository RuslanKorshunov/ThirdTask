package by.epam.thirdtask;

import by.epam.thirdtask.reader.ExcelReader;

import java.io.IOException;

public class Main
{
    public static void main(String[]args) throws IOException
    {
        //new FirstRowParser().parse();
        new ExcelReader().read(0);
        System.out.println("///");
        new ExcelReader().read(1);
        System.out.println("///");
        new ExcelReader().read(2);
        System.out.println("///");
        new ExcelReader().read(3);
    }
}
