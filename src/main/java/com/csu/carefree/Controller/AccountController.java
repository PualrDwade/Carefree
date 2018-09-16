package com.csu.carefree.Controller;


import com.csu.carefree.Model.Account.EmailVerifyRecord;
import com.csu.carefree.Model.Account.Sigon;
import com.csu.carefree.Model.Account.UserProfile;
import com.csu.carefree.Model.TraverAsk.TraverNote;
import com.csu.carefree.Model.TraverAsk.UserAnswer;
import com.csu.carefree.Model.TraverAsk.UserAsk;
import com.csu.carefree.Service.AccountService;
import com.csu.carefree.Service.SystemService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.csu.carefree.Util.RandomNumberUtils;//验证码生成类
import com.csu.carefree.Util.EmailSendUtils;//邮箱发送类

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
public class AccountController {
    /**
     * 实现用户登陆注册模块的业务
     * 实现用户中心模块的业务
     */


    //springmvc自动装配,创建一个用户的服务接口
    @Autowired
    private AccountService accountService;

    //跳转到登陆页面
    @GetMapping("/account/ViewSignonForm")
    public String ViewSigonForm() {
        return "Account/AccountLogin";
    }

    //进行登陆
    @GetMapping("/account/Signon")
    public String Sigon(@RequestParam("username") String username,
                        @RequestParam("password") String password, HttpSession session) {
        //进行业务处理(登录)
        Sigon sigon = accountService.getSigonByUserName(username);
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

    //跳转到注册页面
    @GetMapping("/account/ViewRegister")
    public String ViewRegister() {
        return "Account/AccountRegister";
    }

    //注册的操作(Post操作)
    @PostMapping("/account/Register")
    public String Register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) {
        //发送到指定邮件地址
        //把用户名和密码存入session中
        session.setAttribute("register_username", username);
        session.setAttribute("register_password", password);
        //使用工具类的静态方法随机获得一个8位验证码
        String code = RandomNumberUtils.getRandonString(8);
        //存入数据库
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        EmailVerifyRecord emailVerifyRecord = new EmailVerifyRecord(code, username, "register", df.format(new Date()));
        //存入数据库
        accountService.setVerifyCodeRecord(emailVerifyRecord);
        //发送邮件,同时捕获异常进行处理
        try {
            EmailSendUtils.sendHtmlEmail(username, code, "register");
        } catch (Exception e) {
            System.out.println(e);
            //发送失败,跳转到失败页面
            return "Account/SendEmailFail";
        }
        return "Account/SendEmailSuccees";
    }


    //跳转到找回密码的页面
    @GetMapping("/account/ViewForgetForm")
    public String ViewForgetForm() {
        return "Account/ForgetPasswd";
    }

    //找回密码的操作(Post操作)
    @PostMapping("/account/Forget")
    public String Forget(
            HttpSession session) {
        //获取登陆状态
        String username = (String) session.getAttribute("username");
        if (username != null) {
            //发送到指定邮件地址
            //使用工具类的静态方法随机获得一个8位验证码
            String code = RandomNumberUtils.getRandonString(8);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            EmailVerifyRecord emailVerifyRecord = new EmailVerifyRecord(code, username, "forget", df.format(new Date()));
            //存入数据库
            accountService.setVerifyCodeRecord(emailVerifyRecord);
            //发送邮件,同时捕获异常进行处理
            try {
                EmailSendUtils.sendHtmlEmail(username, code, "forget");
            } catch (Exception e) {
                System.out.println(e);
                //发送失败,跳转到失败页面
                return "Account/SendEmailFail";
            }
            //返回到发送成功页面
            return "Account/SendEmailSuccees";
        }
        return "common/ErrorPage";
    }

    //用户注销登陆的控制器请求url
    @GetMapping("/account/Signup")
    public String Signup(HttpSession session) {
        session.removeAttribute("username");
        return "index";
    }

    //邮箱的控制器请求url
    @GetMapping("/account/EmailVerify")
    public String EmailVerify(
            @RequestParam("code") String code,
            @RequestParam("type") String type,
            HttpSession session) {
        try {
            //存在username
            System.out.println("try.....");
            String username = (String) session.getAttribute("register_username");
            System.out.println(username);
            if (username.equals(accountService.getEmailVerifyRecordByCodeAndType(code, type).getEmail())) {
                //如果是注册
                if (type.equalsIgnoreCase("register")) {
                    //完成注册激活
                    accountService.setSigon(username, (String) session.getAttribute("register_password"));
                    //同时进行登陆
                    session.setAttribute("username", username);
                    return "index";
                }
                //如果是找回密码
                else {
                    //跳转到重置密码的界面
                    session.setAttribute("resetpassword_user", username);
                    return "Account/ResetPassWord";
                }
            } else {
                return "Account/EmailVerifyFail";
            }
        } catch (Exception e) {
            //出错返回出错页面
            System.out.println(e);
            return "/common/ErrorPage";
        }
    }

    //重置密码的控制器url
    @GetMapping("/account/ResetPassword")
    public String ResetPassword(@RequestParam String password, HttpSession session) {
        //修改密码
        String username = (String) session.getAttribute("resetpassword_user");
        if (username != null) {
            accountService.updateSigon(username, password);
            //重置完成,重新进行登陆
            return "Account/AccountLogin";
        } else {
            //重置失败
            return "Account/ResetError";
        }
    }

