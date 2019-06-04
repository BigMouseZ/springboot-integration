package com.integration.create.simplefactory.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by ZhangGang on 2019/6/4.
 */
//再通过一个工具类XMLUtil来读取配置文件中的字符串参数，XMLUtil类的代码如下所示：


public class XMLUtil {
    //该方法用于从XML配置文件中提取图表类型，并返回类型名
    public static String getChartType() {
        try {
            //创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            /**我们可以将静态工厂方法的参数存储在XML或properties格式的配置文件中，如下config.xml所示：
             * config.xml 中的内容
             <?xml version="1.0"?>
             <config>
             <chartType>histogram</chartType>
             </config>
             * */
            doc = builder.parse(new File("config.xml"));

            //获取包含图表类型的文本节点
            NodeList nl = doc.getElementsByTagName("chartType");
            Node classNode = nl.item(0).getFirstChild();
            String chartType = classNode.getNodeValue().trim();
            return chartType;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
