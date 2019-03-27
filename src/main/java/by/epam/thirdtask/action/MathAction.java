package by.epam.thirdtask.action;

import by.epam.thirdtask.exception.IncorrectDataException;

import java.util.ArrayDeque;
import java.util.Deque;

public class MathAction
{
    private static final String SPACE="\\s";
    private static final String MINUS="-";
    private static final String PLUS="+";
    private static final String MULTIPLY="*";
    private static final String DIVIDE="/";
    private static final String LEFT_BRACKET="(";
    private static final String RIGHT_BRACKET=")";

    public String calculateReversePolishNotation(String formula) throws IncorrectDataException
    {
        StringBuffer exit=new StringBuffer();
        Deque<String> deque=new ArrayDeque<>();
        for(String symbolCurrent: formula.split(SPACE))
        {
            switch (symbolCurrent)
            {
                case PLUS:
                case MINUS:
                    if(!deque.isEmpty())
                    {
                        if(!deque.peekLast().equals(LEFT_BRACKET))
                        {
                            String symbol=deque.pollLast();
                            exit.append(symbol+" ");
                        }
                    }
                    deque.offerLast(symbolCurrent);
                    break;
                case DIVIDE:
                case MULTIPLY:
                    if(!deque.isEmpty())
                    {
                        if(!deque.peekLast().equals(LEFT_BRACKET) &&
                                !deque.peekLast().equals(MINUS) &&
                                !deque.peekLast().equals(PLUS))
                        {
                            String symbol=deque.pollLast();
                            exit.append(symbol+" ");
                        }
                    }
                    deque.offerLast(symbolCurrent);
                    break;
                case LEFT_BRACKET:
                    deque.offerLast(symbolCurrent);
                    break;
                case RIGHT_BRACKET:
                    while(!deque.peekLast().equals(LEFT_BRACKET))
                    {
                        String symbol=deque.pollLast();
                        exit.append(symbol+" ");
                    }
                    deque.pollLast();
                    break;
                default:
                    try
                    {
                        Integer.parseInt(symbolCurrent);
                        exit.append(symbolCurrent+" ");
                    }
                    catch(NumberFormatException e)
                    {
                        throw new IncorrectDataException("formula doesn't have "+symbolCurrent);
                    }
                    break;
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