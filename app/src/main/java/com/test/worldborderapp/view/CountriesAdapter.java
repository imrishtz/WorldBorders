package com.test.worldborderapp.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.test.worldborderapp.R;
import com.test.worldborderapp.model.Country;
import com.test.worldborderapp.model.WorldData;

import java.util.ArrayList;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.MyViewHolder>  {
    private ArrayList<Country> mDataset;
    private FragmentManager fragmentManager;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView nativeName;
        TextView area;


        MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.country_name);
            nativeName = itemView.findViewById(R.id.country_native_name);
            area = itemView.findViewById(R.id.country_area);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            Country country =  mDataset.get(pos);
            Fragment bordersFragment = new BordersFragment(country.getName(), country.getNativeName(), WorldData.getBorderingCountries(pos));
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, bordersFragment ); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CountriesAdapter(ArrayList<Country> myDataset, FragmentManager fragmentManager) {
        mDataset = myDataset;
        this.fragmentManager = fragmentManager;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CountriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View vh = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_row, parent, false);

        return new MyViewHolder(vh);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name.setText(mDataset.get(position).getName());
        holder.nativeName.setText(mDataset.get(position).getNativeName());
        holder.area.setText(String.valueOf(mDataset.get(position).getArea()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
