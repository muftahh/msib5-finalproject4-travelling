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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BoardingFragment extends Fragment {

    private String keyBus;

    private DatabaseReference database;
    BoardingAdapter boardingAdapter;
    ArrayList<Boarding> list;

    public BoardingFragment() {

    }

    public static BoardingFragment newInstance(String keyBus) {
        BoardingFragment fragment = new BoardingFragment();
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
        View view = inflater.inflate(R.layout.fragment_boarding, container, false);
        if (getArguments() != null) {
            RecyclerView boardingListRv = view.findViewById(R.id.boarding_rv);
            boardingListRv.setHasFixedSize(true);

            // Perbaiki bagian ini
            boardingListRv.setLayoutManager(new LinearLayoutManager(getContext()));

            list = new ArrayList<>();
            boardingAdapter = new BoardingAdapter(getContext(), list);
            boardingListRv.setAdapter(boardingAdapter);

            keyBus = getArguments().getString("keyBus");
            String baseUrl = getResources().getString(R.string.base_url);
            database = FirebaseDatabase.getInstance(baseUrl).getReference("bus_data").child(keyBus).child("boarding");
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Boarding boarding = dataSnapshot.getValue(Boarding.class);
                        list.add(boarding);
                    }
                    boardingAdapter.notifyDataSetChanged();
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
