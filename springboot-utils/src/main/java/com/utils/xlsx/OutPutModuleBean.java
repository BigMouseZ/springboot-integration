package com.utils.xlsx;

import lombok.Data;

import java.io.Serializable;
@Data
public class OutPutModuleBean implements Serializable {
	private String moduleFilePath;
	private String outFilePath;
	private int pageSize;
	private boolean zip;
	private String basedownurl;
	private String outfilename;
	private String downusername;
}
