package by.epam.thirdtask.entity;

import org.apache.poi.ss.usermodel.CellType;

public class ExcelData //TODO спросить про имя и куда вынести
{
    private String data;
    private String parentData;
    private int firstColumn;
    private int lastColumn;
    private CellType cellType;

    public ExcelData(String data, String parentData, int firstColumn, int lastColumn, CellType cellType)
    {
        this.data = data;
        this.parentData = parentData;
        this.firstColumn=firstColumn;
        this.lastColumn=lastColumn;
        this.cellType=cellType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getParentData() {
        return parentData;
    }

    public void setParentData(String parentData) {
        this.parentData = parentData;
    }

    public int getFirstColumn() {
        return firstColumn;
    }

    public void setFirstColumn(int firstColumn) {
        this.firstColumn = firstColumn;
    }

    public int getLastColumn() {
        return lastColumn;
    }

    public void setLastColumn(int lastColumn) {
        this.lastColumn = lastColumn;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    @Override
    public String toString() {
        return "ExcelData{" +
                "data='" + data + '\'' +
                ", parentData='" + parentData + '\'' +
                ", firstColumn=" + firstColumn +
                ", lastColumn=" + lastColumn +
                ", cellType=" + cellType +
                '}';
    }
}
