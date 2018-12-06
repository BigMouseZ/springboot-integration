package com.integration.mappackage.entity;

import lombok.Builder;
import lombok.Data;

/**
 * Created by ZhangGang on 2018/12/4.
 */
@Data
@Builder
public class QueryInfo {
    private Integer map_type;//=47626774
// <--最小打包级别-->
    private Integer zoom_min;//=1
//    <!--最大打包级别-->
    private Integer zoom_max;//=18
}
