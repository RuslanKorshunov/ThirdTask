package by.epam.thirdtask.composite;

import by.epam.thirdtask.reader.ExcelCell;

public abstract class Composite
{
    public void operation()
    {
        //throw new UnsupportedOperationException(getClass().getName()+" doesn't support this operation");
    }

    public boolean addNewComponent(Composite composite)
    {
        return false;
    }

    public boolean addNewComponent(ExcelCell excelCell)
    {
        return false;
    }

    public boolean addNewBaseElement(ExcelCell excelCell)
    {
        return false;
    }

    public boolean remove(Composite composite)
    {
        return false;
    }

    public Composite get(int index)
    {
        return null;
    }

    public String getName()
    {
        return null;
    }
}
