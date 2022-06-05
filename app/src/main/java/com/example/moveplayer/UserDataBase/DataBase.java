package com.example.moveplayer.UserDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moveplayer.DaoRoom.CategoryDao;
import com.example.moveplayer.DaoRoom.MovieDao;
import com.example.moveplayer.room.CategoryRoom;
import com.example.moveplayer.room.MovieRoom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MovieRoom.class, CategoryRoom.class},version = 1,exportSchema = false)
public abstract class DataBase extends RoomDatabase
{
    public abstract MovieDao getMovie();
    public abstract CategoryDao getCategory();

    public static volatile DataBase INSTANCE;
    private static final int Number_OF_THREDTH=4;
    public static ExecutorService ThredthPool = Executors.newFixedThreadPool(Number_OF_THREDTH);


    //SingleTone

    public static DataBase getInstance (Context context)
    {
        if (INSTANCE==null)
        {
            synchronized (DataBase.class)
            {
                if(INSTANCE==null){
                    INSTANCE=
                            Room.databaseBuilder(context.getApplicationContext(),DataBase.class,
                                    "Movies")
                                    .allowMainThreadQueries()
                                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
