package com.csu.carefree.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 使用SMTP协议发送电子邮件
 */
public class EmailSendUtils {


    // 邮件发送协议
    private final static String PROTOCOL = "smtp";

    // SMTP邮件服务器
    private final static String HOST = "smtp.163.com";

    // SMTP邮件服务器默认端口
    private final static String PORT = "25";

    // 是否要求身份认证
    private final static String IS_AUTH = "true";

    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
    private final static String IS_ENABLED_DEBUG_MOD = "true";

    // 发件人
    private static String from = "17726151998@163.com";

    // 收件人
    private static String to = "";


    // 初始化连接邮件服务器的会话信息
    private static Properties props = null;

    static {
        props = new Properties();
        props.setProperty("mail.transport.protocol", PROTOCOL);
        props.setProperty("mail.smtp.host", HOST);
        props.setProperty("mail.smtp.port", PORT);
        props.setProperty("mail.smtp.auth", IS_AUTH);
        props.setProperty("mail.debug", IS_ENABLED_DEBUG_MOD);
    }

    /**
     * 发送简单的html邮件
     */
    public static boolean sendHtmlEmail(String to, String code, String type) throws Exception {

        String RegistContent =
                "<!doctype html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<meta charset='UTF-8'><meta name='viewport' content='width=device-width initial-scale=1'>\n" +
                        "<title>注册</title></head>\n" +
                        "<body><h4>尊敬的用户,欢迎您注册carefree畅游网,请点击下面的链接完成账号注册</h4>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p><a href='http://localhost:8080/account/EmailVerify/" +
                        "?type=" + type + "&code=" + code +
                        "'>畅游网账号注册</a></p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p><code>技术提供:</code>陈志轩,张恺庭,黄凯,王鹭星,廖智勇</p>\n" +
                        "</body>\n" +
                        "</html>";
        String ResetContent =
                "<!doctype html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<meta charset='UTF-8'><meta name='viewport' content='width=device-width initial-scale=1'>\n" +
                        "<title>找回密码</title></head>\n" +
                        "<body><h4>尊敬的用户,感谢您使用carefree畅游网,请点击下面的链接完成验证重置密码</h4>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p><a href='http://localhost:8080/account/EmailVerify/" +
                        "?type=" + type + "&code=" + code +
                        "'>畅游网密码重置</a></p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p><code>技术提供:</code>陈志轩,张恺庭,黄凯,王鹭星,廖智勇</p>\n" +
                        "</body>\n" +
                        "</html>";
        // 创建Session实例对象
        Session session1 = Session.getInstance(props, new MyAuthenticator());

        // 创建MimeMessage实例对象
        MimeMessage message = new MimeMessage(session1);
        // 设置邮件主题
        message.setSubject("CareFree畅游网");
        // 设置发送人
        message.setFrom(new InternetAddress(from));
        // 设置发送时间
        message.setSentDate(new Date());
        // 设置收件人
        message.setRecipients(RecipientType.TO, InternetAddress.parse(to));

        // 判断是注册还是找回密码
        if (type.equalsIgnoreCase("Register"))
            // 设置html内容为邮件正文，指定MIME类型为text/html类型，并指定字符编码为gbk
            message.setContent(RegistContent, "text/html;charset=utf-8");
        else
            message.setContent(ResetContent, "text/html;charset=utf-8");
        //设置自定义发件人昵称
        String nick = "";
        try {
            nick = javax.mail.internet.MimeUtility.encodeText("CareFree畅游网");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        message.setFrom(new InternetAddress(nick + " <" + from + ">"));
        // 保存并生成最终的邮件内容
        message.saveChanges();

        // 发送邮件
        try {
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 向邮件服务器提交认证信息
     */
    static class MyAuthenticator extends Authenticator {

        private String username = "17726151998";

        private String password = "chen980911";

        public MyAuthenticator() {
            super();
        }

        public MyAuthenticator(String username, String password) {
            super();
            this.username = username;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {

            return new PasswordAuthentication(username, password);
        }
    }
}