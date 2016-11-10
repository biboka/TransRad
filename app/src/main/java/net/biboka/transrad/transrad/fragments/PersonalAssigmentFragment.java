package net.biboka.transrad.transrad.fragments;


import android.os.AsyncTask;
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
import java.util.List;

/**
 * Created by biboka on 2016.11.08..
 */

public class PersonalAssigmentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        TextView assignmentToday = (TextView) rootView.findViewById(R.id.maiBeoszValueTextView);
        final ListView dailyList = (ListView) rootView.findViewById(R.id.dailyAssignListView);
        final WorkplaceListAdapter adapter = new WorkplaceListAdapter(getContext(), new ArrayList<WorkModel>());
        dailyList.setAdapter(adapter);

        assignmentToday.setText("");

        AsyncTask loadData = new AsyncTask<Object,Void, ArrayList<WorkModel>>() {
            @Override
            protected ArrayList<WorkModel> doInBackground(Object... voids) {
                Calendar cal = Calendar.getInstance();
                int dayofMonth = cal.get(Calendar.DAY_OF_MONTH);

                CSVReader reader = new CSVReader(getContext());
                ArrayList<String> header = reader.getHeader();
                ArrayList<String> content = reader.getContent().get(dayofMonth-1);
                ArrayList<WorkModel> preziList = new ArrayList<>();
                for (int i=0;i<header.size();i++) {
                    preziList.add(new WorkModel(content.get(i),AssignUtil.assignmentMap.get(header.get(i))));
                }
                return preziList;
            }


            @Override
            protected void onPostExecute(ArrayList<WorkModel> arrayLists) {
                String allWorkPlaceToday = "";
                int counter = 0;
                for (WorkModel wm: arrayLists) {
                    if (wm.getWorkerName().contains("BA")) {
                        if (counter ==0 ) {
                            allWorkPlaceToday += wm.getWorkSpace();
                            counter++;
                        } else {
                            allWorkPlaceToday += ", "+wm.getWorkSpace();
                        }
                    }
                }
                ( (TextView) rootView.findViewById(R.id.maiBeoszValueTextView)).setText(allWorkPlaceToday);
                //assignmentToday.setText(allWorkPlaceToday);
                adapter.addAll(arrayLists);
            }
        };
        loadData.execute();
        return rootView;
    }
}
