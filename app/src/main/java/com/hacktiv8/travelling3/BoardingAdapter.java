package com.hacktiv8.travelling3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BoardingAdapter extends RecyclerView.Adapter<BoardingAdapter.ViewHolder> {
    Context context;
    ArrayList<Boarding> list;

    public BoardingAdapter(Context context, ArrayList<Boarding> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public BoardingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items_dropping,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardingAdapter.ViewHolder holder, int position) {
        Boarding boarding = list.get(position);
        holder.station_name.setText(boarding.getBus_station());
        holder.desc.setText(boarding.getDesc());
        holder.time.setText(boarding.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder  extends  RecyclerView.ViewHolder {
        TextView station_name, desc, time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            station_name = itemView.findViewById(R.id.station_name);
            desc = itemView.findViewById(R.id.desc_tv);
            time = itemView.findViewById(R.id.time_tv);
        }
    }
}
