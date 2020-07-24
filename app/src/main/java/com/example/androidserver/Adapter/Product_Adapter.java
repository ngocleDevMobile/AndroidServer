package com.example.androidserver.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidserver.Models.News;
import com.example.androidserver.Models.Product;
import com.example.androidserver.Models.Product_Type;
import com.example.androidserver.Profile_Product;
import com.example.androidserver.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.androidserver.ListProductByTheloai.types;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.CustomViewHolder> {
    private List<Product> mlist ;

     Context context;

    public Product_Adapter(List<Product> mlist, Context context) {
        this.mlist = mlist;
//        this.rowLayout = rowLayout;
        this.context = context;
    }



    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        //        LinearLayout moviesLayout;
//        TextView movieTitle;
//        TextView data;
//        TextView movieDescription;
//        TextView rating;
        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvPrice;


        public CustomViewHolder(View v) {
            super(v);
//            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
//            movieTitle = (TextView) v.findViewById(R.id.title);
//            data = (TextView) v.findViewById(R.id.subtitle);
//            movieDescription = (TextView) v.findViewById(R.id.description);
//            rating = (TextView) v.findViewById(R.id.rating);
            ivAvatar = itemView.findViewById(R.id.imageProduct);
            tvName = itemView.findViewById(R.id.tvnamesp);
            tvPrice = itemView.findViewById(R.id.tvContentproduct);
        }

    }


    @Override
    public Product_Adapter.CustomViewHolder onCreateViewHolder(ViewGroup parent,
                                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, parent, false);
        return new Product_Adapter.CustomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(Product_Adapter.CustomViewHolder holder, final int position) {
        holder.tvName.setText(mlist.get(position).getNamesp());
        holder.tvPrice.setText("Giá: "+mlist.get(position).getPrice()+" VNĐ");
        Picasso.get().load(mlist.get(position).getPath()).into(holder.ivAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Profile_Product.class);
                intent.putExtra("theloai", types);
                intent.putExtra("namtype", mlist.get(position).getNamesp());
                intent.putExtra("_id", mlist.get(position).getId());
                intent.putExtra("path", mlist.get(position).getPath());
                intent.putExtra("content", mlist.get(position).getContent());
                intent.putExtra("price", mlist.get(position).getPrice()+"");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void filterList(ArrayList<Product> filteredList) {
        mlist = filteredList;
        notifyDataSetChanged();
    }


//    @NonNull
//    @Override
//    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, parent, false);
//        return new CustomViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
//        holder.bind(mlist.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return mlist.size();
//    }
//
//    public void setData(List<Product> mlist) {
//        this.mlist = mlist;
//        notifyDataSetChanged();
//    }
//
//    class CustomViewHolder extends RecyclerView.ViewHolder {
//
//        private ImageView ivAvatar;
//        private TextView tvName;
//        private TextView tvPrice;
//
//        public CustomViewHolder(View itemView) {
//            super(itemView);
//            ivAvatar = itemView.findViewById(R.id.imageProduct);
//            tvName = itemView.findViewById(R.id.tvnamesp);
//            tvPrice = itemView.findViewById(R.id.tvContentproduct);
//        }
//
//        public void bind(Product result) {
//            tvName.setText(result.getNamesp());
//            tvPrice.setText("Giá: "+result.getPrice()+"VNĐ");
//            Picasso.get().load(result.getPath()).into(ivAvatar);
//        }
//    }
}
