package com.integration.test;

import com.signalway.config.hikvision.HikDictionary;
import com.signalway.config.target.PoliceCarConfig;
import com.signalway.dao.mycat.DictionariesPojoMapper;
import com.signalway.dao.mycat.PeccancyCarPojoMapper;
import com.signalway.dao.mycat.PeccancycarOperationRecordEntityMapper;
import com.signalway.pojo.sichuan.liangshanzhou.PeccancycarOperationRecordEntity;
import com.signalway.pojo.table.PeccancyCarPojo;
import com.signalway.util.FastdfsUtil;
import com.signalway.util.XmlParseUtil;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by ZhangGang on 2019/7/9.
 * 只针对重庆长寿的改造，需要发送短信 hikvision.properties
 */
@org.springframework.stereotype.Service
public class SendMessageServiceImpl implements SendMessageService {

    private Logger logger = LoggerFactory.getLogger(SendMessageServiceImpl.class);

    @Value("${hikvision.zdbs}")
    private String zdbs;
    @Value("${hikvision.jkxlh}")
    private String jkxlh;
    @Value("${hikvision.sendMessageUrl}")
    private String sendMessageUrl;
    @Value("${hikvision.dxnr}")
    private String dxnr;
    @Value("${hikvision.cjbm}")
    private String cjbm;
    @Value("${hikvision.cjr}")
    private String cjr;
    @Value("${hikvision.cjrmc}")
    private String cjrmc;
    @Value("${hikvision.yhbz}")
    private String yhbz;
    @Value("${hikvision.dwmc}")
    private String dwmc;
    @Value("${hikvision.dwjgdm}")
    private String dwjgdm;
    @Value("${hikvision.yhxm}")
    private String yhxm;
    @Value("${hikvision.jkid}")
    private String jkid;
    @Value("${hikvision.methodName}")
    private String methodName;

    /**
     * @param car 违法数据
     * @return 映射的违法数据，只保留业务所需的字段，用于保存到redis中
     */
    public synchronized PeccancycarOperationRecordEntity getJiNanPeccancyTo(PeccancyCarPojo car) {

        PeccancycarOperationRecordEntity beforePeccancy = getPeccnacyTempByMysql(car);

        if (beforePeccancy == null) {
            insertPeccancyTemp(car);
            return null;
        }
        return beforePeccancy;
    }

    private PeccancycarOperationRecordEntity getPeccnacyTempByMysql(PeccancyCarPojo car) {
        PeccancycarOperationRecordEntity query = new PeccancycarOperationRecordEntity();
        query.setCarno(car.getCarno());
        query.setCarnocolor(car.getCarnocolor());
        query.setAddresscode(car.getAddresscode());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        query.setDayDate(sdf1.format(car.getCollectiontime()));

        PeccancycarOperationRecordEntity entity = peccancyTempMapper.selectOneByCondition(query);

        return entity;
    }

    private int insertPeccancyTemp(PeccancyCarPojo car) {
        PeccancycarOperationRecordEntity entity = new PeccancycarOperationRecordEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setCarno(car.getCarno());
        entity.setCarnocolor(car.getCarnocolor());
        entity.setCollectiontime(car.getCollectiontime());
        entity.setAddresscode(car.getAddresscode());
        entity.setIspush("0");
        entity.setAddtime(new Date());
        entity.setPeccancycarid(car.getId());
        entity.setLatitude(Optional.ofNullable(car.getLatitude()).map(d -> d).orElse(0d));
        entity.setLongitude(Optional.ofNullable(car.getLongitude()).map(d -> d).orElse(0d));
        entity.setAddress(car.getAddress());
        entity.setIllegalcode(car.getIllegalcode());
        entity.setMemo(car.getBig1() + "," + car.getBig2() + "," + car.getBig3() + "," + car.getSmall());
        int num = peccancyTempMapper.insertSelective(entity);
        return num;
    }

