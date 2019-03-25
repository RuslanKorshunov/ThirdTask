package by.epam.thirdtask.comparator;

import by.epam.thirdtask.entity.ExcelData;

import java.util.Comparator;

public class ExcelCellComparator implements Comparator<ExcelData>
{
    @Override
    public int compare(ExcelData cellFirst, ExcelData cellSecond)
    {
        int result=-1;
        //TODO может ли дочерняя ячейка иметь последнюю колонку больше родительской
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
