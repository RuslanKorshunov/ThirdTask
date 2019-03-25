package by.epam.thirdtask.composite;

import by.epam.thirdtask.entity.ExcelData;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component
{
    private String name;
    private List<Component> components =new ArrayList<>();

    public Composite()
    {
        name="";
    }

    public Composite(String name)
    {
        this.name=name;
    }

    public void operation()
    {
        System.out.println(name);
        components.forEach(Component::operation);
    }

    @Override
    public boolean addNewComponent(Component component)
    {
        return components.add(component);
    }

    @Override
    public boolean addNewComponent(ExcelData excelData)
    {
        boolean result=false;
        String dataParent= excelData.getParentData();
        String data= excelData.getData();
        if(name.equals(dataParent) && !data.equals(dataParent))
        {
            Component component=new Composite(data);
            result=components.add(component);
        }
        else
        {
            for(Component component: components)
            {
                result=component.addNewComponent(excelData);
                //TODO спросить про использование прерываний
            }
        }
        return result;
    }

    @Override
    public boolean addNewBaseElement(ExcelData excelData)
    {
        boolean result=false;
        String dataParent= excelData.getParentData();
        String data= excelData.getData();
        if(name.equals(dataParent) && !data.equals(dataParent))
        {
            Component baseElement=new BaseElement(data);
            result=components.add(baseElement);
        }
        else
        {
            for(Component component: components)
            {
                result=component.addNewBaseElement(excelData);
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
    public boolean remove(Component component)
    {
        return components.remove(component);
    }

    @Override
    public Component get(int index)
    {
        return components.get(index);
    }

    @Override
    public String getName()
    {
        return name;
    }
}
