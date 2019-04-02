package by.epam.thirdtask.writer;

import by.epam.thirdtask.composite.Component;
import by.epam.thirdtask.composite.Composite;
import by.epam.thirdtask.entity.CellData;
import by.epam.thirdtask.exception.WorkWithFileException;
import by.epam.thirdtask.searcher.SheetSearcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

public class ExcelWriter
{
    private static final Logger logger=LogManager.getLogger(ExcelWriter.class);
    private static final String filePath="src/main/resources/heroesResult.xlsx";

    public void write(Composite composite) throws WorkWithFileException
    {
        int sheetNumber=0;
        XSSFSheet sheet= SheetSearcher.search(filePath, sheetNumber);
        XSSFWorkbook workbook=sheet.getWorkbook();

        for(int index=0; index<composite.size(); index++)
        {
            Component component=composite.get(index);
            writeComponent(component, sheet);
        }

        File file=new File(filePath);
        try
        {
            FileOutputStream fileOutputStream=new FileOutputStream(file);

            workbook.write(fileOutputStream);
            workbook.close();
        }
        catch(FileNotFoundException e)
        {
            logger.warn("File "+filePath+" wasn't found.");
        }
        catch(IOException e)
        {
            logger.warn("workbook wasn't closed.");
        }

    }

    private void writeComponent(Component componentMain, Sheet sheet)
    {

























        CellData cellData=componentMain.getCellData();
        int firstRow=cellData.getFirstRow();
        int lastRow=cellData.getLastRow();
        int firstColumn=cellData.getFirstColumn();
        int lastColumn=cellData.getLastColumn();
        Optional<Row> optionalRow=Optional.ofNullable(sheet.getRow(firstRow));
        Row row=optionalRow.isPresent()? sheet.getRow(firstRow) : sheet.createRow(firstRow);
        Optional<Cell> optionalCell=Optional.ofNullable(row.getCell(firstColumn));
        Cell cell=optionalCell.isPresent()? row.getCell(firstColumn) : row.createCell(firstColumn);
        cell.setCellValue(cellData.getData());
        if(firstRow!=lastRow || firstColumn!=lastColumn)
        {
            CellRangeAddress region=new CellRangeAddress(firstRow, lastRow, firstColumn, lastColumn);
            sheet.addMergedRegion(region);
        }
        for(int index=0; index<componentMain.size(); index++)
        {
            writeComponent(componentMain.get(index), sheet);
        }
    }
}