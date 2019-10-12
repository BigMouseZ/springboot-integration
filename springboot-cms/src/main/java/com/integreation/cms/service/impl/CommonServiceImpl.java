package com.integreation.cms.service.impl;

import com.integreation.cms.entity.cms.cmsresponsevo.FileUploadResponseVo;
import com.integreation.cms.entity.response.ResponseVo;
import com.integreation.cms.service.CommonService;
import com.integreation.cms.utils.fastdfs.FastFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private FastFileUtil fastFileUtil;
    @Override
    public ResponseVo uploadMultipartFile(MultipartFile[] files) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            List<FileUploadResponseVo> list = new ArrayList<>();
            for (MultipartFile multipartFile : files) {
                if (multipartFile.getSize() > 0) {
                    FileUploadResponseVo fileUploadResponseVo = new FileUploadResponseVo();
                    fileUploadResponseVo.setFileName(multipartFile.getOriginalFilename());
                    fileUploadResponseVo.setFilePath(fastFileUtil.uploadMultipartFile(multipartFile));
                    list.add(fileUploadResponseVo);
                }
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
            responseVo.setData(list);
        } catch (Exception e) {
            log.error("文件上传异常：", e);
        }
        return responseVo;
    }
}
