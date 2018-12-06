package com.utils.timeutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class TimeToolUtil {

    //	private  static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	private  static SimpleDateFormat formatter_day = new SimpleDateFormat("yyyy-MM-dd");
    public final static String format = "yyyy-MM-dd HH:mm:ss";
    
    
    
    public static int getMonthSpace(Date date1, Date date2) {
    	if(date1 != null && date2 != null ){
    		
    		 Calendar bef = Calendar.getInstance();
    	        Calendar aft = Calendar.getInstance();
    	        bef.setTime(date1);
    	        aft.setTime(date2);
    	        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
    	        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
    	        return Math.abs(month + result);
    	        
    		

    	}
    	return 0;
        
    }
    
    
    public static Date stringToDate(String time){
    	if(time != null){
    		time = time.replaceAll("/", "-");
    		try {
				return new SimpleDateFormat(format).parse(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return null;
    }

    public static String getCurrentTimeByString(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
    
    
    public static String dateToString(Date date){
    	if(date != null){
    		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
    	}
    	return null;
    }

    public static String getCurrentTimeString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static Date addDay(Date dateFrom, int dayNum) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(dateFrom);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + dayNum);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

    public static Date addDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }


    /**
     * 追加时间的时分秒
     *
     * @param date
     * @param startorend ture 起始 false 结束
     * @return
     */
    public static Date getDateAdd_hms(Date date, boolean startorend) {

        if (date != null) {
            try {
                String datestr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                if (startorend) {
                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datestr + " 00:00:00");
                } else {
                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datestr + " 23:59:59");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    
   
    
    /**
     * 效验时间格式是否正确
     * 适用格式：yyyy-MM-dd HH:MM:ss和yyyy-MM-dd
     * @param datetime
     * @return
     */
    public static boolean checkDateFormat(String datetime){
    	if(datetime != null){
    		return datetime.matches("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
    	}
    	return false;
    }

//	public static Date addHours(Date dateFrom,int hourNum){
//		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
////		Date beginDate = new Date();
//		Calendar date = Calendar.getInstance();
//		date.setHandleTime(dateFrom);
//		date.set(Calendar.DATE, date.get(Calendar.HOUR_OF_DAY) + hourNum);
//		Date endDate = null;
//		try {
//			endDate = dft.parse(dft.format(date.getHandleTime()));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return endDate;
//	}

    public static Date getYestoryMonth(int montyCont){
    	Calendar calendar = Calendar.getInstance();

    	calendar.setTime(new Date());

    	calendar.add(Calendar.MONTH, montyCont);
    	
    	return calendar.getTime();
    }
    
    
    public static void main(String[] args) {
//    	System.out.println(dateToString(getYestoryMonth(-3)));
//    	System.out.println(addDay(new Date(), -1));
    	System.out.println(getMonthSpace(stringToDate("2018-07-05 10:11:11"), stringToDate("2018-01-05 10:11:11")));
    	
//    	String path ="file:/opt/apache-tomcat-8.0.52/webapps/highwayDeviceEntry/WEB-INF/lib/highwayDeviceFilter-1.0.jar!/com/signalway/highway/device/filter/capture/";
//    	System.out.println(path.substring(path.lastIndexOf(":")+1,path.indexOf("WEB-INF")));
//    	
//    	String datetime ="2018-01-04";
//    	 Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
//    	System.out.println(datetime.matches("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$"));
//    	 System.out.println(p.matcher(datetime).matches()); 
//    	
//    	Date da = moveByMinute(new Date(), -1);
//    	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(addMinute(new Date(),-1)));
//    	System.out.println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(da));
//    	
//    	System.out.println("--------------");
//    	
//    	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(addDay(new Date(), 1)));
    	
//        //System.out.println(getCurrentTimeByString(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        System.out.print(getDayBegin());
//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setDataTime(date);
//        //int hour = Integer.parseInt("2017-11-07 17:00:00".substring("2017-11-07 11:00:00".indexOf(" ") + 1,"2017-11-07 11:00:00".indexOf(" ") + 3));
//        int hour = calendar.get(Calendar.HOUR);
//
//        System.out.println(calendar);
//
//        String dateStr = "2017-11-07 18:00:00";
//        int year = Integer.parseInt(dateStr.substring(0,dateStr.indexOf("-")));
//        System.out.println(year);
//        int month = Integer.parseInt(dateStr.substring(dateStr.indexOf("-") +1,dateStr.indexOf("-") + 3));
//        System.out.println(month);
//        int day = Integer.parseInt(dateStr.substring(dateStr.indexOf(" ")-2,dateStr.indexOf(" ")));
//        System.out.println(day);
//        int hour1 = Integer.parseInt(dateStr.substring(dateStr.indexOf(" ")+1,dateStr.indexOf(" ") + 3));
//        System.out.println(hour1);
    }

    public static Map<String,Integer> getYearMonthDayHourFromDateStr(String dateStr){
        Map<String,Integer> map = new HashMap<>();
        map.put("year",Integer.parseInt(dateStr.substring(0,dateStr.indexOf("-"))));
        map.put("month",Integer.parseInt(dateStr.substring(dateStr.indexOf("-") +1,dateStr.indexOf("-") + 3)));
        map.put("day",Integer.parseInt(dateStr.substring(dateStr.indexOf(" ")-2,dateStr.indexOf(" "))));
        map.put("hour",Integer.parseInt(dateStr.substring(dateStr.indexOf(" ")+1,dateStr.indexOf(" ") + 3)));
        return map;
    }


    public static Date getDateByString(String str, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr/*"yyyy-MM-dd HH:mm:ss"*/);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //获取当天的开始时间
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //获取当天的结束时间
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date addSecond(Date date,int second){
    	if(date != null){
        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.add(Calendar.SECOND, second);
	        date = c.getTime();
	        return date;
    	}
    	return null;
    }
    public static Date addMinute(Date date,int minute){
    	if(date != null){
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.add(Calendar.MINUTE, minute);
	        date = c.getTime();
	        return date;
    	}
    	return null;
    }
    
    public static Date moveByMinute(Date targetdate,Integer minute){
    	if(targetdate != null && minute != null && minute != 0){
    		Long time = targetdate.getTime();
    		time =( time + (minute*60*1000));
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTimeInMillis(time);
    		
    		return calendar.getTime();
    	}
    	
    	return targetdate;
    }
    
    
    public static Date moveBySecond(Date targetdate,Integer minute){
    	if(targetdate != null && minute != null && minute != 0){
    		Long time = targetdate.getTime();
    		time =( time + (minute*1000));
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTimeInMillis(time);
    		
    		return calendar.getTime();
    	}
    	
    	return targetdate;
    }
    
    
    public static Date longToDate(Long date){
    	if(date != null){
    		Calendar c = Calendar.getInstance();
    		c.setTimeInMillis(date); 
    		return c.getTime();
    	}
    	return null;
    }
    
   

}
