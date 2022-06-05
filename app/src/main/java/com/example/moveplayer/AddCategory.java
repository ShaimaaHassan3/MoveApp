package com.example.moveplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.moveplayer.UserDataBase.DataBase;
import com.example.moveplayer.room.CategoryRoom;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class AddCategory extends AppCompatActivity {
    EditText eId,eName;
    String nameca,idcat;
    Button submitCategory;
    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        eId=findViewById(R.id.idcategory);
        eName=findViewById(R.id.namecategory);
        submitCategory=findViewById(R.id.submitcategory);
        dataBase=DataBase.getInstance(this);
        submitCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameca =eName.getText().toString();
                idcat= eId.getText().toString();
                addCategory();
                Intent intent1 = new Intent(AddCategory.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
    public void addCategory()
    {
        int id=Integer.parseInt(idcat);
        CategoryRoom categoryRoom=new CategoryRoom(id,nameca);
         dataBase.getCategory().addCategory(categoryRoom);

    }

}