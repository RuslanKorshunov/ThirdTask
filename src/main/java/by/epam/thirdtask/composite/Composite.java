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

    public void operation()//TODO спросить об этом "шедевре"
    {
        for(Component component: components)
        {
            if(component.getClass()==BaseElement.class)
            {
                if(components.indexOf(component)==0)
                {
                    Component baseComponent=new BaseElement("PLATINUM");
                    components.add(baseComponent);
                    break;//TODO так можно делать?
                }
                else
                {
                    Component baseComponent=new BaseElement("1000");
                    components.add(baseComponent);
                    break;//TODO так можно делать?
                }
            }
            component.operation();
        }
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
            CellType cellType=excelData.getCellType();
            if(cellType.equals(CellType.FORMULA))
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
    public int size()
    {
        return components.size();
    }

    @Override
    public int findHightOfTableHeader()
    {
        return super.findHightOfTableHeader();
    }

    @Override
    public int findHight()
    {
        int hight=0;
        hight=findHightOfComponent(hight, components);
        return hight;
    }

    private int findHightOfComponent(int hight, List<Component> components)
    {
        int result=hight;
        if(components!=null)
        {
            if(components.get(0).getClass()==BaseElement.class)
            {
                result+=components.size();
            }
            else
            {
                hight++;
                result=hight;
                for(Component component: components)
                {
                    int hightCurrent=findHightOfComponent(hight, component.getComponents());
                    if(hightCurrent>result)
                    {
                        result=hightCurrent;
                    }
                }
            }
        }
        return result;
    }

    @Override
    protected List<Component> getComponents()
    {
        return components;
    }

    @Override
    public String toString()
    {
        /*StringBuilder result=new StringBuilder(name+"\n");
        components.forEach(component -> result.append(component.toString()));
        return result.toString();*/
        return name;
    }
}