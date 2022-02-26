package com.smart_tech_nk.age_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {

    private Button agecalculator;
    private Button agerdifferance;
    private Button ageremainder;
    private Button daytodate,leapyear;

    private Button sentsms;


    AlertDialog.Builder ExitDialog;


    private InterstitialAd mInterstitialAd;

    AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        InterstitialAd.load(this, getString(R.string.intartial_id), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
            }
        });




        agecalculator = findViewById(R.id.agecalculator);
        agerdifferance = findViewById(R.id.agerdifferance);
        ageremainder = findViewById(R.id.ageremainder);
        daytodate = findViewById(R.id.daytodate);
        leapyear = findViewById(R.id.btleepyear);

        sentsms = findViewById(R.id.sentsms);


//get permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {


            } else {


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            }
        }




        leapyear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            Intent intent1 = new Intent(MainActivity.this, leapyearactivity.class);
            startActivity(intent1);

            }


        });


        agecalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd != null) {

                    mInterstitialAd.show(MainActivity.this);

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            startActivity(new Intent(MainActivity.this, AgeCalculatoreActivity.class));
                            mInterstitialAd = null;
                            LoadInterstitialAds();

                        }
                    });

                } else {






                    Intent intent1 = new Intent(MainActivity.this, AgeCalculatoreActivity.class);
                startActivity(intent1);

                }

            }
        });




        agerdifferance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (mInterstitialAd != null) {

                    mInterstitialAd.show(MainActivity.this);

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            startActivity(new Intent(MainActivity.this, AgeDifferenceActivity.class));
                            mInterstitialAd = null;
                            LoadInterstitialAds();

                        }
                    });

                } else {





                    Intent intent2 = new Intent(MainActivity.this, AgeDifferenceActivity.class);
                startActivity(intent2);

                }

            }
        });


        ageremainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mInterstitialAd != null) {

                    mInterstitialAd.show(MainActivity.this);

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            startActivity(new Intent(MainActivity.this, AddViewmemberListActivity.class));
                            mInterstitialAd = null;
                            LoadInterstitialAds();

                        }
                    });

                } else {




                Intent intent3 = new Intent(MainActivity.this, AddViewmemberListActivity.class);
                startActivity(intent3);

                }

            }
        });





        daytodate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                Intent intent4 = new Intent(MainActivity.this, DateToDayActivity.class);
                startActivity(intent4);



            }
        });





        sentsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                Intent intent5 = new Intent(MainActivity.this, SmsActivity.class);
                startActivity(intent5);



            }
        });







        }





    public void LoadExitDialog() {

        ExitDialog = new AlertDialog.Builder(MainActivity.this);
        ExitDialog.setMessage("Do You Want Exit!");
        ExitDialog.setIcon(R.drawable.icon);
        ExitDialog.setTitle("Age Calculator");
        ExitDialog.setCancelable(false);
        ExitDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });


        ExitDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();


            }
        });


        AlertDialog alertDialog = ExitDialog.create();
        alertDialog.show();


    }


    @Override
    public void onBackPressed() {
        LoadExitDialog();

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.update_app) {


            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + "com.smart_tech_nk.age_calculator")));
            } catch (ActivityNotFoundException e) {


                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + "com.smart_tech_nk.age_calculator")));
            }

            return super.onOptionsItemSelected(item);


        }





        if (item.getItemId()==R.id.share_app){

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");


            String subject = "AGE Calculator";
            String body = "http://play.google.com/store/apps/details?id=" + "com.smart_tech_nk.age_calculator";


            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(intent,"Share By"));



        }


        if (item.getItemId()==R.id.privacy_policy){


            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://smarttechnk.blogspot.com/p/privacy-policy-age-calculator.html")));




        }





        if (item.getItemId() == R.id.rate_me) {


            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + "com.smart_tech_nk.age_calculator")));
            } catch (ActivityNotFoundException e) {


                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + "com.smart_tech_nk.age_calculator")));
            }

            return super.onOptionsItemSelected(item);


        }










        if (item.getItemId()==R.id.more_app){


            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/developer?id=Smart+App+NK")));




        }












        return true;






    }











    private void LoadInterstitialAds() {
        AdRequest adRequest = new AdRequest.Builder().build();

        //use real admob interstitial ad id instead of test ad id, just replace your real id with test id in strings.xml

        InterstitialAd.load(this, getString(R.string.intartial_id), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
            }
        });
    }










}
