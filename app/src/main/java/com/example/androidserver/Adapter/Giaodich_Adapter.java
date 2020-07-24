package com.example.androidserver.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidserver.Models.Giaodich;
import com.example.androidserver.Models.Product_Type;
import com.example.androidserver.Profile_Product;
import com.example.androidserver.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.androidserver.ListProductByTheloai.types;


public class Giaodich_Adapter extends RecyclerView.Adapter<Giaodich_Adapter.MovieViewHolder> {

    private List<Giaodich> productTypes;
    private int rowLayout;
    private Context context;



    public static class MovieViewHolder extends RecyclerView.ViewHolder {
//        LinearLayout moviesLayout;
//        TextView movieTitle;
//        TextView data;
//        TextView movieDescription;
//        TextView rating;
        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvDate;


        public MovieViewHolder(View v) {
            super(v);
//            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
//            movieTitle = (TextView) v.findViewById(R.id.title);
//            data = (TextView) v.findViewById(R.id.subtitle);
//            movieDescription = (TextView) v.findViewById(R.id.description);
//            rating = (TextView) v.findViewById(R.id.rating);
            ivAvatar = itemView.findViewById(R.id.imageGiaodich);
            tvName = itemView.findViewById(R.id.tvnamespg);
            tvPrice = itemView.findViewById(R.id.tvpriceg);
            tvDate = itemView.findViewById(R.id.date);
        }

    }

    public Giaodich_Adapter(List<Giaodich> productTypes, int rowLayout, Context context) {
        this.productTypes = productTypes;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public Giaodich_Adapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.tvName.setText(productTypes.get(position).getNamesp());
        holder.tvPrice.setText("Giá: "+productTypes.get(position).getPrice()+" VNĐ");
        holder.tvDate.setText("Ngày mua: "+productTypes.get(position).getDay());
        Picasso.get().load(productTypes.get(position).getPath()).into(holder.ivAvatar);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, Profile_Product.class);
//                intent.putExtra("theloai", types);
//                intent.putExtra("namtype", productTypes.get(position).getNamesp());
//                intent.putExtra("_id", productTypes.get(position).getId());
//                intent.putExtra("path", productTypes.get(position).getPath());
//                intent.putExtra("content", productTypes.get(position).getContent());
//                intent.putExtra("price", productTypes.get(position).getPrice()+"");
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//
//
//
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return productTypes.size();
    }



}
