package by.epam.thirdtask.composite;

import by.epam.thirdtask.entity.CellData;
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
        CellData cell=new CellData("NEW", "PLAYER",0, 0, 12, 13, CellType.STRING);
        boolean actual=composite.addNewComponent(cell);
        assertTrue(actual);
    }

    @Test(dependsOnMethods = "addNewComponentPositive")
    public void addNewComponentNegative()
    {
        CellData cell=new CellData("PLAYER", "SUPER_PLAYER",0, 0, 12, 13, CellType.STRING);
        boolean actual=composite.addNewComponent(cell);
        assertFalse(actual);
    }

    @Test(dependsOnMethods = "addNewComponentNegative")
    public void addNewBaseElementPositive()
    {
        CellData cell=new CellData("AURUM", "PLAYER",0, 0, 12, 13, CellType.STRING);
        boolean actual=composite.addNewBaseElement(cell);
        assertTrue(actual);
    }

    @Test(dependsOnMethods = "addNewBaseElementPositive")
    public void addNewBaseElementNegative()
    {
        CellData cell=new CellData("AURUM", "SUPER_PLAYER",0, 0, 12, 13, CellType.STRING);
        boolean actual=composite.addNewBaseElement(cell);
        assertFalse(actual);
    }
}