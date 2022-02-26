package com.smart_tech_nk.age_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class SmsActivity extends AppCompatActivity {
    AdView mAdView;
    MyAdapter myAdapter12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        getSupportActionBar().setTitle("BirthDay & AnniversaryDay SMS");


        String[] part১=getResources().getStringArray(R.array.পর্ব১);
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter12=new MyAdapter(part১);
        recyclerView.setAdapter(myAdapter12);


        myAdapter12.setOnItemClickListener(position -> {
            ClipboardManager clipboardManager= (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData=ClipData.newPlainText("label",part১[position]);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(this,"Copyed",Toast.LENGTH_SHORT).show();
        });


    }
}