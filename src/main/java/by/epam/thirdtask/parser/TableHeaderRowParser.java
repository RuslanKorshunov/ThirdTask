package by.epam.thirdtask.parser;

import by.epam.thirdtask.composite.Component;
import by.epam.thirdtask.reader.ExcelCell;
import by.epam.thirdtask.reader.ExcelReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class TableHeaderRowParser extends RowParser
{
    private static Logger logger= LogManager.getLogger(TableHeaderRowParser.class);

    public TableHeaderRowParser(int rowNumber)
    {
        super(rowNumber);
    }

    public TableHeaderRowParser(int rowNumber, RowParser rowParser)
    {
        super(rowNumber, rowParser);
    }

    @Override
    public void parse(Component componentMain)
    {
        ExcelReader excelReader=new ExcelReader();
        try
        {
            List<ExcelCell> cells=excelReader.read(getRowNumber());
            for(ExcelCell cell: cells)
            {
                componentMain.addNewComponent(cell);
            }
            if(hasNext())
            {
                next().parse(componentMain);
            }
        }
        catch (IOException e)
        {
            logger.error(e);
        }
    }

    @Override
    protected boolean hasNext()
    {
        return super.hasNext();
    }

    @Override
    protected RowParser next()
    {
        return super.next();
    }

    @Override
    protected int getRowNumber()
    {
        return super.getRowNumber();
    }
}
