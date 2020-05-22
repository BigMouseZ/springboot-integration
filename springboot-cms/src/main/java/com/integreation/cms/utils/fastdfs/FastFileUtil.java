package com.integreation.cms.utils.fastdfs;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ZhangGang on 2018/6/22.
 * fastdfs 普通类调用 ：   private FastFileUtil fastFileUtil = (FastFileUtil) SpringContextUtils.getBean(FastFileUtil.class);
 */
@Slf4j
@Component
public class FastFileUtil {
    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * @param file 单个文件文件
     * @return String 分组路径:/group1/M00/11/7B/rBIUnVtMcSuAU4LSAABnUJFu1pY593.jpg
     * @throws Exception 默认捕获所有，由日志输出。
     * @Description 功能：上传文件
     **/
    public String uploadMultipartFile(MultipartFile file) {
        StorePath storePath = null;
        try {
            storePath = storageClient.uploadFile((InputStream) file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            return getResAccessUrl(storePath);
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("[uploadMultipartFile]上传fastdfs文件失败：" + e);
        }
        return null;
    }

    /**
     * @param file 文件
     * @return String 分组路径:/group1/M00/11/7B/rBIUnVtMcSuAU4LSAABnUJFu1pY593.jpg
     * @throws Exception 默认捕获所有，由日志输出。
     * @Description 功能：上传文件
     **/
    public String uploadFile(File file) {
        StorePath storePath = null;
        try {
            InputStream inputStream = new FileInputStream(file);
            storePath = storageClient.uploadFile(inputStream, inputStream.available(), FilenameUtils.getExtension(file.getName()), null);
            return getResAccessUrl(storePath);
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("[uploadFile]上传fastdfs文件失败：" + e);
        }
        return null;
    }

    /**
     * @param file     文件字节
     * @param filename 文件名（包含后缀）  支持的图片格式包括"JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"
     *                 rBIUnVtMcSuAU4LSAABnUJFu1pY593.jpg
     * @return String 分组路径:/group1/M00/11/7B/rBIUnVtMcSuAU4LSAABnUJFu1pY593.jpg
     * @throws Exception 默认捕获所有，由日志输出。
     * @Description 功能：上传文件
     **/

    public String uploadByteFile(byte[] file, String filename) {
        StorePath storePath = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(file);
            storePath = storageClient.uploadFile(inputStream, inputStream.available(), FilenameUtils.getExtension(filename), null);
            return getResAccessUrl(storePath);
        } catch (Exception e) {
            //e.printStackTrace();
            log.error("[uploadByteFile]上传fastdfs文件失败：" + e);
        }
        return null;
    }

    /**
     * @param url 文件URL ：/group1/M00/11/7B/rBIUnVtMcSuAU4LSAABnUJFu1pY593.jpg
     *            多了第一个斜杠，因为警用的上传的配置写死多了一个斜杠
     * @return 文件字节
     * @throws Exception 默认捕获所有，由日志输出。
     * @Description 功能：下载文件
     */
    public byte[] downloadByteFile(String url) throws IOException {
        try {
            String fileUrl = "";
            //去掉开头的斜杠
            if (url.indexOf("/") == 0) {
                fileUrl = url.substring(1, url.length());
            } else {
                fileUrl = url;
            }
            String group = fileUrl.substring(0, fileUrl.indexOf("/"));
            String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
            DownloadByteArray downloadByteArray = new DownloadByteArray();
            byte[] bytes = storageClient.downloadFile(group, path, downloadByteArray);
            return bytes;
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("[downloadByteFile]下载fastdfs文件失败：" + e);
        }
        return null;
    }

    /**
     * 删除文件
     * @param url 文件访问地址 ：group1/M00/11/7B/rBIUnVtMcSuAU4LSAABnUJFu1pY593.jpg
     * @return
     */
    public void deleteFile(String url) {
        if (StringUtils.isEmpty(url)) {
            return;
        }
        String fileUrl = "";
        //去掉开头的斜杠
        if (url.indexOf("/") == 0) {
            fileUrl = url.substring(1, url.length());
        } else {
            fileUrl = url;
        }
        try {
            storageClient.deleteFile(fileUrl);
        } catch (Exception e) {
            log.error("[deleteFile]删除fastdfs文件失败：" + e);
        }
    }

    /**
     * @param storePath 存储路径
     * @return String 分组路径:/group1/M00/11/7B/rBIUnVtMcSuAU4LSAABnUJFu1pY593.jpg
     * @Description 功能：封装文件完整URL地址
     */
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = null;
        if (storePath != null) {
          //  fileUrl = fdfsConfig.getResHost() + ":" + fdfsConfig.getStoragePort() + "/" + storePath.getFullPath();
            //加 “/” 主要针对与警用。
            fileUrl ="/" + storePath.getFullPath();
        }
        return fileUrl;
    }
}
