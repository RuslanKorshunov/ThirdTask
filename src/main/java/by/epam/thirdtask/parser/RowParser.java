package by.epam.thirdtask.parser;

import by.epam.thirdtask.composite.Composite;
import by.epam.thirdtask.exception.IncorrectDataException;

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

    public void parse(Composite composite2Main) throws IncorrectDataException {}

    public void parse(Composite composite2Main, List<String> parents) throws IncorrectDataException {}

    protected int getRowNumber()
    {
        return rowNumber;
    }
}
