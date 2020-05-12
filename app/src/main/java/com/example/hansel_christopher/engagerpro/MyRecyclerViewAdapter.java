package com.example.hansel_christopher.engagerpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private List<Complaint> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, uid, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            uid = (TextView) view.findViewById(R.id.user);
        }
    }


    public MyRecyclerViewAdapter(List<Complaint> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Complaint movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.uid.setText(movie.getDept());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}