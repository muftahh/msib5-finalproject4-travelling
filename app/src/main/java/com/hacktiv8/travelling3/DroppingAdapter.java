package com.hacktiv8.travelling3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DroppingAdapter extends RecyclerView.Adapter<DroppingAdapter.ViewHolder> {
    Context context;
    ArrayList<Dropping> list;

    public DroppingAdapter(Context context, ArrayList<Dropping> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public DroppingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items_dropping,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DroppingAdapter.ViewHolder holder, int position) {
        Dropping dropping = list.get(position);
        holder.station_name.setText(dropping.getBus_station());
        holder.desc.setText(dropping.getDesc());
        holder.time.setText(dropping.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView station_name, desc, time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            station_name = itemView.findViewById(R.id.station_name);
            desc = itemView.findViewById(R.id.desc_tv);
            time = itemView.findViewById(R.id.time_tv);
        }
    }
}
