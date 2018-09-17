package com.csu.carefree.Model.TraverAsk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//这是一个功能bean的容器,用来实现用户问答的抽象
public class AskAnswerContainer {
    //创建问答容器
    private HashMap<UserAsk, List<UserAnswer>> askListHashMap;

    public AskAnswerContainer() {
    }

    public AskAnswerContainer(HashMap<UserAsk, List<UserAnswer>> askListHashMap) {
        this.askListHashMap = askListHashMap;
    }

    //通过回答找到问题
    public UserAsk getAskByAnswer(UserAnswer userAnswer) {
        for (UserAsk userAsk : askListHashMap.keySet()) {
            if (askListHashMap.get(userAsk).contains(userAnswer)) {
                return userAsk;
            }
        }
        //循环完了还是没有找到对应的问题
        return null;
    }

    //通过问题找到所有回答
    public List<UserAnswer> getAnswerListByask(UserAsk userAsk) {
        return askListHashMap.get(userAsk);
    }

    //得到用户所有的问题
    public List<UserAsk> getUserAskList() {
        return (List<UserAsk>) askListHashMap.keySet();
    }

    //得到用户所有的回答
    public List<UserAnswer> getUserAnswerList() {
        List<UserAnswer> list = new ArrayList<>();
        for (List<UserAnswer> answers : askListHashMap.values()) {
            //添加进入list容器中
            list.addAll(answers);
        }
        return list;
    }
}
