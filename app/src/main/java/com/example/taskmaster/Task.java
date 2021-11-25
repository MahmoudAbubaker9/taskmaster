//package com.example.taskmaster;
//
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//
//@Entity
//public class Task {
//
//    @PrimaryKey(autoGenerate = true)
//    public long id;
//
//    @ColumnInfo(name="data_title")
//    String title;
//    @ColumnInfo(name="data_body")
//    String body;
//    @ColumnInfo(name="data_state")
//    State state;
//
//
//    public Task(String title, String body, State state) {
//        this.title = title;
//        this.body = body;
//        this.state = state;
//    }
//
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getBody() {
//        return body;
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }
//
//    public State getState() {
//        return state;
//    }
//
//    public void setState(State state) {
//        this.state = state;
//    }
//
//}
