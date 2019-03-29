package by.epam.thirdtask.interpreter;

import by.epam.thirdtask.exception.IncorrectDataException;

import java.util.ArrayList;
import java.util.List;

public class ReversePolishNotationParser
{
    public List<MathExpression> parse(String polishNotation) throws IncorrectDataException
    {
        List<MathExpression> result=new ArrayList<>();

        for(String symbol: polishNotation.split(InterpreterConstant.SPACE))
        {
            switch (symbol)
            {
                case InterpreterConstant.PLUS:
                    result.add(c->c.push(c.pop()+c.pop()));
                    break;
                case InterpreterConstant.MINUS:
                    result.add(c->c.push(-c.pop()+c.pop()));
                    break;
                case InterpreterConstant.MULTIPLY:
                    result.add(c->c.push(c.pop()*c.pop()));
                    break;
                case InterpreterConstant.DIVIDE:
                    result.add(c->c.push(1/c.pop()*c.pop()));
                    break;
                default:
                    try
                    {
                        result.add(c->c.push(Double.parseDouble(symbol)));
                    }
                    catch(NumberFormatException e)//TODO правильно ли так делать?
                    {
                        throw new IncorrectDataException("polishNotation can't have "+symbol+".");
                    }
            }
        }

        return result;
    }
}
