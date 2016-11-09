package net.biboka.transrad.transrad.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import net.biboka.transrad.transrad.R;
import net.biboka.transrad.transrad.activity.WorkplaceListAdapter;
import net.biboka.transrad.transrad.model.WorkModel;
import net.biboka.transrad.transrad.util.AssignUtil;
import net.biboka.transrad.transrad.util.CSVReader;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by biboka on 2016.11.08..
 */

public class PersonalAssigmentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView assignmentToday = (TextView) rootView.findViewById(R.id.maiBeoszValueTextView);
        assignmentToday.setText(AssignUtil.assignmentMap.get("UH"));
        ListView dailyList = (ListView) rootView.findViewById(R.id.dailyAssignListView);
        Calendar cal = Calendar.getInstance();
        int dayofMonth = cal.get(Calendar.DAY_OF_MONTH);
        ArrayList<WorkModel> preziList = new ArrayList<>();
        /*File nev = new File(getResources().getIdentifier("FILENAME_WITHOUTH_EXTENSION","raw","tr201611")));*/
        InputStream nev = getResources().openRawResource(R.raw.tr201611);
        CSVReader reader = new CSVReader(nev);
        ArrayList<String> header = reader.getHeader();
        ArrayList<String> content = reader.getContent().get(dayofMonth-1);
        for (int i=2;i<header.size();i++) {
            preziList.add(new WorkModel(content.get(i),AssignUtil.assignmentMap.get(header.get(i))));
        }
        dailyList.setAdapter(new WorkplaceListAdapter(preziList));
        return rootView;
    }
}
