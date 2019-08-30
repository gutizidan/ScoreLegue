package com.example.scorelegue;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private jadwalAdapter adapter;
//
//    private ArrayList<ModelJadwal> dataList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        dataList=new ArrayList<>();
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//
//        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328")
//                .setTag("test")
//                .setPriority(Priority.LOW)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray data=response.getJSONArray("events");
//                            for (int i=0;i<data.length() ;i++){
//                                ModelJadwal model=new ModelJadwal();
//                                JSONObject json=data.getJSONObject(i);
//                                model.setStrHomeTeam(json.getString("strHomeTeam"));
//                                model.setStrAwayTeam(json.getString("strAwayTeam"));
//                                model.setStrDate(json.getString("strDate"));
//                                dataList.add(model);
//                            }
//                            adapter= new jadwalAdapter(dataList);
//
//                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//
//                            recyclerView.setLayoutManager(layoutManager);
//
//                            recyclerView.setAdapter(adapter);
//                            Log.d("hasil","jumlahdata:"+dataList.size());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Log.d("hasil","onError:"+anError.toString());
//
//                    }
//                });
//    }
//}

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rvSurah;
    private jadwalAdapter allLeaguesAdapter;
    private List<ModelJadwal> allLeagueList = new ArrayList<>();
    private ProgressDialog mProgress;
    SwipeRefreshLayout swipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvSurah = findViewById(R.id.recycler_view );
        swipeLayout = findViewById(R.id.swipe_container);

        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        mProgress.show();
        fetchscheduleApi();



        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here
                allLeagueList.clear();
                fetchscheduleApi();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 1500);
                Toast.makeText(getApplicationContext(), "Data is Up to date!", Toast.LENGTH_SHORT).show();// Delay in millis
            }
        });

        setupRecycler();
    }

    private void setupRecycler(){
        allLeaguesAdapter = new jadwalAdapter(this,  allLeagueList);
        rvSurah.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSurah.setHasFixedSize(true);
        rvSurah.setAdapter(allLeaguesAdapter);
    }

    private void fetchscheduleApi() {
        AndroidNetworking.get(Constants.BASE_URL)
                .setTag("leagues")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray hasilList = response.getJSONArray("events");
                            for (int i = 0; i < hasilList.length(); i++) {
                                JSONObject hasil = hasilList.getJSONObject(i);
                                ModelJadwal item = new ModelJadwal();
                                item.setStrHomeTeam(hasil.getString("strHomeTeam"));
                                item.setStrAwayTeam(hasil.getString("strAwayTeam"));
                                item.setStrDate(hasil.getString("strDate"));
                                item.setStrTime(hasil.getString("strTime"));
                                item.setIntAwayScore(hasil.getString("intAwayScore"));
                                item.setIntHomeScore(hasil.getString("intHomeScore"));
                                item.setStrEvent(hasil.getString("strEvent"));
                                item.setStrHomeYellowCards(hasil.getString("strHomeYellowCards"));
                                item.setStrAwayYellowCards(hasil.getString("strAwayYellowCards"));
                                item.setStrHomeLineupGoalkeeper(hasil.getString("strHomeLineupGoalkeeper"));
                                item.setStrAwayLineupGoalkeeper(hasil.getString("strAwayLineupGoalkeeper"));
                                item.setStrAwayLineupMidfield(hasil.getString("strAwayLineupMidfield"));
                                item.setStrHomeLineupMidfield(hasil.getString("strHomeLineupMidfield"));
                                item.setStrAwayLineupDefense(hasil.getString("strAwayLineupDefense"));
                                item.setStrHomeLineupDefense(hasil.getString("strHomeLineupDefense"));

                                item.setStrThumb(hasil.getString("strThumb"));
                                System.out.println("qwert "+hasil.getString("strEvent"));
                                allLeagueList.add(item);
                            }
                            mProgress.dismiss();
                            allLeaguesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                                e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("", "onError: " + anError.getErrorBody());
                        Toast.makeText(getApplicationContext(), Constants.EROR, Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
