package com.integration.mappackage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.integration.mappackage.dao.GmapnetcacheMapper;
import com.integration.mappackage.entity.Gmapnetcache;
import com.integration.mappackage.entity.QueryInfo;
import com.integration.mappackage.service.MapPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhangGang on 2018/12/4.
 */
@Service
public class MapPackageServiceImpl implements MapPackageService {
    @Value("${map_type}")
    private int map_type;
    @Value("${zoom_min}")
    private int zoom_min;
    @Value("${zoom_max}")
    private int zoom_max;
    @Autowired
    GmapnetcacheMapper gmapnetcacheMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void packageMap(String path) {
        QueryInfo queryInfo = QueryInfo.builder()
                .map_type(map_type)
                .zoom_min(zoom_min)
                .zoom_max(zoom_max)
                .build();
        long startime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            long start = System.currentTimeMillis();
            PageHelper.startPage(i, 1000,false);
            List<Gmapnetcache> list = gmapnetcacheMapper.selectAll(queryInfo);
            PageInfo<Gmapnetcache> pageInfo = new PageInfo<>(list);
            System.out.println(pageInfo.getSize());
            long end = System.currentTimeMillis();
            System.out.println("每次查询耗时："+(end  - start) / 1000 + "秒");
            if (pageInfo.getSize() < 1000){
                break;
            }
        }
        long endtime = System.currentTimeMillis();
        System.out.println("总耗时："+(endtime  - startime) / 1000 + "秒");
    }
}
