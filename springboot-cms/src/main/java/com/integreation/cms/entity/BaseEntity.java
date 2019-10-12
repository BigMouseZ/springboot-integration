package com.integreation.cms.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {

    private int pageSize;
    private int page;
    private int offset;
    private int maxoffset;
    public int getOffset() {
        return offset = (page - 1) * pageSize;
    }
    public int getMaxoffset(){
    	return page*pageSize;
    }

}
