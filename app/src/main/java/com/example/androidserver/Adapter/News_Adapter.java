package com.example.androidserver.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidserver.Models.News;
import com.example.androidserver.R;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.transform.Result;

public class News_Adapter extends RecyclerView.Adapter<News_Adapter.CustomViewHolder> {
    private List<News> mlist = Collections.emptyList();

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_popular_courses, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.bind(mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(List<News> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvCity;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.imageNews);
            tvName = itemView.findViewById(R.id.tvTitle);
            tvCity = itemView.findViewById(R.id.tvContent);
        }

        public void bind(News result) {
            tvName.setText(result.getTitle());
            tvCity.setText(result.getContent());
            Picasso.get().load(result.getPath()).into(ivAvatar);
        }
    }
}
