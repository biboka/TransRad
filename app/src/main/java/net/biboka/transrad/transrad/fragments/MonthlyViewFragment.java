package net.biboka.transrad.transrad.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.biboka.transrad.transrad.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthlyViewFragment extends Fragment {


    public MonthlyViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_monthly_view, container, false);
        return  root;
    }

}
