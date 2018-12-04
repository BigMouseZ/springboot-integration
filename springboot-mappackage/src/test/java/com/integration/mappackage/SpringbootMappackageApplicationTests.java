package com.integration.mappackage;

import com.integration.mappackage.service.MapPackageService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMappackageApplicationTests {
    @Autowired
    MapPackageService mapPackageService;

    @Test
    public void contextLoads() {
        mapPackageService.packageMap(null);
    }

    @Test
    @SneakyThrows
    public void test() {
        long start = System.currentTimeMillis();
        //数据库连接地址
        String URL = "jdbc:mysql://localhost:3306/raster_maps_guangxi_s31s40?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
        //用户名
        String USERNAME = "ZhangGang";
        //密码
        String PASSWORD = "123@abc";
        //加载的驱动程序类（这个类就在我们导入的jar包中）
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        //方法：查询操作
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        String sql = " select Type, Zoom, X, Y, Tile from gmapnetcache";
        Statement state = conn.createStatement();
        //执行查询并返回结果集
        ResultSet rs = state.executeQuery(sql);
        // 打开一个随机访问文件流，按读写方式
        RandomAccessFile randomFile = new RandomAccessFile("E:\\mapcache\\"+ UUID.randomUUID()+".pak", "rw");

        int i =0;
        // 创建解析器工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        Document document = db.newDocument();
        document.setXmlStandalone(true);
        document.appendChild(document.createElement("map"));
        while (rs.next()) {  //通过next来索引：判断是否有下一个记录
            i++;
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
            Node znode, xnode, ynode;

            document.getElementsByTagName("z"+z)


        }
        rs.close();
        state.close();
        conn.close();
        randomFile.close();
        long end = System.currentTimeMillis();
        System.out.println("总耗时："+(end  - start) / 1000 + "秒");
    }
    @Test
    public void testXml(){
        Long start = System.currentTimeMillis();
        createXml();
        System.out.println("运行时间："+ (System.currentTimeMillis() - start)/1000);
    }

    /**
     * 生成xml方法
     */
    public static void createXml(){
        try {
            // 创建解析器工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document document = db.newDocument();
            // 不显示standalone="no"
            document.setXmlStandalone(true);
            Element bookstore = document.createElement("bookstore");
            // 向bookstore根节点中添加子节点book
            Element book = document.createElement("book");

            Element name = document.createElement("name");
            // 不显示内容 name.setNodeValue("不好使");
            name.setTextContent("雷神");
            book.appendChild(name);
            // 为book节点添加属性
            book.setAttribute("id", "1");
            // 将book节点添加到bookstore根节点中
            bookstore.appendChild(book);
            // 将bookstore节点（已包含book）添加到dom树中
            document.appendChild(bookstore);

            // 创建TransformerFactory对象
            TransformerFactory tff = TransformerFactory.newInstance();
            // 创建 Transformer对象
            Transformer tf = tff.newTransformer();

            // 输出内容是否使用换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            // 创建xml文件并写入内容
            tf.transform(new DOMSource(document), new StreamResult(new File("E:\\mapcache\\"+ UUID.randomUUID()+".idx")));
            System.out.println("生成book1.xml成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成book1.xml失败");
        }
    }
}
