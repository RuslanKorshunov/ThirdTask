package by.epam.thirdtask.composite;

import by.epam.thirdtask.action.MathAction;
import by.epam.thirdtask.entity.ExcelData;
import by.epam.thirdtask.exception.IncorrectDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component
{
    private static final Logger logger= LogManager.getLogger(Composite.class);

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
        for(Component component: components)
        {
            if(component.getClass()==BaseElement.class)
            {

            }
        }
        /*System.out.println(name);
        components.forEach(Component::operation);*/
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
            components.forEach(component -> component.addNewComponent(excelData));
            /*for(Component component: components)
            {
                result=component.addNewComponent(excelData);
                //TODO спросить про использование прерываний
            }*/
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
            if(excelData.getCellType().equals(CellType.FORMULA))
            {
                try
                {
                    MathAction mathAction=new MathAction();
                    String polishNotation=mathAction.calculateReversePolishNotation(data);
                    data=mathAction.calculate(polishNotation);
                }
                catch(IncorrectDataException e)
                {
                    logger.error(e);
                    data="";
                }
            }
            Component baseElement=new BaseElement(data);
            result=components.add(baseElement);
        }
        else
        {
            components.forEach(component -> component.addNewBaseElement(excelData));
            /*for(Component component: components)
            {
                result=component.addNewBaseElement(excelData);
                if(result)
                {
                    break;
                }
                //TODO спросить про использование прерываний
            }*/
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

    @Override
    public String toString()
    {
        StringBuilder result=new StringBuilder(name+"\n");
        components.forEach(component -> result.append(component.toString()));
        return result.toString();
    }
}