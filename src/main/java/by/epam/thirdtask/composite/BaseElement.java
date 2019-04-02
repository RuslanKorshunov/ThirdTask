package by.epam.thirdtask.composite;

import by.epam.thirdtask.entity.CellData;

public class BaseElement extends Component
{
    private CellData cellData;

    public BaseElement(CellData cellData)
    {
        this.cellData = cellData;
    }

    @Override
    public String getName()
    {
        return cellData.getData();
    }

    @Override
    public CellData getCellData()
    {
        return cellData;
    }

    @Override
    public String toString()
    {
        return getName()+"\n";
    }
}