    //跳转到用户中心的控制器url
    @GetMapping("/account/ViewUserCenter")
    public String ViewUserCenter(Model model, HttpSession session) {
//        //错误处理,没有登陆就发起请求
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            //返回登陆页面
//            return "Account/AccountLogin";
//        }
//        //用户名不为空,则通过username获取需要的信息进行渲染
//        List<TraverNote> traverNoteList = accountService.getTraverNodeListbyName(username);
//        List<UserAsk> userAskList = accountService.getUserAskListbyName(username);
//        List<UserAnswer> userAnswerList = accountService.getUserAnswerListByName(username);
//        UserProfile userProfile = accountService.getUserProfile(username);
//        model.addAttribute("traverNoteList", traverNoteList);
//        model.addAttribute("userAskList", userAskList);
//        model.addAttribute("userAnswerList", userAnswerList);
//        model.addAttribute("userProfile", userProfile);
        return "Account/UserCenter";
    }

    //跳转到我的游记界面
    @GetMapping("/account/ViewMyTraverNotes")
    public String ViewMyTraverNotes(Model model, HttpSession session) {
//        //错误处理,没有登陆就发起请求
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            //返回登陆页面
//            return "Account/AccountLogin";
//        }
//        //用户名不为空,则通过username获取需要的信息进行渲染
//        UserProfile userProfile = accountService.getUserProfile(username);
//        List<TraverNote> traverNoteList = accountService.getTraverNodeListbyName(username);
//        int notesNum = traverNoteList.size();
//        model.addAttribute("userProfile", userProfile);
//        model.addAttribute("traverNoteList", traverNoteList);
//        model.addAttribute("NotesNum", notesNum);
//        //后期加阅读量
        return "Account/MyTravelNote";
    }


    //跳转到我的回答界面
    @GetMapping("/account/ViewMyTraverAnswers")
    public String ViewMyTraverAnswers(Model model, HttpSession session) {
//        //错误处理,没有登陆就发起请求
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            //返回登陆页面
//            return "Account/AccountLogin";
//        }
//        //用户名不为空,则通过username获取需要的信息进行渲染
//        UserProfile userProfile = accountService.getUserProfile(username);
//        List<UserAsk> userAskList = accountService.getUserAskListbyName(username);
//        List<UserAnswer> userAnswerList = accountService.getUserAnswerByAsk(username);
//        int askNum = userAskList.size();
//        int answeredNum = 0;
//        for (UserAsk userAsk : userAskList) {
//            //遍历用户问题,得出回答总数
//            answeredNum += accountService.getUserAnswerByAsk(userAsk.getId()).size();
//        }
//        System.out.println("回答总数:" + answeredNum);
//        model.addAttribute("userProfile", userProfile);
//        model.addAttribute("userAnswerList", userAnswerList);
//        model.addAttribute("askNum", askNum);
//        model.addAttribute("answeredNum", answeredNum);//他人回答数
        return "Account/MyAnswer";
    }


    //跳转到我的问题界面
    @GetMapping("/account/ViewMyTraverAsks")
    public String ViewMyTraverAsks(Model model, HttpSession session) {
//        //错误处理,没有登陆就发起请求
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            //返回登陆页面
//            return "Account/AccountLogin";
//        }
//        //用户名不为空,则通过username获取需要的信息进行渲染
//        UserProfile userProfile = accountService.getUserProfile(username);
//        List<UserAsk> userAskList = accountService.getUserAskListbyName(username);
//        int askNum = userAskList.size();
//        int answeredNum = 0;
//        for (UserAsk userAsk : userAskList) {
//            //遍历用户问题,得出回答总数
//            answeredNum += accountService.getUserAnswerByAsk(userAsk.getId()).size();
//        }
//        System.out.println("回答总数:" + answeredNum);
//        model.addAttribute("userProfile", userProfile);
//        model.addAttribute("userAskList", userAskList);
//        model.addAttribute("askNum", askNum);
//        model.addAttribute("answeredNum", answeredNum);//他人回答数
        return "Account/MyAsk";
    }

    //跳转到用户信息设置
    @GetMapping("/account/AccountSetting")
    public String AccountSetting(HttpSession session) {
        return "account/AccountSetting";
    }

    //跳转到用户头像设置
    @GetMapping("/account/GotoSettingInfo")
    public String GotoSettingInfo(HttpSession session) {
        return "account/SettingUserInfo";
    }

    //跳转到隐私设置
    @GetMapping("/account/GotoSettingPrivacy")
    public String GotoSettingPrivacy(HttpSession session) {
        return "account/SettingPrivacy";
    }

    //跳转到安全设置
    @GetMapping("/account/GotoSettingSecurity")
    public String GotoSettingSecurity(HttpSession session) {
        return "account/SettingSecurity";
    }

    // 跳转到写游记
    @GetMapping("/account/GotoCreateNote")
    public String GotoCreateNote(HttpSession session) {
        return "TraverAsk/CreateNote";
    }

    //跳转到写问题
    @GetMapping("/account/GotoCreateAsk")
    public String GotoCreateAsk(HttpSession session) {
        return "TraverAsk/CreateAsk";
    }


}
