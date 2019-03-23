package by.epam.thirdtask.composite;

public abstract class Composite
{
    public void operation()
    {
        throw new UnsupportedOperationException(getClass().getName()+" doesn't support this operation");
    }

    public boolean add(Composite composite)
    {
        throw new UnsupportedOperationException(getClass().getName()+" doesn't support this operation");
    }

    public boolean remove(Composite composite)
    {
        throw new UnsupportedOperationException(getClass().getName()+" doesn't support this operation");
    }

    public Composite get(int index)
    {
        throw new UnsupportedOperationException(getClass().getName()+" doesn't support this operation");
    }

    public String getName()
    {
        throw new UnsupportedOperationException(getClass().getName()+" doesn't support this operation");
    }
}
