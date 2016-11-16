package net.biboka.transrad.transrad.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.biboka.transrad.transrad.R;
import net.biboka.transrad.transrad.model.MyMonthlyModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by biboka on 2016.11.12..
 */

public class MyMonthlyAdapter extends BaseAdapter {
    private List<MyMonthlyModel> listElement;
    private Context context;

    public MyMonthlyAdapter(Context context, List<MyMonthlyModel> listElement) {
        this.listElement = listElement;
        this.context = context;
    }

    @Override
    public int getCount() { return listElement.size(); }

    @Override
    public Object getItem(int i) {return  listElement.get(i); }

    @Override
    public long getItemId(int i) { return i;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.mymonthly_listitem,viewGroup,false);
        TextView workPlace = (TextView) rootView.findViewById(R.id.workPlaceTextView);
        TextView honapNap = (TextView) rootView.findViewById(R.id.honapNapTextView);
        TextView hetNap = (TextView) rootView.findViewById(R.id.hetNapTextView);
        TextView coWorker = (TextView) rootView.findViewById(R.id.coWorkerTextView);

        MyMonthlyModel mm = (MyMonthlyModel) getItem(i);
        workPlace.setText(mm.getWorkplace());
        honapNap.setText(mm.getHonapNap());
        hetNap.setText(mm.getHetNap());
        coWorker.setText(mm.getCoWorker());
        return rootView;
    }

    public void addAll (ArrayList<MyMonthlyModel> tomb) {
        listElement.addAll(tomb);
        notifyDataSetChanged();
    }
}
