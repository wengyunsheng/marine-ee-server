package com.example.demo.service;

import com.example.demo.entity.EngineInfo;
import com.example.demo.entity.dto.EngineImportDTO;
import com.example.demo.entity.dto.EngineQueryDTO;
import com.example.demo.entity.vo.EngineDetailVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EngineDataService {

    int importEngineFromExcel(MultipartFile file);

    boolean importEngine(EngineImportDTO importDTO);

    EngineDetailVO getEngineDetail(Long engineId);

    List<EngineInfo> queryEngines(EngineQueryDTO queryDTO);

    boolean deleteEngine(Long engineId);

    List<EngineInfo> getEngineList();
}
