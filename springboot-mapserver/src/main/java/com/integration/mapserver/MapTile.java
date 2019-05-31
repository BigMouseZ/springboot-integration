package com.integration.mapserver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by liYu on 2017/3/23.
 */
@Getter
@Setter
@AllArgsConstructor
public class MapTile {
    private Long offset;
    private int length;
    private String filePath;
}
