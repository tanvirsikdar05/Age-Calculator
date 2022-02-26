package com.smart_tech_nk.age_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgeDifferenceActivity extends AppCompatActivity {
     EditText firstname1,firstname2,day1,day2,
     month1,month2,year1,year2;
     TextView agedyear,agedmonth,agedday,prsn1curretnage,
    prsn2curretage,prsnelder,prsnyounger;
     ImageView ivclander1,ivclander2;
     TextView tvclear,tvcalculate,subinvalid1,subinvalid2,undername1,undername2,dayname,dayname2;
     LinearLayout invalidata1,invaliddata2,llAge;
     private DatePickerDialog datePickerDialog;
     private DatePicker datePicker;


    InterstitialAd mInterstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_difference);


        getSupportActionBar().setTitle("Age Differance");



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });


        AdRequest adRequest = new AdRequest.Builder().build();






        //find section

        firstname1=findViewById(R.id.firstpersonname);
        firstname2=findViewById(R.id.secondpersonname);
        day1=findViewById(R.id.edittxtday1);
        day2=findViewById(R.id.edittxtday2);
        month1=findViewById(R.id.edittxtm1);
        month2=findViewById(R.id.edittxtm2);
        year1=findViewById(R.id.edittxty1);
        year2=findViewById(R.id.edittxty2);
        invalidata1=findViewById(R.id.invaliddata);
        invaliddata2=findViewById(R.id.invaliddata2);
        llAge=findViewById(R.id.llAge);
        subinvalid1=findViewById(R.id.subinvaliddata1);
        subinvalid2=findViewById(R.id.subinvaliddata2);
        undername1=findViewById(R.id.undername1);
        undername2=findViewById(R.id.undername2);
        dayname=findViewById(R.id.dayname);
        dayname2=findViewById(R.id.dayname2);

        agedday=findViewById(R.id.agedday);
        agedmonth=findViewById(R.id.agedmonth);
        agedyear=findViewById(R.id.agedyear);
        prsn1curretnage=findViewById(R.id.prsn1curretage);
        prsn2curretage=findViewById(R.id.prsn2curretage);
        prsnelder=findViewById(R.id.prsnelder);
        prsnyounger=findViewById(R.id.prsnyunger);

        ivclander1=findViewById(R.id.ivclanderv1);
        ivclander2=findViewById(R.id.ivclanderv2);
        tvclear=findViewById(R.id.tvClear);
        tvcalculate=findViewById(R.id.tvCalculate);


        //textdayfilter
        textdayfilter(day1);
        textdayfilter(month1);
        textdayfilter(year1);
        textdayfilter(day2);
        textdayfilter(month2);
        textdayfilter(year2);


        datePicker=new DatePicker(AgeDifferenceActivity.this);
        int currentDays=datePicker.getDayOfMonth();
        int currentMonth=(datePicker.getMonth())+1;
        int currentYear=datePicker.getYear();

        //clander 1 click
        ivclander1.setOnClickListener(view -> {
            datePickerDialog=new DatePickerDialog(AgeDifferenceActivity.this,
                    (datePicker, i, i1, i2) -> {
                        day1.setText(String.valueOf(i2));
                        month1.setText(String.valueOf(i1));
                        year1.setText(String.valueOf(i));

                    },currentYear,currentMonth,currentDays);
            datePickerDialog.show();


        });
        //clnader 2 click
        ivclander2.setOnClickListener(view -> {
            datePickerDialog=new DatePickerDialog(AgeDifferenceActivity.this,
                    (datePicker, i, i1, i2) -> {
                        day2.setText(String.valueOf(i2));
                        month2.setText(String.valueOf(i1));
                        year2.setText(String.valueOf(i));

                    },currentYear,currentMonth,currentDays);
            datePickerDialog.show();


        });

