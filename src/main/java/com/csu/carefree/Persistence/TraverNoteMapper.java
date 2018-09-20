package com.csu.carefree.Persistence;

import com.csu.carefree.Model.TraverAsk.TraverNote;

import java.util.ArrayList;
import java.util.List;

public interface TraverNoteMapper {

    //获取所有游记信息
    ArrayList<TraverNote> getTraverNoteList();

    //通过关键词搜索游记信息
    List<TraverNote> searchTraverNoteList(String keyord);

    //通过游记ID获取游记信息
    TraverNote getTraverNoteById(String traverNoteId);

    //存储用户游记信息
    void insertTraverNote(TraverNote traverNote);

    //通过username得到用户游记列表
    List<TraverNote> getTraverNodeListbyName(String username);

    //通过城市名来查询相应的游记
    List<TraverNote> getTraverNodeListbyCityName(String cityId);

    //获得所有traverNote
    List<TraverNote> getAllTraverNoteList();

    List<TraverNote> getHotTraverNodeListbyCityName(String cityName);
}
