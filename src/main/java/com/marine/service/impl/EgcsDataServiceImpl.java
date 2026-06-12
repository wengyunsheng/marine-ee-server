package com.marine.service.impl;

import com.marine.entity.DenitrificationInfo;
import com.marine.entity.DesulfurizationInfo;
import com.marine.mapper.DenitrificationInfoMapper;
import com.marine.mapper.DesulfurizationInfoMapper;
import com.marine.service.EgcsDataService;
import com.marine.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class EgcsDataServiceImpl implements EgcsDataService {

    private final DesulfurizationInfoMapper desulfurizationInfoMapper;

    private final DenitrificationInfoMapper denitrificationInfoMapper;

    private final ExcelUtil excelUtil;

    @Override
    public void importEgcsFromExcel(Long deviceId, MultipartFile file) throws IOException {
        Workbook workbook = excelUtil.createWorkbook(file, file.getInputStream());
        int desulfurizationCount = 0;
        int denitrificationCount = 0;

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            String sheetName = sheet.getSheetName().trim();

            if ("脱硫".equals(sheetName)) {
                desulfurizationCount = importDesulfurizationSheet(deviceId, sheet);
            } else if ("脱硝".equals(sheetName)) {
                denitrificationCount = importDenitrificationSheet(deviceId, sheet);
            }
        }

        workbook.close();

        log.info("成功导入脱硫设备{}条，脱硝设备{}条", desulfurizationCount, denitrificationCount);
    }

    private int importDesulfurizationSheet(Long deviceId, Sheet sheet) {
        int startRow = findDataStartRow(sheet);
        if (startRow == -1) {
            log.warn("脱硫Sheet未找到有效数据");
            return 0;
        }

        int successCount = 0;
        for (int i = startRow + 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null || excelUtil.isEmptyRow(row)) {
                continue;
            }

            DesulfurizationInfo info = parseDesulfurizationRow(deviceId, row);
            if (desulfurizationInfoMapper.insert(info) > 0) {
                successCount++;
            }
        }

        return successCount;
    }

    private int importDenitrificationSheet(Long deviceId, Sheet sheet) {
        int startRow = findDataStartRow(sheet);
        if (startRow == -1) {
            log.warn("脱硝Sheet未找到有效数据");
            return 0;
        }

        int successCount = 0;
        for (int i = startRow + 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null || excelUtil.isEmptyRow(row)) {
                continue;
            }

            DenitrificationInfo info = parseDenitrificationRow(deviceId, row);
            if (denitrificationInfoMapper.insert(info) > 0) {
                successCount++;
            }
        }

        return successCount;
    }

    private int findDataStartRow(Sheet sheet) {
        for (int i = 0; i <= Math.min(10, sheet.getLastRowNum()); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            Cell cell = row.getCell(0);
            if (cell != null) {
                String value = excelUtil.getCellValueAsString(cell);
                if (value != null && value.matches("\\d+")) {
                    return i;
                }
            }
        }
        return -1;
    }

    private DesulfurizationInfo parseDesulfurizationRow(Long deviceId, Row row) {
        DesulfurizationInfo info = new DesulfurizationInfo();

        info.setDeviceId(deviceId);
        info.setBrand(excelUtil.getCellValueAsString(row.getCell(0)));
        info.setModel(excelUtil.getCellValueAsString(row.getCell(1)));
        info.setType(excelUtil.getCellValueAsString(row.getCell(2)));
        info.setSmokeFlowRate(excelUtil.getCellValueAsBigDecimal(row.getCell(3)));
        info.setDesulfurizationEfficiency(excelUtil.getCellValueAsBigDecimal(row.getCell(4)));
        info.setSo2RemovalAmount(excelUtil.getCellValueAsBigDecimal(row.getCell(5)));
        info.setPowerRating(excelUtil.getCellValueAsInteger(row.getCell(6)));
        info.setEnergyConsumptionRatio(excelUtil.getCellValueAsBigDecimal(row.getCell(7)));
        info.setSulfurContent(excelUtil.getCellValueAsBigDecimal(row.getCell(8)));
        info.setImoCompliance(excelUtil.getCellValueAsString(row.getCell(9)));
        info.setEfficiencyLevel(excelUtil.getCellValueAsInteger(row.getCell(10)));
        info.setIsEvaluated(false);
        info.setCreatedTime(LocalDateTime.now());
        info.setIsDeleted(0);

        return info;
    }

    private DenitrificationInfo parseDenitrificationRow(Long deviceId, Row row) {
        DenitrificationInfo info = new DenitrificationInfo();

        info.setDeviceId(deviceId);
        info.setBrand(excelUtil.getCellValueAsString(row.getCell(0)));
        info.setModel(excelUtil.getCellValueAsString(row.getCell(1)));
        info.setSmokeFlowRate(excelUtil.getCellValueAsBigDecimal(row.getCell(2)));
        info.setDenitrificationEfficiency(excelUtil.getCellValueAsBigDecimal(row.getCell(3)));
        info.setSystemPower(excelUtil.getCellValueAsInteger(row.getCell(4)));
        info.setNoxRemovalAmount(excelUtil.getCellValueAsBigDecimal(row.getCell(5)));
        info.setEnergyConsumptionRatio(excelUtil.getCellValueAsBigDecimal(row.getCell(6)));
        info.setImoCompliance(excelUtil.getCellValueAsString(row.getCell(7)));
        info.setEfficiencyLevel(excelUtil.getCellValueAsInteger(row.getCell(8)));
        info.setIsEvaluated(false);
        info.setCreatedTime(LocalDateTime.now());
        info.setIsDeleted(0);

        return info;
    }
}
