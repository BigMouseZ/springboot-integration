package com.integration.mapserver;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by liYu on 2017/3/23.
 */
@Data
@AllArgsConstructor
public class MapTile {
    private Long offset;
    private int length;
    private String filePath;
}
