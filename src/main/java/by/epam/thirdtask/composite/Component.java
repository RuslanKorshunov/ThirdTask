package by.epam.thirdtask.composite;

import by.epam.thirdtask.entity.ExcelCell;

import java.util.List;

public abstract class Component
{
    public void operation()
    {}

    public boolean addNewComponent(Component component)
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

    public int size()
    {
        return -1;
    }

    public int findHightOfTableHeader()
    {
        return -1;
    }

    public int findHight()
    {
        return -1;
    }

    protected List<Component> getComponents()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return "Component{}";
    }
}
