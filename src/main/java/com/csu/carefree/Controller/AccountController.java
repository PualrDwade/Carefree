package com.csu.carefree.Controller;


import com.csu.carefree.Model.Account.EmailVerifyRecord;
import com.csu.carefree.Model.Account.Sigon;
import com.csu.carefree.Model.Account.UserProfile;
import com.csu.carefree.Model.TraverAsk.AskAnswerContainer;
import com.csu.carefree.Model.TraverAsk.TraverNote;
import com.csu.carefree.Model.TraverAsk.UserAnswer;
import com.csu.carefree.Model.TraverAsk.UserAsk;
import com.csu.carefree.Service.AccountService;
import com.csu.carefree.Service.TraverAskService;
import com.csu.carefree.Util.PageInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
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
import java.util.HashMap;
import java.util.List;


@Controller
public class AccountController {
    /**
     * 实现用户登陆注册模块的业务
     * 实现用户中心模块的业务
     */
    //springmvc自动装配,创建一个用户的服务层接口
    @Autowired
    private AccountService accountService;
    @Autowired
    private TraverAskService traverAskService;
    private static final int ANSWERPAGESIZE = 3;

    //跳转到登陆页面的请求
    @GetMapping("/account/ViewSignonForm")
    public String ViewSigonForm() {
        return "Account/AccountLogin";
    }

    //进行登陆的请求
    @PostMapping("/account/Signon")
    public String Sigon(@RequestParam("username") String username, Model model, @RequestParam("password") String password, HttpSession session) {
        //进行业务处理(登录)
        Sigon sigon = accountService.getSigonByUserName(username);
        if (sigon != null) {
            //判断密码是否正确
            if (sigon.getPassword().equals(password)) {
                //登陆成功
                session.setAttribute("username", username);
                //加载用户信息(回答数,提问数)
                /**用户信息加载模块*/
                List<UserAsk> userAskList = accountService.getUserAskListbyName(username);
                int askNum = userAskList.size();//提问数
                int answerNum = traverAskService.getUserAnswerListByName(username).size();//回答数
                //渲染到视图,用户的问题容器
                session.setAttribute("askNum", askNum);
                session.setAttribute("answerNum", answerNum);
                //首先判断是否有详细信息字段//
                UserProfile userProfile = accountService.getUserProfileByUserName(username);
                if (userProfile == null) {
                    userProfile = new UserProfile();
                    userProfile.setEmail(username);//设置邮箱字段为用户名
                    userProfile.setImage("http://img4.imgtn.bdimg.com/it/u=1106367332,2196124484&fm=26&gp=0.jpg");//设置为默认头像
                    userProfile.setNick_name("畅游网新用户");//默认昵称
                    accountService.setUserProfile(userProfile);
                }
                model.addAttribute("userProfile", userProfile);
                session.setAttribute("userProfile", userProfile);
                /**用户信息加载完成*/
                //重定向到首页页面
                return "redirect:/";
            } else {
                System.out.println("密码错误");
                //返回给页面进行渲染
                model.addAttribute("loginResponse", 1);
                return "redirect:/account/ViewSignonForm";
            }
        } else {
            System.out.println("用户名不存在");
            //返回给页面进行渲染
            model.addAttribute("loginResponse", 2);
            return "redirect:/account/ViewSignonForm";
        }
    }

    //跳转到注册页面的请求
    @GetMapping("/account/ViewRegister")
    public String ViewRegister() {
        return "Account/AccountRegister";
    }

    //注册的操作的请求(Post操作)
    @PostMapping("/account/Register")
    public String Register(Model model, @RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        //首先判断用户是否存在
        Sigon sigon = accountService.getSigonByUserName(username);
        //如果不存在
        if (sigon == null) {
            //发送到指定邮件地址
            //把用户名和密码存入session中
            session.setAttribute("verify_username", username);
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
            //传入下一个页面获取邮箱号码进行显示
            model.addAttribute("emailSender", username);
            return "Account/SendEmailSuccees";
        }
        //用户已经存在了,传递一个值到下一个页面
        else {
            model.addAttribute("registerResponse", 1);//用户名已存在
            return "redirect:/account/ViewRegister";
        }
    }

