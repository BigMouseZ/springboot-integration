package com.utils.xlsx;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class XlsHandleStateResult implements Serializable{

	private boolean completeState;
	private int completeCount;
	private int totalCount;
	private String fileUrl;
	private boolean zip;

	
}
