package com.integration.mappackage.dao;

import com.integration.mappackage.entity.Gmapnetcache;
import com.integration.mappackage.entity.QueryInfo;

import java.util.List;

public interface GmapnetcacheMapper {

    List<Gmapnetcache> selectAll(QueryInfo queryInfo);
}