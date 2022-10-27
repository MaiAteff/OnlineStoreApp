package com.example.nectar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nectar.R;
import com.example.nectar.model.Product;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> implements Filterable {
    private ArrayList<Product> listdata;
    private Context context;
    private static ItemClickListener mClickListener;
    private static addClickListener bClickListener;

    public MyListAdapter(Context context, ArrayList<Product> listdata) {
        this.context = context;
        this.listdata = listdata;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(listdata.get(position).title);
        holder.price.setText("$" + String.valueOf(listdata.get(position).price));
        Glide.with(context).load(listdata.get(position).image).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img,addButton;
        public TextView title, price;
        View view1;

        public ViewHolder(View itemView) {
            super(itemView);

            this.img = itemView.findViewById(R.id.img);
            this.title = itemView.findViewById(R.id.title);
            this.price = itemView.findViewById(R.id.price);
            this.addButton = itemView.findViewById(R.id.addButton);
            this.view1 = itemView;

            itemView.setOnClickListener(view -> {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            });

            addButton.setOnClickListener(view -> {
                if (bClickListener != null) bClickListener.onItemClick(view, getAdapterPosition());
            });
        }
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public void setClickListener(addClickListener itemClickListener) {
        this.bClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface addClickListener {
        void onItemClick(View view, int position);
    }
}
