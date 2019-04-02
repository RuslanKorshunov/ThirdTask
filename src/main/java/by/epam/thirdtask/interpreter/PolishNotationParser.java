package by.epam.thirdtask.interpreter;

import by.epam.thirdtask.exception.IncorrectDataException;

import java.util.ArrayList;
import java.util.List;

public class PolishNotationParser
{
    public List<MathExpression> parse(String polishNotation) throws IncorrectDataException
    {
        if(polishNotation==null)
        {
            throw new IncorrectDataException("polishNotation can't be null.");
        }
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
                        Double number=Double.parseDouble(symbol);
                        result.add(c->c.push(number));
                    }
                    catch(NumberFormatException e)
                    {
                        throw new IncorrectDataException("polishNotation can't have \""+symbol+"\".");
                    }
            }
        }

        return result;
    }
}
