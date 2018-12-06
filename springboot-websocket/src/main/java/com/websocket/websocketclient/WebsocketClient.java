package com.websocket.websocketclient;

import com.utils.jsonutils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangGang on 2018/11/30.
 */
@Slf4j
public class WebsocketClient {

    public static WebSocketClient client;

    public static void main(String[] args) {
        try {
            client = new WebSocketClient(new URI("ws://172.18.10.168:8100/signalway/websocket"), new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    log.info("握手成功");
                }

                @Override
                public void onMessage(String msg) {
                    log.info("收到消息==========" + msg);
                    handleMessage(msg);
                    if (msg.equals("over")) {
                        client.close();
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    log.info("链接已关闭");
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                    log.info("发生错误已关闭");
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        client.connect();
        log.info(String.valueOf(client.getDraft()));
        while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
            log.info("正在连接...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //连接成功,发送登录信息
        String loginJson = "{\n" +
                "  \"command\": \"WS.LOGIN\",\n" +
                "  \"data\": {\n" +
                "    \"loginName\": \"signalway\",\n" +
                "    \"password\": \"89ed6ba074b4feac4b63b44a45e6be8b\"\n" +
                "  }\n" +
                "}";
        client.send(loginJson);
    }

    private static void handleMessage(String message) {
        Map<String, Object> map = GsonUtil.changeGsonToMaps(message);
        if (map != null) {
            Map<String, Object> reMap = new HashMap<>();
            reMap.put("requestId", map.get("requestId").toString());
            //command 暂时不用 ，随便填
            reMap.put("command", "returnCommand");
            reMap.put("code", "0");
            reMap.put("message", "执行成功");
            reMap.put("data", null);
            List<Map<String, String>> dataList = new ArrayList<>();
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            switch (map.get("command").toString()) {
                case WSCommand.BINDING:
                    //处理signalway.f3.binding 车牌绑定
                    //默认返回即可
                    break;
                case WSCommand.PLATENUMBER_LIST:
                    //处理signalway.f3.platenumberlist 获取车主车牌列表
                    map1.put("plateNumber", "桂A12345");
                    map1.put("plateColor", "蓝");
                    dataList.add(map1);
                    map2.put("plateNumber", "桂B12345");
                    map2.put("plateColor", "蓝");
                    dataList.add(map2);
                    reMap.put("data",dataList);
                    break;
                case WSCommand.PARKING_HISTORY_RECORD_LIST:
                    //处理signalway.f3.history.parkinglist 历史订单查询
                    map1.put("orderId", "A12345");
                    map1.put("plateNumber", "桂A12345");
                    map1.put("startTime", "2018-11-20 11:00:00");
                    map1.put("orderType", "超时订单");
                    map1.put("payStatus", "已支付");
                    map1.put("price", "100");// 单位：分
                    dataList.add(map1);
                    map2.put("orderId", "B12345");
                    map2.put("plateNumber", "桂B12345");
                    map2.put("startTime", "2018-11-21 11:00:00");
                    map2.put("orderType", "超时订单");
                    map2.put("payStatus", "已支付");
                    map2.put("price", "101");// 单位：分
                    dataList.add(map2);
                    reMap.put("data", dataList);
                    break;
                case WSCommand.ORDERINFO:
                    //处理signalway.f3.orderinfo 订单详情查询
                    map1.put("orderId", "桂A12345");
                    map1.put("plateNumber", "桂A12345");
                    map1.put("startTime", "2018-11-20 11:00:00");
                    map1.put("orderType", "超时订单");
                    map1.put("payStatus", "已支付");
                    map1.put("price", "100");// 单位：分
                    reMap.put("data", map1);
                    break;
                case WSCommand.PARKING_RECORD_LIST:
                    //处理signalway.f3.parkinglist 未支付订单查询(待讨论)
                    map1.put("orderId", "A12345");
                    map1.put("plateNumber", "桂A12345");
                    map1.put("startTime", "2018-11-20 11:00:00");
                    map1.put("endTime", "2018-11-20 13:00:00");
                    map1.put("orderType", "超时订单");
                    map1.put("payStatus", "未支付");
                    map1.put("price", "100");// 单位：分
                    dataList.add(map1);
                    map2.put("orderId", "B12345");
                    map2.put("plateNumber", "桂B12345");
                    map2.put("startTime", "2018-11-21 11:00:00");
                    map2.put("orderType", "普通订单");
                    map2.put("payStatus", "未支付");
                    map2.put("price", "101");// 单位：分
                    dataList.add(map2);
                    reMap.put("data",dataList);
                    break;
                case WSCommand.WITHOUTPLATENUMBEROUT:
                     //处理signalway.f3.withoutPlateNumberOut 无牌车闸前位离场请求 询问F3该车主是否有未支付订单
                    map1.put("status", "0");
                    map1.put("memo", "该车主没有未支付的订单");
                    reMap.put("data", map1);
                    break;
                default:
                    log.error("没有这个命令");
                    break;
            }
            log.info("返回给服务端的信息："+GsonUtil.createGsonString(reMap));
            client.send(GsonUtil.createGsonString(reMap));
        }
    }
}