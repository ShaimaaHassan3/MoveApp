package com.example.moveplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.moveplayer.UserDataBase.DataBase;
import com.example.moveplayer.room.CategoryRoom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
 ListView categoryName;
 Button addCategory,addmovie;
 ArrayAdapter<String>listCategory;
 DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryName=findViewById(R.id.categoryName);
        addCategory=findViewById(R.id.addcategory);
        addmovie=findViewById(R.id.movieadd);
        dataBase=DataBase.getInstance(this);
        listCategory=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        addmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AddMovie.class);
                startActivity(intent);
            }
        });
        addCategory.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,AddCategory.class);
               startActivity(intent);
           }
       });

       loadCategory();
        List<String> categors=dataBase.getCategory().getCategorys();
        for(int i=0;i<categors.size();i++) {
            listCategory.add(categors.get(i));
            listCategory.notifyDataSetChanged();
        }
        Log.d("Size",String.valueOf(categors.size()));
        categoryName.setAdapter(listCategory);
        categoryName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = ((TextView) view).getText().toString();
                Intent intent = new Intent(MainActivity.this, MoviePage.class);
                intent.putExtra("categoryName", name);
                Intent intent1 = new Intent(MainActivity.this, AddMovie.class);
                intent.putExtra("categoryName", name);
                startActivity(intent);}
            });

    }
    public void loadCategory()
    {
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(Jason.getJason(this));
            JSONArray categories=jsonObject.getJSONArray("categories");
            for(int i=0;i<categories.length();i++) {
                JSONObject categoryObject = categories.getJSONObject(i);
                int id=categoryObject.getInt("id");
                String name = categoryObject.getString("name");
                CategoryRoom categoryRoom=new CategoryRoom(id,name);
                dataBase.getCategory().addCategory(categoryRoom);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
 }