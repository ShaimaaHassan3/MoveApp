package com.example.moveplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.moveplayer.UserDataBase.DataBase;
import com.example.moveplayer.room.MovieRoom;

public class AddMovie extends AppCompatActivity {
EditText movieName,movieId,movieDescription,movieCategory;
String nameString,description,category;int idInt;float rateFloat;
Button Edit;
RatingBar rate;
String categoryName;
DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        movieName=findViewById(R.id.namemovie);
        movieId=findViewById(R.id.idmovie);
        movieDescription=findViewById(R.id.moviedescription);
        rate=findViewById(R.id.ratingBar2);
        Edit=findViewById(R.id.addmovie);
        movieCategory=findViewById(R.id.moviecategory);
        category=movieCategory.getText().toString();
        dataBase=DataBase.getInstance(this);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameString=movieName.getText().toString();
                description=movieDescription.getText().toString();
                idInt=Integer.parseInt(movieId.getText().toString());
                category = movieCategory.getText().toString();
                rateFloat=rate.getRating();
                Log.d("jhf",nameString);
                MovieRoom movieRoom=new MovieRoom(idInt,nameString,description,rateFloat,category);
                int len=dataBase.getMovie().getMovies(categoryName).size();
                dataBase.getMovie().addMovie(movieRoom);

            }
        });
    }


}