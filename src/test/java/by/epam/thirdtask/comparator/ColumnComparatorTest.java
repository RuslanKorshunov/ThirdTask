package by.epam.thirdtask.comparator;

import by.epam.thirdtask.entity.ExcelCell;
import org.apache.poi.ss.usermodel.CellType;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ColumnComparatorTest
{

    @Test
    public void compareFirst()
    {
        ColumnComparator comparator=new ColumnComparator();
        ExcelCell first=new ExcelCell("", "", 0, 0, 0, 0, CellType.BLANK);
        ExcelCell second=new ExcelCell("", "",0, 0,0, 0, CellType.BLANK);
        int actual=comparator.compare(first, second);
        int expected=0;
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "compareFirst")
    public void compareSecond()
    {
        ColumnComparator comparator=new ColumnComparator();
        ExcelCell first=new ExcelCell("", "",0, 0,1, 1, CellType.BLANK);
        ExcelCell second=new ExcelCell("", "",0, 0,0, 0, CellType.BLANK);
        int actual=comparator.compare(first, second);
        int expected=1;
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "compareSecond")
    public void compareThird()
    {
        ColumnComparator comparator=new ColumnComparator();
        ExcelCell first=new ExcelCell("", "",0, 0,1, 1, CellType.BLANK);
        ExcelCell second=new ExcelCell("", "",0, 0,2, 4, CellType.BLANK);
        int actual=comparator.compare(first, second);
        int expected=-1;
        assertEquals(actual, expected);
    }
}