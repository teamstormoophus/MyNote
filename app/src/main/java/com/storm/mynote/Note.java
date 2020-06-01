package com.storm.mynote;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    private String id;
    private String title;
    private String content;
    private Date date;
    private String tag;

    public  Note(String id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }

    @NonNull
    @Override
    public String toString() {
        return "Title: " + title + ".\n"+ content;
    }
}
