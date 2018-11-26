package com.utils.fastdfs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by ZhangGang on 2018/6/25.
 */
@Component
public class FdfsConfig {
    @Value("${fdfs.reshost}")
    private String resHost;

    @Value("${fdfs.storageport}")
    private String storagePort;

    public String getResHost() {
        return resHost;
    }

    public void setResHost(String resHost) {
        this.resHost = resHost;
    }

    public String getStoragePort() {
        return storagePort;
    }

    public void setStoragePort(String storagePort) {
        this.storagePort = storagePort;
    }

}
