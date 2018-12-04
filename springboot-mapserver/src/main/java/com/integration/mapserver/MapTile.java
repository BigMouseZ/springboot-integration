package com.integration.mapserver;

/**
 * Created by liYu on 2017/3/23.
 */
public class MapTile {
    private Long offset;
    private int length;
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public MapTile(Long offset, int length, String filePath) {
        this.offset = offset;
        this.length = length;
        this.filePath = filePath;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
