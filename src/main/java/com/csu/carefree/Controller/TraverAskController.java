package com.csu.carefree.Controller;


import com.csu.carefree.Model.ProductDT.HotelMsg;
import com.csu.carefree.Model.TraverAsk.UserAnswer;
import com.csu.carefree.Model.TraverAsk.UserAsk;
import com.csu.carefree.Service.CatalogService;
import com.csu.carefree.Service.TraverAskService;
import com.csu.carefree.Service.impl.CatalogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TraverAskController {

    @Autowired
    private TraverAskService traverAskService;

    /*****************用户问答模块****************/

    //进入问答专区的控制层url
    @GetMapping("/TraverAsk/QuestionAnswer")
    public String ViewQuesttionAnswer(Model model) {
        //获取所有的问题列表
        //List<UserAsk> userAskList = traverAskService.getUserAskList();
        //进行分页操作
        //按点赞数进行排序
        //返回页面
        List<UserAsk> askList = traverAskService.getUserAskList();//获得所有UserAsk
        model.addAttribute("askList", askList);
        return "TraverAsk/QuestionAnswer";
    }

    //进入用户提出问题模块
    @GetMapping("/TraverAsk/ViewCreateAsk")
    public String ViewCreateAsk() {

        return "TraverAsk/CreateAsk";
    }

    //查看热门问题
    @GetMapping("/TraverAsk/ViewHotQuestion")
    public String ViewHotQuestion() {
        //通过点赞先后完成问题列表查询
        //排序关键词star_num
        //直接刷新网页
        return "TraverAsk/QuestionAnswer";
    }

    //查看最新问题
    @GetMapping("/TraverAsk/ViewNewQuestion")
    public String ViewNewQuestion() {
        //通过时间先后完成问题列表查询
        //排序关键词add_time
        //直接刷新网页
        return "TraverAsk/QuestionAnswer";
    }

    //搜索问题列表
    @GetMapping("/TraverAsk/searchAskList")
    public String searchAskList() {
        //通过用户输入信息搜索问题列表
        //排序是顺序暂定为点赞数量star_num
        return "TraverAsk/QuestionAnswer";
    }

    //进入问题详细信息模块
    @GetMapping("/TraverAsk/viewAskAnswerDetail")
    public String viewAskAnswerDetail(@RequestParam("askId") String askId, Model model) {
        //获得当前问题ID
        //获取问题答案List
        try {
            UserAsk userAsk = traverAskService.getUserAskById(askId);
            List<UserAnswer> userAnswerList = traverAskService.getUserAnswerByAsk(askId);
            model.addAttribute("userAsk", userAsk);
            model.addAttribute("userAnswerList", userAnswerList);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return "TraverAsk/AskAnswerDetail";
    }


    /*****************用户游记模块******************/

    //进入用户游记的控制器url
    @GetMapping("/TraverAsk/ViewTraverNote")
    public String ViewTraverNote() {
        //进行业务操作
        //返回页面进行渲染
        return "TraverAsk/TraverNoteList";
    }
}
