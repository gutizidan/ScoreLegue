package com.example.scorelegue;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private jadwalAdapter adapter;
    private ArrayList<ModelJadwal> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data=response.getJSONArray("events");
                            for (int i=0;i<data.length() ;i++){
                                ModelJadwal model=new ModelJadwal();
                                JSONObject json=data.getJSONObject(i);
                                model.setStrHomeTeam(json.getString("strHomeTeam"));
                                model.setStrAwayTeam(json.getString("strAwayTeam"));
                                model.setStrDate(json.getString("strDate"));
                                dataList.add(model);
                            }
                            adapter= new jadwalAdapter(dataList);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

                            recyclerView.setLayoutManager(layoutManager);

                            recyclerView.setAdapter(adapter);
                            Log.d("hasil","jumlahdata:"+dataList.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("hasil","onError:"+anError.toString());

                    }
                });
    }
}
