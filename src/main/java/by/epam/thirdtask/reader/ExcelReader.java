package by.epam.thirdtask.reader;

import by.epam.thirdtask.comparator.ExcelCellComparator;
import by.epam.thirdtask.entity.ExcelData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class ExcelReader
{
    private static final String filePath="src/main/resources/heroes.xlsx";//TODO нужно ли это выносить в отдельное поле

    public List<ExcelData> read(int rowNumber) throws IOException//TODO пробрасываание своего исключения
    {
        XSSFSheet sheet=findSheet();
        List<ExcelData> cells=findCells(sheet, rowNumber);
        if(rowNumber!=0)
        {
            ExcelCellComparator comparator=new ExcelCellComparator();
            List<ExcelData> cellsPrevious=findCells(sheet, rowNumber-1);
            for(ExcelData cell : cells)
            {
                for(ExcelData cellPrevious : cellsPrevious)
                {
                    if(comparator.compare(cell, cellPrevious)==0)
                    {
                        cell.setParentData(cellPrevious.getData());
                    }
                }
            }
        }
        return cells;
    }

    public boolean hasMergedRegions(int rowNumber) throws IOException
    {
        boolean answer=false;
        XSSFSheet sheet=findSheet();
        if(findMergedRegions(sheet, rowNumber).size()>0)
        {
            answer=true;
        }
        return answer;
    }

    public boolean hasNextRow(int currentRowNumber) throws IOException
    {
        XSSFSheet sheet=findSheet();
        Optional<XSSFRow> rowOptional=Optional.ofNullable(sheet.getRow(currentRowNumber+1));
        return rowOptional.isPresent();
    }

    private List<CellRangeAddress> findMergedRegions(XSSFSheet sheet, int rowNumber)
    {
        List<CellRangeAddress> regions=new ArrayList<>();
        for(int i=0; i<sheet.getNumMergedRegions(); i++)
        {
            CellRangeAddress region=sheet.getMergedRegion(i);
            if(region.containsRow(rowNumber))
            {
                regions.add(region);
            }
        }
        return regions;
    }

    private List<ExcelData> findCells(XSSFSheet sheet, int rowNumber)
    {
        List<ExcelData> result=new ArrayList<>();
        XSSFRow row=sheet.getRow(rowNumber);
        Iterator<Cell> iterator=row.iterator();
        List<CellRangeAddress> regions=findMergedRegions(sheet, rowNumber);
        while(iterator.hasNext())
        {
            Cell cell=iterator.next();
            String value=findName(cell);
            ExcelData data=new ExcelData("", "", cell.getColumnIndex(), cell.getColumnIndex());
            for(CellRangeAddress region: regions)
            {
                if(region.isInRange(cell))
                {
                    Cell cellWithName=row.getCell(cell.getColumnIndex());
                    if(cell.getRowIndex()!=region.getFirstRow())
                    {
                        XSSFRow rowWithName=sheet.getRow(region.getFirstRow());
                        cellWithName=rowWithName.getCell(cell.getColumnIndex());
                    }
                    value=findName(cellWithName);
                    while (cell.getColumnIndex()<region.getLastColumn())
                    {
                        cell=iterator.next();
                    }
                    data.setLastColumn(region.getLastColumn());
                }
            }
            data.setData(value);
            result.add(data);
        }
        return result;
    }

    private String findName(Cell cell)
    {
        String name="";
        switch (cell.getCellType())
        {
            case STRING:
                name=cell.getStringCellValue();
                break;
            case NUMERIC:
                name=String.valueOf(cell.getNumericCellValue());
                break;
            case FORMULA:
                name=cell.getCellFormula();
                break;
            case BLANK:
                name=cell.getStringCellValue();
                break;
            case ERROR:
                name=String.valueOf(cell.getErrorCellValue());
                break;
            case BOOLEAN:
                name=String.valueOf(cell.getBooleanCellValue());
                break;
            case _NONE:
                name=cell.getStringCellValue();
                break;
        }
        return name;
    }

    private XSSFSheet findSheet() throws IOException
    {
        final int numberOfSheet=0;
        File file=new File(filePath);
        FileInputStream inputStream=new FileInputStream(file);
        XSSFWorkbook workbook=new XSSFWorkbook(inputStream);
        return workbook.getSheetAt(numberOfSheet);
    }
}
