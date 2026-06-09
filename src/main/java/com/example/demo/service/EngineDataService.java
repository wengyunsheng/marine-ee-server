package com.example.demo.service;

import com.example.demo.entity.EngineInfo;
import com.example.demo.entity.dto.EngineImportDTO;
import com.example.demo.entity.dto.EngineQueryDTO;
import com.example.demo.entity.dto.EvaluationResultDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EngineDataService {

    int importEngineFromExcel(MultipartFile file);

    boolean importEngine(EngineImportDTO importDTO);

    List<EngineInfo> queryEngines(EngineQueryDTO queryDTO);

    EvaluationResultDTO completeEvaluation(Long engineId);

    EvaluationResultDTO calculateEfficiency(Long engineId);

    EvaluationResultDTO getEvaluation(Long engineId);
}
