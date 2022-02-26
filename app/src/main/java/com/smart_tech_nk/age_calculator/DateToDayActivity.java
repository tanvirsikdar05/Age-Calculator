package com.smart_tech_nk.age_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateToDayActivity extends AppCompatActivity {
    private TextView todaydates,resulttxtview,resultshowbox;
    private EditText enterdays;
    private DatePicker cdate;
    private int currentDays,currentMonth,currentYear;
    private String crday,crmonth,cryear,currentdec;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_to_day);

        getSupportActionBar().setTitle("Date to Day");



        todaydates=findViewById(R.id.todaydates);
        resulttxtview=findViewById(R.id.resulttxtview);
        resultshowbox=findViewById(R.id.resultshowbox);
        enterdays=findViewById(R.id.enterdays);
        cdate=new DatePicker(DateToDayActivity.this);
       //get current date as integer value
         currentDays=cdate.getDayOfMonth();
         currentMonth=(cdate.getMonth())+1;
         currentYear=cdate.getYear();

         //get current date as String
        crday=String.valueOf(currentDays);
        crmonth=String.valueOf(currentMonth);
        cryear=String.valueOf(currentYear);

        currentdec=crday+"/"+crmonth+"/"+cryear;
        todaydates.setText(currentdec);
        enterdays.requestFocus();

        //auto open keybord
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);






        todaydates.setOnClickListener(view -> {

            datePickerDialog=new DatePickerDialog(DateToDayActivity.this,
                    (datePicker, i, i1, i2) -> {


                        String ddate=i2+"/"+i1+"/"+i;

                        todaydates.setText(ddate);

                    },currentYear,currentMonth,currentDays);
            datePickerDialog.show();



        });

        enterdays.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (enterdays.getText().toString().contains("-")){
                    Toast.makeText(DateToDayActivity.this,"Can't enter this value",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (enterdays.getText().toString().trim().isEmpty()){
                    resulttxtview.setText("Result");
                    resultshowbox.setText("");

                }else {

                    if (enterdays.getText().toString().trim().length()>9){
                        return;
                    }
                    String resultboxuppertxt = "Date After " + enterdays.getText().toString() + " Days";
                    resulttxtview.setText(resultboxuppertxt);


                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    try {
                        c.setTime(sdf.parse(todaydates.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    c.add(Calendar.DATE, Integer.parseInt(enterdays.getText().toString().trim()));  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                    String output1 = sdf1.format(c.getTime());

                    SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                    String fdayname = outFormat.format(c.getTime());
                    String decoretedboxdata=output1+"\n"+fdayname;
                    resultshowbox.setText(decoretedboxdata);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    
}