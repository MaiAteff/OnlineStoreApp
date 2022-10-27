package com.example.nectar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nectar.R;

import java.util.ArrayList;
import java.util.Map;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    private ArrayList<String> text;
    private Map<String,String> img;
    private Context context;
    private static ItemClickListener mClickListener;


    public CategoryListAdapter(Context context, ArrayList<String> text, Map<String,String> img) {
        this.context = context;
        this.text = text;
        this.img = img;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.category, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(text.get(position).substring(0,1).toUpperCase() + text.get(position).substring(1));
        Glide.with(context).load(img.get(text.get(position))).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return text.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            this.img = itemView.findViewById(R.id.img);
            this.title = itemView.findViewById(R.id.title);

            itemView.setOnClickListener(view -> {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            });

        }
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


}

