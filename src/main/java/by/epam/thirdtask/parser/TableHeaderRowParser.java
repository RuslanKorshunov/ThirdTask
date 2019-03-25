package by.epam.thirdtask.parser;

import by.epam.thirdtask.composite.Composite;
import by.epam.thirdtask.entity.ExcelData;
import by.epam.thirdtask.exception.IncorrectDataException;
import by.epam.thirdtask.reader.ExcelReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableHeaderRowParser extends RowParser
{
    private static Logger logger= LogManager.getLogger(TableHeaderRowParser.class);

    public TableHeaderRowParser(int rowNumber) throws IncorrectDataException
    {
        super(rowNumber);
    }

    @Override
    public void parse(Composite composite2Main) throws IncorrectDataException
    {
        ExcelReader excelReader=new ExcelReader();
        try
        {
            List<ExcelData> dataAll=excelReader.read(getRowNumber());
            for(ExcelData data: dataAll)
            {
                composite2Main.addNewComponent(data);
            }
            if(excelReader.hasNextRow(getRowNumber()))
            {
                if(excelReader.hasMergedRegions(getRowNumber()+1))
                {
                    RowParser parser=new TableHeaderRowParser(getRowNumber()+1);
                    parser.parse(composite2Main);
                }
                else
                {
                    List<String> parents=new ArrayList<>();
                    for(ExcelData excelData: dataAll)
                    {
                        String parent=excelData.getData();
                        parents.add(parent);
                    }
                    RowParser parser=new DataRowParser(getRowNumber()+1);
                    parser.parse(composite2Main, parents);
                }
            }
        }
        catch (IOException e)
        {
            logger.error(e);
        }
    }

    @Override
    protected int getRowNumber()
    {
        return super.getRowNumber();
    }
}