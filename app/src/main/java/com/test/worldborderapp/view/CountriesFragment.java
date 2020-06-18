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
import com.test.worldborderapp.model.WorldData;
import com.test.worldborderapp.presenter.CountriesPresenter;

public class CountriesFragment extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private CountriesPresenter countriesPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.countries, container, false);
        RecyclerView recyclerView =  view.findViewById(R.id.countries_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new CountriesAdapter(countriesPresenter.getCountriesList(), getFragmentManager());
        recyclerView.setAdapter(mAdapter);
        Button sortByArea = view.findViewById(R.id.sort_by_area);
        sortByArea.setOnClickListener(new SortByAreaOnClickListener());
        Button sortByName = view.findViewById(R.id.sort_by_name);
        sortByName.setOnClickListener(new SortByNameClickListener());

        return view;
    }
    CountriesFragment(CountriesPresenter countriesPresenter) {
        this.countriesPresenter = countriesPresenter;
    }
    class SortByAreaOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            countriesPresenter.sortByArea();
            mAdapter.notifyDataSetChanged();
        }
    }
    class SortByNameClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            countriesPresenter.sortByName();
            mAdapter.notifyDataSetChanged();
        }
    }

}
