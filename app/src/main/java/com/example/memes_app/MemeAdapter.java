package com.example.memes_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memes_app.DATA.DataClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.viewHolder> {

    ArrayList<DataClass> data;
    Context content;

    public MemeAdapter(ArrayList<DataClass>data, Context content){
        this.data = data;
        this.content = content;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(content).inflate(R.layout.meme_layout , parent , false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Picasso.get()
                .load(data.get(position).getUrl())
                .into(holder.imageView);
        Log.d("dps" , data.get(position).getUrl());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends   RecyclerView.ViewHolder {
        ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
