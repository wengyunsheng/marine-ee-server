package com.marine.service;

import com.marine.entity.EngineInfo;
import com.marine.entity.EnginePerformanceCurve;
import com.marine.entity.EngineTestCondition;
import com.marine.entity.dto.EngineImportDTO;
import com.marine.entity.vo.EngineInfoVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EngineDataService {

    /**
     * 从Excel文件导入发动机信息
     *
     * @param deviceId 设备ID
     * @param file     Excel文件
     * @throws IOException 创建Workbook失败时抛出异常
     */
    void importEngineFromExcel(Long deviceId, MultipartFile file) throws IOException;

    /**
     * 导入发动机信息
     *
     * @param importDTO 发动机导入数据传输对象
     * @return 是否导入成功
     */
    boolean importEngine(EngineImportDTO importDTO);

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

    /**
     * 根据发动机ID查询详细信息
     *
     * @param engineId 发动机ID
     * @return 发动机信息
     */
    EngineInfo queryEngine(Long engineId);

    List<EngineInfoVO> getAllEngines();
}
