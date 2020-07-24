package com.example.androidserver.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidserver.ListProductByTheloai;
import com.example.androidserver.Models.Product;
import com.example.androidserver.Models.Theloai;
import com.example.androidserver.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class Theloai_Adapter extends RecyclerView.Adapter<Theloai_Adapter.CustomViewHolder> {
    private List<Theloai> mlist;
    private int rowLayout;
    private Context mContext;

    public Theloai_Adapter(List<Theloai> mlist, int rowLayout, Context mContext) {
        this.mlist = mlist;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvColor;


        public CustomViewHolder(View v) {
            super(v);
            tvName = itemView.findViewById(R.id.tvnametype);
            tvColor = itemView.findViewById(R.id.tvColor);
        }
    }

    @Override
    public Theloai_Adapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new Theloai_Adapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Theloai_Adapter.CustomViewHolder holder, final int position) {
        holder.tvName.setText(mlist.get(position).getNamely());
        holder.tvColor.setText(mlist.get(position).getColor());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ListProductByTheloai.class);
                intent.putExtra("theloai", mlist.get(position).getId());
                intent.putExtra("namtype", mlist.get(position).getNamely());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return mlist.size();
    }
    public void setData(List<Theloai> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }






}
