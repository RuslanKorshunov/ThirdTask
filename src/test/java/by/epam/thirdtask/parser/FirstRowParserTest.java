package by.epam.thirdtask.parser;

import org.testng.annotations.Test;

public class FirstRowParserTest {

    @Test
    public void testParse()
    {
        RowParser firstRowParser=new FirstRowParser();
        firstRowParser.parse();
    }
}