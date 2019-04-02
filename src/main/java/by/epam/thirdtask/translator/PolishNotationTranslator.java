package by.epam.thirdtask.translator;

import by.epam.thirdtask.exception.IncorrectDataException;
import by.epam.thirdtask.interpreter.InterpreterConstant;

import java.util.ArrayDeque;
import java.util.Deque;

public class PolishNotationTranslator
{
    public String translate(String formula) throws IncorrectDataException
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
                            String symbol=deque.pollLast();
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
                    catch(NumberFormatException e)
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
}