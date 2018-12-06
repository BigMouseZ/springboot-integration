package com.integration.mappackage.entity;

import lombok.Data;

@Data
public class Gmapnetcache {
    private Integer type;

    private Integer zoom;

    private Integer x;

    private Integer y;

    private byte[] tile;


}