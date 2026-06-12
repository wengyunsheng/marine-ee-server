package com.marine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marine.entity.*;
import com.marine.entity.dto.EngineImportDTO;
import com.marine.entity.vo.EngineInfoVO;
import com.marine.entity.vo.EvaluationResultVO;
import com.marine.mapper.EngineInfoMapper;
import com.marine.mapper.EnginePerformanceCurveMapper;
import com.marine.mapper.EngineTestConditionMapper;
import com.marine.service.DeviceService;
import com.marine.service.EfficiencyService;
import com.marine.service.EngineDataService;
import com.marine.service.TestCycleService;
import com.marine.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class EngineDataServiceImpl extends ServiceImpl<EngineInfoMapper, EngineInfo> implements EngineDataService {

    private final DeviceService deviceService;

    private final EngineTestConditionMapper testConditionMapper;

    private final EnginePerformanceCurveMapper performanceCurveMapper;

    private final EngineInfoMapper engineInfoMapper;

    private final TestCycleService testCycleService;

    private final EfficiencyService efficiencyService;

    private final ExcelUtil excelUtil;

    @Override
    @Transactional
    public void importEngineFromExcel(Long deviceId, MultipartFile file) throws IOException {
        Workbook workbook = excelUtil.createWorkbook(file, file.getInputStream());

        if (workbook == null) {
            throw new IllegalArgumentException("无法解析Excel文件");
        }

        int sheetCount = workbook.getNumberOfSheets();

        for (int sheetIndex = 0; sheetIndex < sheetCount; sheetIndex++) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            String sheetName = sheet.getSheetName();
            if (!"MAN".equals(sheetName) && !"WINGD".equals(sheetName)) {
                continue;
            }
            log.info("开始解析Sheet: {}", sheetName);
            int successCount = parseSheet(deviceId, sheet, sheetName);
            log.info("Sheet {} 导入成功 {} 条", sheetName, successCount);
        }

        workbook.close();
    }

    /**
     * 解析单个Sheet页中的数据
     *
     * @param deviceId   设备ID，用于关联导入的发动机数据
     * @param sheet      要解析的Excel Sheet对象
     * @param dataSource 数据来源标识（如"MAN"或"WINGD"），用于区分不同格式的数据
     * @return 成功导入的记录数
     */
    private int parseSheet(Long deviceId, Sheet sheet, String dataSource) {
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
            if (seqCell == null || excelUtil.getCellValueAsString(seqCell) == null) {
                continue;
            }

            try {
                EngineImportDTO dto = parseRow(deviceId, row, dataSource);
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
     * 从Sheet的前10行中搜索，找到第一列值为数字的行作为数据起始行
     *
     * @param sheet Excel Sheet对象
     * @return 数据起始行的索引（从0开始），如果未找到则返回-1
     */
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

    /**
     * 解析一行Excel数据，转换为EngineImportDTO对象
     *
     * @param deviceId   设备ID，用于关联发动机数据
     * @param row        Excel行对象
     * @param dataSource 数据来源标识（如"MAN"或"WINGD"），不同来源的数据列位置可能不同
     * @return 封装好的发动机导入数据传输对象
     */
    private EngineImportDTO parseRow(Long deviceId, Row row, String dataSource) {
        EngineImportDTO dto = new EngineImportDTO();

        // 基础信息（列索引从0开始）
        // 0: 序号（跳过）
        dto.setDeviceId(deviceId);
        dto.setBrand(excelUtil.getCellValueAsString(row.getCell(1)));
        dto.setModel(excelUtil.getCellValueAsString(row.getCell(2)));
        dto.setCylinderCount(excelUtil.getCellValueAsInteger(row.getCell(3)));
        dto.setCylinderBore(excelUtil.getCellValueAsInteger(row.getCell(4)));
        dto.setFuelType(excelUtil.getCellValueAsString(row.getCell(5)));
        dto.setFuelType1(excelUtil.getCellValueAsString(row.getCell(6)));
        dto.setFuelType1CalorificValue(excelUtil.getCellValueAsBigDecimal(row.getCell(7)));
        dto.setFuelType2(excelUtil.getCellValueAsString(row.getCell(8)));
        dto.setFuelType2CalorificValue(excelUtil.getCellValueAsBigDecimal(row.getCell(9)));
        dto.setFuelType3(excelUtil.getCellValueAsString(row.getCell(10)));
        dto.setFuelType3CalorificValue(excelUtil.getCellValueAsBigDecimal(row.getCell(11)));


        // 用途和排放等级
        dto.setEngineUsage(excelUtil.getCellValueAsString(row.getCell(12)));
        dto.setEmissionStandard(excelUtil.getCellValueAsString(row.getCell(13)));
        dto.setRatedSpeed(excelUtil.getCellValueAsInteger(row.getCell(14)));
        dto.setRatedPower(excelUtil.getCellValueAsInteger(row.getCell(15)));

        if ("WINGD".equals(dataSource)) {
            dto.setAmbientTemp(excelUtil.getCellValueAsBigDecimal(row.getCell(16)));
            dto.setAmbientPressure(excelUtil.getCellValueAsInteger(row.getCell(17)));
            dto.setAmbientHumidity(excelUtil.getCellValueAsInteger(row.getCell(18)));
            dto.setExhaustTemp(excelUtil.getCellValueAsInteger(row.getCell(19)));
            dto.setCoolantInlet(excelUtil.getCellValueAsInteger(row.getCell(20)));
            dto.setCoolantOutlet(excelUtil.getCellValueAsInteger(row.getCell(21)));
            dto.setLubeOilTemp(excelUtil.getCellValueAsInteger(row.getCell(22)));
            dto.setLubeOilPressure(excelUtil.getCellValueAsBigDecimal(row.getCell(23)));
        } else if ("MAN".equals(dataSource)) {
            // 环境条件
            dto.setAmbientTemp(excelUtil.getCellValueAsBigDecimal(row.getCell(16)));
            dto.setAmbientHumidity(excelUtil.getCellValueAsInteger(row.getCell(17)));
        }

        // 解析性能曲线（4个负荷点）
        List<EngineImportDTO.PerformanceCurveDTO> curves = parsePerformanceCurves(row, dataSource);
        dto.setPerformanceCurves(curves);

        return dto;
    }

    /**
     * 解析性能曲线数据
     *
     * @param row        Excel行对象
     * @param dataSource 数据来源标识（如"MAN"或"WINGD"），决定性能曲线的起始列位置
     * @return 性能曲线列表，包含4个负荷点的数据
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
            int colOffset = startCol + i * 6;  // 每组6列

            BigDecimal power = excelUtil.getCellValueAsBigDecimal(row.getCell(colOffset));
            BigDecimal speed = excelUtil.getCellValueAsBigDecimal(row.getCell(colOffset + 1));
            BigDecimal bsfc = excelUtil.getCellValueAsBigDecimal(row.getCell(colOffset + 2));
            BigDecimal bspc = excelUtil.getCellValueAsBigDecimal(row.getCell(colOffset + 3));
            BigDecimal bsgc = excelUtil.getCellValueAsBigDecimal(row.getCell(colOffset + 4));
            BigDecimal bsec = excelUtil.getCellValueAsBigDecimal(row.getCell(colOffset + 5));

            EngineImportDTO.PerformanceCurveDTO curve = new EngineImportDTO.PerformanceCurveDTO();
            curve.setLoadFactor(BigDecimal.valueOf(loadFactors[i]));
            curve.setPower(power);
            curve.setSpeed(speed);
            curve.setBsfc(bsfc);
            curve.setBspc(bspc);
            curve.setBsgc(bsgc);
            curve.setBsec(bsec);

            curves.add(curve);
        }

        return curves;
    }

    @Override
    public boolean importEngine(EngineImportDTO importDTO) {
        // 保存发动机信息
        EngineInfo engineInfo = new EngineInfo();
        BeanUtils.copyProperties(importDTO, engineInfo);
        engineInfo.setCreatedTime(LocalDateTime.now());

        boolean saved = save(engineInfo);
        if (!saved) {
            throw new IllegalArgumentException("保存发动机信息失败");
        }

        Long engineId = engineInfo.getId();

        // 保存测试工况
        EngineTestCondition condition = getEngineTestCondition(importDTO, engineId);
        testConditionMapper.insert(condition);

        Long conditionId = condition.getId();

        // 保存性能曲线
        if (CollectionUtils.isNotEmpty(importDTO.getPerformanceCurves())) {
            for (EngineImportDTO.PerformanceCurveDTO curveDTO : importDTO.getPerformanceCurves()) {
                EnginePerformanceCurve curve = getEnginePerformanceCurve(curveDTO, engineId, conditionId);
                performanceCurveMapper.insert(curve);
            }
        }

        // 完成发动机的完整能效评估
        completeEvaluation(engineInfo);

        log.info("导入成功: engineId={}, conditionId={}, curveCount={}",
                engineId, conditionId,
                CollectionUtils.isNotEmpty(importDTO.getPerformanceCurves()) ? importDTO.getPerformanceCurves().size() : 0);

        return true;
    }

    /**
     * 根据性能曲线DTO创建发动机性能曲线对象
     *
     * @param curveDTO    性能曲线数据传输对象
     * @param engineId    发动机ID，用于关联性能曲线
     * @param conditionId 测试工况ID，用于关联性能曲线
     * @return 封装好的发动机性能曲线对象
     */
    private EnginePerformanceCurve getEnginePerformanceCurve(EngineImportDTO.PerformanceCurveDTO curveDTO, Long engineId, Long conditionId) {
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
        return curve;
    }

    /**
     * 根据导入数据创建发动机测试工况对象
     *
     * @param importDTO 发动机导入数据传输对象
     * @param engineId  发动机ID，用于关联测试工况
     * @return 封装好的发动机测试工况对象
     */
    private EngineTestCondition getEngineTestCondition(EngineImportDTO importDTO, Long engineId) {
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
        return condition;
    }

    /**
     * 完成发动机的完整能效评估
     * 包括计算能效指数、确定能效等级等
     *
     * @param engineInfo 发动机
     */
    private void completeEvaluation(EngineInfo engineInfo) {
        EvaluationResultVO result = calculateEfficiency(engineInfo);

        engineInfo.setEfficiencyIndex(result.getEfficiencyIndex());
        engineInfo.setEfficiencyLevel(result.getEfficiencyLevel());
        engineInfo.setEfficiencyBaseValue(result.getBaseValue());

        updateById(engineInfo);
    }

    private EvaluationResultVO calculateEfficiency(EngineInfo engineInfo) {
        Device device = deviceService.getById(engineInfo.getDeviceId());
        if (device == null) {
            throw new IllegalArgumentException("设备不存在");
        }

        List<EnginePerformanceCurve> curves = getByEngineId(engineInfo.getId());
        if (CollectionUtils.isEmpty(curves)) {
            throw new IllegalArgumentException("发动机性能曲线数据不完整");
        }

        Map<BigDecimal, BigDecimal> weightFactorsMap = getWeightFactors(engineInfo.getEngineUsage());

        BigDecimal sumWeighted = BigDecimal.ZERO;
        String deviceCode = device.getCode();
        for (EnginePerformanceCurve curve : curves) {
            BigDecimal bsfc = Optional.ofNullable(curve.getBsfc()).orElse(BigDecimal.ZERO);
            BigDecimal bspc = Optional.ofNullable(curve.getBspc()).orElse(BigDecimal.ZERO);
            BigDecimal bsgc = Optional.ofNullable(curve.getBsgc()).orElse(BigDecimal.ZERO);

            BigDecimal loadFactor = curve.getLoadFactor();
            if (loadFactor == null || !weightFactorsMap.containsKey(loadFactor)) {
                log.warn("负荷因子 {} 未找到对应的加权系数，跳过", loadFactor);
                continue;
            }

            BigDecimal weight = weightFactorsMap.get(loadFactor);

            if ("engine-01".equals(deviceCode) || "engine-02".equals(deviceCode)) {
                if (bsfc.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                sumWeighted = sumWeighted.add(weight.divide(bsfc, 10, RoundingMode.HALF_UP));
            } else if ("engine-03".equals(deviceCode) || "engine-04".equals(deviceCode)) {
                bsgc = bsgc.multiply(BigDecimal.valueOf(50000));
                bspc = bspc.multiply(BigDecimal.valueOf(42700));
                BigDecimal add = bsgc.add(bspc);
                if (add.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                sumWeighted = sumWeighted.add(weight.divide(add, 10, RoundingMode.HALF_UP));
            } else if ("engine-05".equals(deviceCode) || "engine-06".equals(deviceCode)) {
                bsgc = bsgc.multiply(BigDecimal.valueOf(19900));
                bspc = bspc.multiply(BigDecimal.valueOf(42700));
                BigDecimal add = bsgc.add(bspc);
                if (add.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                sumWeighted = sumWeighted.add(weight.divide(add, 10, RoundingMode.HALF_UP));
            }
        }

        BigDecimal indexMultiplier = BigDecimal.ZERO;
        if ("engine-01".equals(deviceCode) || "engine-02".equals(deviceCode)) {
            indexMultiplier = BigDecimal.valueOf(84.309133);
        } else if ("engine-03".equals(deviceCode) || "engine-04".equals(deviceCode)
                || "engine-05".equals(deviceCode) || "engine-06".equals(deviceCode)) {
            indexMultiplier = BigDecimal.valueOf(3600000);
        }
        BigDecimal efficiencyIndex = indexMultiplier.multiply(sumWeighted)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal singleCylinderPower = BigDecimal.valueOf(engineInfo.getRatedPower())
                .divide(BigDecimal.valueOf(engineInfo.getCylinderCount()), 2, RoundingMode.HALF_UP);

        List<EngineEfficiency> baseValueRecords = findBaseValues(
                deviceCode,
                engineInfo.getEmissionStandard(),
                singleCylinderPower
        );

        if (CollectionUtils.isEmpty(baseValueRecords)) {
            throw new IllegalArgumentException("未找到匹配的能效基值，排放等级=" + engineInfo.getEmissionStandard()
                    + ", 单缸功率=" + singleCylinderPower);
        }

        int efficiencyLevel = determineEfficiencyLevel(efficiencyIndex, baseValueRecords);

        EngineEfficiency baseValueRecord = baseValueRecords.stream()
                .filter(r -> r.getEfficiencyLevel().equals(efficiencyLevel))
                .findFirst()
                .orElse(baseValueRecords.get(0));

        EvaluationResultVO result = new EvaluationResultVO();
        result.setEfficiencyIndex(efficiencyIndex);
        result.setEfficiencyLevel(efficiencyLevel);
        result.setBaseValue(baseValueRecord.getBaseValue());
        result.setPassed(efficiencyLevel <= 2);

        log.info("评估完成: engineId={}, 能效指标={}, 能效等级={}级, 基值={}, 单缸功率={}",
                engineInfo.getId(), efficiencyIndex, efficiencyLevel, baseValueRecord.getBaseValue(), singleCylinderPower);

        return result;
    }

    @Override
    public List<EnginePerformanceCurve> getByEngineId(Long engineId) {
        LambdaQueryWrapper<EnginePerformanceCurve> curveWrapper = new LambdaQueryWrapper<>();
        curveWrapper.eq(EnginePerformanceCurve::getEngineId, engineId)
                .orderByAsc(EnginePerformanceCurve::getLoadFactor);
        return performanceCurveMapper.selectList(curveWrapper);
    }

    @Override
    public EngineTestCondition getConditionByEngineId(Long engineId) {
        LambdaQueryWrapper<EngineTestCondition> conditionWrapper = new LambdaQueryWrapper<>();
        conditionWrapper.eq(EngineTestCondition::getEngineId, engineId);
        return testConditionMapper.selectOne(conditionWrapper);
    }

    @Override
    public EngineInfo queryEngine(Long engineId) {
        EngineInfo engineInfo = engineInfoMapper.selectById(engineId);

        EngineTestCondition condition = getConditionByEngineId(engineInfo.getId());
        engineInfo.setTestCondition(condition);

        List<EnginePerformanceCurve> curves = getByEngineId(engineInfo.getId());
        engineInfo.setPerformanceCurves(curves);
        return engineInfo;
    }

    @Override
    public List<EngineInfoVO> getAllEngines() {
        List<EngineInfo> engines = engineInfoMapper.selectList(null);
        return engines.stream()
                .map(engine -> new EngineInfoVO(engine.getId(), "品牌：" + engine.getBrand() + " 型号：" + engine.getModel()))
                .collect(Collectors.toList());
    }

    /**
     * 根据发动机用途获取各负荷点的权重系数
     *
     * @param engineUsage 发动机用途描述（如"恒速主机"、"推进"等）
     * @return 负荷因子与权重系数的映射Map，key为负荷因子，value为权重系数
     */
    private Map<BigDecimal, BigDecimal> getWeightFactors(String engineUsage) {
        if (StringUtils.isBlank(engineUsage)) {
            throw new IllegalArgumentException("发动机用途不能为空");
        }

        String cycleCode = determineCycleCode(engineUsage);
        List<TestCycle> testCycles = testCycleService.getByCycleCode(cycleCode);
        if (CollectionUtils.isEmpty(testCycles)) {
            throw new IllegalArgumentException("未找到试验循环 " + cycleCode + " 的加权系数数据");
        }

        Map<BigDecimal, BigDecimal> weightFactorsMap = new HashMap<>();
        for (TestCycle cycle : testCycles) {
            String powerMode = cycle.getPowerMode();
            BigDecimal weightCoefficient = cycle.getWeightCoefficient();

            if (StringUtils.isNotBlank(powerMode) && weightCoefficient != null) {
                BigDecimal loadFactor = parsePowerModeToBigDecimal(powerMode);
                if (loadFactor != null) {
                    weightFactorsMap.put(loadFactor, weightCoefficient);
                }
            }
        }

        log.info("查询试验循环: cycleCode={}, cycleName={}, 加权系数数量={}",
                cycleCode, testCycles.get(0).getCycleName(), weightFactorsMap.size());

        return weightFactorsMap;
    }

    /**
     * 将功率模式字符串转换为BigDecimal类型的负荷因子
     *
     * @param powerMode 功率模式字符串（如"25%"、"0.25"等）
     * @return 负荷因子的BigDecimal值，如果转换失败则返回null
     */
    private BigDecimal parsePowerModeToBigDecimal(String powerMode) {
        if (StringUtils.isBlank(powerMode)) {
            return null;
        }

        String mode = powerMode.trim();
        if (mode.endsWith("%")) {
            String numberPart = mode.substring(0, mode.length() - 1);
            return new BigDecimal(numberPart).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        } else {
            return new BigDecimal(mode);
        }
    }

    /**
     * 根据发动机用途确定试验循环代码
     *
     * @param engineUsage 发动机用途描述（如"恒速主机"、"推进辅机"等）
     * @return 试验循环代码（E2、E3、D2），如果无法识别则默认返回E2
     */
    private String determineCycleCode(String engineUsage) {
        if (engineUsage.contains("恒速") && (engineUsage.contains("主机") || engineUsage.contains("电力推进"))) {
            return "E2";
        } else if (engineUsage.contains("推进")) {
            return "E3";
        } else if (engineUsage.contains("恒速") && engineUsage.contains("辅机")) {
            return "D2";
        } else {
            log.warn("无法识别发动机用途: {}, 默认使用 E2 试验循环", engineUsage);
            return "E2";
        }
    }

    /**
     * 根据设备编码、排放等级和单缸功率查找能效基值记录
     *
     * @param deviceCode          设备编码（如engine-01、engine-02等）
     * @param emissionStandard    排放等级（如Tier I、Tier II等）
     * @param singleCylinderPower 单缸功率（额定功率除以缸数）
     * @return 匹配的能效基值记录列表，按能效等级升序排列；如果未找到则返回null或空列表
     */
    private List<EngineEfficiency> findBaseValues(String deviceCode, String emissionStandard, BigDecimal singleCylinderPower) {
        List<EngineEfficiency> allRecords = efficiencyService.getEngineList(deviceCode, emissionStandard);
        if (CollectionUtils.isEmpty(allRecords)) {
            return null;
        }

        List<EngineEfficiency> matchedRecords = new ArrayList<>();

        for (EngineEfficiency record : allRecords) {
            BigDecimal minPower = record.getPowerRangeMin();
            BigDecimal maxPower = record.getPowerRangeMax();

            boolean inRange = minPower == null || singleCylinderPower.compareTo(minPower) >= 0;

            if (maxPower != null && singleCylinderPower.compareTo(maxPower) >= 0) {
                inRange = false;
            }

            if (inRange) {
                matchedRecords.add(record);
            }
        }

        if (matchedRecords.isEmpty()) {
            return null;
        }

        matchedRecords.sort(Comparator.comparing(EngineEfficiency::getEfficiencyLevel));
        return matchedRecords;
    }

    /**
     * 根据能效指标和基值记录确定能效等级
     *
     * @param efficiencyIndex 计算得到的能效指标
     * @param baseValues      能效基值记录列表，按能效等级升序排列
     * @return 确定的能效等级（1-5级，数字越小表示能效越高）
     */
    private int determineEfficiencyLevel(BigDecimal efficiencyIndex, List<EngineEfficiency> baseValues) {
        if (CollectionUtils.isEmpty(baseValues)) {
            throw new IllegalArgumentException("未找到匹配的能效基值");
        }

        for (EngineEfficiency baseValue : baseValues) {
            if (efficiencyIndex.compareTo(baseValue.getBaseValue()) <= 0) {
                return baseValue.getEfficiencyLevel();
            }
        }

        return baseValues.get(baseValues.size() - 1).getEfficiencyLevel();
    }
}
