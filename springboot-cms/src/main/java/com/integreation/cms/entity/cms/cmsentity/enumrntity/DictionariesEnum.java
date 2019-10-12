package com.integreation.cms.entity.cms.cmsentity.enumrntity;


public enum DictionariesEnum {
    // 分组
    dictionaryType,                               // 字典类型
    status,                                     //状态
    checkStatus,                                     // 审核状态
    publicStatus,                                 // 发布状态
    orderStatus,                                     // 订单状态
    bookingFailCause,                                   // 预约失败原因
    serviceEndCause,                                   // 结束服务原因
    cancelBookingCause,                                   // 取消预约原因
    typeIcon,                                     // 分类图标--会变动
    baseService,                                   // 基础服务-会变动
    valueAddedService;                                 // 增值服务--会变动

    public enum dictionaryTypeValue {
        group,      //组
        enums,      //枚举
        tree,        //树
        config    //配置项
    }

    public enum statusValue {
        statusOn,//启用
        statusOff//禁用
    }

    public enum checkStatusValue {
        checkStatusYes,//已审核
        checkStatusNo //未审核
    }


    public enum publicStatusValue {
        publicStatusUp,//上架
        publicStatusDown//下架

    }

    public enum orderStatusValue {
        orderStatusWaitConfirm,//等待客服确认 0
        orderStatusWaitConsume,//等待到店消费 1
        orderStatusFail,//预约失败 2
        orderStatusConsume,//已到店消费 3
        orderStatusCancel//预约已取消 4

    }

    public enum bookingFailCauseValue {
        bookingFailCause1,//客户所选项目全部繁忙，且客户不想等待
        bookingFailCause2//客户特殊要求我方无法满足
    }

    public enum serviceEndCauseValue {
        serviceEndCause1, //预约成功
        serviceEndCause2,//预约失败
        serviceEndCause3//客户IM声明取消且客户没说明取消原因

    }

    public enum cancelBookingCauseValue {
        cancelBookingCause1, //临时有事，去不了
        cancelBookingCause2,//不想去了
        cancelBookingCause3,//预约信息有误，重新下单
        cancelBookingCause4//其它原因
    }

}
