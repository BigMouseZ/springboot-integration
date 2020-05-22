package com.utils.fastdfs;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.utils.fastdfs.config.FdfsConfig;
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
@Component
public class FastFileUtil {
    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private FdfsConfig fdfsConfig;

    public String uploadMultipartFile(MultipartFile file)  {
        StorePath storePath = null;
        try {
            storePath = storageClient.uploadFile((InputStream)file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getResAccessUrl(storePath);
    }

    public String uploadFile(File file)  {
        StorePath storePath = null;
        try {
            InputStream inputStream = new FileInputStream(file);
            storePath = storageClient.uploadFile(inputStream,inputStream.available(),FilenameUtils.getExtension(file.getName()), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getResAccessUrl(storePath);
    }

    public String uploadByteFile(byte[] file,String filename)  {
        StorePath storePath = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(file);
            storePath = storageClient.uploadFile(inputStream,inputStream.available(),FilenameUtils.getExtension(filename), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getResAccessUrl(storePath);
    }

    /**
     * 下载文件
     *
     * @param url 文件URL /group1/M00/11/7B/rBIUnVtMcSuAU4LSAABnUJFu1pY593.jpg  多了第一个斜杠，因为警用的上传的配置写死多了一个斜杠
     * @return 文件字节
     * @throws IOException
     */
    public byte[] downloadByteFile(String url) throws IOException {
        //去掉开头的斜杠
        String fileUrl = url.substring(1, url.length());
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = storageClient.downloadFile(group, path, downloadByteArray);
        return bytes;
    }
    /**
     * 删除文件
     * @param fileUrl 文件访问地址
     * @return
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            e.printStackTrace();
        }
    }
    /**
     * 封装文件完整URL地址
     * @param storePath
     * @return
     */
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = null;
        if(storePath !=null){
            fileUrl = fdfsConfig.getResHost() + ":" + fdfsConfig.getStoragePort() + "/" + storePath.getFullPath();
            //fileUrl ="/" + storePath.getFullPath();
        }
        return fileUrl;
    }
}
