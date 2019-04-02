package by.epam.thirdtask.interpreter;

import by.epam.thirdtask.exception.IncorrectDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PolishNotationParserTest
{
    private static final Logger logger=LogManager.getLogger(PolishNotationParserTest.class);

    @Test
    public void parseFirstNegative()
    {
        try
        {
            PolishNotationParser parser=new PolishNotationParser();
            parser.parse(null);
            fail("parseFirstNegative was failed.");
        }
        catch(IncorrectDataException e)
        {
            logger.error(e);
        }
    }

    @Test(dependsOnMethods = "parseFirstNegative")
    public void parseSecondNegative()
    {
        try
        {
            PolishNotationParser parser=new PolishNotationParser();
            parser.parse("42 + a");
            fail("parseSecondNegative was failed.");
        }
        catch(IncorrectDataException e)
        {
            logger.error(e);
        }
    }
}