    //跳转到找回密码的页面的请求
    @GetMapping("/account/ViewForgetForm")
    public String ViewForgetForm() {
        return "Account/ForgetPasswd";
    }

    //找回密码的操作(Post操作)的请求
    @PostMapping("/account/FindPassWord")
    public String Forget(Model model, @RequestParam("username") String username, HttpSession session) {
        try {
            //首先判断这个用户是否存在
            if (accountService.getSigonByUserName(username) == null) {
                System.out.println("不存在这个用户,请注册");
                return "common/ErrorPage";
            }
            //把用户名和密码存入session中
            session.setAttribute("verify_username", username);
            //发送到指定邮件地址
            //使用工具类的静态方法随机获得一个8位验证码
            String code = RandomNumberUtils.getRandonString(8);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            EmailVerifyRecord emailVerifyRecord = new EmailVerifyRecord(code, username, "forget", df.format(new Date()));
            //存入数据库
            accountService.setVerifyCodeRecord(emailVerifyRecord);
            //返回到发送成功页面,同时放入数据
            model.addAttribute("emailSender", username);
            //发送邮件,同时捕获异常进行处理
            try {
                EmailSendUtils.sendHtmlEmail(username, code, "forget");
            } catch (Exception e) {
                System.out.println(e);
                //发送失败,跳转到失败页面
                return "Account/SendEmailFail";
            }
            return "Account/SendEmailSuccees";
        } catch (Exception e) {
            //发生未知错误.打印错误消息,返回错误页面
            System.out.println(e);
            return "common/ErrorPage";
        }
    }

    //用户注销登陆的控制器请求
    @GetMapping("/account/Signup")
    public String Signup(HttpSession session) {
        session.removeAttribute("username");
        return "redirect:/";
    }

    //邮箱的控制器请求
    @GetMapping("/account/EmailVerify")
    public String EmailVerify(@RequestParam("code") String code, @RequestParam("type") String type, HttpSession session) {
        try {
            //存在username
            System.out.println("try.....");
            String username = (String) session.getAttribute("verify_username");
            System.out.println(username);
            if (username.equals(accountService.getEmailVerifyRecordByCodeAndType(code, type).getEmail())) {
                //如果是注册
                if (type.equalsIgnoreCase("register")) {
                    //完成注册激活
                    System.out.println("succ");
                    accountService.setSigon(username, (String) session.getAttribute("register_password"));
                    //同时进行登陆
                    session.setAttribute("username", username);
                    //直接登陆,重定向到主页
                    return "redirect:/";
                }
                //如果是找回密码
                else {
                    //跳转到重置密码的界面
                    session.setAttribute("forget_username", username);
                    return "Account/ResetPassWord";
                }
            } else {
                //邮箱验证失败
                return "Account/EmailVerifyFail";
            }
        } catch (Exception e) {
            //出错返回出错页面
            System.out.println(e);
            return "/common/ErrorPage";
        }
    }

    //进行重置密码的请求
    @PostMapping("/account/ResetPassword")
    public String ResetPassword(@RequestParam String password, Model model, HttpSession session) {
        //修改密码
        try {
            String username = (String) session.getAttribute("forget_username");
            System.out.println("更新用户信息....");
            accountService.updateSigon(username, password);
            System.out.println("修改成功");
            //重置完成,重新进行登陆
            //给前台页面返回信息
            session.removeAttribute("username");//移除登陆状态
            model.addAttribute("resetResponse", "修改密码成功,请重新登陆");
            return "redirect:/account/ViewSignonForm";
        } catch (Exception e) {
            return "common/ErrorPage";
        }
    }

