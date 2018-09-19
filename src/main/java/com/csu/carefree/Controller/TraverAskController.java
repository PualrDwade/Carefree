package com.csu.carefree.Controller;


import com.csu.carefree.Model.Account.UserProfile;
import com.csu.carefree.Model.ProductDT.HotelMsg;
import com.csu.carefree.Model.TraverAsk.AskAnswerContainer;
import com.csu.carefree.Model.TraverAsk.TraverNote;
import com.csu.carefree.Model.TraverAsk.UserAnswer;
import com.csu.carefree.Model.TraverAsk.UserAsk;
import com.csu.carefree.Service.AccountService;
import com.csu.carefree.Service.CatalogService;
import com.csu.carefree.Service.TraverAskService;
import com.csu.carefree.Service.impl.CatalogServiceImpl;
import com.csu.carefree.Util.HtmlEncode;
import com.csu.carefree.Util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.print.Book;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Controller
public class TraverAskController {

    @Autowired
    private TraverAskService traverAskService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private AccountService accountService;

    //创建静态常量
    private static final int TRAVERPAGE = 8;
    /*****************用户问答模块****************/

    private static final int TRAVELNOTEPAGESIZE = 8;

    //进入问答专区的控制层url.设置session内容
    @GetMapping("/TraverAsk/QuestionAnswer")
    public String ViewQuesttionAnswer(Model model, HttpSession session) {
        //第一次访问存入session
        if (session.getAttribute("askSum") == null) {
            int askSum = traverAskService.getUserAskList().size();
            int answerSum = traverAskService.getUserAnswerList().size();
            session.setAttribute("askSum", askSum);
            session.setAttribute("answerSum", answerSum);
        }
        return "redirect:/TraverAsk/ViewHotQuestion";
    }

