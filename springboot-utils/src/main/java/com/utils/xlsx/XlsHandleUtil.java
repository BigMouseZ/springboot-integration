package com.utils.xlsx;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XlsHandleUtil {

	public static boolean outPut(String moduleFilePath, String outPutPath,
			List<?> dataList, Class dataClass, int maxpageSize,boolean zip,XlsHandleStateListen stateListen) {
		if (moduleFilePath == null || outPutPath == null || dataList == null
				|| dataClass == null) {
			return false;
		}
		if (dataList.size() == 0) {
			return false;
		}
		File moduleFile = new File(moduleFilePath);
		if (moduleFile.exists()) {
			try {
				// 模版文件存在，读取模版文件
				InputStream stream = new FileInputStream(moduleFile);
				Workbook workbook = new XSSFWorkbook(stream);
				stream.close();
				List<XlsFormatModule> moduleProperty = getXlsModule(workbook);
				Map<String, Field> dataFieldMap = getObjectField(dataClass);
				if (moduleProperty != null && dataFieldMap != null && moduleProperty.size() > 0 && dataFieldMap.size() >0) {
					//开始生成表格
					if(maxpageSize >63500){
						maxpageSize = 63500;
					}
					int recod_index = 1;
					Row recod_row = null;
					int book=2;
					Sheet sheet = workbook.getSheetAt(0);
					int totalcount=dataList.size();
					int completeCount =0;
					for(Object bean:dataList){
						if(recod_index == maxpageSize){
							//创建新的分页
							sheet=workbook.createSheet("第"+book+"页");
							Row head_row2 = sheet.createRow(0);
							for (int j = 0; j < moduleProperty.size(); j++) {
								sheet.setColumnWidth(j, moduleProperty.get(j).getClommwidth());
								head_row2.createCell(j).setCellValue(
										moduleProperty.get(j).getTitlename());
								head_row2.getCell(j).setCellStyle(moduleProperty.get(j).getTitleStyle());

							}
							book++;
							recod_index=1;
						}
						recod_row = sheet.createRow(recod_index);
						for (int m = 0; m < moduleProperty.size(); m++) {

							Field fff=dataFieldMap.get(moduleProperty.get(m).getValuename());
							if (fff != null) {
								Object o = fff.get(bean);
								
								if(o == null){
									recod_row.createCell(m).setCellValue("");
								}else{
									if(o.toString().startsWith("HYPERLINK")){
										recod_row.createCell(m).setCellFormula(o.toString());
									}else{
										
										recod_row.createCell(m).setCellValue(o.toString());
									}
								}
							} else {
								recod_row.createCell(m).setCellValue("");
							}
							recod_row.getCell(m).setCellStyle(moduleProperty.get(m).getValueStyle());
						}
						recod_index++;
						completeCount++;
						if(stateListen != null){
							if(completeCount == totalcount){
								stateListen.handleState(new XlsHandleStateResult(true, completeCount, totalcount, outPutPath, zip));
							}else{
								stateListen.handleState(new XlsHandleStateResult(false, completeCount, totalcount, outPutPath, zip));
							}
						}
					}
					
					//保存文件
					OutputStream out = new FileOutputStream(outPutPath);
					workbook.write(out);
					out.close();
					workbook.close();
					
					return true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	private static List<XlsFormatModule> getXlsModule(Workbook workbook) {
		List<XlsFormatModule> list = null;
		if (workbook != null) {
			try {
				// 第一行（标题行）
				Row first_row = workbook.getSheetAt(0).getRow(0);
				Row second_row = workbook.getSheetAt(0).getRow(1);
				if (first_row != null && second_row != null) {
					list = new ArrayList<XlsFormatModule>();
					for (int i = 0; i < first_row.getPhysicalNumberOfCells(); i++) {
						list.add(new XlsFormatModule(first_row.getCell(i)
								.getStringCellValue(), first_row.getCell(i)
								.getCellStyle(), second_row.getCell(i)
								.getStringCellValue(), second_row.getCell(i)
								.getCellStyle(), workbook.getSheetAt(0)
								.getColumnWidth(i)));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	private static Map<String, Field> getObjectField(Class targetclass) {
		Map<String, Field> map = null;
		if (targetclass != null) {
			map=new HashMap<String, Field>();
			for (Field ff : targetclass.getDeclaredFields()) {
				ff.setAccessible(true);
				map.put(ff.getName(), ff);
			}
		}
		return map;

	}

}
