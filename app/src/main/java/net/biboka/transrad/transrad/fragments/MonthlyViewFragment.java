package net.biboka.transrad.transrad.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import net.biboka.transrad.transrad.R;
import net.biboka.transrad.transrad.activity.MyMonthlyAdapter;
import net.biboka.transrad.transrad.model.MyMonthlyModel;
import net.biboka.transrad.transrad.util.AssignUtil;
import net.biboka.transrad.transrad.util.CSVReader;
import net.biboka.transrad.transrad.util.FullReader;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthlyViewFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_monthly_view, container, false);

        final ListView monthlyList = (ListView) rootView.findViewById(R.id.monthlyAssignListView);
        final MyMonthlyAdapter adapter = new MyMonthlyAdapter(getContext(), new ArrayList<MyMonthlyModel>());
        monthlyList.setAdapter(adapter);

        AsyncTask loadData = new AsyncTask<Object,Void, ArrayList<MyMonthlyModel>>() {

            @Override
            protected ArrayList<MyMonthlyModel> doInBackground(Object... objects) {
                Calendar cal = Calendar.getInstance();
                int dayofMonth = cal.get(Calendar.DAY_OF_MONTH);
                int lastDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

                FullReader reader = new FullReader(getContext());
                ArrayList<String> header = reader.getHeader();
                ArrayList<ArrayList<String>> content = reader.getContent().get(dayofMonth);

                String placeholer = "-";
                ArrayList<MyMonthlyModel> elementList = new ArrayList<>();
                for (int i=0;i<lastDayOfMonth-1;i++) {
                    int counter = 0;
                    String todayWorkplace = "";
                    int todaySize = content.get(i).size();
                    Log.d("MonthlyViewFragment",i+"/"+todaySize);
                        for (int k = 2; k < todaySize; k++) {
                            if (content.get(i).get(k).contains("BA")) {
                                if (counter > 0) { todayWorkplace += ", "; }
                                todayWorkplace += AssignUtil.assignmentMap.get(header.get(k));
                                counter++;
                            }
                        }
                        elementList.add(new MyMonthlyModel(todayWorkplace,content.get(i).get(1),content.get(i).get(0),placeholer));
                }
                //adapter.addAll(elementList);
                return elementList;
            }
            @Override
            protected void onPostExecute(ArrayList<MyMonthlyModel> arrayLists) {
                adapter.addAll(arrayLists);
            }
        };

        loadData.execute();
        return rootView;
    }




    /*public MonthlyViewFragment() {
        // Required empty public constructor
    }*/


    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_monthly_view, container, false);
        return  root;
    }*/

}
