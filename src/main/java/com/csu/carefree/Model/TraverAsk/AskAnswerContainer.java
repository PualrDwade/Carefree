package com.csu.carefree.Model.TraverAsk;

import java.util.*;

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
        //循环完了还是没有找到对应的问题,返回空
        return null;
    }

    //通过问题找到所有回答
    public List<UserAnswer> getAnswerListByask(UserAsk userAsk) {
        return askListHashMap.get(userAsk);
    }

    //得到用户所有的问题
    public List<UserAsk> getUserAskList() {
        List<UserAsk> list = new ArrayList<>();
        for (Map.Entry<UserAsk, List<UserAnswer>> entry : askListHashMap.entrySet()) {
            list.add(entry.getKey());
        }
        return list;
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

    //得到最热门的问题List排序(按照回复数进行排序)
    public List<UserAsk> getHotestAskList() {
        List<Map.Entry<UserAsk, Integer>> userAskList = new ArrayList<>();
        for (Map.Entry<UserAsk, List<UserAnswer>> entry : askListHashMap.entrySet()) {
            Map.Entry<UserAsk, Integer> item = new Map.Entry<UserAsk, Integer>() {
                @Override
                public UserAsk getKey() {
                    return entry.getKey();
                }

                @Override
                public Integer getValue() {
                    return entry.getValue().size();
                }

                @Override
                public Integer setValue(Integer value) {
                    return null;
                }
            };
            userAskList.add(item);
        }
        //重写排序方法
        Collections.sort(userAskList, new Comparator<Map.Entry<UserAsk, Integer>>() {
            @Override
            public int compare(Map.Entry<UserAsk, Integer> o1, Map.Entry<UserAsk, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        List<UserAsk> list = new ArrayList<>();
        for (Map.Entry<UserAsk, Integer> entry : userAskList) {
            list.add(entry.getKey());
        }
        return list;
    }
    //得到最新的问题
}
