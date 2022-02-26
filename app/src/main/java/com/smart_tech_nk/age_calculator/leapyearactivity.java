package com.smart_tech_nk.age_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class leapyearactivity extends AppCompatActivity {
    EditText enteryear;
    TextView tvtotaldays,tvleapyear,jan,feb,march,april,may,jun,julay,aug,sep,oct,nov,dec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Leap Year");


        setContentView(R.layout.activity_leapyearactivity);
        enteryear=findViewById(R.id.etEnterYear);
        tvtotaldays=findViewById(R.id.tvTotalDays);
        tvleapyear=findViewById(R.id.tvLeap);

        jan=findViewById(R.id.tvJanuary);
        feb=findViewById(R.id.tvFeb);
        march=findViewById(R.id.tvMarch);
        april=findViewById(R.id.tvApril);
        may=findViewById(R.id.tvMay);
        jun=findViewById(R.id.tvJune);
        julay=findViewById(R.id.tvJuly);
        aug=findViewById(R.id.tvAug);
        sep=findViewById(R.id.tvSept);
        oct=findViewById(R.id.tvOct);
        nov=findViewById(R.id.tvNov);
        dec=findViewById(R.id.tvDesc);



        enteryear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (enteryear.getText().toString().trim().length()==4){

                    View view=leapyearactivity.this.getCurrentFocus();
                    if (view != null){
                        InputMethodManager mm= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        mm.hideSoftInputFromWindow(view.getWindowToken(),0);
                    }

                    boolean lp_or_not=checkleepyear(Integer.parseInt(enteryear.getText().toString().trim()));
                    if (lp_or_not){
                        tvleapyear.setText("Yes");
                        feb.setText("29");
                        leapyears();
                        tvtotaldays.setText("366");
                    }else {
                        tvleapyear.setText("No");
                       feb.setText("28");
                       leapyears();
                        tvtotaldays.setText("365");
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    static boolean checkleepyear(int year){

     //leap year check
            if (year % 400 == 0)
                return true;
            if (year % 100 == 0)
                return false;

            if (year % 4 == 0)
                return true;
            return false;


    }
    @SuppressLint("SetTextI18n")
    public void leapyears(){

        jan.setText("31");
        march.setText("31");
        april.setText("30");
        may.setText("31");
        jun.setText("30");
        julay.setText("31");
        aug.setText("31");
        sep.setText("30");
        oct.setText("31");
        nov.setText("30");
        dec.setText("31");


    }
}