    //进入用户提出问题模块url
    @GetMapping("/TraverAsk/ViewCreateAsk")
    public String ViewCreateAsk(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/account/ViewSignonForm";
        }
        return "TraverAsk/CreateAsk";
    }

    //查看热门问题
    @GetMapping("/TraverAsk/ViewHotQuestion")
    public String ViewHotQuestion(Model model, HttpSession session) {
        session.setAttribute("tab", 1);
        //穿件问答容器
        //通过获取所有提问
        List<UserAsk> askList = traverAskService.getUserAskList();//获得所有Ask
        //循环,通过每一个提问获取提问的回答
        for (UserAsk userAsk : askList) {
            //通过提问获取所有的回答
            List<UserAnswer> userAnswerList = traverAskService.getUserAnswerByAsk(userAsk.getId());
            userAsk.setAnswer_num(userAnswerList.size());
        }
        //排序关键词:评论数
        askList.sort(Comparator.reverseOrder());
        model.addAttribute("askList", askList);
        return "TraverAsk/QuestionAnswer";
    }

    //查看最新问题
    @GetMapping("/TraverAsk/ViewNewQuestion")
    public String ViewNewQuestion(Model model, HttpSession session) {
        session.setAttribute("tab", 2);
        //通过时间先后完成问题列表查询
        //排序关键词add_time
        //直接刷新网页
        List<UserAsk> askList = traverAskService.getUserAskListByTime();//获得所有Ask
        model.addAttribute("askList", askList);
        return "TraverAsk/QuestionAnswer";
    }

    //搜索问题列表
    @GetMapping("/TraverAsk/searchAskList")
    public String searchAskList(@RequestParam("keyword") String keyword, Model model) {
        //通过用户输入信息搜索问题列表
        //排序是顺序暂定为点赞数量star_num
        List<UserAsk> askList = traverAskService.searchUserAskList(keyword);
        askList.sort(Comparator.reverseOrder());
        model.addAttribute("askList", askList);
        return "TraverAsk/QuestionAnswer";
    }

    //进入问题详细信息模块
    @GetMapping("/TraverAsk/viewAskAnswerDetail")
    public String viewAskAnswerDetail(@RequestParam("askId") String askId, HttpSession session, Model model) {
        //获得当前问题ID
        //获取问题答案List
        UserAsk userAsk = traverAskService.getUserAskById(askId);
        List<UserAnswer> userAnswerList = traverAskService.getUserAnswerByAsk(userAsk.getId());
        session.setAttribute("askId", askId);
        model.addAttribute("userAsk", userAsk);
        model.addAttribute("userAnswerList", userAnswerList);
        return "TraverAsk/AskAnswerDetail";
    }

    //创建新用户问答
    //@RequestMapping(value = "/TraverAsk/createAsk" , method = RequestMethod.POST)
    //@GetMapping("/TraverAsk/createAsk")
    @RequestMapping(value = "/TraverAsk/createAsk", method = RequestMethod.POST)
    public String createAsk(@RequestParam("title") String title, @RequestParam("askContent") String askContent, HttpSession session, Model model) {
        //获得表单内容
        //以HTML形式保存用户问题
        //获得用户ID
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String askId = "test_" + (traverAskService.getUserAskList().size() - 12);
        String username = (String) session.getAttribute("username");
        UserAsk userAsk = new UserAsk(askId, title, askContent, "0", df.format(new Date()), username, "CN_city10");
        traverAskService.insertUserAsk(userAsk);
        int askSum = traverAskService.getUserAskList().size();
        session.setAttribute("askSum", askSum);

        return "redirect:/TraverAsk/ViewNewQuestion";
    }

    //问题回复
    //@GetMapping("/TraverAsk/answerAsk")
    @RequestMapping(value = "/TraverAsk/answerAsk", method = RequestMethod.POST)
    public String answerUserAsk(@RequestParam("answerContent") String answerContent, HttpSession session, Model model) {
        //获得当前问题ID
        //获取问题答案List
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String askId = (String) session.getAttribute("askId");
        String userId = (String) session.getAttribute("username");
        UserAsk userAsk = traverAskService.getUserAskById(askId);
        String answerId = "test_" + (traverAskService.getUserAnswerList().size() - 23);
        UserAnswer userAnswer = new UserAnswer(answerId, answerContent, df.format(new Date()), askId, userId);
        traverAskService.insertUserAnswer(userAnswer);
        List<UserAnswer> userAnswerList = traverAskService.getUserAnswerByAsk(userAsk.getId());
        int answerSum = traverAskService.getUserAnswerList().size();
        session.setAttribute("answerSum", answerSum);
        model.addAttribute("userAsk", userAsk);
        model.addAttribute("userAnswerList", userAnswerList);

        return "TraverAsk/AskAnswerDetail";
    }

    /*****************用户游记模块******************/

    //进入用户游记的控制器url
    @GetMapping("/TraverAsk/ViewTraverNote")
    public String ViewTraverNote(Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
        //进行业务操作
        //返回页面进行渲染
        //获取游记列表
        List<TraverNote> traverNoteList = catalogService.getTraverNoteList();
        List<TraverNote> hotTraverNoteList = catalogService.getHotTraverNoteList(6);
        PageInfo<TraverNote> traverNotePageInfo = new PageInfo<>();
        traverNotePageInfo.setPageData(traverNoteList, TRAVELNOTEPAGESIZE, pageNum);
        model.addAttribute("traverNoteList", traverNoteList);
        model.addAttribute("hotTraverNoteList", hotTraverNoteList);
        model.addAttribute("travelNotePageInfo", traverNotePageInfo);
        return "TraverAsk/TraverNoteList";
    }

    //进入用户游记详细信息界面
    @GetMapping("/TraverAsk/ViewTraverNoteDetail")
    public String viewTraverNoteDetail(@RequestParam("traverNoteId") String traverNoteId, Model model) {
        //  获取游记ID
        // 通过游记ID获取游记内容
        TraverNote traverNote = catalogService.getTraverNoteById(traverNoteId);
        UserProfile userProfile = accountService.getUserProfileByUserName(traverNote.getUser_id());
        model.addAttribute("traverNote", traverNote);
        model.addAttribute("userProfile", userProfile);
        return "TraverAsk/TraverNoteDetail";
    }

    //搜索游记功能
    @GetMapping("TraverAsk/searchNoteList")
    public String searchNoteList(@RequestParam("keyword") String keyword, Model model,
                                 @RequestParam(defaultValue = "1") Integer pageNum) {
        //根据关键词搜索游记
        List<TraverNote> traverNoteList = catalogService.searchTraverNoteList(keyword);
        List<TraverNote> hotTraverNoteList = catalogService.getHotTraverNoteList(6);
        model.addAttribute("traverNoteList", traverNoteList);
        model.addAttribute("hotTraverNoteList", hotTraverNoteList);
        PageInfo<TraverNote> travelNotePageInfo = new PageInfo<>();
        travelNotePageInfo.setPageData(traverNoteList, TRAVERPAGE, pageNum);
        model.addAttribute("travelNotePageInfo", travelNotePageInfo);
        return "TraverAsk/TraverNoteList";
    }

    //创建新用户游记
    @RequestMapping(value = "/TraverAsk/createNote", method = RequestMethod.POST)
    public String createNote(@RequestParam("title") String title, @RequestParam("noteContent") String noteContent, HttpSession session, Model model) {
        //获取用户写的游记TITLE
        //获得游记写的游记内容
        //List<TraverNote> traverNoteList = catalogService.getTraverNoteList();
        //model.addAttribute("traverNoteList", traverNoteList);
        if (session.getAttribute("username") == null) {
            return "redirect:/account/ViewSignonForm";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String traverNoteId = "CN_traverNote_test_" + (catalogService.getAllTraverNoteList().size() - 97);
        String username = (String) session.getAttribute("username");
        TraverNote traverNote = new TraverNote(traverNoteId, title, noteContent, "0", "1", df.format(new Date()), "http://software.csu.edu.cn/", username, "CN_city68");
        catalogService.insertTraverNote(traverNote);
        List<TraverNote> traverNoteList = catalogService.getTraverNoteList();
        List<TraverNote> hotTraverNoteList = catalogService.getHotTraverNoteList(6);
        model.addAttribute("traverNoteList", traverNoteList);
        model.addAttribute("hotTraverNoteList", hotTraverNoteList);
        return "redirect:/TraverAsk/ViewTraverNote";
    }

}
