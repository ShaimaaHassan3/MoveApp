package com.example.moveplayer.DaoRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.moveplayer.room.MovieRoom;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MovieDao
{
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   public void addMovie(MovieRoom movieRoom);
   @Update
   public void updateMovie(MovieRoom movieRoom);
   @Delete
   public void removeMovie(MovieRoom movieRoom);

   @Query("Select * From  Movie where name=:name")
   public MovieRoom getMovie(String name);

   @Query("Select * From  Movie where category=:category")
   public List<MovieRoom> getMovies(String category);

}
