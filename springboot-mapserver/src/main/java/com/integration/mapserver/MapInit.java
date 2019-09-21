package com.integration.mapserver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by Admin on 2017/3/16.
 */
@Component
public class MapInit implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (_mapInit()) {
            System.out.println("地图初始化成功");
        } else {
            System.out.println("无需地图初始化或地图初始化失败");
        }
    }

    @Value("${dburl}")
    private String dburl;
    @Value("${openDBModel:false}")
    private boolean openDBModel;

    private boolean _mapInit() {
        try {
            if (Map.nomapImg == null) {
                InputStream in = this.getClass().getResourceAsStream("/static/images/nomap.jpg");
                InputStream in2 = this.getClass().getResourceAsStream("/static/images/nomap1.jpg");
                //  File file = ResourceUtils.getFile("classpath:images/nomap.jpg");
                // File file2 = ResourceUtils.getFile("classpath:images/nomap1.jpg");
                Map.nomapImg = ImageIO.read(in);
                Map.nomapTipImg = ImageIO.read(in2);
            }
            //初始化地图配置
            String numberValue = env.getProperty("sync.mapConfig.value");   //mapList ;//prop.getProperty("sync.mapConfig.value");
//            System.out.println("启动端口：" + prop.getProperty("server.port"));
            System.out.println("启动端口：" + env.getProperty("server.port"));
            if (!openDBModel) {
                System.out.println("地图模式：访问地图包！");
                if (numberValue != null && numberValue.length() > 0) {
                    String[] numberValues = numberValue.split(",");
                    for (String map : numberValues) {
                        HashMap<String, MapTile> tileIdx = Map.mapIdx.get(map);
                        String packPaths = env.getProperty(map);//prop.getProperty(map);
                        if (tileIdx == null) {
                            tileIdx = new HashMap<String, MapTile>();
                            String[] packPathList = packPaths.split(",");
                            InputStream mapStream = null;
                            for (String one : packPathList) {
                                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                                DocumentBuilder db = dbf.newDocumentBuilder();
                                String packPath = env.getProperty(one);// prop.getProperty(one);
                                System.out.println("地图包路径：" + packPath);
                                File pack = new File(packPath + ".idx");
                                mapStream = new FileInputStream(pack);
                                Document doc = db.parse(mapStream);
                                Element root = doc.getDocumentElement();
                                NodeList zlist = root.getChildNodes();
                                String x, y, z;
                                for (int i = 0; i < zlist.getLength(); i++) {
                                    if (zlist.item(i).getNodeType() == Node.ELEMENT_NODE) {
                                        Node znode = zlist.item(i);
                                        z = znode.getNodeName();
                                        NodeList xlist = znode.getChildNodes(); //所有x轴子节点的list
                                        for (int j = 0; j < xlist.getLength(); j++) {
                                            if (xlist.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                                Node xnode = xlist.item(j);
                                                x = xnode.getNodeName();
                                                NodeList ylist = xnode.getChildNodes(); //所有y轴子节点的list
                                                for (int k = 0; k < ylist.getLength(); k++) {
                                                    if (ylist.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                                        Element ynode = (Element) ylist.item(k);
                                                        y = ynode.getNodeName();
                                                        Long offset = Long.parseLong(ynode.getAttribute("offset"));
                                                        int length = Integer.parseInt(ynode.getAttribute("length"));
                                                        MapTile tile = new MapTile(offset, length, packPath);
                                                        tileIdx.put(z + "_" + x + "_" + y, tile);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (mapStream != null) {
                                mapStream.close();
                            }
                        }
                        Map.mapConfig.put(map, packPaths);
                        Map.mapIdx.put(map, tileIdx);
                    }
                }
            } else {
                System.out.println("地图模式：访问数据库！");
            }

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
