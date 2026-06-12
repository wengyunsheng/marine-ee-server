package com.marine.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;

/**
 * Excel工具类
 *
 * @author admin
 * @since 2026-06-12
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ExcelUtil {

    /**
     * 根据文件扩展名创建对应的Workbook对象
     *
     * @param file        上传的Excel文件
     * @param inputStream 文件输入流
     * @return Workbook对象，如果是其他格式则返回null
     * @throws Exception 创建Workbook失败时抛出异常
     */
    public Workbook createWorkbook(MultipartFile file, InputStream inputStream) throws Exception {
        String fileName = file.getOriginalFilename();
        if (StringUtils.isNotBlank(fileName) && fileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(inputStream);
        } else if (StringUtils.isNotBlank(fileName) && fileName.endsWith(".xls")) {
            return new HSSFWorkbook(inputStream);
        }
        return null;
    }

    /**
     * 将Excel单元格的值转换为字符串
     *
     * @param cell Excel单元格对象
     * @return 单元格值的字符串表示，如果单元格为空或转换失败则返回null
     */
    public String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                String value = cell.getStringCellValue();
                return StringUtils.isNotBlank(value) ? value.trim() : null;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toString();
                }
                // 数字转字符串，去除.0
                double num = cell.getNumericCellValue();
                if (num == (long) num) {
                    return String.valueOf((long) num);
                }
                return String.valueOf(num);
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return getCellValueAsString(cell.getCachedFormulaResultType() == CellType.NUMERIC ?
                        cell : null);
            default:
                return null;
        }
    }

    /**
     * 将Excel单元格的值转换为整数
     *
     * @param cell Excel单元格对象
     * @return 整数值，如果单元格为空或转换失败则返回null
     */
    public Integer getCellValueAsInteger(Cell cell) {
        String value = getCellValueAsString(cell);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return (int) Double.parseDouble(value);
    }

    /**
     * 将Excel单元格的值转换为BigDecimal类型
     *
     * @param cell Excel单元格对象
     * @return BigDecimal值，如果单元格为空或转换失败则返回null
     */
    public BigDecimal getCellValueAsBigDecimal(Cell cell) {
        String value = getCellValueAsString(cell);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return new BigDecimal(value);
    }
}
