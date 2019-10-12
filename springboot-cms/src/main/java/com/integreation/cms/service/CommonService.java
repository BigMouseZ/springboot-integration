package com.integreation.cms.service;

import com.integreation.cms.entity.response.ResponseVo;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {

    ResponseVo uploadMultipartFile(MultipartFile[] files);
}
