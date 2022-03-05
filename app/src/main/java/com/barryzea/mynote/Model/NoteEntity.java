package com.barryzea.mynote.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String content;
    private boolean favorite;
    private String color;

    public boolean isFavorite() {
        return favorite;
    }
    public NoteEntity(String name, String content, boolean favorite,  String color) {
        this.name = name;
        this.content = content;
        this.favorite=favorite;
        this.color=color;
    }
    public NoteEntity(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
