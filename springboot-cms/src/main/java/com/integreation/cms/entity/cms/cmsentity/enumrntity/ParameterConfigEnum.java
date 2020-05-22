package com.integreation.cms.entity.cms.cmsentity.enumrntity;

public enum ParameterConfigEnum {
    // 分组
    bookSystemSetting;// 系统参数设置

    //配置项
    public enum bookSystemSettingValue {
        userRegisterAuditMode, //	用户注册审核模式（0自动、1人工）
        recommendMax,//	安卓端首页广告位推荐数量上限	10
        itemCategoryMax,//分类数量上限	8
        itemDetailFileMax,//	项目详情顶栏推荐图片/视频合计数量上限	5
        itemDetailVideoMax,//	项目详情顶栏推荐视频数量上限	1
        itemCategoryIconMax,//	分类图标字数上限	4
        itemDescriptionWordMax,	//项目简单介绍字数上限	20
        serviceNumberMax,	//客服服务客户数量上限	5
        chatRecordNumberMax,	//聊天窗口默认聊天记录数量上限	50
        currentAppVersion,  // 当前app版本号

        currentAppVersionValue, //当前app版本号值

        latestApkUrl   //
    }
}
