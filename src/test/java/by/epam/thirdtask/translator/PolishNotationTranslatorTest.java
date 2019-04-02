package by.epam.thirdtask.translator;

import by.epam.thirdtask.exception.IncorrectDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PolishNotationTranslatorTest
{
    private static final Logger logger= LogManager.getLogger(PolishNotationTranslatorTest.class);

    @Test
    public void translatePositive() throws IncorrectDataException
    {
        String formula="42 - 12";
        PolishNotationTranslator translator=new PolishNotationTranslator();
        String actual=translator.translate(formula);
        String expected="42.0 12.0 - ";
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "translatePositive")
    public void translateFirstNegative()
    {
        try
        {
            PolishNotationTranslator translator=new PolishNotationTranslator();
            translator.translate(null);
            fail("translateFirstNegative was failed");
        }
        catch (IncorrectDataException e)
        {
            logger.error(e);
        }
    }

    @Test(dependsOnMethods = "translateFirstNegative")
    public void translateSecondNegative()
    {
        try
        {
            String formula="42 - a";
            PolishNotationTranslator translator=new PolishNotationTranslator();
            translator.translate(formula);
            fail("translateFirstNegative was failed");
        }
        catch (IncorrectDataException e)
        {
            logger.error(e);
        }
    }
}