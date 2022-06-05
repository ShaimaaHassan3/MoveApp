package com.example.moveplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.moveplayer.UserDataBase.DataBase;
import com.example.moveplayer.room.CategoryRoom;
import com.example.moveplayer.room.MovieRoom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MoviePage extends AppCompatActivity {
   RecyclerView recyclerView;
   JSONArray movieList;
   LinearLayoutManager linearLayoutManager;
   DataBase database;
   List<MovieRoom> moviesL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);
        recyclerView=findViewById(R.id.movierecycle);
        linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        moviesL=new ArrayList<MovieRoom>();
        database=DataBase.getInstance(this);
        String categoryName=getIntent().getStringExtra("categoryName");
        if(check())loadJson(categoryName);
        moviesL=database.getMovie().getMovies(categoryName);
        RecycleViewAdaptar recyclerView1=new RecycleViewAdaptar(moviesL,this);
        recyclerView.setAdapter(recyclerView1);

    }
    public void loadJson(String categoryName)
    {
        String categry;
        try {
            JSONObject jsonObject=new JSONObject(Jason.getJason(this));
            JSONArray categories=jsonObject.getJSONArray("categories");
            for(int i=0;i<categories.length();i++) {
                JSONObject categoryObject = categories.getJSONObject(i);
                int id = categoryObject.getInt(String.valueOf("id"));
                String name = categoryObject.getString("name");
                movieList = categoryObject.getJSONArray("movies");
                JSONObject moviecategory = categories.getJSONObject(i);
                    for(int j=0;j<movieList.length();j++)
                    {
                        JSONObject movieObject1=movieList.getJSONObject(j);
                        int idMovie=movieObject1.getInt("id");
                        Log.d("ID",String.valueOf(idMovie));
                        String nameMovie=movieObject1.getString("name");
                        String description=movieObject1.getString("description");
                        Float rate=Float.parseFloat(String.valueOf(movieObject1.getInt("rate")));
                        if (moviecategory.getString("name").equals(categoryName)) {
                      MovieRoom movieRoom=new MovieRoom(idMovie,nameMovie,description,rate,categoryName);
                      database.getMovie().addMovie(movieRoom);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public boolean check()
    {
        boolean mboolean = false;

        SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
        mboolean = settings.getBoolean("FIRST_RUN", false);
        if (!mboolean) {
            // do the thing for the first time
            settings = getSharedPreferences("PREFS_NAME", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("FIRST_RUN", true);
            editor.commit();
            return true;
        } else {
            // other time your app loads
            return false;
        }
    }
}
