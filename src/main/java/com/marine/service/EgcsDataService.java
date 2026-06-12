package com.marine.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EgcsDataService {

    /**
     * 从Excel文件导入尾气处理装置数据（脱硫和脱硝）
     *
     * @param deviceId 设备ID
     * @param file     Excel文件
     * @throws IOException 创建Workbook失败时抛出异常
     */
    void importEgcsFromExcel(Long deviceId, MultipartFile file) throws IOException;
}
