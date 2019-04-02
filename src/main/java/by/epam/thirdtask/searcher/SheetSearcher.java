package by.epam.thirdtask.searcher;

import by.epam.thirdtask.exception.WorkWithFileException;
import by.epam.thirdtask.validator.FileValidator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SheetSearcher
{
    private SheetSearcher(){}

    public static XSSFSheet search(String filePath, int sheetNumber) throws WorkWithFileException
    {
        if(!FileValidator.existFile(filePath))
        {
            throw new WorkWithFileException("File "+filePath+" doesnt exist.");
        }
        if(!FileValidator.canReadFile(filePath))
        {
            throw new WorkWithFileException("File "+filePath+" couldn't be read.");
        }
        XSSFWorkbook workbook;
        try
        {
            File file=new File(filePath);
            FileInputStream inputStream=new FileInputStream(file);
            workbook=new XSSFWorkbook(inputStream);
        }
        catch (IOException e)
        {
            throw new WorkWithFileException("There was a problem reading from the file "+filePath+".");
        }
        return workbook.getSheetAt(sheetNumber);
    }
}
