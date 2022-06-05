package com.example.moveplayer.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Movie")
public class MovieRoom
{
    @PrimaryKey(autoGenerate = false)@NonNull
    int id;
    String name;
    String description;
    float rate;
    String category;

    public MovieRoom() {
    }

    public MovieRoom(int id, String name, String description, float rate, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
