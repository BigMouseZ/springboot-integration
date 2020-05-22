package com.integration.mapserver;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;


/**
 * Created by LiYu on 2017/3/15.
 * <p>
 * public class Map extends HttpServlet {
 */

@Slf4j
@RestController
public class Map {
    @Value("${dburl}")
    private String dburl;
    @Value("${openDBModel}")
    private boolean openDBModel;
    @Value("${tablename}")
    private String tablename;

    @Autowired
    private Environment env;
    public static HashMap<String, HashMap<String, MapTile>> mapIdx = new HashMap<String, HashMap<String, MapTile>>();
    public static HashMap<String, String> mapConfig = new HashMap<String, String>();
    public static BufferedImage nomapImg = null;
    public static BufferedImage nomapTipImg = null;
    private static final Logger logger = LoggerFactory.getLogger(Map.class);

    @CrossOrigin
    @RequestMapping(value = "/map.ashx", method = RequestMethod.GET)
    public void helloController(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("image/png");
        OutputStream os = resp.getOutputStream();
        String type = req.getParameter("t");
        int x = Integer.parseInt(req.getParameter("x"));
        int y = Integer.parseInt(req.getParameter("y"));
        int z = Integer.parseInt(req.getParameter("z"));
        try {
            byte[] buffer = null;
            if (!openDBModel) {
                if (type == null || ("").equals(type)) {
                    type = "mapList";
                }
                String packPath = mapConfig.get(type);
                if (packPath == null) {
                    packPath = env.getProperty(type);//prop.getProperty(type);
                    if (packPath != null) {
                        mapConfig.put(type, packPath);
                    } else {
                        throw new Exception("无法找到该地图包:type:" + type);
                    }
                }
                buffer = this._writePackMap(type, x, y, z, packPath); //读取地图包
            } else {
                buffer = this._writeMySqlMap(type, x, y, z, dburl); //读取地图Mysql数据库
            }
            if (buffer == null) {
                throw new Exception("无法找到该地图瓦片:type:" + type + ",x:" + x + ",y:" + y + ",z:" + z);
            }
            os.write(buffer, 0, buffer.length);
        } catch (Exception ex) {
            if (nomapImg == null && z <= 14) {
                //  File file = ResourceUtils.getFile("classpath:images/nomap.jpg");
                nomapImg = ImageIO.read(this.getClass().getResourceAsStream("/static/images/nomap.jpg"));
            } else if (z <= 14) {
                ImageIO.write(nomapImg, "jpeg", os);
            }
            if (nomapTipImg == null && z > 14) {
                //   File file = new File(this.getServletContext().getRealPath("/") + "/images/nomaptip.jpg");
                nomapTipImg = ImageIO.read(this.getClass().getResourceAsStream("/static/images/nomaptip.jpg"));
            } else if (z > 14) {
                ImageIO.write(nomapTipImg, "jpeg", os);
            }
            logger.error(ex.getMessage());
        }
    }

    private byte[] _writePackMap(String type, int x, int y, int z, String packPath) throws IOException {
        byte[] buffer = null;

        //获取地图瓦片索引
        HashMap<String, MapTile> tileIdx = mapIdx.get(type);
        if (tileIdx == null) {
            return buffer;
        } else {
            //读取地图包瓦片
            MapTile mapTile = tileIdx.get("z" + z + "_" + "x" + x + "_" + "y" + y);
            if (mapTile != null) {
                buffer = new byte[mapTile.getLength()];
                RandomAccessFile rf = null;
                try {
                    rf = new RandomAccessFile(mapTile.getFilePath() + ".pak", "r");
                    rf.seek(mapTile.getOffset());
                    rf.read(buffer, 0, mapTile.getLength());
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                } finally {
                    if (null != rf) {
                        try {
                            rf.close();
                        } catch (Exception ex) {
                            logger.error(ex.getMessage());
                        }
                    }
                }
            }
            return buffer;
        }
    }

    private byte[] _writeMySqlMap(String type, int x, int y, int z, String packPath) throws Exception {
        byte[] buffer = null;

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(packPath);
        String sql = "select Tile from " + tablename + "  where x=? and y=? and zoom=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, x + "");
        ptmt.setString(2, y + "");
        ptmt.setString(3, z + "");
        ResultSet rs = ptmt.executeQuery();
        while (rs.next()) {
            buffer = rs.getBytes("Tile");
        }
        rs.close();
        ptmt.close();
        conn.close();

        return buffer;
    }
}

