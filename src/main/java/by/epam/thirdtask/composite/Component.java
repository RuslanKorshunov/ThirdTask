package by.epam.thirdtask.composite;

import by.epam.thirdtask.entity.ExcelData;

public abstract class Component
{
    public void operation()
    {
        //throw new UnsupportedOperationException(getClass().getName()+" doesn't support this operation");
    }

    public boolean addNewComponent(Component component)
    {
        return false;
    }

    public boolean addNewComponent(ExcelData excelData)
    {
        return false;
    }

    public boolean addNewBaseElement(ExcelData excelData)
    {
        return false;
    }

    public boolean remove(Component component)
    {
        return false;
    }

    public Component get(int index)
    {
        return null;
    }

    public String getName()
    {
        return null;
    }
}
