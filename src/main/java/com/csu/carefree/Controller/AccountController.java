package com.csu.carefree.Controller;


import com.csu.carefree.Model.Account.EmailVerifyRecord;
import com.csu.carefree.Model.Account.Sigon;
import com.csu.carefree.Service.AccountService;
import com.csu.carefree.Service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {
    /**
     * 实现用户登陆注册模块的业务
     * 实现用户中心模块的业务
     */


    //springmvc自动装配,创建一个用户的服务接口
    @Autowired
    private AccountService accountService;
    //自动装备一个类
    @Autowired
    private SystemService systemService;
    //进行业务代码的编写

    @GetMapping("/account/ViewSignonForm")
    public String ViewSigonForm() {
        return "Account/AccountLogin";
    }


    @GetMapping("/account/Signon")
    public String Sigon(@RequestParam("username") String username,
                        @RequestParam("password") String password, HttpSession session) {
        //进行业务处理(登录)
        Sigon sigon = accountService.getSigon(username);
        if (session != null) {
            //判断密码是否正确
            if (sigon.getPassword().equals(password)) {
                //登陆成功
                session.setAttribute("username", username);
                return "index";
            } else {
                System.out.println("密码错误");
            }

        } else {
            System.out.println("用户名不存在");
        }
        return "index";
    }

    @GetMapping("/account/ViewRegister")
    public String ViewRegister() {
        return "Account/AccountRegister";
    }

    //发用邮箱的api,用来注册,找回密码
    @GetMapping("/account/Register")
    public String SendEmail(@RequestParam("type") String type,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session) {
        //发送到指定邮件地址
        //把密码存入session会话中
        session.setAttribute("password", password);
        //随机获得一个验证码
        String code = systemService.getRandomCode();
        //存入数据库
        accountService.setVerifyCode(username, code, type);
        boolean result = systemService.sendEmail(username, code, type);
        if (result == false) {
            return "Account/SendEmailFail";
        } else {
            return "Account/SendEmailSuccees";
        }
    }

    //邮箱激活的控制器请求url
    @GetMapping("/account/EmailVerify")
    public String Register(@RequestParam("username") String username,
                           @RequestParam("code") String code,
                           @RequestParam("type") String type,
                           HttpSession session) {
        if (code.equals(accountService.getVerifyCode(username, "register"))) {
            //完成注册激活
            accountService.setSigon(username, (String) session.getAttribute("password"));
            //同时进行登陆
            session.setAttribute("username", username);
            return "index";

        } else if (code.equals(accountService.getVerifyCode(username, "forget"))) {
            //完成找回密码
            session.setAttribute("resetpassword_user", username);
            return "Account/ResetPassword";
        } else {
            //未知失败
            return "Account/EmailFailError";
        }
    }

    //重置密码的控制器url
    @GetMapping("/account/ResetPassword")
    public String ResetPassword(@RequestParam String password, HttpSession session) {
        //修改密码
        String username = (String) session.getAttribute("resetpassword_user");
        if (username != null) {
            accountService.updateSigon(username, password);
            return "Account/AccountLogin";
        } else {
            return "Account/ResetError";
        }
    }


}
