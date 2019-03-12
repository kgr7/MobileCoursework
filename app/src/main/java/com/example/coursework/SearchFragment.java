package com.example.coursework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SearchFragment extends Fragment {

    Spinner spinner;
    ListView lv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Search");

        boolean isInnerFragment = false;

        // code below changes hamburger menu to back button
        if (isInnerFragment){
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((MainActivity) getActivity()).barToggle.setDrawerIndicatorEnabled(false);
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((MainActivity)getActivity()).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
        else {
            ((MainActivity)getActivity()).barToggle.setDrawerIndicatorEnabled(true);
        }

        return inflater.inflate(R.layout.search_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        spinner = (Spinner)view.findViewById(R.id.limit_results_spinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.recent_limiter, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        spinner.setAdapter(adapter);
//
//        final DataInterface data = new DataInterface();
//        data.startProgress();
//        final ArrayList<QuakeItem> items;
//
//        lv = (ListView) view.findViewById(R.id.recentQuakes);
//        final ProgressBar progressBar = view.findViewById(R.id.progressBar1);
//        final LinearLayout header = view.findViewById(R.id.recent_item_list_header);
//        header.setVisibility(View.GONE);
//        final LinearLayout itemList = view.findViewById(R.id.recent_item_list_layout);
//        itemList.setVisibility(View.GONE);
//        progressBar.isIndeterminate();
//        final int selectedValue = 10; // initializing in case it fails
//
//
//        new Thread(new Runnable() {
//
//            ArrayList<QuakeItem> items;
//            public void run() {
//                items=data.getQuakeItems();
//                try{
//                    if (items == null){
//                        // show loading
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                progressBar.setVisibility(View.VISIBLE);
//                                header.setVisibility(View.GONE);
//                                itemList.setVisibility(View.GONE);
//                            }
//                        });
//
//                    }
//                    else{
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                progressBar.setVisibility(View.GONE);
//                                header.setVisibility(View.VISIBLE);
//                                itemList.setVisibility(View.VISIBLE);
//                            }
//                        });
//                    }
//                }
//                catch(Exception ex){
//                    Log.e("THREAD BROKEN: ", ex.getMessage());
//                }
//
//                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        fillListView(items, Integer.parseInt(parent.getSelectedItem().toString()));
//                        // parent.getSelectedItem().toString();
//                        Log.e("SELECTED: ", parent.getSelectedItem().toString());
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });
//            }
//
//        }).start();

    }

    public void fillListView(ArrayList<QuakeItem> items, int selectedValue){

        Collections.sort(items, new Comparator<QuakeItem>() {
            @Override
            public int compare(QuakeItem o1, QuakeItem o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        if (selectedValue > items.size()){
            selectedValue = items.size();
        }

        items = new ArrayList<QuakeItem>(items.subList(0, selectedValue));
        EarthquakeListViewAdapter adapter = new EarthquakeListViewAdapter(getActivity(), items);
        lv.setAdapter(adapter);
    }

}
