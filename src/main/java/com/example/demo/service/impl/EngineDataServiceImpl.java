package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.EngineInfo;
import com.example.demo.entity.EnginePerformanceCurve;
import com.example.demo.entity.EngineTestCondition;
import com.example.demo.entity.dto.EngineImportDTO;
import com.example.demo.entity.dto.EngineQueryDTO;
import com.example.demo.entity.vo.EngineDetailVO;
import com.example.demo.mapper.EngineInfoMapper;
import com.example.demo.mapper.EnginePerformanceCurveMapper;
import com.example.demo.mapper.EngineTestConditionMapper;
import com.example.demo.service.EngineDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EngineDataServiceImpl extends ServiceImpl<EngineInfoMapper, EngineInfo> implements EngineDataService {

    private final EngineTestConditionMapper testConditionMapper;
    private final EnginePerformanceCurveMapper performanceCurveMapper;

    public EngineDataServiceImpl(EngineTestConditionMapper testConditionMapper,
                                 EnginePerformanceCurveMapper performanceCurveMapper) {
        this.testConditionMapper = testConditionMapper;
        this.performanceCurveMapper = performanceCurveMapper;
    }

    @Override
    @Transactional
    public int importEngineFromExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = createWorkbook(file, inputStream)) {

            if (workbook == null) {
                throw new IllegalArgumentException("无法解析Excel文件");
            }

            int totalSuccess = 0;
            int sheetCount = workbook.getNumberOfSheets();

            for (int sheetIndex = 0; sheetIndex < sheetCount; sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                String sheetName = sheet.getSheetName();
                if (!"MAN".equals(sheetName) && !"WINGD".equals(sheetName)) {
                    continue;
                }
                log.info("开始解析Sheet: {}", sheetName);
                int successCount = parseSheet(sheet, sheetName);
                totalSuccess += successCount;
                log.info("Sheet {} 导入成功 {} 条", sheetName, successCount);
            }

            return totalSuccess;
        } catch (Exception e) {
            log.error("解析Excel文件失败", e);
            throw new IllegalArgumentException("Excel文件格式错误: " + e.getMessage());
        }
    }

    private Workbook createWorkbook(MultipartFile file, InputStream inputStream) throws Exception {
        String fileName = file.getOriginalFilename();
        if (fileName != null && fileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(inputStream);
        } else if (fileName != null && fileName.endsWith(".xls")) {
            return new HSSFWorkbook(inputStream);
        }
        return null;
    }

    /**
     * 解析单个Sheet
     */
    private int parseSheet(Sheet sheet, String dataSource) {
        // 找到数据起始行（第一个"序号"所在行，且序号为数字）
        int dataStartRow = findDataStartRow(sheet);
        if (dataStartRow == -1) {
            log.warn("Sheet {} 未找到数据起始行", sheet.getSheetName());
            return 0;
        }

        log.info("数据起始行: {}", dataStartRow);

        int successCount = 0;
        for (int i = dataStartRow; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            // 检查序号列是否为空
            Cell seqCell = row.getCell(0);
            if (seqCell == null || getCellValueAsString(seqCell) == null) {
                continue;
            }

            try {
                EngineImportDTO dto = parseRow(row, dataSource);
                if (importEngine(dto)) {
                    successCount++;
                }
            } catch (Exception e) {
                log.error("解析第{}行失败: {}", i + 1, e.getMessage());
            }
        }

        return successCount;
    }

    /**
     * 找到数据起始行（第一个序号为数字的行）
     */
    private int findDataStartRow(Sheet sheet) {
        for (int i = 0; i <= Math.min(10, sheet.getLastRowNum()); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Cell cell = row.getCell(0);
            if (cell != null) {
                String value = getCellValueAsString(cell);
                if (value != null && value.matches("\\d+")) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 解析一行数据
     */
    private EngineImportDTO parseRow(Row row, String dataSource) {
        EngineImportDTO dto = new EngineImportDTO();

        // 基础信息（列索引从0开始）
        // 0: 序号（跳过）
        dto.setBrand(getCellValueAsString(row.getCell(1)));
        dto.setModel(getCellValueAsString(row.getCell(2)));
        dto.setCylinderCount(getCellValueAsInteger(row.getCell(3)));
        dto.setCylinderBore(getCellValueAsInteger(row.getCell(4)));
        dto.setFuelType(getCellValueAsString(row.getCell(5)));
        dto.setFuelType1(getCellValueAsString(row.getCell(6)));
        dto.setFuelType1CalorificValue(getCellValueAsBigDecimal(row.getCell(7)));
        dto.setFuelType2(getCellValueAsString(row.getCell(8)));
        dto.setFuelType2CalorificValue(getCellValueAsBigDecimal(row.getCell(9)));
        dto.setFuelType3(getCellValueAsString(row.getCell(10)));
        dto.setFuelType3CalorificValue(getCellValueAsBigDecimal(row.getCell(11)));


        // 用途和排放等级
        dto.setEngineUsage(getCellValueAsString(row.getCell(12)));
        dto.setEmissionStandard(getCellValueAsString(row.getCell(13)));
        dto.setRatedSpeed(getCellValueAsInteger(row.getCell(14)));
        dto.setRatedPower(getCellValueAsInteger(row.getCell(15)));

        if ("WINGD".equals(dataSource)) {
            dto.setAmbientTemp(getCellValueAsBigDecimal(row.getCell(16)));
            dto.setAmbientPressure(getCellValueAsInteger(row.getCell(17)));
            dto.setAmbientHumidity(getCellValueAsInteger(row.getCell(18)));
            dto.setExhaustTemp(getCellValueAsInteger(row.getCell(19)));
            dto.setCoolantInlet(getCellValueAsInteger(row.getCell(20)));
            dto.setCoolantOutlet(getCellValueAsInteger(row.getCell(21)));
            dto.setLubeOilTemp(getCellValueAsInteger(row.getCell(22)));
            dto.setLubeOilPressure(getCellValueAsBigDecimal(row.getCell(23)));
        } else if ("MAN".equals(dataSource)) {
            // 环境条件
            dto.setAmbientTemp(getCellValueAsBigDecimal(row.getCell(16)));
            dto.setAmbientHumidity(getCellValueAsInteger(row.getCell(17)));
        }

        // 解析性能曲线（4个负荷点）
        List<EngineImportDTO.PerformanceCurveDTO> curves = parsePerformanceCurves(row, dataSource);
        dto.setPerformanceCurves(curves);

        return dto;
    }

    /**
     * 解析性能曲线
     * <p>
     * Excel结构（MAN表）：
     * 列18-21: 0.25负荷（功率、转速、燃油消耗率、能量消耗率）
     * 列22-25: 0.5负荷
     * 列26-29: 0.75负荷
     * 列30-33: 1.0负荷
     * <p>
     * WINGD表类似，但列起始位置不同
     */
    private List<EngineImportDTO.PerformanceCurveDTO> parsePerformanceCurves(Row row, String dataSource) {
        List<EngineImportDTO.PerformanceCurveDTO> curves = new ArrayList<>();

        // 确定各负荷点的起始列
        int startCol;
        if ("MAN".equals(dataSource)) {
            startCol = 18;  // MAN表第18列开始
        } else {
            startCol = 24;  // WINGD表第24列开始
        }

        // 负荷因子数组
        double[] loadFactors = {0.25, 0.5, 0.75, 1.0};

        for (int i = 0; i < loadFactors.length; i++) {
            int colOffset = startCol + i * 4;  // 每组4列

            BigDecimal power = getCellValueAsBigDecimal(row.getCell(colOffset));
            if (power == null) continue;  // 无数据则跳过

            BigDecimal speed = getCellValueAsBigDecimal(row.getCell(colOffset + 1));
            BigDecimal bsfc = getCellValueAsBigDecimal(row.getCell(colOffset + 2));
            BigDecimal bsec = getCellValueAsBigDecimal(row.getCell(colOffset + 3));

            EngineImportDTO.PerformanceCurveDTO curve = new EngineImportDTO.PerformanceCurveDTO();
            curve.setLoadFactor(BigDecimal.valueOf(loadFactors[i]));
            curve.setPower(power);
            curve.setSpeed(speed);
            curve.setBsfc(bsfc);
            // 其他字段可能没有数据，设为0或null
            curve.setBspc(BigDecimal.ZERO);
            curve.setBsgc(BigDecimal.ZERO);
            curve.setBsec(bsec);

            curves.add(curve);
        }

        return curves;
    }

    @Override
    @Transactional
    public boolean importEngine(EngineImportDTO importDTO) {
        // 保存发动机信息
        EngineInfo engineInfo = new EngineInfo();
        BeanUtils.copyProperties(importDTO, engineInfo);
        engineInfo.setCreatedTime(LocalDateTime.now());

        boolean saved = save(engineInfo);
        if (!saved) {
            log.error("保存发动机信息失败");
            return false;
        }

        Long engineId = engineInfo.getId();

        // 保存测试工况
        EngineTestCondition condition = new EngineTestCondition();
        condition.setEngineId(engineId);
        condition.setAmbientTemp(importDTO.getAmbientTemp());
        condition.setAmbientHumidity(importDTO.getAmbientHumidity());
        condition.setAmbientPressure(importDTO.getAmbientPressure());
        condition.setExhaustTemp(importDTO.getExhaustTemp());
        condition.setCoolantInlet(importDTO.getCoolantInlet());
        condition.setCoolantOutlet(importDTO.getCoolantOutlet());
        condition.setLubeOilTemp(importDTO.getLubeOilTemp());
        condition.setLubeOilPressure(importDTO.getLubeOilPressure());
        testConditionMapper.insert(condition);

        Long conditionId = condition.getId();

        // 保存性能曲线
        if (importDTO.getPerformanceCurves() != null && !importDTO.getPerformanceCurves().isEmpty()) {
            for (EngineImportDTO.PerformanceCurveDTO curveDTO : importDTO.getPerformanceCurves()) {
                EnginePerformanceCurve curve = new EnginePerformanceCurve();
                curve.setEngineId(engineId);
                curve.setConditionId(conditionId);
                curve.setLoadFactor(curveDTO.getLoadFactor());
                curve.setPower(curveDTO.getPower());
                curve.setSpeed(curveDTO.getSpeed());
                curve.setBsfc(curveDTO.getBsfc());
                curve.setBspc(curveDTO.getBspc());
                curve.setBsgc(curveDTO.getBsgc());
                curve.setBsec(curveDTO.getBsec());
                performanceCurveMapper.insert(curve);
            }
        }

        log.info("导入成功: engineId={}, conditionId={}, curveCount={}",
                engineId, conditionId,
                importDTO.getPerformanceCurves() != null ? importDTO.getPerformanceCurves().size() : 0);

        return true;
    }

    // ==================== 工具方法 ====================

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return null;
        try {
            switch (cell.getCellType()) {
                case STRING:
                    String value = cell.getStringCellValue();
                    return value != null && !value.trim().isEmpty() ? value.trim() : null;
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
        } catch (Exception e) {
            return null;
        }
    }

    private Integer getCellValueAsInteger(Cell cell) {
        String value = getCellValueAsString(cell);
        if (value == null) return null;
        try {
            return (int) Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private BigDecimal getCellValueAsBigDecimal(Cell cell) {
        String value = getCellValueAsString(cell);
        if (value == null) return null;
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public EngineDetailVO getEngineDetail(Long engineId) {
        EngineInfo engineInfo = getById(engineId);
        if (engineInfo == null) {
            return null;
        }

        EngineDetailVO vo = new EngineDetailVO();
        vo.setEngineInfo(engineInfo);

        LambdaQueryWrapper<EngineTestCondition> conditionWrapper = new LambdaQueryWrapper<>();
        conditionWrapper.eq(EngineTestCondition::getEngineId, engineId);
        EngineTestCondition condition = testConditionMapper.selectOne(conditionWrapper);
        vo.setTestCondition(condition);

        LambdaQueryWrapper<EnginePerformanceCurve> curveWrapper = new LambdaQueryWrapper<>();
        curveWrapper.eq(EnginePerformanceCurve::getEngineId, engineId)
                .orderByAsc(EnginePerformanceCurve::getLoadFactor);
        List<EnginePerformanceCurve> curves = performanceCurveMapper.selectList(curveWrapper);
        vo.setPerformanceCurves(curves);

        return vo;
    }

    @Override
    public List<EngineInfo> queryEngines(EngineQueryDTO queryDTO) {
        LambdaQueryWrapper<EngineInfo> wrapper = new LambdaQueryWrapper<>();

        if (StringUtils.hasText(queryDTO.getBrand())) {
            wrapper.eq(EngineInfo::getBrand, queryDTO.getBrand());
        }
        if (StringUtils.hasText(queryDTO.getModel())) {
            wrapper.like(EngineInfo::getModel, queryDTO.getModel());
        }
        if (StringUtils.hasText(queryDTO.getEmissionStandard())) {
            wrapper.eq(EngineInfo::getEmissionStandard, queryDTO.getEmissionStandard());
        }

        wrapper.orderByDesc(EngineInfo::getCreatedTime);

        List<EngineInfo> engineList = list(wrapper);

        for (EngineInfo engine : engineList) {
            LambdaQueryWrapper<EngineTestCondition> conditionWrapper = new LambdaQueryWrapper<>();
            conditionWrapper.eq(EngineTestCondition::getEngineId, engine.getId());
            EngineTestCondition condition = testConditionMapper.selectOne(conditionWrapper);
            engine.setTestCondition(condition);

            LambdaQueryWrapper<EnginePerformanceCurve> curveWrapper = new LambdaQueryWrapper<>();
            curveWrapper.eq(EnginePerformanceCurve::getEngineId, engine.getId())
                    .orderByAsc(EnginePerformanceCurve::getLoadFactor);
            List<EnginePerformanceCurve> curves = performanceCurveMapper.selectList(curveWrapper);
            engine.setPerformanceCurves(curves);
        }

        return engineList;
    }

    @Override
    @Transactional
    public boolean deleteEngine(Long engineId) {
        LambdaQueryWrapper<EnginePerformanceCurve> curveWrapper = new LambdaQueryWrapper<>();
        curveWrapper.eq(EnginePerformanceCurve::getEngineId, engineId);
        performanceCurveMapper.delete(curveWrapper);

        LambdaQueryWrapper<EngineTestCondition> conditionWrapper = new LambdaQueryWrapper<>();
        conditionWrapper.eq(EngineTestCondition::getEngineId, engineId);
        testConditionMapper.delete(conditionWrapper);

        return removeById(engineId);
    }

    @Override
    public List<EngineInfo> getEngineList() {
        LambdaQueryWrapper<EngineInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(EngineInfo::getCreatedTime);
        return list(wrapper);
    }
}
