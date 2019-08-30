package com.example.scorelegue;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class jadwalAdapter extends RecyclerView.Adapter<jadwalAdapter.JadwalViewHolder> {
    private Context mContext;



    private List<ModelJadwal> dataList;
    public jadwalAdapter(Context mContext, List<ModelJadwal> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;

    }

    @NonNull


@Override
public JadwalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.soccer_list, parent, false);
        return new JadwalViewHolder(view);
        }

@Override
public void onBindViewHolder(JadwalViewHolder holder, int position) {
    final ModelJadwal surah = dataList.get(position);


    holder.txtNama.setText(surah.getStrHomeTeam());
    holder.txtNpm.setText(surah.getStrAwayTeam());
    holder.txtNoHp.setText(surah.getStrDate());
    holder.time.setText(surah.getStrTime());
    holder.scoreaway.setText(surah.getIntAwayScore());
    holder.scorehome.setText(surah.getIntHomeScore());
    Glide.with(holder.itemView.getContext())
            .load(surah.getStrThumb())
            .apply(new RequestOptions().override(350, 550))
            .into(holder.img);

        }

@Override
public int getItemCount() {
    return dataList.size();

        }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

public class JadwalViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
    private TextView txtNama, txtNpm, txtNoHp , time , scoreaway , scorehome ,strAwayYellowCards , strAwayLineupGoalkeeper ,strAwayLineupDefense,strAwayLineupMidfield,strHomeYellowCards,strHomeLineupGoalkeeper,strHomeLineupDefense,strHomeLineupMidfield,strEvent;

    public JadwalViewHolder(View itemView) {
        super(itemView);
        txtNama = (TextView) itemView.findViewById(R.id.tv_Leaguehome);
        txtNpm = (TextView) itemView.findViewById(R.id.tv_Leagueaway);
        txtNoHp = (TextView) itemView.findViewById(R.id.date);
        time = (TextView) itemView.findViewById(R.id.atime);
        scorehome = (TextView) itemView.findViewById(R.id.tv_scorehome);
        scoreaway = (TextView) itemView.findViewById(R.id.tv_scoreaway);
        strAwayYellowCards = (TextView) itemView.findViewById(R.id.tv_awayyellowcard);
        strAwayLineupGoalkeeper = (TextView) itemView.findViewById(R.id.tv_AwayLineupGoalkeeper);
        strAwayLineupDefense = (TextView) itemView.findViewById(R.id.tv_AwayLineupDefense);
        strAwayLineupMidfield = (TextView) itemView.findViewById(R.id.tv_AwayLineupMidfield);
        strHomeYellowCards = (TextView) itemView.findViewById(R.id.tv_homeyellowcard);
        strHomeLineupGoalkeeper = (TextView) itemView.findViewById(R.id.tv_lineupgoalkepperhome);
        strHomeLineupDefense = (TextView) itemView.findViewById(R.id.tv_HomeLineupDefense);
        strHomeLineupMidfield  = (TextView) itemView.findViewById(R.id.tv_HomeLineupMidfield);
        strEvent = (TextView) itemView.findViewById(R.id.strEvent);
        img =  itemView.findViewById(R.id.image_view);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, Main2Activity.class);

                i.putExtra("events", dataList.get(getAdapterPosition()).getStrEvent());
                i.putExtra("events2", dataList.get(getAdapterPosition()).getStrThumb());
                i.putExtra("events3", dataList.get(getAdapterPosition()).getStrHomeYellowCards());
                i.putExtra("events4", dataList.get(getAdapterPosition()).getStrAwayLineupDefense());
                i.putExtra("events5", dataList.get(getAdapterPosition()).getStrAwayYellowCards());
                i.putExtra("events6", dataList.get(getAdapterPosition()).getStrHomeLineupMidfield());
                i.putExtra("events7", dataList.get(getAdapterPosition()).getStrAwayLineupGoalkeeper());
                i.putExtra("events8", dataList.get(getAdapterPosition()).getStrAwayLineupMidfield());
                i.putExtra("events9", dataList.get(getAdapterPosition()).getStrHomeLineupGoalkeeper());
                i.putExtra("events10", dataList.get(getAdapterPosition()).getStrHomeLineupDefense());
                mContext.startActivity(i);
            }
        });
    }

}
}
