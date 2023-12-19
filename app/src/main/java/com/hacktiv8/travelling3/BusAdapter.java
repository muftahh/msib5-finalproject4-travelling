package com.hacktiv8.travelling3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ViewHolder> {

    Context context;
    ArrayList<Bus> list;

    public BusAdapter(Context context, ArrayList<Bus> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_ticket,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bus bus = list.get(position);
        holder.pt_name.setText(bus.getPt_name());
        holder.price.setText(bus.getPrice());
        holder.fasility.setText(bus.getFacility());
        holder.departure.setText(bus.getDeparture());
        holder.travel_time.setText(bus.getTravel_time());
        holder.number_seat.setText(String.valueOf(bus.getNumber_seats()));
        holder.number_bed.setText(String.valueOf(bus.getNumber_beds()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView pt_name, price, fasility, number_seat, number_bed, departure, travel_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pt_name = itemView.findViewById(R.id.itemLT_namaBis);
            price = itemView.findViewById(R.id.itemLT_hargaBis);
            fasility = itemView.findViewById(R.id.itemLT_fasilitasBis);
            departure = itemView.findViewById(R.id.itemLT_departure);
            travel_time = itemView.findViewById(R.id.itemLT_jlmWaktuBis);
            number_seat = itemView.findViewById(R.id.number_seat);
            number_bed = itemView.findViewById(R.id.number_bed);
        }
    }
}
