package com.integration.mail;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ZhangGang on 2018/12/6.
 */
@Data
public class MailBean implements Serializable {
    private String recipient;   //邮件接收人
    private String subject; //邮件主题
    private String content; //邮件内容

}