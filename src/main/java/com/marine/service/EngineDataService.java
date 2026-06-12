package com.marine.service;

import com.marine.entity.EngineInfo;
import com.marine.entity.EnginePerformanceCurve;
import com.marine.entity.EngineTestCondition;
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
     * 获取发动机的能效评估结果
     * 查询已保存的评估结果
     *
     * @param engineId 发动机ID
     * @return 能效评估结果
     */
    EvaluationResultVO getEvaluation(Long engineId);

    /**
     * 根据发动机ID查询性能曲线
     *
     * @param engineId 发动机ID
     * @return 发动机性能曲线列表
     */
    List<EnginePerformanceCurve> getByEngineId(Long engineId);

    /**
     * 根据发动机ID查询试验条件
     *
     * @param engineId 发动机ID
     * @return 发动机试验条件
     */
    EngineTestCondition getConditionByEngineId(Long engineId);

}
