package com.archiiro.app.Core.Other;

import com.archiiro.app.Core.Dto.Function.AdministrativeUnitImportExcel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUploadUtils {
    public static List<AdministrativeUnitImportExcel> getDataFromExcel(InputStream is) throws Exception {
        List<AdministrativeUnitImportExcel> listData = new ArrayList<AdministrativeUnitImportExcel>();
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            int rowIndex = 1;
            // Lấy dòng cuối cùng trong 1 sheet
            int number = datatypeSheet.getLastRowNum();
            while (rowIndex < number) {
                // Đọc file từ dòng 1 đến dòng cuối cùng của sheet
                // Lấy dòng tương ứng với mỗi lần lặp
                Row currentRow = datatypeSheet.getRow(rowIndex);
                Cell currentCell = null;
                if(currentRow != null) {
                    AdministrativeUnitImportExcel dto = new AdministrativeUnitImportExcel();

                    // Tên tỉnh
                    Integer index = 0;
                    currentCell = currentRow.getCell(index); // Lấy giá trị của ô đầu tiên trong dòng tương ứng với vòng lặp
                    if(currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String nameProvince =  String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setNameProvince(nameProvince);
                    } else if(currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getStringCellValue() != null) {
                        String nameProvince = currentCell.getStringCellValue().trim();
                        dto.setNameProvince(nameProvince);
                    }

                    // Mã tỉnh
                    index = 1;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String codeProvince = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setCodeProvince(codeProvince);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String codeProvince = currentCell.getStringCellValue().trim();
                        dto.setCodeProvince(codeProvince);
                    }

                    // Tên quận huyện
                    index = 2;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String nameDistrict = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setNameDistrict(nameDistrict);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String nameDistrict = currentCell.getStringCellValue().trim();
                        dto.setNameDistrict(nameDistrict);
                    }

                    // Mã quận huyện
                    index = 3;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String codeDistrict = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setCodeDistrict(codeDistrict);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String codeDistrict = currentCell.getStringCellValue().trim();
                        dto.setCodeDistrict(codeDistrict);
                    }

                    // Tên phường, xã
                    index = 4;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String nameCommune = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setNameCommune(nameCommune);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String nameCommune = currentCell.getStringCellValue().trim();
                        dto.setNameCommune(nameCommune);
                    }

                    // Code phường, xã
                    index = 5;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String codeCommune = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setCodeCommune(codeCommune);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String codeCommune = currentCell.getStringCellValue().trim();
                        dto.setCodeCommune(codeCommune);
                    }
                    listData.add(dto);
                }
                rowIndex++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listData;
    }
}
