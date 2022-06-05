package com.example.moveplayer.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Category")
public class CategoryRoom
{
    @PrimaryKey(autoGenerate = false)
    @NonNull
    int id;
    String Name;
    public CategoryRoom() {

    }
    public CategoryRoom(int id, String name) {
        this.id = id;
        Name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
