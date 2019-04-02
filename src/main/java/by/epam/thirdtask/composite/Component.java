package by.epam.thirdtask.composite;

import by.epam.thirdtask.entity.CellData;

import java.util.List;

public abstract class Component
{
    public void operation()
    {}

    public boolean addNewComponent(Component component)
    {
        return false;
    }

    public boolean addNewComponent(CellData cellData)
    {
        return false;
    }

    public boolean addNewBaseElement(CellData cellData)
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
    
    public CellData getCellData()
    {
        return null;
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
