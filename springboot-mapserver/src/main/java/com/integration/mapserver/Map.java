package com.integration.mapserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Properties;


/**
 * Created by LiYu on 2017/3/15.
 */
@WebServlet("/map.ashx")
public class Map extends HttpServlet {
    public static HashMap<String, HashMap<String, MapTile>> mapIdx = new HashMap<String, HashMap<String, MapTile>>();
    public static HashMap<String, String> mapConfig = new HashMap<String, String>();
    public static BufferedImage nomapImg = null;
    public static BufferedImage nomapTipImg = null;
    private static final Logger logger = LoggerFactory.getLogger(Map.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/png");
        OutputStream os = resp.getOutputStream();
        String type = req.getParameter("t");
        int x = Integer.parseInt(req.getParameter("x"));
        int y = Integer.parseInt(req.getParameter("y"));
        int z = Integer.parseInt(req.getParameter("z"));
        try {

            if (type == null || ("").equals(type)) {
                type = "pack_path";
            }
            String packPath = mapConfig.get(type);
            if (packPath == null) {
                Properties prop = new Properties();
                String realPath = getServletContext().getRealPath("/WEB-INF/classes/mapconfig.properties");
                InputStreamReader reader = new InputStreamReader(new FileInputStream(realPath), "utf-8");
                prop.load(reader);
                packPath = prop.getProperty(type);
                reader.close();
                if (packPath != null) {
                    mapConfig.put(type, packPath);
                } else {
                    throw new Exception("无法找到该地图包:type:" + type);
                }
            }

            byte[] buffer = this._writePackMap(type, x, y, z, packPath); //读取地图包
//            byte[] buffer = this._writeMySqlMap(type, x, y, z, packPath); //读取地图Mysql数据库

            if (buffer == null) {
                throw new Exception("无法找到该地图瓦片:type:" + type + ",x:" + x + ",y:" + y + ",z:" + z);
            }
            os.write(buffer, 0, buffer.length);
        } catch (Exception ex) {
            if (nomapImg == null && z <= 14) {
                File file = new File(this.getServletContext().getRealPath("/") + "/images/nomap.jpg");
                nomapImg = ImageIO.read(file);
            }else if( z <= 14){
                ImageIO.write(nomapImg, "jpeg", os);
            }
            if (nomapTipImg == null && z > 14) {
                File file = new File(this.getServletContext().getRealPath("/") + "/images/nomaptip.jpg");
                nomapTipImg = ImageIO.read(file);
            }else if(z > 14){
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

        String sql = "select Tile from gmapnetcache where x=? and y=? and zoom=?";
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