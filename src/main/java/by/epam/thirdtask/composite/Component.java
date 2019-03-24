package by.epam.thirdtask.composite;

import by.epam.thirdtask.reader.ExcelCell;

import java.util.ArrayList;
import java.util.List;

public class Component extends Composite
{
    private String name;
    private List<Composite> components =new ArrayList<>();

    public Component()
    {
        name="";
    }

    public Component(String name)
    {
        this.name=name;
    }

    public void operation()
    {
        System.out.println(name);
        components.forEach(Composite::operation);
    }

    @Override
    public boolean addNewComponent(Composite composite)
    {

        return components.add(composite);
    }

    @Override
    public boolean addNewComponent(ExcelCell excelCell)
    {
        boolean result=false;
        String dataParent=excelCell.getParentData();
        String data=excelCell.getData();
        if(name.equals(dataParent) && !data.equals(dataParent))
        {
            Composite component=new Component(data);
            result=components.add(component);
        }
        else
        {
            for(Composite component: components)
            {
                result=component.addNewComponent(excelCell);
                //TODO спросить про использование прерываний
            }
        }
        return result;
    }

    @Override
    public boolean addNewBaseElement(ExcelCell excelCell)
    {
        boolean result=false;
        String dataParent=excelCell.getParentData();
        String data=excelCell.getData();
        if(name.equals(dataParent) && !data.equals(dataParent))
        {
            Composite baseElement=new BaseElement(data);
            result=components.add(baseElement);
        }
        else
        {
            for(Composite component: components)
            {
                result=component.addNewBaseElement(excelCell);
                if(result)
                {
                    break;
                }
                //TODO спросить про использование прерываний
            }
        }
        return result;
    }

    @Override
    public boolean remove(Composite composite)
    {
        return components.remove(composite);
    }

    @Override
    public Composite get(int index)
    {
        return components.get(index);
    }

    @Override
    public String getName()
    {
        return name;
    }
}
