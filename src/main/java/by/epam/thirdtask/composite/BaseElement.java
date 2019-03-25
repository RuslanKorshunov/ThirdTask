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
        System.out.println(name);
    }

    @Override
    public String getName() {
        return name;
    }
}
