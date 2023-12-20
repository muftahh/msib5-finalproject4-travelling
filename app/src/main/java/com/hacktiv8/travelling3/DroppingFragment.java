package com.hacktiv8.travelling3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DroppingFragment extends Fragment {
    private String keyBus;
    private DatabaseReference database;
    DroppingAdapter droppingAdapter;
    ArrayList<Dropping> list;


    public static DroppingFragment newInstance(String keyBus) {
        DroppingFragment fragment = new DroppingFragment();
        Bundle args = new Bundle();
        args.putString("keyBus", keyBus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dropping, container, false);
        if (getArguments() != null) {
            RecyclerView droppingListRv = view.findViewById(R.id.dropping_rv);
            droppingListRv.setHasFixedSize(true);

            // Perbaiki bagian ini
            droppingListRv.setLayoutManager(new LinearLayoutManager(getContext()));

            list = new ArrayList<>();
            droppingAdapter = new DroppingAdapter(getContext(), list);
            droppingListRv.setAdapter(droppingAdapter);

            keyBus = getArguments().getString("keyBus");
            String baseUrl = getResources().getString(R.string.base_url);
            database = FirebaseDatabase.getInstance(baseUrl).getReference("bus_data").child(keyBus).child("dropping");
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Dropping dropping = dataSnapshot.getValue(Dropping.class);
                        list.add(dropping);
                    }
                    droppingAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("DroppingFragment", "Database Error: " + error.getMessage());
                }
            });
        }
        return view;
    }

}