package com.utils.xlsx;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.CellStyle;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class XlsFormatModule implements Serializable{
	private String titlename;
	private CellStyle titleStyle;
	private String valuename;
	private CellStyle valueStyle;
	private int clommwidth;
}
