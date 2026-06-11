package com.marine.service;

import com.marine.entity.EngineInfo;
import com.marine.entity.dto.EngineImportDTO;
import com.marine.entity.dto.EngineQueryDTO;
import com.marine.entity.vo.EvaluationResultVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EngineDataService {

    /**
     * 从Excel文件导入发动机信息
     *
     * @param deviceId 设备ID
     * @param file     Excel文件
     */
    void importEngineFromExcel(Long deviceId, MultipartFile file);

    /**
     * 导入发动机信息
     *
     * @param importDTO 发动机导入数据传输对象
     * @return 是否导入成功
     */
    boolean importEngine(EngineImportDTO importDTO);

    /**
     * 查询发动机信息列表
     *
     * @param queryDTO 发动机查询条件
     * @return 发动机信息列表
     */
    List<EngineInfo> queryEngines(EngineQueryDTO queryDTO);

    /**
     * 完成发动机的完整能效评估
     * 包括计算能效指数、确定能效等级等
     *
     * @param engineId 发动机ID
     * @return 能效评估结果
     */
    EvaluationResultVO completeEvaluation(Long engineId);

    /**
     * 计算发动机能效
     * 根据测试数据和性能曲线计算能效指标
     *
     * @param engineId 发动机ID
     * @return 能效评估结果
     */
    EvaluationResultVO calculateEfficiency(Long engineId);

    /**
     * 获取发动机的能效评估结果
     * 查询已保存的评估结果
     *
     * @param engineId 发动机ID
     * @return 能效评估结果
     */
    EvaluationResultVO getEvaluation(Long engineId);
}
