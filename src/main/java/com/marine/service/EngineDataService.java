package com.marine.service;

import com.marine.entity.EngineInfo;
import com.marine.entity.dto.EngineImportDTO;
import com.marine.entity.dto.EngineQueryDTO;
import com.marine.entity.vo.EvaluationResultVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EngineDataService {

    int importEngineFromExcel(MultipartFile file);

    boolean importEngine(EngineImportDTO importDTO);

    List<EngineInfo> queryEngines(EngineQueryDTO queryDTO);

    EvaluationResultVO completeEvaluation(Long engineId);

    EvaluationResultVO calculateEfficiency(Long engineId);

    EvaluationResultVO getEvaluation(Long engineId);
}
