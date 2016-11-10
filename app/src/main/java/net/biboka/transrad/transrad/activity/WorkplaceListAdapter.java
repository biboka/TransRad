package net.biboka.transrad.transrad.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.biboka.transrad.transrad.R;
import net.biboka.transrad.transrad.model.WorkModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by biboka on 2016.11.08..
 */

public class WorkplaceListAdapter extends BaseAdapter {
    private List<WorkModel> sorGeci;
    private Context context;

    public WorkplaceListAdapter(Context context, List<WorkModel> sorGeci) {
        this.sorGeci = sorGeci;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sorGeci.size();
    }

    @Override
    public Object getItem(int i) {
        return sorGeci.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.workplace_listitem,viewGroup,false);
        TextView workPlace = (TextView) rootView.findViewById(R.id.workplaceTextView);
        TextView worker = (TextView) rootView.findViewById(R.id.workerTextView);
        WorkModel wm = (WorkModel) getItem(i);
        workPlace.setText(wm.getWorkSpace());
        worker.setText(wm.getWorkerName());
        return rootView;
    }

    public void addAll (ArrayList<WorkModel> tomb) {
        sorGeci.addAll(tomb);
        notifyDataSetChanged();
    }
}

