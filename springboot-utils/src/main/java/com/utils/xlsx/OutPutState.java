package com.utils.xlsx;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class OutPutState implements Serializable {
	private String statemessage;
	private int state;//0正在获取数，1正在生成文件,2正在打包文件，3导出完成
	private int completeCount;
	private int totalCount;
	private String downloadurl;

	

}
