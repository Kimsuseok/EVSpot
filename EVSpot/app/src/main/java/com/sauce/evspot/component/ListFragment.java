package com.sauce.evspot.component;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sauce.evspot.R;
import com.sauce.evspot.dto.Station;
import com.sauce.evspot.ui.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Junyoung on 2016-06-27.
 */

public class ListFragment extends Fragment {

    final int ITEM_SIZE = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        Toast.makeText(this.getContext(), "This is ListFragment", Toast.LENGTH_SHORT).show();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Station> items = new ArrayList<>();
        Station[] station = new Station[ITEM_SIZE];
        station[0] = new Station(R.drawable.a, "성남시 분당구 123번지", "성남 충전소", "운영중");
        station[1] = new Station(R.drawable.b, "서울시 송파구 123번지", "서울 충전소", "운영중");
        station[2] = new Station(R.drawable.c, "성남시 분당구 223번지", "성남 충전소", "운영중");
        station[3] = new Station(R.drawable.d, "성남시 분당구 323번지", "성남 충전소", "운영중");
        station[4] = new Station(R.drawable.e, "성남시 분당구 423번지", "성남 충전소", "운영중");

        for(int i = 0; i < ITEM_SIZE; i++) {
            items.add(station[i]);
        }
        recyclerView.setAdapter(new RecyclerAdapter(getContext(), items, R.layout.fragment_list));

        return view;
    }
}