    //跳转到用户中心的控制器url
    @GetMapping("/account/ViewUserCenter")
    public String ViewUserCenter(Model model, HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum) {
        //错误处理,没有登陆就发起请求
        try {
            String username = (String) session.getAttribute("username");
            if (username == null) {
                //返回登陆页面
                return "redirect:/account/ViewSignonForm";
            }
            /*** 用户问题以及问题的回复 ***/
            HashMap<UserAsk, List<UserAnswer>> askListHashMap = new HashMap<>();
            //通过用户名获取所有提问
            List<UserAsk> userAskList = accountService.getUserAskListbyName(username);
            //循环,通过每一个提问获取提问的回答
            for (UserAsk userAsk : userAskList) {
                //通过提问获取所有的回答
                List<UserAnswer> userAnswerList = traverAskService.getUserAnswerByAsk(userAsk.getId());
                //保存到map容器中
                askListHashMap.put(userAsk, userAnswerList);
            }
            //最后添加到问答容器
            AskAnswerContainer askAnswerContainer = new AskAnswerContainer(askListHashMap);
            model.addAttribute("askAnswerContainer", askAnswerContainer);
            /***** 游记内容获取*******/
            //通过用户名获取所有游记
            List<TraverNote> traverNoteList = accountService.getTraverNodeListbyName(username);
            /***用户详细信息获取***/

            PageInfo<UserAsk> userAskPageInfo = new PageInfo<>();
            userAskPageInfo.setPageData(askAnswerContainer.getUserAskList(), ANSWERPAGESIZE, pageNum);

            model.addAttribute("userAskPageInfo", userAskPageInfo);

            //完成数据渲染,返回到前端页面
            return "Account/UserCenter";
        } catch (Exception e) {
            System.out.println(e);
            return "common/ErrorPage";
        }
    }


    //跳转到我的回答界面
    @GetMapping("/account/ViewMyTraverAnswers")
    public String ViewMyTraverAnswers(Model model, HttpSession session) {
        //错误处理,没有登陆就发起请求
        String username = (String) session.getAttribute("username");
        if (username == null) {
            //返回登陆页面
            return "redirect:/account/ViewSignonForm";
        }
        //获取用户所有的回答
        List<UserAnswer> userAnswerList = accountService.getUserAnswerListByName(username);
        //渲染到前端
        model.addAttribute("userAnswerList", userAnswerList);
        return "Account/MyAnswer";
    }


    //跳转到我的问题界面
    @GetMapping("/account/ViewMyTraverAsks")
    public String ViewMyTraverAsks(Model model, HttpSession session) {
        //错误处理,没有登陆就发起请求
        String username = (String) session.getAttribute("username");
        if (username == null) {
            //返回登陆页面
            return "redirect:/account/ViewSignonForm";
        }
        /*** 用户问题以及问题的回复 ***/
        HashMap<UserAsk, List<UserAnswer>> askListHashMap = new HashMap<>();
        //通过用户名获取所有提问
        List<UserAsk> userAskList = accountService.getUserAskListbyName(username);
        //循环,通过每一个提问获取提问的回答
        for (UserAsk userAsk : userAskList) {
            //通过提问获取所有的回答
            List<UserAnswer> userAnswerList = traverAskService.getUserAnswerByAsk(userAsk.getId());
            //保存到map容器中
            askListHashMap.put(userAsk, userAnswerList);
        }
        //最后添加到问答容器
        AskAnswerContainer askAnswerContainer = new AskAnswerContainer(askListHashMap);
        model.addAttribute("askAnswerContainer", askAnswerContainer);
        return "Account/MyAsk";
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

    // 跳转到游记专区
    @GetMapping("/account/GotoCreateNote")
    public String GotoCreateNote(HttpSession session) {
        //请求转发到写游记界面
        return "TraverAsk/CreateNote";
    }

    //跳转到问答专区
    @GetMapping("/account/GotoCreateAsk")
    public String GotoCreateAsk(HttpSession session) {
        //请求转发到写问题界面
        return "redirect:/TraverAsk/QuestionAnswer";
    }


}
