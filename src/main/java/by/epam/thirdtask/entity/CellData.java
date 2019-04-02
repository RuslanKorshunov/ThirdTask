package by.epam.thirdtask.entity;

import org.apache.poi.ss.usermodel.CellType;

public class CellData
{
    private String data;
    private String parentData;
    private int firstRow;
    private int lastRow;
    private int firstColumn;
    private int lastColumn;
    private CellType cellType;

    public CellData(String data,
                    String parentData,
                    int firstRow,
                    int lastRow,
                    int firstColumn,
                    int lastColumn,
                    CellType cellType)
    {
        this.data = data;
        this.parentData = parentData;
        this.firstRow=firstRow;
        this.lastRow=lastRow;
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

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getLastRow() {
        return lastRow;
    }

    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }

    @Override
    public String toString() {
        return "CellData{" +
                "data='" + data + '\'' +
                ", parentData='" + parentData + '\'' +
                ", firstRow=" + firstRow +
                ", lastRow=" + lastRow +
                ", firstColumn=" + firstColumn +
                ", lastColumn=" + lastColumn +
                ", cellType=" + cellType +
                '}';
    }
}
