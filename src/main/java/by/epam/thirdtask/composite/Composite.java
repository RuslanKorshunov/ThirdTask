package by.epam.thirdtask.composite;

import by.epam.thirdtask.action.PolishNotationAction;
import by.epam.thirdtask.entity.CellData;
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

    private CellData cellData;
    private List<Component> components =new ArrayList<>();

    public Composite()
    {
        cellData =new CellData("",
                                "",
                                -1,
                                -1,
                                -1,
                                -1,
                                CellType._NONE);
    }

    public Composite(CellData cellData)
    {
        this.cellData = cellData;
    }

    public void operation()
    {
        final int index=0;
        Component component=components.get(index);
        if(component.getClass()==BaseElement.class)
        {
            components.remove(component);
            for(Component componentCurrent: components)
            {
                CellData cellData=componentCurrent.getCellData();
                int firstRow=cellData.getFirstRow()-1;
                int lastRow=cellData.getLastRow()-1;
                cellData.setFirstRow(firstRow);
                cellData.setLastRow(lastRow);
            }
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
    public boolean addNewComponent(CellData cell)
    {
        boolean result=false;
        String dataParent= cell.getParentData();
        String data=cell.getData();
        if(getName().equals(dataParent) && !data.equals(dataParent))
        {
            Component component=new Composite(cell);
            result=components.add(component);
        }
        else
        {
            for(Component component: components)
            {
                if(!result)
                {
                    result=component.addNewComponent(cell);
                }
            }
        }
        return result;
    }

    @Override
    public boolean addNewBaseElement(CellData cell)
    {
        boolean result=false;
        String dataParent= cell.getParentData();
        String data= cell.getData();
        if(getName().equals(dataParent) && !data.equals(dataParent))
        {
            CellType cellType=cell.getCellType();
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
            cell.setData(data);
            Component baseElement=new BaseElement(cell);
            result=components.add(baseElement);
        }
        else
        {
            for(Component component: components)
            {
                if(!result)
                {
                    result=component.addNewBaseElement(cell);
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
        return cellData.getData();
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
        StringBuilder result=new StringBuilder(getName()+"\n");
        components.forEach(component -> result.append(component.toString()));
        return result.toString();
    }

    @Override
    public CellData getCellData()
    {
        return cellData;
    }
}