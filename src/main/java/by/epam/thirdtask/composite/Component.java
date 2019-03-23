package by.epam.thirdtask.composite;

import java.util.ArrayList;
import java.util.List;

public class Component extends Composite
{
    private String name;
    private List<Composite> composites =new ArrayList<>();

    public Component(String name)
    {
        this.name=name;
    }

    public void operation()
    {
        /*for(Composite composite : composites)
        {
            composite.operation();
        }*/
        //или
        System.out.println(name);
        composites.forEach(Composite::operation);
    }

    @Override
    public boolean add(Composite composite)
    {
        return composites.add(composite);
    }

    @Override
    public boolean remove(Composite composite)
    {
        return composites.remove(composite);
    }

    @Override
    public Composite get(int index)
    {
        return composites.get(index);
    }

    @Override
    public String getName()
    {
        return name;
    }
}
