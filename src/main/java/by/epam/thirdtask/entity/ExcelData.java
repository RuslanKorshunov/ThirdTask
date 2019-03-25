package by.epam.thirdtask.entity;

public class ExcelData //TODO спросить про имя и куда вынести
{
    private String data;
    private String parentData;
    private int firstColumn;
    private int lastColumn;

    public ExcelData(String data, String parentData, int firstColumn, int lastColumn)
    {
        this.data = data;
        this.parentData = parentData;
        this.firstColumn=firstColumn;
        this.lastColumn=lastColumn;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getParentData()
    {
        return parentData;
    }

    public void setParentData(String parentData)
    {
        this.parentData = parentData;
    }

    public int getFirstColumn()
    {
        return firstColumn;
    }

    public void setFirstColumn(int firstColumn)
    {
        this.firstColumn = firstColumn;
    }

    public int getLastColumn()
    {
        return lastColumn;
    }

    public void setLastColumn(int lastColumn)
    {
        this.lastColumn = lastColumn;
    }
}
