package by.epam.thirdtask.parser;

import by.epam.thirdtask.composite.Component;

public abstract class RowParser
{
    private RowParser next;
    private int rowNumber;

    public RowParser(int rowNumber)
    {
        this.rowNumber=rowNumber;
    }

    public RowParser(int rowNumber, RowParser rowParser)
    {
        this.rowNumber=rowNumber;
        this.next=rowParser;
    }

    public void parse(Component componentMain)
    {

    }

    protected boolean hasNext()
    {
        return (next==null? false : true);
    }

    protected RowParser next()
    {
        return next;
    }

    protected int getRowNumber()
    {
        return rowNumber;
    }
}
