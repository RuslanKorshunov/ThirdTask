package by.epam.thirdtask.action;

import by.epam.thirdtask.exception.IncorrectDataException;
import by.epam.thirdtask.translator.PolishNotationTranslator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PolishNotationActionTest
{

    private static final Logger logger= LogManager.getLogger(PolishNotationActionTest.class);

    @Test
    public void calculatePositive() throws IncorrectDataException
    {
        PolishNotationTranslator parser=new PolishNotationTranslator();
        PolishNotationAction action=new PolishNotationAction();

        String polishNotation=parser.translate("42 - 12");

        String actual=action.calculate(polishNotation);
        String expected="30.0";

        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "calculatePositive")
    public void calculateFirstNegative()
    {
        try
        {
            PolishNotationAction action=new PolishNotationAction();
            String actual=action.calculate(null);
            fail("calculateFirstNegative was failed.");
        }
        catch (IncorrectDataException e)
        {
            logger.log(Level.ERROR, e);
        }
    }

    @Test(dependsOnMethods = "calculateFirstNegative")
    public void calculateSecondNegative()
    {
        try
        {
            PolishNotationAction action=new PolishNotationAction();
            String actual=action.calculate("");
            fail("calculateFirstNegative was failed.");
        }
        catch (IncorrectDataException e)
        {
            logger.log(Level.ERROR, e);
        }
    }
}