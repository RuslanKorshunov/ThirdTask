package by.epam.thirdtask.composite;

public class BaseElement extends Component
{
    private String name;

    public BaseElement(String name)
    {
        this.name=name;
    }

    @Override
    public void operation()
    {

        //System.out.println(name);
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        StringBuilder result=new StringBuilder(name+"\n");
        return result.toString();
    }
}
