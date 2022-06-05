package com.example.moveplayer.DaoRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moveplayer.room.CategoryRoom;
import com.example.moveplayer.room.MovieRoom;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addCategory(CategoryRoom categoryRoom);
    @Update
    public void updateCategory(CategoryRoom categoryRoom);
    @Delete
    public void removeCategory(CategoryRoom categoryRoom);
    @Query("Select name From  Category")
    public List<String> getCategorys();
}
