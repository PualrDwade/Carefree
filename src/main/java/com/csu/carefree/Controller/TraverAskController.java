package com.csu.carefree.Controller;


import com.csu.carefree.Model.TraverAsk.UserAsk;
import com.csu.carefree.Service.TraverAskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TraverAskController {
    //@Autowired
    private TraverAskService traverAskService;

    //进入问答专区的控制层url
    @GetMapping("/TraverAsk/QuestionAnswer")
    public String ViewQuesttionAnswer() {
        //获取所有的问题列表
        //List<UserAsk> userAskList = traverAskService.getUserAskList();
        //进行分页操作
        //按时间进行排序
        //返回页面
        return "TraverAsk/QuestionAnswer";
    }

    //进入用户游记的控制器url
    @GetMapping("TraverAsk/ViewTraverNote")
    public String ViewTraverNote() {
        //进行业务操作
        //返回页面进行渲染
        return "TraverAsk/TraverNoteList";
    }


}
