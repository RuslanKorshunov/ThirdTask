package by.epam.thirdtask.parser;

import by.epam.thirdtask.composite.Composite;
import by.epam.thirdtask.entity.ExcelCell;
import by.epam.thirdtask.exception.IncorrectDataException;
import by.epam.thirdtask.exception.WorkWithFileException;
import by.epam.thirdtask.reader.ExcelReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TableHeaderRowParser extends RowParser
{
    private static final Logger logger= LogManager.getLogger(TableHeaderRowParser.class);

    public TableHeaderRowParser(int rowNumber) throws IncorrectDataException
    {
        super(rowNumber);
    }

    @Override
    public void parse(Composite composite) throws IncorrectDataException, WorkWithFileException
    {
        if(composite==null)
        {
            throw new IncorrectDataException("composite can't be null.");
        }
        ExcelReader excelReader=new ExcelReader();
        List<ExcelCell> cells=excelReader.read(getRowNumber());
        for(ExcelCell cell: cells)
        {
            composite.addNewComponent(cell);
        }
        if(excelReader.hasNextRow(getRowNumber()))
        {
            if(excelReader.hasMergedRegions(getRowNumber()+1))
            {
                RowParser parser=new TableHeaderRowParser(getRowNumber()+1);
                parser.parse(composite);
            }
            else
            {
                List<String> parents=new ArrayList<>();
                for(ExcelCell cell : cells)
                {
                    String parent= cell.getData();
                    parents.add(parent);
                }
                RowParser parser=new DataRowParser(getRowNumber()+1);
                parser.parse(composite, parents);
            }
        }
    }

    @Override
    protected int getRowNumber()
    {
        return super.getRowNumber();
    }
}