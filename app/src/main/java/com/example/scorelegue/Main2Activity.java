package com.example.scorelegue;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView;

    TextView tv_nomor, tv_NS, tv_asma, tv_arti, tv_JmlAyat,tv_TurunSurat,tv_urutan,tv_keterangan, title_surah , title_asma ;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.tl_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        this.setTitle("Detail " + getIntent().getStringExtra("events"));
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        //inisialisasi
        title_surah =  findViewById(R.id.strEvent);
        tv_nomor =  findViewById(R.id.tv_homeyellowcard);
        tv_NS =  findViewById(R.id.tv_awayyellowcard);
        tv_asma =  findViewById(R.id.tv_AwayLineupDefense);
        tv_arti =  findViewById(R.id.tv_AwayLineupGoalkeeper);
        tv_JmlAyat =  findViewById(R.id.tv_HomeLineupDefense);
        tv_TurunSurat =  findViewById(R.id.tv_lineupgoalkepperhome);
        tv_urutan =  findViewById(R.id.tv_HomeLineupMidfield);
        tv_keterangan =  findViewById(R.id.tv_AwayLineupMidfield);
         imageView=findViewById(R.id.imageview);

        final ModelJadwal surah = getIntent().getExtras().getParcelable("hasil");
        System.out.println("teasssds " + getIntent().getStringExtra("events"));
        if(getIntent().getExtras() != null){

            String thumb = getIntent().getStringExtra("events2");
            Glide.with(this)
                    .load(thumb)
                    .into(imageView);

            tv_nomor.setText(getIntent().getStringExtra("events3"));
            tv_NS.setText(getIntent().getStringExtra("events5"));
            tv_asma.setText(getIntent().getStringExtra("events4"));
            tv_arti.setText(getIntent().getStringExtra("events7"));
            tv_JmlAyat.setText(getIntent().getStringExtra("events10"));
            tv_TurunSurat.setText(getIntent().getStringExtra("events9"));
            tv_urutan.setText(getIntent().getStringExtra("events6"));
            tv_keterangan.setText(getIntent().getStringExtra("events8"));
            title_surah.setText(getIntent().getStringExtra("events"));

        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
