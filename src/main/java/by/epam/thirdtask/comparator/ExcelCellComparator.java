package by.epam.thirdtask.comparator;

import by.epam.thirdtask.reader.ExcelCell;

import java.util.Comparator;

public class ExcelCellComparator implements Comparator<ExcelCell>
{
    @Override
    public int compare(ExcelCell cellFirst, ExcelCell cellSecond)
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
