package com.archiiro.app.Core.Other;

import com.archiiro.app.Core.Domain.Ethnics;
import com.archiiro.app.Core.Dto.CountryDto;
import com.archiiro.app.Core.Dto.EthnicsDto;
import com.archiiro.app.Core.Dto.Function.AdministrativeUnitImportExcel;
import com.archiiro.app.Core.Dto.ReligionDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUploadUtils {
    // Import Administrative
    public static List<AdministrativeUnitImportExcel> getDataAdministrativeUnit(InputStream is) throws Exception {
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
    // Import Ethnics
    public static List<EthnicsDto> getDataEthnics(InputStream is) throws  Exception {
        List<EthnicsDto> listData = new ArrayList<EthnicsDto>();
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            int rowIndex = 1;
            int number = datatypeSheet.getLastRowNum();
            while (rowIndex < number) {
                Row currentRow = datatypeSheet.getRow(rowIndex);
                Cell currentCell = null;
                if(currentRow != null) {
                    EthnicsDto dto = new EthnicsDto();
                    // Code
                    Integer index = 0;
                    currentCell = currentRow.getCell(index);
                    if(currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String code = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setCode(code);
                    } else if(currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getStringCellValue() != null) {
                        String code = currentCell.getStringCellValue().trim();
                        dto.setCode(code);
                    }

                    // Name
                    index = 1;
                    currentCell = currentRow.getCell(index);
                    if(currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String name = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setName(name);
                    } else if(currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getStringCellValue() != null) {
                        String name = currentCell.getStringCellValue().trim();
                        dto.setName(name);
                    }

                    // Description
                    index = 2;
                    currentCell = currentRow.getCell(index);
                    if(currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String description = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setDescription(description);
                    } else if(currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getStringCellValue() != null) {
                        String description = currentCell.getStringCellValue().trim();
                        dto.setDescription(description);
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

    // Import Country
    public static List<CountryDto> getDataCountry(InputStream is) throws  Exception {
        List<CountryDto> listData = new ArrayList<CountryDto>();
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            int rowIndex = 1;
            int number = datatypeSheet.getLastRowNum();
            while (rowIndex < number) {
                Row currentRow = datatypeSheet.getRow(rowIndex);
                Cell currentCell = null;
                if(currentRow != null) {
                    CountryDto dto = new CountryDto();
                    // Code
                    Integer index = 0;
                    currentCell = currentRow.getCell(index);
                    if(currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String code = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setCode(code);
                    } else if(currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getStringCellValue() != null) {
                        String code = currentCell.getStringCellValue().trim();
                        dto.setCode(code);
                    }

                    // Name
                    index = 1;
                    currentCell = currentRow.getCell(index);
                    if(currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String name = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setName(name);
                    } else if(currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getStringCellValue() != null) {
                        String name = currentCell.getStringCellValue().trim();
                        dto.setName(name);
                    }

                    // Description
                    index = 2;
                    currentCell = currentRow.getCell(index);
                    if(currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String description = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setDescription(description);
                    } else if(currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getStringCellValue() != null) {
                        String description = currentCell.getStringCellValue().trim();
                        dto.setDescription(description);
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

    // Import Religion
    public static List<ReligionDto> getDataReligion(InputStream is) throws  Exception {
        List<ReligionDto> listData = new ArrayList<ReligionDto>();
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            int rowIndex = 1;
            int number = datatypeSheet.getLastRowNum();
            while (rowIndex < number) {
                Row currentRow = datatypeSheet.getRow(rowIndex);
                Cell currentCell = null;
                if(currentRow != null) {
                    ReligionDto dto = new ReligionDto();
                    // Code
                    Integer index = 0;
                    currentCell = currentRow.getCell(index);
                    if(currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String code = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setCode(code);
                    } else if(currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getStringCellValue() != null) {
                        String code = currentCell.getStringCellValue().trim();
                        dto.setCode(code);
                    }

                    // Name
                    index = 1;
                    currentCell = currentRow.getCell(index);
                    if(currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String name = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setName(name);
                    } else if(currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getStringCellValue() != null) {
                        String name = currentCell.getStringCellValue().trim();
                        dto.setName(name);
                    }

                    // Description
                    index = 2;
                    currentCell = currentRow.getCell(index);
                    if(currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String description = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setDescription(description);
                    } else if(currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getStringCellValue() != null) {
                        String description = currentCell.getStringCellValue().trim();
                        dto.setDescription(description);
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
