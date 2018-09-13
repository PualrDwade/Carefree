package com.csu.carefree.Model.TraverAsk;

public class UserAnswer {
    private String id;
    private String title;
    private String answer_content;
    private String add_time;
    private String ask_id;
    private String user_id;

    @Override
    public String toString() {
        return "UserAnswer{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", answer_content='" + answer_content + '\'' +
                ", ask_id='" + ask_id + '\'' +
                ", add_time='" + add_time + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public String getAsk_id() {
        return ask_id;
    }

    public void setAsk_id(String ask_id) {
        this.ask_id = ask_id;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public UserAnswer() {
    }

    public UserAnswer(String id, String title, String answer_content,
                      String ask_id, String add_time, String user_id) {
        this.id = id;
        this.title = title;
        this.answer_content = answer_content;
        this.ask_id = ask_id;
        this.add_time = add_time;
        this.user_id = user_id;
    }

}
