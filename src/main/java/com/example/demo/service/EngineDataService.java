package com.example.demo.service;

import com.example.demo.entity.EngineInfo;
import com.example.demo.entity.dto.EngineImportDTO;
import com.example.demo.entity.dto.EngineQueryDTO;
import com.example.demo.entity.vo.EvaluationResultVO;
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
