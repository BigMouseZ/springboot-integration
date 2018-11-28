package com.utils.threadpool;

import com.utils.property.PropertiesUtils;
import com.utils.threadpool.config.ThreadConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * Created by ZhangGang on 2018/11/27.
 */
@Service
public class ServiceStartListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceStartListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // TODO Auto-generated method stub
        logger.info("服务启动事件监听");
        if (event.getApplicationContext().getParent() == null) {
            logger.info("开始初始化数据操作");
            //初始化线程池
            initThreadPool();
        }

    }

    public void initThreadPool(){
        Properties properties = PropertiesUtils.load("config/thread.properties");

        if (properties == null || properties.size() == 0) {
            logger.error("无法加载classpath:sync.properties");
            return;
        }

        boolean carDataOpen = Boolean.parseBoolean(properties.getProperty("carDataOpen"));
        if(carDataOpen){
            ThreadConfig.carDataThreadPopolKey = properties.getProperty("carDataThreadPopolKey");
            ThreadConfig.carDataThreadPopolName = properties.getProperty("carDataThreadPopolName");
            ThreadConfig.carDataThreadPopolCount = Integer.parseInt(properties.getProperty("carDataThreadPopolCount"));

            WorkThreadJonPoolControl.createNewJobThreadPool(ThreadConfig.carDataThreadPopolKey,ThreadConfig.carDataThreadPopolName,ThreadConfig.carDataThreadPopolCount);
            logger.info("启动线程池完成");
        }

    }
}
