package by.epam.thirdtask.parser;

import by.epam.thirdtask.composite.Composite;
import by.epam.thirdtask.exception.IncorrectDataException;
import by.epam.thirdtask.exception.WorkWithFileException;

import java.util.List;

public abstract class RowParser
{
    private int rowNumber;

    public RowParser(int rowNumber) throws IncorrectDataException
    {
        if(rowNumber<0)
        {
            throw new IncorrectDataException("rowNumber has to be greater than or equal to 0 but it's "+rowNumber+".");
        }
        this.rowNumber=rowNumber;
    }

    public void parse(Composite composite) throws IncorrectDataException, WorkWithFileException {}

    public void parse(Composite composite, List<String> parents) throws IncorrectDataException, WorkWithFileException {}

    protected int getRowNumber()
    {
        return rowNumber;
    }
}
