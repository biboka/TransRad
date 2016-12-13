package net.biboka.transrad.transrad.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
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
import java.util.Calendar;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by biboka on 2016.11.08..
 */

public class CSVReader {
    private ArrayList<String> header;
    private ArrayList<ArrayList<String>> content;
    boolean alreadyRead = false;
    private Context context;

    public void setDb(String db) {
        this.db = db;
    }

    private String db;

    public CSVReader(Context context) {
        this.context = context;
        header = new ArrayList<>();
        content = new ArrayList<>();
        getDb();
        //db = testDb();
        getTable();
    }

    public void getTable() {

            String[] arr = db.split("\n");
            String line = "";
            String[] kocka = arr[0].split(",");
            for (int i=2;i<kocka.length;i++) {
                if (kocka[i] == null) {
                    header.add("");
                } else {
                    header.add(kocka[i]);
                }
            }
            content = new ArrayList<>();
            for (int i=1;i<arr.length;i++) {
                kocka = arr[i].split(",");
                ArrayList<String> tmp = new ArrayList<>();
                for (int j=2;j<kocka.length;j++) {
                    if (kocka[j] == null) {
                        tmp.add("");
                    } else {
                        tmp.add(kocka[j]);
                    }
                    if (j == 11 && kocka.length == 12) {
                        tmp.add("");
                    }
                }
                content.add(tmp);
            }

            Log.d("CSV READER",header.toString());
            alreadyRead = true;
    }

    private void getDb() {
                Calendar cal = Calendar.getInstance();
                int thisYear = cal.get(Calendar.YEAR);
                int thisMonth = cal.get(Calendar.MONTH)+1;
                String url = "http://www.biboka.net/transrad/tr"+thisYear+thisMonth+".csv";
                Log.d("CSV READER", url);
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = null;
                String vissza = "";
                try {
                    response = client.newCall(request).execute();
                    vissza = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setDb(vissza);
    }

    private String testDb() {
        String dbTest = "november,,ÜGY,ÜGY2,UH,UH2,BIOP,IR,ITO/rtg,CT,CT2,oktat/vizsga,mri\n"+
                "1,k,DO,,,,,,,,,,\n" +
                "2,sze,FK,,FK,SH,DO/BA,,DO/SD,DO/KD,DO,,\n" +
                "3,cs,DP,,FK,JÁ,DP/KD,DP/KD,DP/SD,DO/BA,DO/SH,KA,\n" +
                "4,p,DO,KG,KG,JÁ,DP/SH,,DP/SD,KA/BA,KA/KD,,FK\n" +
                "5,szo,DO,KG,,,,,,,,,\n" +
                "6,v,FK,KG,,,,,,,,,\n" +
                "7,h,OÉ,KG,DP,KD,HE/SH,KA/BA,FLZS/SD,OÉ/SZA,FK,,\n" +
                "8,k,KA,BA,OÉ,JÁ,KA/BA,DP/DO,HE/SD,NA/SZA,FLZS/KD,FLZS,\n" +
                "9,sze,FK,,HE,KD,DO,DO/BA,OÉ/SH,FK/SZA,OÉ/SD,,\n" +
                "10,cs,FK,KD,KA,JÁ,DO/BA,DP/DO/BA,DP/SD,FLZS/KD,FK/SH,KA,\n" +
                "11,p,KA,BA,KG/DO,JÁ,DO/SH,DO/BA,DO/SD,FK/SZA,FK/KD,,FLZS\n" +
                "12,szo,KA,,,,,,,,,,\n" +
                "13,v,DO,BA,,,,,,,,,\n" +
                "14,h,OÉ,KG,DP,KD,FK/BA,DO/KA,KA/SD,OÉ/SZA,FK/SH,,\n" +
                "15,k,KA,KD,OÉ,JÁ,KA/BA,DP/DO,HE/SD,NA/SZA,FLZS/KD,FLZS,\n" +
                "16,sze,FK,,FK,KD,DO/SH,DO/BA,FLZS/SD,OÉ/SZA,HE,,\n" +
                "17,cs,DP,BA,KA,JÁ,DO/BA,DP/DO,DP/SD,FK/KD,FLZS/SH,KA,\n" +
                "18,p ,KA,KD,KG/DO,JÁ,DP/KD,DP/BA,DP/SD,HE/SZA,HE/SH,,HE\n" +
                "19,szo,HE,KD,,,,,,,,,\n" +
                "20,v,HE,KD,,,,,,,,,\n" +
                "21,H,DO,BA,DP,KD,HE,DO/BA,HE/SD,KA/SZA,DO/SH,,\n" +
                "22,K,DO,,OÉ,BA,KA,DO/DP,DP/SD,NA/SZA,HE/KD,FLZS,\n" +
                "23,SZE,HE,,HE,KD,DO,DO/BA,DO/SD,OÉ/SZA,FLZS/SH,,\n" +
                "24,cs,DP,,FLZS,SH,DP/BA,,DP/SD,DP/BA,OÉ,KA,\n" +
                "25,p,HE,,KG,SH,DP,,DP/SD,HE/SZA,HE/BA,,FLZS\n" +
                "26,szo,DP,,,,,,,,,,\n" +
                "27,v,DP,,,,,,,,,,\n" +
                "28,H,DO,,DP/FK,KD,HE/FK,KA/BA,KA/SD,HE/SZA,BA/SH,,\n" +
                "29,K,KA,,OÉ,BA,KA/KD,DP,HE/SD,NA/SZA,FLZS/KD,FLZS,\n" +
                "30,SZ,HE,,HE,KD,DO/BA,DO/BA,FLZS/SD,FK/SZA,OÉ/SH,,\n" +
                ",,ÜGY,ÜGY2,UH,,BIOP,IR,ITO/rtg,CT,CT2,oktat/vizsga,CEUS\n" +
                ",,ÜGY,ÜGY2,,,,,,,,,\n";
        return dbTest;
    }

    public ArrayList<String> getHeader() {
        return  header;
    }

    public ArrayList<ArrayList<String>> getContent() {

        return content;
    }
}
