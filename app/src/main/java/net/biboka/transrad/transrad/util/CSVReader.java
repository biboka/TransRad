package net.biboka.transrad.transrad.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.util.SparseArray;

import net.biboka.transrad.transrad.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by biboka on 2016.11.08..
 */

public class CSVReader {
    private ArrayList<String> header;
    private ArrayList<ArrayList<String>> content;
    boolean alreadyRead = false;
    private Context context;

    public CSVReader(InputStream fajl) {
        getTable(context,fajl);
    }

    public void getTable(Context context,InputStream csv) {
        try  {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("assets/tr201611.csv")));
            String line = "";
            line = reader.readLine();
            String[] kocka = line.split(",");
            for (int i=1;i<kocka.length;i++) {
                header.add(kocka[i]);
            }
            content = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                kocka = line.split(",");
                ArrayList<String> tmp = new ArrayList<>();
                for (int i=1;i<kocka.length;i++) {
                    if (kocka[i] == null) {
                        tmp.add("");
                    } else {
                        tmp.add(kocka[i]);
                    }
                }
                content.add(tmp);
            }
            reader.close();
            Log.d("CSV READER",header.toString());
            alreadyRead = true;
        } catch (Exception e){}
    }

    public ArrayList<String> getHeader() {
        return  header;
    }

    public ArrayList<ArrayList<String>> getContent() {

        return content;
    }
}
