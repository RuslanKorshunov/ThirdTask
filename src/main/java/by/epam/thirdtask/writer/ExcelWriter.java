package by.epam.thirdtask.writer;

import by.epam.thirdtask.composite.BaseElement;
import by.epam.thirdtask.composite.Component;
import by.epam.thirdtask.composite.Composite;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

public class ExcelWriter
{
    private static final String filePath="src/main/resources/heroes.xlsx";

    public void write(Composite composite) throws IOException
    {
        final int NUMBER_OF_SHEET=1;
        final String NAME_OF_SHEET="result";

        File file=new File(filePath);
        FileInputStream fileInputStream=new FileInputStream(file);
        XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);

        Optional<XSSFSheet> optionalSheet=Optional.ofNullable(workbook.getSheet(NAME_OF_SHEET));
        XSSFSheet sheet=optionalSheet.isPresent()? workbook.getSheet(NAME_OF_SHEET): workbook.createSheet(NAME_OF_SHEET);

        for(int i=0; i<composite.findHight(); i++)
        {
            Row row=sheet.createRow(i);
        }

        /*for(int i=0; i<composite.size(); i++)
        {
            *//*Row row=sheet.getRow(0);
            Cell cell=row.createCell(i);
            cell.setCellValue(composite.get(i).getName());*//*
            writeComponent(composite, sheet, composite.findHightOfTableHeader(), 0);
        }*/

        writeComponent(composite.get(1), sheet, composite.findHightOfTableHeader()-1, 0, 0);

        FileOutputStream fileOutputStream=new FileOutputStream(file);
        workbook.write(fileOutputStream);

        workbook.close();
    }

    private void writeComponent(Component componentMain,
                                Sheet sheet,
                                final int heightOfTableHeader,
                                int rowNumberMain,
                                int cellNumberMain)
    {
        Row row=sheet.getRow(rowNumberMain);
        Cell cell=row.createCell(cellNumberMain);
        cell.setCellValue(componentMain.getName());
        if(componentMain.getClass()!=BaseElement.class)
        {
            int firstRow=rowNumberMain;
            int lastRow=componentMain.get(0).getClass()==BaseElement.class?
                        heightOfTableHeader : rowNumberMain;
            int firstColumn=cellNumberMain;
            /*int lastColumn=componentMain.get(0).getClass()==BaseElement.class?
                        cellNumberMain : cellNumberMain+componentMain.size()-1;*/
            int lastColumn=cellNumberMain+method(componentMain, 0)-1;
            if(firstRow!=lastRow || firstColumn!=lastColumn)
            {
                CellRangeAddress region=new CellRangeAddress(firstRow, lastRow, firstColumn, lastColumn);
                sheet.addMergedRegion(region);
            }
            int rowNumber=0;
            int cellNumber=0;
            for(int index=0; index<componentMain.size(); index++)
            {
                Component component=componentMain.get(index);
                rowNumber+=componentMain.get(0).getClass()==BaseElement.class?
                        lastRow+1+index : lastRow+1;
                cellNumber+=componentMain.get(0).getClass()==BaseElement.class?
                        firstColumn : firstColumn+index;
                writeComponent(component, sheet, heightOfTableHeader, rowNumber, cellNumber);
                if(component.getClass()!=BaseElement.class)
                {
                    cellNumber+=method(component, 0)-1;
                }
            }
        }
    }

    private int method(Component component, int children)
    {
        int result=children;
        if(component.size()==0 || component.get(0).getClass()==BaseElement.class)
        {
            children++;
        }
        else
        {
            for(int index=0; index<component.size(); index++)
            {
                children=method(component.get(index), children);
            }
        }
        return children;
    }
}