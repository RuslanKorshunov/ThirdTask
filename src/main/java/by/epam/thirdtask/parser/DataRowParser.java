package by.epam.thirdtask.parser;

import by.epam.thirdtask.composite.Composite;
import by.epam.thirdtask.entity.CellData;
import by.epam.thirdtask.exception.IncorrectDataException;
import by.epam.thirdtask.exception.WorkWithFileException;
import by.epam.thirdtask.reader.ExcelReader;

import java.util.List;

public class DataRowParser extends RowParser
{
    public DataRowParser(int rowNumber) throws IncorrectDataException
    {
        super(rowNumber);
    }

    @Override
    public void parse(Composite composite, List<String> parents) throws IncorrectDataException, WorkWithFileException
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
        List<CellData> cells=excelReader.read(getRowNumber());
        if(cells.size()!=parents.size())
        {
            throw new IncorrectDataException("Size of cells isn't equal to size of parents.");
        }
        for(int index=0; index<cells.size(); index++)
        {
            CellData cell=cells.get(index);
            cell.setParentData(parents.get(index));
        }
        for(CellData cell: cells)
        {
            composite.addNewBaseElement(cell);
        }
        if(excelReader.hasNextRow(getRowNumber()))
        {
            RowParser parser=new DataRowParser(getRowNumber()+1);
            parser.parse(composite, parents);
        }

    }

    @Override
    protected int getRowNumber()
    {
        return super.getRowNumber();
    }
}
