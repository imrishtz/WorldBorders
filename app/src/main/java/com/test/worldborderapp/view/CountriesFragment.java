package com.test.worldborderapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.worldborderapp.R;
import com.test.worldborderapp.model.Country;
import com.test.worldborderapp.model.WorldData;

import java.util.ArrayList;

public class CountriesFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    RecyclerView recyclerView;
    private WorldData data;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.countries, container, false);
        recyclerView =  view.findViewById(R.id.countries_list);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CountriesAdapter(data.getList(), getFragmentManager());
        recyclerView.setAdapter(mAdapter);

        Button sortByArea = view.findViewById(R.id.sort_by_area);
        sortByArea.setOnClickListener(new SortByAreaOnClickListener());
        Button sortByName = view.findViewById(R.id.sort_by_name);
        sortByName.setOnClickListener(new SortByNameClickListener());

        return view;
    }
    CountriesFragment(WorldData data) {
        this.data = data;
    }
    class SortByAreaOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            WorldData.sortByArea();
            mAdapter.notifyDataSetChanged();
        }
    }
    class SortByNameClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            WorldData.sortByNameAbc();
            mAdapter.notifyDataSetChanged();
        }
    }
}
