package com.example.taskmaster;

public class Task {
    String title;
    String body;
    State state;


    public Task(String title, String body, State state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }

    public enum State {
        NEW, ASSIGNED, IN_PROGRESS, COMPLETE;
    }


    public Task(String title, String body, String state) {
        this.title = title;
        this.body = body;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
