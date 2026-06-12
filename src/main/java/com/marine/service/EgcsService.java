package com.marine.service;

import org.springframework.web.multipart.MultipartFile;

public interface EgcsService {
    void importEgcsFromExcel(Long deviceId, MultipartFile file);
}
