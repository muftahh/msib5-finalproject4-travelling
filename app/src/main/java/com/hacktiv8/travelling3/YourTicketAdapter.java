package com.hacktiv8.travelling3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;


import java.util.List;


public class YourTicketAdapter extends ArrayAdapter<YourTicketModel> {
    public YourTicketAdapter(Context context, int ressource, List<YourTicketModel> data){
        super(context, ressource, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_your_ticket, parent, false);
        }

        YourTicketModel currentItem = getItem(position);

        if (currentItem != null) {


            TextView tv_namaBisYT = convertView.findViewById(R.id.tv_namaBisYT);
            TextView tv_jamBerangkatYT = convertView.findViewById(R.id.tv_jamBerangkatYT);
            TextView tv_namaTerminalYT = convertView.findViewById(R.id.tv_namaTerminalYT);
            TextView tv_tujuanBisYT = convertView.findViewById(R.id.tv_tujuanBisYT);

            tv_namaBisYT.setText(currentItem.getNamaBis());
            tv_jamBerangkatYT.setText(currentItem.getJamPergi());
            tv_namaTerminalYT.setText(currentItem.getAsalBis());
            tv_tujuanBisYT.setText(currentItem.getTujuanBis());
        }
        return convertView;
    }
}
