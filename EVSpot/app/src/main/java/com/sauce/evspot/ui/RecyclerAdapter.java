package com.sauce.evspot.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sauce.evspot.R;
import com.sauce.evspot.dto.Station;

import java.util.List;

/**
 * Created by Junyoung on 2016-06-27.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Station> items;
    int item_layout;

    public RecyclerAdapter(Context context, List<Station> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Station station = items.get(position);
        Drawable drawable = ContextCompat.getDrawable(context, station.getImage());
        holder.image.setBackground(drawable);
        holder.name.setText(station.getName());
        holder.address.setText(station.getAddress());
        holder.status.setText(station.getStatus());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, station.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView address;
        Button status;
        ImageButton imageButton;
        MyCardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.stationName);
            address = (TextView) itemView.findViewById(R.id.address);
            status = (Button) itemView.findViewById(R.id.status);
            imageButton = (ImageButton) itemView.findViewById(R.id.imageButton);
            cardview = (MyCardView) itemView.findViewById(R.id.cardView);
        }
    }
}