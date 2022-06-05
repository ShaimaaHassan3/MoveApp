package com.example.moveplayer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moveplayer.UserDataBase.DataBase;
import com.example.moveplayer.room.MovieRoom;

import java.util.List;

public class RecycleViewAdaptar extends RecyclerView.Adapter<RecycleViewAdaptar.ProjectHolder> {

    List<MovieRoom> movies;
    MovieRoom movie;
    Context context;
    DataBase dataBase;
    int pos;
    public class ProjectHolder extends RecyclerView.ViewHolder {
        public TextView name,id;
        public TextView description;
        public TextView categoryName;
        public RatingBar rate;
        ImageButton remove;
        public ProjectHolder(@NonNull View itemView) {
            super(itemView);
            dataBase=DataBase.getInstance(context);
            context=itemView.getContext();
            name=itemView.findViewById(R.id.name);
            description=itemView.findViewById(R.id.description);
            rate=itemView.findViewById(R.id.rate);
            id=itemView.findViewById(R.id.id);
            remove=itemView.findViewById(R.id.imageViewremove);
            categoryName=itemView.findViewById(R.id.categoryName);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pos=getBindingAdapterPosition();
                    movie=movies.get(pos);
                    dataBase.getMovie().removeMovie(movie);
                    movies.remove(movie);
                    notifyItemRangeChanged(pos, movies.size());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pos=getBindingAdapterPosition();
                    movie=movies.get(pos);
                    Intent intent=new Intent(itemView.getContext(), EditMovie.class);
                    intent.putExtra("Movie_id",String.valueOf(movie.getId()));
                    intent.putExtra("Movie_Name",movie.getName());
                    intent.putExtra("Movie_Description",movie.getDescription());
                    intent.putExtra("Movie_Category",movie.getCategory());
                    intent.putExtra("Movie_Rate",movie.getRate());

                   // dataBase.getMovie().updateMovie(movie);
                    notifyItemRangeChanged(pos, movies.size());
                    context.startActivity(intent);
                }
            });

        }
    }
    public RecycleViewAdaptar(List<MovieRoom> movies,Context context) {
        this.movies = movies;
        this.context=context;
    }

    @NonNull
    @Override
    public RecycleViewAdaptar.ProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.moviedisplay,parent,false);
        ProjectHolder projectHolder=new ProjectHolder(view);
        return projectHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdaptar.ProjectHolder holder, int position)
    {
        MovieRoom movie=movies.get(position);
        holder.id.setText(String.valueOf(movie.getId()));
        holder.name.setText(movie.getName());
        holder.description.setText(movie.getDescription());
        holder.rate.setRating(movie.getRate());
        holder.categoryName.setText(movie.getCategory());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


}