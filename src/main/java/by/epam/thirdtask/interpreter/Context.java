package by.epam.thirdtask.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public class Context
{
    private Deque<Double> deque=new ArrayDeque<>();

    public Double pop()
    {
        return deque.pop();
    }

    public void push(Double symbol)
    {
        deque.push(symbol);
    }
}
