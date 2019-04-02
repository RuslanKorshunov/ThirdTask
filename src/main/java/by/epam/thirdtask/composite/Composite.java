package by.epam.thirdtask.composite;

import by.epam.thirdtask.action.PolishNotationAction;
import by.epam.thirdtask.entity.ExcelCell;
import by.epam.thirdtask.exception.IncorrectDataException;
import by.epam.thirdtask.translator.PolishNotationTranslator;
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
        final int index=0;
        Component component=components.get(index);
        if(component.getClass()==BaseElement.class)
        {
            components.remove(component);
        }
        else
        {
            components.forEach(Component::operation);
        }
    }

    @Override
    public boolean addNewComponent(Component component)
    {
        return components.add(component);
    }

    @Override
    public boolean addNewComponent(ExcelCell excelCell)
    {
        boolean result=false;
        String dataParent= excelCell.getParentData();
        String data= excelCell.getData();
        if(name.equals(dataParent) && !data.equals(dataParent))
        {
            Component component=new Composite(data);
            result=components.add(component);
        }
        else
        {
            for(Component component: components)
            {
                if(!result)
                {
                    result=component.addNewComponent(excelCell);
                }
            }
        }
        return result;
    }

    @Override
    public boolean addNewBaseElement(ExcelCell excelCell)
    {
        boolean result=false;
        String dataParent= excelCell.getParentData();
        String data= excelCell.getData();
        if(name.equals(dataParent) && !data.equals(dataParent))
        {
            CellType cellType= excelCell.getCellType();
            if(cellType.equals(CellType.FORMULA))
            {
                try
                {
                    PolishNotationTranslator polishNotationTranslator =new PolishNotationTranslator();
                    PolishNotationAction polishNotationAction=new PolishNotationAction();
                    String polishNotation= polishNotationTranslator.translate(data);
                    data=polishNotationAction.calculate(polishNotation);
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
            for(Component component: components)
            {
                if(!result)
                {
                    result=component.addNewBaseElement(excelCell);
                }
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

    @Override
    public int size()
    {
        return components.size();
    }

    @Override
    protected List<Component> getComponents()
    {
        return components;
    }

    @Override
    public String toString()
    {
        StringBuilder result=new StringBuilder(name+"\n");
        components.forEach(component -> result.append(component.toString()));
        return result.toString();
    }


    //TODO нужно удалить
    @Override
    public int findHightOfTableHeader()
    {
        int height=0;
        height=findHeightWithoutBaseElements(height, components);
        return height;
    }

    //TODO нужно удалить
    @Override
    public int findHight()
    {
        int hight=0;
        hight=findHightOfComponent(hight, components);
        return hight;
    }

    //TODO возможно, нужно удалить
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

    //TODO возможно, нужно удалить
    private int findHeightWithoutBaseElements(int hight, List<Component> components)
    {
        int result=hight;
        if(components!=null && components.get(0).getClass()!=BaseElement.class)
        {
            hight++;
            result=hight;
            for(Component component: components)
            {
                int hightCurrent=findHeightWithoutBaseElements(hight, component.getComponents());
                if(hightCurrent>result)
                {
                    result=hightCurrent;
                }
            }
        }
        return result;
    }
}