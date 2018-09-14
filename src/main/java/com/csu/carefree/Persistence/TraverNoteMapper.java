package com.csu.carefree.Persistence;

import com.csu.carefree.Model.TraverAsk.TraverNote;

import java.util.List;

public interface TraverNoteMapper {

    //获取所有游记信息
    List<TraverNote> getTraverNoteList();

    //通过关键词搜索游记信息
    List<TraverNote> searchTraverNoteList(String keyword);

    //通过游记ID获取游记信息
    TraverNote getTraverNoteById(String traverNoteId);

    //存储用户游记信息
    void insertTraverNote(TraverNote traverNote);

    //通过username得到用户游记列表
    List<TraverNote> getTraverNodeListbyName(String username);
}
