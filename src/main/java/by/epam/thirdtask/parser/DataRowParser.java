package by.epam.thirdtask.parser;

import by.epam.thirdtask.composite.Composite;
import by.epam.thirdtask.entity.ExcelData;
import by.epam.thirdtask.exception.IncorrectDataException;
import by.epam.thirdtask.reader.ExcelReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class DataRowParser extends RowParser
{
    private static final Logger logger= LogManager.getLogger(DataRowParser.class);

    public DataRowParser(int rowNumber) throws IncorrectDataException
    {
        super(rowNumber);
    }

    @Override
    public void parse(Composite composite, List<String> parents) throws IncorrectDataException
    {
        if(composite==null)
        {
            throw new IncorrectDataException("composite can't be null.");
        }
        if(parents==null)
        {
            throw new IncorrectDataException("parents can't be null.");
        }
        ExcelReader excelReader=new ExcelReader();
        try
        {
            List<ExcelData> cells=excelReader.read(getRowNumber());
            if(cells.size()!=parents.size())
            {
                throw new IncorrectDataException("Size of cells isn't equal to size of parents.");
            }
            for(int index=0; index<cells.size(); index++)
            {
                ExcelData cell=cells.get(index);
                cell.setParentData(parents.get(index));
            }
            for(ExcelData cell: cells)
            {
                composite.addNewBaseElement(cell);
            }
            if(excelReader.hasNextRow(getRowNumber()))
            {
                RowParser parser=new DataRowParser(getRowNumber()+1);
                parser.parse(composite, parents);
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
