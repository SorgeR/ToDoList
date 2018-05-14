package com.app.sorge.todolist.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import dagger.Component;

/**
 * Created by stefa on 13/05/2018.
 */
@Entity
public class Plan {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo(name = "TITLE")
    private String title;

    @ColumnInfo(name = "DESCRIPTION")
    private String description;

    @ColumnInfo(name = "DATE")
    private String date;

    public Plan(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
