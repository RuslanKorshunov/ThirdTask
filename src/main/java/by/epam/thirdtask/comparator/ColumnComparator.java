package by.epam.thirdtask.comparator;

import by.epam.thirdtask.entity.CellData;

import java.util.Comparator;

public class ColumnComparator implements Comparator<CellData>
{
    @Override
    public int compare(CellData cellFirst, CellData cellSecond)
    {
        int result=-1;
        if(cellFirst.getFirstColumn()>=cellSecond.getFirstColumn() &&
        cellFirst.getLastColumn()<=cellSecond.getLastColumn())
        {
            result=0;
        }
        if(cellFirst.getFirstColumn()>cellSecond.getLastColumn())
        {
            result=1;
        }
        return result;
    }
}
