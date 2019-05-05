package com.utils.xlsx;

import java.io.File;
import java.util.UUID;


public class XlsLocalFileStorehouseManager {
	
	public static String baseurl = "";
	public static String careate_out_folder="outxls";
	public static String module_file_folder="xlsmodule";
	public static int defauelt_page_size=60000;
	 
	 public static String CARANTICIPATION_OUT_MODULEFILENAME="疑似逃费导出数据模版.xlsx";
	 public static String SERVICE_AREA_RETENTION_OUT_MODULEFILENAME="服务区数据导出数据模版.xlsx";
	 public static String ETC_DATA_OUT_MODULEFILENAME="ETC导出数据模版.xlsx";
	 public static String CAR_PATH_OUT_MODULEFILENAME="车辆轨迹导出数据模版.xlsx";
	 public static String CAR_BALCK_OUT_MODULEFILENAME="黑名单导出数据模版.xlsx";
	 public static String EXDATA_OUT_MODULEFILENAME="异常数据导出数据模版.xlsx";
	 public static String CAR_HISTORY_DATA_OUT_MODULEFILENAME="历史数据导出数据模版.xlsx";
	 public static String CAR_IN_OUT_DATA_OUT_MODULEFILENAME="进出比对数据导出模版.xlsx";
	    static {
	        try {
	            File target_f = new File(new File(XlsLocalFileStorehouseManager.class.getResource("/").getPath()).getCanonicalPath() + File.separator + ".." + File.separator + ".." + File.separator);
	            baseurl = target_f.getCanonicalPath();
	            File dowfolder=new File(baseurl+File.separator+careate_out_folder);
	            if(!dowfolder.exists()){
	            	dowfolder.mkdirs();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    public static OutPutModuleBean getOutPutModuleBean(XlsModuleFile datatype){
	    	OutPutModuleBean bean =new OutPutModuleBean();
	    	bean.setPageSize(defauelt_page_size);
	    	bean.setZip(false);
	    	bean.setOutfilename(getOutPathFileName(datatype));
	    	bean.setOutFilePath(baseurl+File.separator+careate_out_folder+File.separator+bean.getOutfilename());
    		bean.setBasedownurl("/"+careate_out_folder+"/"+bean.getOutfilename());
	    	if(XlsModuleFile.CARANTICIPATION_OUT.equals(datatype)){
	    		//疑似逃费数据导出
	    		bean.setModuleFilePath(baseurl+File.separator+module_file_folder+File.separator+CARANTICIPATION_OUT_MODULEFILENAME);
	    	}else if(XlsModuleFile.SERVICE_AREA_RETENTION_OUT.equals(datatype)){
	    		//服务区滞留导出
	    		bean.setModuleFilePath(baseurl+File.separator+module_file_folder+File.separator+SERVICE_AREA_RETENTION_OUT_MODULEFILENAME);
	    	}else if(XlsModuleFile.ETC_DATA_OUT.equals(datatype)){
	    		//ETC过车
	    		bean.setModuleFilePath(baseurl+File.separator+module_file_folder+File.separator+ETC_DATA_OUT_MODULEFILENAME);
	    	}else if(XlsModuleFile.CAR_PATH_OUT.equals(datatype)){
	    		//车辆轨迹
	    		bean.setModuleFilePath(baseurl+File.separator+module_file_folder+File.separator+CAR_PATH_OUT_MODULEFILENAME);
	    	}else if(XlsModuleFile.CAR_BALCK_OUT.equals(datatype)){
	    		//黑名单库
	    		bean.setModuleFilePath(baseurl+File.separator+module_file_folder+File.separator+CAR_BALCK_OUT_MODULEFILENAME);
	    	}else if(XlsModuleFile.EXDATA_OUT.equals(datatype)){
	    		//异常数据
	    		bean.setModuleFilePath(baseurl+File.separator+module_file_folder+File.separator+EXDATA_OUT_MODULEFILENAME);
	    	}else if(XlsModuleFile.CAR_HISTORY_DATA_OUT.equals(datatype)){
	    		//历史数据
	    		bean.setModuleFilePath(baseurl+File.separator+module_file_folder+File.separator+CAR_HISTORY_DATA_OUT_MODULEFILENAME);
	    	}else if(XlsModuleFile.CARINOUT_DATA_OUT.equals(datatype)){
	    		//进出对比数据
	    		bean.setModuleFilePath(baseurl+File.separator+module_file_folder+File.separator+CAR_IN_OUT_DATA_OUT_MODULEFILENAME);
	    	}else{
	    		return null;
	    	}
	    	
	    	return bean;
	    }
	    
	    
	    private static String getOutPathFileName(XlsModuleFile datatype){
	    	String filename="没有数据";
	    	if(XlsModuleFile.CARANTICIPATION_OUT.equals(datatype)){
	    		filename="疑似逃费数据_"+UUID.randomUUID().toString().replaceAll("-", "")+CARANTICIPATION_OUT_MODULEFILENAME.substring(CARANTICIPATION_OUT_MODULEFILENAME.lastIndexOf("."));
	    		//疑似逃费数据导出
	    	}else if(XlsModuleFile.SERVICE_AREA_RETENTION_OUT.equals(datatype)){
	    		//服务区滞留导出
	    		filename="服务区滞留数据_"+UUID.randomUUID().toString().replaceAll("-", "")+SERVICE_AREA_RETENTION_OUT_MODULEFILENAME.substring(SERVICE_AREA_RETENTION_OUT_MODULEFILENAME.lastIndexOf("."));
	    	}else if(XlsModuleFile.ETC_DATA_OUT.equals(datatype)){
	    		//ETC过车
	    		filename="ETC过车数据_"+UUID.randomUUID().toString().replaceAll("-", "")+ETC_DATA_OUT_MODULEFILENAME.substring(ETC_DATA_OUT_MODULEFILENAME.lastIndexOf("."));
	    	}else if(XlsModuleFile.CAR_PATH_OUT.equals(datatype)){
	    		//车辆轨迹
	    		filename="车辆轨迹_"+UUID.randomUUID().toString().replaceAll("-", "")+CAR_PATH_OUT_MODULEFILENAME.substring(CAR_PATH_OUT_MODULEFILENAME.lastIndexOf("."));
	    	}else if(XlsModuleFile.CAR_BALCK_OUT.equals(datatype)){
	    		//黑名单库
	    		filename="黑名单数据_"+UUID.randomUUID().toString().replaceAll("-", "")+CAR_BALCK_OUT_MODULEFILENAME.substring(CAR_BALCK_OUT_MODULEFILENAME.lastIndexOf("."));
	    	}else if(XlsModuleFile.EXDATA_OUT.equals(datatype)){
	    		//异常数据
	    		filename="异常过车数据_"+UUID.randomUUID().toString().replaceAll("-", "")+EXDATA_OUT_MODULEFILENAME.substring(EXDATA_OUT_MODULEFILENAME.lastIndexOf("."));
	    	}else if(XlsModuleFile.CAR_HISTORY_DATA_OUT.equals(datatype)){
	    		//历史数据
	    		filename="历史过车数据_"+UUID.randomUUID().toString().replaceAll("-", "")+CAR_HISTORY_DATA_OUT_MODULEFILENAME.substring(CAR_HISTORY_DATA_OUT_MODULEFILENAME.lastIndexOf("."));
	    	}else if(XlsModuleFile.CARINOUT_DATA_OUT.equals(datatype)){
	    		//进出对比数据
	    		filename="进出比对数据_"+UUID.randomUUID().toString().replaceAll("-", "")+CAR_IN_OUT_DATA_OUT_MODULEFILENAME.substring(CAR_IN_OUT_DATA_OUT_MODULEFILENAME.lastIndexOf("."));
	    	}
	    	
	    	
	    	return filename;
	    	
	    }
	    
	    /**
	     * 保留最近7天前的导出数据文件
	     */
	    public static void deleteOldOutPutFile(){
	    	//读取目录文件
	    	try{
	    		File targetfile=new File(baseurl+File.separator+careate_out_folder);
	    		if(targetfile.exists()){
	    			File[] files = targetfile.listFiles();
	    			for(File f:files){
	    			 	if((System.currentTimeMillis()-f.lastModified()) > (1000*60*60*24*7) ){
	    			 		f.delete();
	    			 	}
	    			}
	    		}
	    	}catch(Exception e){
	    		
	    	}
	    	
	    	
	    }
	

}
