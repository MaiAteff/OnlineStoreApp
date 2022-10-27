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
import com.example.nectar.model.CartData;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<CartData> listdata;
    private Context context;
    private static SubClickListener sClickListener;
    private static AddClickListener aClickListener;
    private static DeleteClickListener dClickListener;
    public CartListAdapter(Context context, ArrayList<CartData> listdata) {
        this.context = context;
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.cart_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(listdata.get(position).title);
        holder.price.setText("$" + String.valueOf(listdata.get(position).price));
        Glide.with(context).load(listdata.get(position).image).into(holder.img);
        holder.count.setText(String.valueOf(listdata.get(position).count));

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img,delete,add,sub;
        public TextView title, count, price;
        View view1;

        public ViewHolder(View itemView) {
            super(itemView);

            this.img = itemView.findViewById(R.id.img);
            this.title = itemView.findViewById(R.id.title);
            this.price = itemView.findViewById(R.id.price);
            this.delete = itemView.findViewById(R.id.delete);
            this.count = itemView.findViewById(R.id.count);
            this.sub = itemView.findViewById(R.id.sub);
            this.add = itemView.findViewById(R.id.add);
            this.view1 = itemView;

            sub.setOnClickListener(view->{
                if(sClickListener != null) sClickListener.onSubClick(view, getAdapterPosition());
            });

            add.setOnClickListener(view->{
                if(aClickListener != null) aClickListener.onAddClick(view, getAdapterPosition());
            });

            delete.setOnClickListener(view->{
                if(dClickListener != null) dClickListener.onDeleteClick(view, getAdapterPosition());
            });
        }
    }

    public void setClickListener(SubClickListener sClickListener) {
        this.sClickListener = sClickListener;
    }

    public interface SubClickListener {
        void onSubClick(View view, int position);
    }

    public void setClickListener(AddClickListener aClickListener) {
        this.aClickListener = aClickListener;
    }

    public interface AddClickListener {
        void onAddClick(View view, int position);
    }

    public void setClickListener(DeleteClickListener dClickListener) {
        this.dClickListener = dClickListener;
    }

    public interface DeleteClickListener {
        void onDeleteClick(View view, int position);
    }
}