//clear button click
        tvclear.setOnClickListener(view -> {
            day1.setText("");
            day2.setText("");
            month1.setText("");
            month2.setText("");
            year1.setText("");
            year2.setText("");
            firstname1.setText("");
            firstname2.setText("");
            invalidata1.setVisibility(View.GONE);
            invaliddata2.setVisibility(View.GONE);
            dayname.setVisibility(View.GONE);
            dayname2.setVisibility(View.GONE);
            llAge.setVisibility(View.GONE);
        });
        //calculat button click
        tvcalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (day1.getText().toString().trim().isEmpty() || month1.getText().toString().trim().isEmpty() ||
                 year1.getText().toString().trim().isEmpty() || day2.getText().toString().trim().isEmpty() ||
                month2.getText().toString().trim().isEmpty() || year2.getText().toString().trim().isEmpty()){
                    Toast.makeText(AgeDifferenceActivity.this,"fill data curretly",
                            Toast.LENGTH_SHORT).show();
                }else {


                    if (Integer.parseInt(month1.getText().toString())>12 ){
                        invalidata1.setVisibility(View.VISIBLE);
                        subinvalid1.setText("Invalid Month");
                        return;

                    }
                    if (Integer.parseInt(month2.getText().toString())>12){
                        invaliddata2.setVisibility(View.VISIBLE);
                        subinvalid2.setText("Invalid Month");
                        return;

                    }
                        undername1.setText(firstname1.getText().toString());
                        undername2.setText(firstname2.getText().toString());
                        invalidata1.setVisibility(View.GONE);
                        invaliddata2.setVisibility(View.GONE);
                        llAge.setVisibility(View.VISIBLE);
                        // age different calculate
                        @SuppressLint("SimpleDateFormat") DateFormat dayFormate = new SimpleDateFormat("dd");
                        @SuppressLint("SimpleDateFormat") DateFormat monthFormate = new SimpleDateFormat("MM");
                        @SuppressLint("SimpleDateFormat") DateFormat yearFormate = new SimpleDateFormat("yyyy");
                        Date date = new Date();


                        //get current date
                        int getcurrentday= Integer.parseInt(dayFormate.format(date));
                        int getcurrentmonth= Integer.parseInt(monthFormate.format(date));
                        int getcurrentyear= Integer.parseInt(yearFormate.format(date));

                        int getprsn1day= Integer.parseInt(day1.getText().toString().trim());
                        int getprsn1month= Integer.parseInt(month1.getText().toString().trim());
                        int getprsn1year= Integer.parseInt(year1.getText().toString().trim());

                        int getprsn2day= Integer.parseInt(day2.getText().toString().trim());
                        int getprsn2month= Integer.parseInt(month2.getText().toString().trim());
                        int getprsn2year= Integer.parseInt(year2.getText().toString().trim());

                        currentagecalculate(getcurrentday,getcurrentmonth,getcurrentyear,getprsn1day,
                                getprsn1month,getprsn1year);
                        currentagecalculate2(getcurrentday,getcurrentmonth,getcurrentyear,
                                getprsn2day,getprsn2month,getprsn2year);
                        //end current date calculate

                        agedifferent(getprsn1day, getprsn1month,getprsn1year,
                                getprsn2day,getprsn2month,getprsn2year);

                        agedistance(getprsn1day,getprsn1month,getprsn1year,
                                getprsn2day,getprsn2month,getprsn2year);








                }
                //Log.d("Month",dateFormat.format(date));
                //String yr= String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
                //Toast.makeText(AgeDifferenceActivity.this,,Toast.LENGTH_SHORT).show();
            }
        });




    }
    public  void textdayfilter(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


               if (editText==day1){

                   if (day1.getText().toString().length()==2){
                       month1.requestFocus();
                   }
               }else if (editText==month1){
                   if (month1.getText().toString().length()==2){
                       year1.requestFocus();
                   }

               } else if (editText == year1) {
                   if (year1.getText().toString().length()==4){

                       day2.requestFocus();
                       String dayt=day1.getText().toString();
                       String montht=month1.getText().toString();
                       String yeart=year1.getText().toString();
                       if (!dayt.isEmpty()|| !montht.isEmpty()){

                           SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
                           String s1=dayt+"-"+montht+"-"+yeart;
                           Date dategg = null;
                           try {
                               dategg = inFormat.parse(s1);
                           } catch (ParseException e) {
                               e.printStackTrace();
                           }
                           SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                           String goal = outFormat.format(dategg);
                           dayname.setVisibility(View.VISIBLE);
                           dayname.setText(goal);
                       }
                   }
               }else if (editText==day2){
                   if (day2.getText().toString().length()==2){
                       month2.requestFocus();
                   }
               }else if (editText==month2){
                   if (month2.getText().toString().length()==2){
                       year2.requestFocus();
                   }
               }else if (editText==year2){

                   String dayt1=day2.getText().toString();
                   String montht1=month2.getText().toString();
                   String yeart1=year2.getText().toString();
                   if (year2.getText().toString().length()==4){

                       SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
                       String s1=dayt1+"-"+montht1+"-"+yeart1;
                       Date dategg2 = null;
                       try {
                           dategg2 = inFormat.parse(s1);
                       } catch (ParseException e) {
                           e.printStackTrace();
                       }
                       SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                       String goal2 = outFormat.format(dategg2);
                       dayname2.setVisibility(View.VISIBLE);
                       dayname2.setText(goal2);

                       View view=AgeDifferenceActivity.this.getCurrentFocus();
                       if (view != null){
                           InputMethodManager mm= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                           mm.hideSoftInputFromWindow(view.getWindowToken(),0);
                       }





                   }
               }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
    }
    public void currentagecalculate(int current_date, int current_month,
                                    int current_year, int birth_date,
                                    int birth_month, int birth_year) {
        int[] month = {31, 28, 31, 30, 31, 30, 31,
                31, 30, 31, 30, 31};


        if (birth_date > current_date) {
            current_month = current_month - 1;
            current_date = current_date + month[birth_month - 1];
        }


        if (birth_month > current_month) {
            current_year = current_year - 1;
            current_month = current_month + 12;
        }


        int calculated_date = current_date - birth_date;
        int calculated_month = current_month - birth_month;
        int calculated_year = current_year - birth_year;
        String showage = calculated_year + " Years " + calculated_month + " Months " +
                calculated_date + " Days";
        prsn1curretnage.setText(showage);



    }
    public void currentagecalculate2(int current_date, int current_month,
                                     int current_year, int birth_date,
                                     int birth_month, int birth_year) {
        int[] month = {31, 28, 31, 30, 31, 30, 31,
                31, 30, 31, 30, 31};


        if (birth_date > current_date) {
            current_month = current_month - 1;
            current_date = current_date + month[birth_month - 1];
        }


        if (birth_month > current_month) {
            current_year = current_year - 1;
            current_month = current_month + 12;
        }


        int calculated_date = current_date - birth_date;
        int calculated_month = current_month - birth_month;
        int calculated_year = current_year - birth_year;
        String showage = calculated_year + " Years " + calculated_month + " Months " + calculated_date + " Days";
        prsn2curretage.setText(showage);



    }
    public void agedifferent(int first_age,int first_month,int first_year,
                             int second_age,int second_month,int second_year){

        if (first_year>second_year){

            prsnelder.setText(firstname2.getText().toString());
            prsnyounger.setText(firstname1.getText().toString());
        }else if (first_year<second_year){
            prsnelder.setText(firstname1.getText().toString());
            prsnyounger.setText(firstname2.getText().toString());

        }
        if (first_year == second_year){
            if (first_month>second_month){
                prsnelder.setText(firstname2.getText().toString());
                prsnyounger.setText(firstname1.getText().toString());

            }else if (first_month<second_month){
                prsnelder.setText(firstname1.getText().toString());
                prsnyounger.setText(firstname2.getText().toString());
            }
        }
        if (first_month==second_month){
            if (first_age>second_age){
                prsnelder.setText(firstname2.getText().toString());
                prsnyounger.setText(firstname1.getText().toString());

            }else if (first_age<second_age){
                prsnelder.setText(firstname1.getText().toString());
                prsnyounger.setText(firstname2.getText().toString());

            }

        }



    }
    public void agedistance(int first_age,int first_month,int first_year,
                            int second_age,int second_month,int second_year){
        String distance_year=String.valueOf(first_year - second_year);
        String distance_month=String.valueOf(first_month - second_month);
        String distance_day=String.valueOf(first_age - second_age);

        agedyear.setText(distance_year.replace("-",""));
        agedmonth.setText(distance_month.replace("-",""));
        agedday.setText(distance_day.replace("-",""));
    }
}