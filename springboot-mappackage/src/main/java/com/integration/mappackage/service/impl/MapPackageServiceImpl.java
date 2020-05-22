package com.integration.mappackage.service.impl;

import com.integration.mappackage.dao.GmapnetcacheMapper;
import com.integration.mappackage.service.MapPackageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by ZhangGang on 2018/12/4.
 */
@Service
public class MapPackageServiceImpl implements MapPackageService {
    @Value("${map_type}")
    private int map_type;
    @Value("${zoom_min}")
    private int zoom_min;
    @Value("${zoom_max}")
    private int zoom_max;
    @Value("${pak_name}")
    private String pak_name;
    @Value("${datasource_url}")
    private String datasource_url;
    @Value("${datasource_username}")
    private String datasource_username;
    @Value("${datasource_password}")
    private String datasource_password;

    @Resource
    GmapnetcacheMapper gmapnetcacheMapper;
    @SneakyThrows
    @Override
    public void packageMapByw3c(String path) {
        long start = System.currentTimeMillis();
        //加载的驱动程序类（这个类就在我们导入的jar包中）
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        //方法：查询操作
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(datasource_url, datasource_username, datasource_password);
        String sql = " select Type, Zoom, X, Y, Tile from gmapnetcache  where Type = "+map_type+" and Zoom >= "+zoom_min+" and Zoom <= "+zoom_max+" ORDER BY zoom,x,y";
        Statement state = conn.createStatement();
        //执行查询并返回结果集
        ResultSet rs = state.executeQuery(sql);
        System.out.println("读取数据库耗时："+(System.currentTimeMillis()-start)/1000);
        // 打开一个随机访问文件流，按读写方式
        RandomAccessFile randomFile = new RandomAccessFile("D:\\mapcache\\"+ pak_name+".pak", "rw");

        // 创建解析器工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        Document  document = db.newDocument();
        document.setXmlStandalone(true);
        Element root=document.createElement("map");
        document.appendChild(root);
        while (rs.next()) {  //通过next来索引：判断是否有下一个记录
            //rs.getInt("id"); //方法：int java.sql.ResultSet.getInt(String columnLabel) throws SQLException
            int type = rs.getInt(1);  //方法：int java.sql.ResultSet.getInt(int columnIndex) throws SQLException
            int z = rs.getInt(2);
            int x = rs.getInt(3);
            int y = rs.getInt(4);
            byte[] tile = rs.getBytes(5);
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.write(tile);
            Element znode, xnode, ynode;
            NodeList znodelist =root.getElementsByTagName("z"+z);
            if(znodelist.getLength()==0){
                znode=document.createElement("z"+z);
                root.appendChild(znode);
            }else {
                znode= (Element) znodelist.item(0);
            }
            NodeList xnodeLsit = znode.getElementsByTagName("x"+x);
            if(xnodeLsit.getLength()==0){
                xnode=document.createElement("x"+x);
                znode.appendChild(xnode);
            }else {
                xnode= (Element) xnodeLsit.item(0);
            }
            NodeList ynodeList = xnode.getElementsByTagName("y"+y);
            if(ynodeList.getLength()==0){
                ynode=document.createElement("y"+y);
                xnode.appendChild(ynode);
            }else {
                ynode= (Element) xnodeLsit.item(0);
            }
            ynode.setAttribute("offset",fileLength+"");
            ynode.setAttribute("length",tile.length+"");
        }
        // 创建TransformerFactory对象
        TransformerFactory tff = TransformerFactory.newInstance();
        // 创建 Transformer对象
        Transformer tf = tff.newTransformer();

        // 输出内容是否使用换行
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        // 创建xml文件并写入内容
        tf.transform(new DOMSource(document), new StreamResult(new File("D:\\mapcache\\"+ pak_name+".idx")));
        rs.close();
        state.close();
        conn.close();
        randomFile.close();
        long end = System.currentTimeMillis();
        System.out.println("总耗时："+(end  - start) / 1000 + "秒");
    }
}
