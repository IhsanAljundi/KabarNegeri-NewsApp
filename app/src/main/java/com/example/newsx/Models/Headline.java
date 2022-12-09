package com.example.newsx.Models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Headline {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "publishedat")
    public String publishedat;

    @ColumnInfo(name = "content")
    public String content;
}
