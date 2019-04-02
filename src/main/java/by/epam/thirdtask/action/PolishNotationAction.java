package by.epam.thirdtask.action;

import by.epam.thirdtask.exception.IncorrectDataException;
import by.epam.thirdtask.interpreter.Context;
import by.epam.thirdtask.interpreter.MathExpression;
import by.epam.thirdtask.interpreter.PolishNotationParser;

import java.util.List;

public class PolishNotationAction
{
    public String calculate(String polishNotation) throws IncorrectDataException
    {
        if(polishNotation==null)
        {
            throw new IncorrectDataException("polishNotation can't be null.");
        }
        if(polishNotation.equals(""))
        {
            throw new IncorrectDataException("polishNotation can't be empty.");
        }
        PolishNotationParser translator=new PolishNotationParser();
        List<MathExpression> mathExpressionList=translator.parse(polishNotation);
        Context context=new Context();
        mathExpressionList.forEach((mathExpression)->mathExpression.interpret(context));
        return String.valueOf(context.pop());
    }
}
