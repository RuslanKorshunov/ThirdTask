package by.epam.thirdtask.reader;

import by.epam.thirdtask.comparator.ColumnComparator;
import by.epam.thirdtask.entity.CellData;
import by.epam.thirdtask.exception.WorkWithFileException;
import by.epam.thirdtask.searcher.SheetSearcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class ExcelReader
{
    private static final Logger logger= LogManager.getLogger(ExcelReader.class);
    private static final String filePath="src/main/resources/heroes.xlsx";

    public List<CellData> read(int rowNumber) throws WorkWithFileException
    {
        int sheetNumber=0;
        XSSFSheet sheet=SheetSearcher.search(filePath, sheetNumber);
        List<CellData> cells=findCells(sheet, rowNumber);
        if(rowNumber!=0)
        {
            ColumnComparator comparator=new ColumnComparator();
            List<CellData> cellsPrevious=findCells(sheet, rowNumber-1);
            for(CellData cell : cells)
            {
                for(CellData cellPrevious : cellsPrevious)
                {
                    if(comparator.compare(cell, cellPrevious)==0)
                    {
                        cell.setParentData(cellPrevious.getData());
                    }
                }
            }
        }

        XSSFWorkbook workbook=sheet.getWorkbook();
        try
        {
            workbook.close();
        }
        catch (IOException e)
        {
            logger.warn("workbook wasn't closed.");
        }
        return cells;
    }

    public boolean hasMergedRegions(int rowNumber) throws WorkWithFileException
    {
        boolean answer=false;
        int sheetNumber=0;
        XSSFSheet sheet=SheetSearcher.search(filePath, sheetNumber);
        if(findMergedRegions(sheet, rowNumber).size()>0)
        {
            answer=true;
        }
        return answer;
    }

    public boolean hasNextRow(int currentRowNumber) throws WorkWithFileException
    {
        int sheetNumber=0;
        XSSFSheet sheet=SheetSearcher.search(filePath, sheetNumber);
        Optional<XSSFRow> rowOptional=Optional.ofNullable(sheet.getRow(currentRowNumber+1));
        return rowOptional.isPresent();
    }

    private List<CellRangeAddress> findMergedRegions(XSSFSheet sheet, int rowNumber)
    {
        List<CellRangeAddress> regions=new ArrayList<>();
        for(int index=0; index<sheet.getNumMergedRegions(); index++)
        {
            CellRangeAddress region=sheet.getMergedRegion(index);
            if(region.containsRow(rowNumber))
            {
                regions.add(region);
            }
        }
        return regions;
    }

    private List<CellData> findCells(XSSFSheet sheet, int rowNumber)
    {
        List<CellData> result=new ArrayList<>();
        XSSFRow row=sheet.getRow(rowNumber);
        Iterator<Cell> iterator=row.iterator();
        List<CellRangeAddress> regions=findMergedRegions(sheet, rowNumber);
        while(iterator.hasNext())
        {
            Cell cell=iterator.next();
            String value=findName(cell);
            CellData data=new CellData("",
                    "",
                    cell.getRowIndex(),
                    cell.getRowIndex(),
                    cell.getColumnIndex(),
                    cell.getColumnIndex(),
                    cell.getCellType());
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
                    data.setLastRow(region.getLastRow());
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
}