    /**
     * 给车主发送短信
     *
     * @param carPojo 违法数据
     */
    public void sendMessage(PeccancyCarPojo carPojo, PoliceCarConfig carConfig, PeccancycarOperationRecordEntity beforePeccancy) {

        try {
            //TODO  调用WSDL接口完成短信推送，保存短信发送图片
            String hphm = carPojo.getCarno();
            if ("无牌车".equals(carPojo.getCarno()) || StringUtils.isBlank(carPojo.getCarno())) {
                // 海康接口要求：不能识别车牌则填充"车牌"
                hphm = "车牌";
            }
            // 号牌种类
            String hpzl = HikDictionary.getHpzlMap(carPojo.getCarnocolor());
            String message = new String(dxnr.getBytes("iso-8859-1"), "utf-8").replace("[carno]", hphm)
                    .replace("[address]", carPojo.getAddress())
                    .replace("[minute]", turnToZh(carPojo.getCollectiontime()));
            String ret = "";
            String endpoint = sendMessageUrl;
            ;
//            String methodName = "writeObjectOut";
            StringBuffer sb = new StringBuffer(
                    "<?xml version=\"1.0\" encoding=\"GBK\"?>");
            sb.append("<root>");
            sb.append("<wzxx>");
            appendTag(sb, "hpzl", hpzl);
            appendTag(sb, "hphm", hphm);
            appendTag(sb, "dxnr", message);//这里是任一符合短信模板的内容。
            appendTag(sb, "cjbm", cjbm);
            appendTag(sb, "cjr", cjr);
            appendTag(sb, "cjrmc", cjrmc);
            sb.append("</wzxx>");
            sb.append("</root>");
            Object parms[] = new Object[]{"04", jkxlh, jkid, yhbz,
                    dwmc, dwjgdm, yhxm, zdbs, sb.toString()};
            try {
                String reString = callWs(endpoint, methodName, parms);
                //    ret = String.valueOf(retObj);
                //   String reString = URLDecoder.decode(ret, "utf-8");
                //   logger.info("调用短信发送返回信息：" + reString);
                //解析返回信息
           /*     <?xml version="1.0" encoding="GBK"?>
                <root>
                <head>
                  <retval></retval>
                  <msg>[039919001]:短信发送成功</msg>
                  <message></message>
                  <message1></message1>
                  <colname></colname>
                  <smsNum>1</smsNum>
                  <code>1</code>                所有写入接口通用返回参数，1为成功，0为失败
                  <retcode></retcode>
                  <retdesc></retdesc>
                  <xm>杨建</xm>
                  <zp></zp>                      接口参数，发送短信的证据图片
                  <sjhm>13637923667</sjhm>
                  <description></description>
                  <faillist></faillist>
                  <result>84973</result>
                  <serialNumber>20161208150005862128</serialNumber> 接口参数，该条发送短信的序列号。短信发送成功的才有。
                </head>
                </root>
*/
                if (StringUtils.isNotBlank(reString)) {
                    String code = XmlParseUtil.getNode(reString, "code");
                    String serialNumber = XmlParseUtil.getNode(reString, "serialNumber");
                    String zp = XmlParseUtil.getNode(reString, "zp");
                    if ("1".equals(code)) {
                        //接口调用成功成功
                        logger.info("调用" + sendMessageUrl + "成功");
                        if (StringUtils.isNotBlank(serialNumber) && StringUtils.isNotBlank(zp)) {
                            //短信发送成功且返回发送图片存在
                            String localPath = FastdfsUtil.uploadFile(zp.trim(), "group1", "jpg");
                            logger.info("保存返回短信图片:" + localPath);
                            //big4保存图片地址
                            carPojo.setBig4(localPath);

                        } else {
                            //TODO 发送失败，给默认的吗

                            //    carPojo.setBig4(localPath);

                        }
                    }
                } else {

                    logger.error("发送短信返回信息为空：" + reString);
                }
            } catch (Exception e) {
                ret = e.getMessage();
                e.printStackTrace();
            }
        } catch (Exception e) {
            logger.error("发送短信异常：" + e.toString(), e);
        }
    }

    /***
     * 调用接口
     *
     * @param url
     * @param methodName
     * @param obj
     * @return
     * @throws Exception
     */
    public static String callWs(String url, String methodName, Object[] obj)
            throws Exception {
        URL endPoint = new URL(url);
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endPoint);
            call.setOperationName(methodName);
            call.setTimeout(60000);
            call.addParameter("xtlb", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("jkxlh", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("jkid", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("yhbz", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("dwmc", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("dwjgdm", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("yhxm", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("zdbs", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("WriteXmlDoc", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            String ret_obj = (String) call.invoke(obj);
            return ret_obj;
        } catch (Exception e) {
            throw new Exception("调用接口: " + url + " 的 " + methodName + " 方法失败"
                    + e.getMessage());
        }
    }

    private static void appendTag(StringBuffer sb, String tag, String value) {
        if (value != null) {
            sb.append("<" + tag + ">");
            sb.append(value);
            sb.append("</" + tag + ">");
        } else {
            sb.append("<" + tag + ">");
            sb.append("</" + tag + ">");
        }
    }

    private String turnToZh(Date date) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        dateTime.getYear();
        dateTime.getMonth();
        dateTime.getDayOfMonth();
        dateTime.getHour();
        dateTime.getMinute();
        return dateTime.getYear() + " 年 " + dateTime.getMonth() + " 月 " + dateTime.getDayOfMonth() + " 日 "
                + dateTime.getHour() + " 分 " + dateTime.getMinute() + " 分 ";

    }


}
