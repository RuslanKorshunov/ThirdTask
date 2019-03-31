package by.epam.thirdtask.action;

import by.epam.thirdtask.exception.IncorrectDataException;
import by.epam.thirdtask.interpreter.Context;
import by.epam.thirdtask.interpreter.InterpreterConstant;
import by.epam.thirdtask.interpreter.MathExpression;
import by.epam.thirdtask.interpreter.ReversePolishNotationParser;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class MathAction//TODO как назвать класс?
{
    public String calculateReversePolishNotation(String formula) throws IncorrectDataException
    {
        if(formula==null)
        {
            throw new IncorrectDataException("formula can't be null.");
        }
        StringBuilder exit=new StringBuilder();
        Deque<String> deque=new ArrayDeque<>();
        for(String symbolCurrent: formula.split(InterpreterConstant.SPACE))
        {
            switch (symbolCurrent)
            {
                case InterpreterConstant.PLUS:
                case InterpreterConstant.MINUS:
                    if(!deque.isEmpty())
                    {
                        if(!deque.peekLast().equals(InterpreterConstant.LEFT_BRACKET))
                        {
                            String symbol=deque.pollLast();//TODO излишне?
                            exit.append(symbol+" ");
                        }
                    }
                    deque.offerLast(symbolCurrent);
                    break;
                case InterpreterConstant.DIVIDE:
                case InterpreterConstant.MULTIPLY:
                    if(!deque.isEmpty())
                    {
                        if(!deque.peekLast().equals(InterpreterConstant.LEFT_BRACKET) &&
                                !deque.peekLast().equals(InterpreterConstant.MINUS) &&
                                !deque.peekLast().equals(InterpreterConstant.PLUS))
                        {
                            String symbol=deque.pollLast();
                            exit.append(symbol+" ");
                        }
                    }
                    deque.offerLast(symbolCurrent);
                    break;
                case InterpreterConstant.LEFT_BRACKET:
                    deque.offerLast(symbolCurrent);
                    break;
                case InterpreterConstant.RIGHT_BRACKET:
                    while(!deque.peekLast().equals(InterpreterConstant.LEFT_BRACKET))
                    {
                        String symbol=deque.pollLast();
                        exit.append(symbol+" ");
                    }
                    deque.pollLast();
                    break;
                default:
                    try
                    {
                        exit.append(Double.parseDouble(symbolCurrent)+" ");
                    }
                    catch(NumberFormatException e)//TODO правильно ли так делать?
                    {
                        throw new IncorrectDataException("formula can't have "+symbolCurrent+".");
                    }
            }
        }
        while(!deque.isEmpty())
        {
            String symbol=deque.pollLast();
            exit.append(symbol+" ");
        }
        return exit.toString();
    }

    //TODO куда вынести метод
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
        ReversePolishNotationParser parser=new ReversePolishNotationParser();
        List<MathExpression> mathExpressionList=parser.parse(polishNotation);
        Context context=new Context();
        mathExpressionList.forEach((mathExpression)->mathExpression.interpret(context));
        return String.valueOf(context.pop());
    }
}