package com.marine.service.impl;

import com.marine.service.EgcsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EgcsServiceImpl implements EgcsService {

    @Override
    public void importEgcsFromExcel(Long deviceId, MultipartFile file) {

    }
}
