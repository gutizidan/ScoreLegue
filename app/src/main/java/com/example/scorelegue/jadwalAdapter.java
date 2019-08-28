package com.example.scorelegue;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class jadwalAdapter extends RecyclerView.Adapter<jadwalAdapter.JadwalViewHolder> {

    private ArrayList<ModelJadwal> dataList;
    public jadwalAdapter(ArrayList<ModelJadwal> dataList) {
        this.dataList = dataList;
    }

    @NonNull


@Override
public JadwalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.soccer_list, parent, false);
        return new JadwalViewHolder(view);
        }

@Override
public void onBindViewHolder(JadwalViewHolder holder, int position) {
    holder.txtNama.setText(dataList.get(position).getStrHomeTeam());
    holder.txtNpm.setText(dataList.get(position).getStrAwayTeam());
    holder.txtNoHp.setText(dataList.get(position).getStrDate());
        }

@Override
public int getItemCount() {
    return (dataList != null) ? dataList.size() : 0;

        }

public class JadwalViewHolder extends RecyclerView.ViewHolder{
    private TextView txtNama, txtNpm, txtNoHp;

    public JadwalViewHolder(View itemView) {
        super(itemView);
        txtNama = (TextView) itemView.findViewById(R.id.tv_Leaguehome);
        txtNpm = (TextView) itemView.findViewById(R.id.tv_Leagueaway);
        txtNoHp = (TextView) itemView.findViewById(R.id.date);
    }
}
}
