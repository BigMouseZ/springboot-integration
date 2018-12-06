package com.websocket.websocketclient;/**
 * Created by 邓东海（dengdh） on 2018/11/27.
 */

/**
 * @author dengdh
 * @program: root
 * @description: 描述
 * @create: 2018-11-27
 **/
public class WSCommand {
    public static final String LOGIN = "WS.LOGIN";
    public static final String LOGIN_RETURN = "WS.LOGIN.RETURN";

    public static final String BINDING = "signalway.f3.binding";
    public static final String BINDING_RETURN = "signalway.f3.binding.return";


    public static final String PLATENUMBER_LIST = "signalway.f3.platenumberlist";
    public static final String PLATENUMBER_LIST_RETURN = "signalway.f3.platenumberlist.return";


    public static final String PARKING_RECORD_LIST = "signalway.f3.parkinglist";
    public static final String PARKING_RECORD_LIST_RETURN = "signalway.f3.parkinglist.return";

    public static final String PARKING_HISTORY_RECORD_LIST = "signalway.f3.history.parkinglist";
    public static final String PARKING_HISTORY_RECORD_LIST_RETURN = "signalway.f3.history.parkinglist.return";

    public static final String ORDERINFO = "signalway.f3.orderinfo";
    public static final String ORDERINFO_RETURN = "signalway.f3.orderinfo.return";


    public static final String ERROR_MESSAGE = "signalway.f3.error";
    public static final String PARKINGWITHOUTPLATENUMBER_RETURN = "signalway.f3.parkingWithoutPlateNumber.return";


    public static final String WITHOUTPLATENUMBEROUT = "signalway.f3.withoutPlateNumberOut";
    public static final String WITHOUTPLATENUMBEROUT_RETURN = "signalway.f3.withoutPlateNumberOut.return";
}
