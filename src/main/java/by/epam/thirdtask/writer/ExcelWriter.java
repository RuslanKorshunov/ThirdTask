package by.epam.thirdtask.writer;

import by.epam.thirdtask.composite.Component;
import by.epam.thirdtask.composite.Composite;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriter
{
    private static final String filePath="src/main/resources/heroes.xlsx";

    public void write(Composite composite) throws IOException
    {
        final int NUMBER_OF_SHEET=1;
        final String NAME_OF_SHEET="result";

        File file=new File(filePath);
        FileInputStream fileInputStream=new FileInputStream(file);
        XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);

        XSSFSheet sheet=workbook.createSheet(NAME_OF_SHEET);

        for(int i=0; i<composite.size(); i++)
        {

        }

        FileOutputStream fileOutputStream=new FileOutputStream(file);
        workbook.write(fileOutputStream);

        workbook.close();
    }
}
