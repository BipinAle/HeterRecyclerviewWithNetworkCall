package com.example.bipin.recyclervolley.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.bipin.recyclervolley.R;
import com.example.bipin.recyclervolley.pojos.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.MyViewHolder> {
    private ArrayList<User> items = new ArrayList<>();
    Context context;

    public VerticalAdapter(ArrayList<User> items, Context context) {
        this.items = items;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_single_row, parent, false);
         return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.description.setText(items.get(position).getUsername());
        holder.title.setText(items.get(position).getName());
        Picasso.with(context).load("http://www.wetpaint.com/wp-content/uploads/2017/04/Screen-Shot-2017-04-03-at-2.18.24-PM-640x480-1491254635.png").into(holder.image);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, description;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }
}
