package by.epam.thirdtask.composite;

import by.epam.thirdtask.entity.ExcelCell;
import by.epam.thirdtask.exception.IncorrectDataException;
import by.epam.thirdtask.exception.WorkWithFileException;
import by.epam.thirdtask.parser.RowParser;
import by.epam.thirdtask.parser.TableHeaderRowParser;
import org.apache.poi.ss.usermodel.CellType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeTest
{
    private Composite composite=new Composite();

    @BeforeClass
    public void setComposite() throws IncorrectDataException, WorkWithFileException
    {
        RowParser parser=new TableHeaderRowParser(0);
        parser.parse(composite);
    }

    @Test
    public void addNewComponentPositive()
    {
        ExcelCell cell=new ExcelCell("NEW", "PLAYER",0, 0, 12, 13, CellType.STRING);
        boolean actual=composite.addNewComponent(cell);
        assertTrue(actual);
    }

    @Test(dependsOnMethods = "addNewComponentPositive")
    public void addNewComponentNegative()
    {
        ExcelCell cell=new ExcelCell("PLAYER", "SUPER_PLAYER",0, 0, 12, 13, CellType.STRING);
        boolean actual=composite.addNewComponent(cell);
        assertFalse(actual);
    }

    @Test(dependsOnMethods = "addNewComponentNegative")
    public void addNewBaseElementPositive()
    {
        ExcelCell cell=new ExcelCell("AURUM", "PLAYER",0, 0, 12, 13, CellType.STRING);
        boolean actual=composite.addNewBaseElement(cell);
        assertTrue(actual);
    }

    @Test(dependsOnMethods = "addNewBaseElementPositive")
    public void addNewBaseElementNegative()
    {
        ExcelCell cell=new ExcelCell("AURUM", "SUPER_PLAYER",0, 0, 12, 13, CellType.STRING);
        boolean actual=composite.addNewBaseElement(cell);
        assertFalse(actual);
    }
}