package com.example.moveplayer;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public interface Jason {
    public static  String getJason(Context context) {
        String jason = null;
        try {
            InputStream inputStream = context.getAssets().open("movies-data.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            jason = new String(bufferData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jason;
    }
    public static void removeMovie(Context context,String categoryName ,String movieId)
    {
        JSONArray movies=new JSONArray();
        int index=0;
        try {
            // Create a new instance of a JSONObject
            JSONObject jsonObject=new JSONObject(Jason.getJason(context));
            JSONArray categories=jsonObject.getJSONArray("categories");
            for(int i=0;i<categories.length();i++) {
                JSONObject categoryObject = categories.getJSONObject(i);
                int id = categoryObject.getInt(String.valueOf("id"));
                String name = categoryObject.getString("name");
                JSONArray movieList = categoryObject.getJSONArray("movies");
                JSONObject moviecategory = categories.getJSONObject(i);
                if (moviecategory.getString("name").equals(categoryName)) {
                    for(int j=0;j<movieList.length();j++) {
                        JSONObject movieObject1=movieList.getJSONObject(j);
                        int idMovie=movieObject1.getInt("id");
                        if (String.valueOf(idMovie).equals(movieId))
                            continue;
                        else {
                            JSONObject movieObject2=movieList.getJSONObject(j);
                            movies.put(movieObject2);
                        }
                        categoryObject.put("movies",movies);
                    }
                }
            }

        } catch (JSONException e) {
            Log.e("", "Failed to create JSONObject", e);
        }
    }
}
