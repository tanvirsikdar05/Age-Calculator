package com.smart_tech_nk.age_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
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

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.ReadableInstant;
import org.joda.time.Seconds;
import org.joda.time.Weeks;
import org.joda.time.Years;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class AgeCalculatoreActivity extends AppCompatActivity {


    EditText A;
    EditText B;
    EditText C;
    EditText D;
    EditText E;
    EditText F;
    EditText G;
    boolean H = false;
    ImageView I;
    ImageView J;
    String K;
    SharedPreferences L;
    LinearLayout M;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat N = new SimpleDateFormat("yyyy");
    TextView O,P,Q, AA,S,T,U,V,W,X,Y,Z;
    int aA;
    private int aB;
    private int aC;
    private long aE;

    private int aJ;
    private int aK;
    private int aL;
    private int aM;
    TextView aa;
    TextView ab,ac, ad, ae,af;
    TextView ag;
    TextView ah;
    TextView ai;
    TextView aj;
    TextView ak;
    TextView al;
    TextView am;
    TextView an;
    TextView ao;
    TextView ap;
    TextView aq;
    TextView ar;
    TextView as;
    TextView at;
    TextView au;
    TextView av;
    TextView aw;
    TextView ax;
    TextView ay;
    TextView az;
    ArrayList<String> l;
    SimpleDateFormat o = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat p = new SimpleDateFormat("dd-MM");
    DecimalFormat q = new DecimalFormat("00");
    DecimalFormat r = new DecimalFormat("00");
    String s;
    String t;
    String u;
    DatePickerDialog v;
    DatePickerDialog w;
    EditText x;
    EditText y;
    EditText z;


    AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculatore);


        getSupportActionBar().setTitle("Age Calculator");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



////--------------------------------------
        if (getIntent().getExtras() != null) {
            for (String str : getIntent().getExtras().keySet()) {
                if (str.equals("link")) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getIntent().getExtras().getString(str))));
                    finish();
                }
            }
        }
///--------------------------------------


        this.az = findViewById(R.id.tvYears);
        this.aa = findViewById(R.id.tvMonths);
        this.Z = findViewById(R.id.tvDays);
        this.W = findViewById(R.id.tvBMonth);
        this.V = findViewById(R.id.tvBDay);
        this.U = findViewById(R.id.tvAgeYears);
        this.AA = findViewById(R.id.tvAgeMonths);
        this.T = findViewById(R.id.tvAgeWeeks);
        this.O = findViewById(R.id.tvAgeDays);
        this.P = findViewById(R.id.tvAgeHours);
        this.Q = findViewById(R.id.tvAgeMinutes);
        this.S = findViewById(R.id.tvAgeSeconds);
        this.av = findViewById(R.id.tvSeprator1);
        this.aw = findViewById(R.id.tvSeprator2);
        this.ax = findViewById(R.id.tvSeprator3);
        this.ay = findViewById(R.id.tvSeprator4);
        this.X = findViewById(R.id.tvCalculate);
        this.Y = findViewById(R.id.tvClear);
        this.M = (LinearLayout) findViewById(R.id.rlo5);
        this.ab = findViewById(R.id.tvNextDate1);
        this.ad = findViewById(R.id.tvNextDate2);
        this.ae = findViewById(R.id.tvNextDate3);
        this.af = findViewById(R.id.tvNextDate4);
        this.ag = findViewById(R.id.tvNextDate5);
        this.ah = findViewById(R.id.tvNextDate6);
        this.ai = findViewById(R.id.tvNextDate7);
        this.aj = findViewById(R.id.tvNextDate8);
        this.ak = findViewById(R.id.tvNextDate9);
        this.ac = findViewById(R.id.tvNextDate10);
        this.al = findViewById(R.id.tvNextDay1);
        this.an = findViewById(R.id.tvNextDay2);
        this.ao = findViewById(R.id.tvNextDay3);
        this.ap = findViewById(R.id.tvNextDay4);
        this.aq = findViewById(R.id.tvNextDay5);
        this.ar = findViewById(R.id.tvNextDay6);
        this.as = findViewById(R.id.tvNextDay7);
        this.at = findViewById(R.id.tvNextDay8);
        this.au = findViewById(R.id.tvNextDay9);
        this.am = findViewById(R.id.tvNextDay10);
        this.I = findViewById(R.id.ivCalendar1);
        this.J = findViewById(R.id.ivCalendar2);
        this.L = getSharedPreferences("myAgePrefs", 0);
        this.K = this.L.getString("myDateFormate", "dd-MM-yyyy");
        if (this.K.equals("MM-dd-yyyy")) {
            this.y =  findViewById(R.id.etTDay);
            this.x =  findViewById(R.id.etTMonth);
            this.z =  findViewById(R.id.etTYear);
            this.C =  findViewById(R.id.etBDay);
            this.A =  findViewById(R.id.etBMonth);
            this.E =  findViewById(R.id.etBYear);
        } else if (this.K.equals("yyyy-MM-dd")) {
            this.z =  findViewById(R.id.etTDay);
            this.y =  findViewById(R.id.etTMonth);
            this.x =  findViewById(R.id.etTYear);
            this.E =  findViewById(R.id.etBDay);
            this.C =  findViewById(R.id.etBMonth);
            this.A =  findViewById(R.id.etBYear);
        } else {
            this.x =  findViewById(R.id.etTDay);
            this.y =  findViewById(R.id.etTMonth);
            this.z =  findViewById(R.id.etTYear);
            this.A =  findViewById(R.id.etBDay);
            this.C =  findViewById(R.id.etBMonth);
            this.E =  findViewById(R.id.etBYear);
        }
        String str2 = this.L.getString("mySeprator", "-");
        if (str2.equals("/")) {
            this.av.setText("/");
            this.aw.setText("/");
            this.ax.setText("/");
            this.ay.setText("/");
        } else if (str2.equals(".")) {
            this.av.setText(".");
            this.aw.setText(".");
            this.ax.setText(".");
            this.ay.setText(".");
        } else {
            this.av.setText("/");
            this.aw.setText("/");
            this.ax.setText("/");
            this.ay.setText("/");
        }
        this.x.setHint("dd");
        this.y.setHint("mm");
        this.z.setHint("yyyy");
        this.A.setHint("dd");
        this.C.setHint("mm");
        this.E.setHint("yyyy");
        Calendar instance = Calendar.getInstance();
        CharSequence format = this.q.format((long) instance.get(5));
        CharSequence format2 = this.q.format((long) (instance.get(2) + 1));
        CharSequence format3 = this.q.format((long) instance.get(1));
        this.x.setText(format);
        this.y.setText(format2);
        this.z.setText(format3);
        this.A.setText(this.s);
        this.C.setText(this.t);
        this.E.setText(this.u);
        o();
        this.l = new ArrayList();
        this.l.add("Birthday");
        this.l.add("Anniversary");
        this.I.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AgeCalculatoreActivity.this.aB = Integer.valueOf(AgeCalculatoreActivity.this.x.getText().toString().trim());
                AgeCalculatoreActivity.this.aJ = Integer.valueOf(AgeCalculatoreActivity.this.y.getText().toString().trim());
                AgeCalculatoreActivity.this.aL = Integer.valueOf(AgeCalculatoreActivity.this.z.getText().toString().trim());
                AgeCalculatoreActivity.this.aJ = AgeCalculatoreActivity.this.aJ - 1;
                try {
                    AgeCalculatoreActivity.this.v.updateDate(AgeCalculatoreActivity.this.aL, AgeCalculatoreActivity.this.aJ, AgeCalculatoreActivity.this.aB);
                } catch (Exception e) {
                }
                AgeCalculatoreActivity.this.H = true;
                AgeCalculatoreActivity.this.showDialog(0);
            }
        });

        ///-----------------------------------------






        this.J.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    AgeCalculatoreActivity.this.aC = Integer.valueOf(AgeCalculatoreActivity.this.A.getText().toString().trim());
                    AgeCalculatoreActivity.this.aK = Integer.valueOf(AgeCalculatoreActivity.this.C.getText().toString().trim());
                    AgeCalculatoreActivity.this.aM = Integer.valueOf(AgeCalculatoreActivity.this.E.getText().toString().trim());
                    AgeCalculatoreActivity.this.aK = AgeCalculatoreActivity.this.aK - 1;
                } catch (Exception e) {
                    Calendar instance = Calendar.getInstance();
                    AgeCalculatoreActivity.this.aM = instance.get(1);
                    AgeCalculatoreActivity.this.aK = instance.get(2);
                    AgeCalculatoreActivity.this.aC = instance.get(5);
                }
                try {
                    AgeCalculatoreActivity.this.w.updateDate(AgeCalculatoreActivity.this.aM, AgeCalculatoreActivity.this.aK, AgeCalculatoreActivity.this.aC);
                } catch (Exception e2) {
                }
                AgeCalculatoreActivity.this.H = false;
                AgeCalculatoreActivity.this.showDialog(1);
            }
        });
        X.setOnClickListener(new View.OnClickListener() {

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @SuppressLint("WrongConstant")
            public void onClick(View view) {
                try {
                    boolean bl;
                    boolean bl2;
                    AgeCalculatoreActivity.this.x.setText(AgeCalculatoreActivity.this.q.format((Object) Integer.valueOf((String) AgeCalculatoreActivity.this.x.getText().toString().trim())));
                    AgeCalculatoreActivity.this.y.setText(AgeCalculatoreActivity.this.q.format((Object) Integer.valueOf((String) AgeCalculatoreActivity.this.y.getText().toString().trim())));
                    AgeCalculatoreActivity.this.z.setText(AgeCalculatoreActivity.this.q.format((Object) Integer.valueOf((String) AgeCalculatoreActivity.this.z.getText().toString().trim())));
                    AgeCalculatoreActivity.this.A.setText(AgeCalculatoreActivity.this.q.format((Object) Integer.valueOf((String) AgeCalculatoreActivity.this.A.getText().toString().trim())));
                    AgeCalculatoreActivity.this.C.setText(AgeCalculatoreActivity.this.q.format((Object) Integer.valueOf((String) AgeCalculatoreActivity.this.C.getText().toString().trim())));
                    AgeCalculatoreActivity.this.E.setText(AgeCalculatoreActivity.this.q.format((Object) Integer.valueOf((String) AgeCalculatoreActivity.this.E.getText().toString().trim())));
                    String string = AgeCalculatoreActivity.this.x.getText().toString().trim();
                    String string2 = AgeCalculatoreActivity.this.y.getText().toString().trim();
                    String string3 = AgeCalculatoreActivity.this.z.getText().toString().trim();
                    String string4 = AgeCalculatoreActivity.this.A.getText().toString().trim();
                    String string5 = AgeCalculatoreActivity.this.C.getText().toString().trim();
                    String string6 = AgeCalculatoreActivity.this.E.getText().toString().trim();
                    if (AgeCalculatoreActivity.this.a(string + "-" + string2 + "-" + string3)) {
                        bl2 = true;
                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder((Context) AgeCalculatoreActivity.this).create();
                        alertDialog.setMessage(Html.fromHtml((String) "<html><body><b>Invalid Date !</b></body></html>"));
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialogInterface, int n) {
                            }
                        });
                        alertDialog.show();
                        AgeCalculatoreActivity.this.m();
                        AgeCalculatoreActivity.this.o();
                        bl2 = false;
                    }
                    if (AgeCalculatoreActivity.this.a(string4 + "-" + string5 + "-" + string6)) {
                        bl = true;
                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder((Context) AgeCalculatoreActivity.this).create();
                        alertDialog.setMessage(Html.fromHtml((String) "<html><body><b>Invalid Date !</b></body></html>"));
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialogInterface, int n) {
                            }
                        });
                        alertDialog.show();
                        AgeCalculatoreActivity.this.m();
                        bl = false;
                    }
                    if (bl2 && bl) {
                        AgeCalculatoreActivity.this.o();
                    }
                    ((InputMethodManager) AgeCalculatoreActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(AgeCalculatoreActivity.this.E.getWindowToken(), 0);
                } catch (Exception exception) {
                }
                AgeCalculatoreActivity MainActivity1 = AgeCalculatoreActivity.this;
                MainActivity1.aA = 1 + MainActivity1.aA;
                if (AgeCalculatoreActivity.this.aA % 2 == 0 && AgeCalculatoreActivity.this.aA != 0) {
                }
                Toast.makeText(AgeCalculatoreActivity.this, "Calculate Done", Toast.LENGTH_LONG).show();
            }

        });
        this.Y.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(View view) {
                ((InputMethodManager) AgeCalculatoreActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(AgeCalculatoreActivity.this.E.getWindowToken(), 0);
                AgeCalculatoreActivity.this.n();
                AgeCalculatoreActivity MainActivity1 = AgeCalculatoreActivity.this;
                MainActivity1.aA++;
                if (AgeCalculatoreActivity.this.aA % 2 == 0 && AgeCalculatoreActivity.this.aA != 0) {

                }
            }
        });


        //------------------------------



        this.x.addTextChangedListener(this.aF);
        this.y.addTextChangedListener(this.aF);
        this.z.addTextChangedListener(this.aF);
        this.A.addTextChangedListener(this.aF);
        this.C.addTextChangedListener(this.aF);
        this.E.addTextChangedListener(this.aF);
        InputFilter[] inputFilterArr = new InputFilter[]{new InputFilter.LengthFilter(2)};
        this.x.setFilters(inputFilterArr);
        this.y.setFilters(inputFilterArr);
        this.A.setFilters(inputFilterArr);
        this.C.setFilters(inputFilterArr);
        inputFilterArr = new InputFilter[]{new InputFilter.LengthFilter(4)};
        this.z.setFilters(inputFilterArr);
        this.E.setFilters(inputFilterArr);



    }

    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 0:
                try {
                    this.v = new DatePickerDialog(this, this.aH, this.aL, this.aJ, this.aB);
                    return this.v;
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
        }
        try {
            this.w = new DatePickerDialog(this, this.aI, this.aM, this.aK, this.aC);
            return this.w;
        } catch (Exception e2) {
            return null;
        }
    }
///--------------------------------------------

    private TextWatcher aF = new TextWatcher() {
        @SuppressLint("WrongConstant")

        public void afterTextChanged(Editable editable) {
            int parseInt;
            if (AgeCalculatoreActivity.this.K.equals("dd-MM-yyyy")) {
                if (AgeCalculatoreActivity.this.x.getText().hashCode() == editable.hashCode()) {
                    if (editable.length() == 2) {
                        AgeCalculatoreActivity.this.y.requestFocus();
                    }
                } else if (AgeCalculatoreActivity.this.y.getText().hashCode() == editable.hashCode()) {
                    try {
                        parseInt = Integer.parseInt(AgeCalculatoreActivity.this.y.getText().toString());
                        if (editable.length() == 1 && parseInt > 1) {
                            AgeCalculatoreActivity.this.y.setText("0" + AgeCalculatoreActivity.this.y.getText().toString().trim());
                        }
                        if (editable.length() == 2) {
                            AgeCalculatoreActivity.this.z.requestFocus();
                        }
                    } catch (Exception e) {
                    }
                } else if (AgeCalculatoreActivity.this.z.getText().hashCode() == editable.hashCode()) {
                    if (editable.length() == 4) {
                        AgeCalculatoreActivity.this.A.requestFocus();
                    }
                } else if (AgeCalculatoreActivity.this.A.getText().hashCode() == editable.hashCode()) {
                    if (editable.length() == 2) {
                        AgeCalculatoreActivity.this.C.requestFocus();
                        AgeCalculatoreActivity.this.l();
                    }
                } else if (AgeCalculatoreActivity.this.C.getText().hashCode() == editable.hashCode()) {
                    try {
                        parseInt = Integer.parseInt(AgeCalculatoreActivity.this.C.getText().toString());
                        if (editable.length() == 1 && parseInt > 1) {
                            AgeCalculatoreActivity.this.C.setText("0" + AgeCalculatoreActivity.this.C.getText().toString().trim());
                        }
                        if (editable.length() == 2) {
                            AgeCalculatoreActivity.this.E.requestFocus();
                            AgeCalculatoreActivity.this.l();
                        }
                    } catch (Exception e2) {
                    }
                } else if (AgeCalculatoreActivity.this.E.getText().hashCode() == editable.hashCode() && editable.length() == 4) {
                    ((InputMethodManager) AgeCalculatoreActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(AgeCalculatoreActivity.this.E.getWindowToken(), 0);
                    AgeCalculatoreActivity.this.l();
                }
            } else if (AgeCalculatoreActivity.this.K.equals("MM-dd-yyyy")) {
                if (AgeCalculatoreActivity.this.y.getText().hashCode() == editable.hashCode()) {
                    try {
                        parseInt = Integer.parseInt(AgeCalculatoreActivity.this.y.getText().toString());
                        if (editable.length() == 1 && parseInt > 1) {
                            AgeCalculatoreActivity.this.y.setText("0" + AgeCalculatoreActivity.this.y.getText().toString().trim());
                        }
                        if (editable.length() == 2) {
                            AgeCalculatoreActivity.this.x.requestFocus();
                        }
                    } catch (Exception e3) {
                    }
                } else if (AgeCalculatoreActivity.this.x.getText().hashCode() == editable.hashCode()) {
                    if (editable.length() == 2) {
                        AgeCalculatoreActivity.this.z.requestFocus();
                    }
                } else if (AgeCalculatoreActivity.this.z.getText().hashCode() == editable.hashCode()) {
                    if (editable.length() == 4) {
                        AgeCalculatoreActivity.this.C.requestFocus();
                    }
                } else if (AgeCalculatoreActivity.this.C.getText().hashCode() == editable.hashCode()) {
                    try {
                        parseInt = Integer.parseInt(AgeCalculatoreActivity.this.C.getText().toString());
                        if (editable.length() == 1 && parseInt > 1) {
                            AgeCalculatoreActivity.this.C.setText("0" + AgeCalculatoreActivity.this.C.getText().toString().trim());
                        }
                        if (editable.length() == 2) {
                            AgeCalculatoreActivity.this.A.requestFocus();
                            AgeCalculatoreActivity.this.l();
                        }
                    } catch (Exception e4) {
                    }
                } else if (AgeCalculatoreActivity.this.A.getText().hashCode() == editable.hashCode()) {
                    if (editable.length() == 2) {
                        AgeCalculatoreActivity.this.E.requestFocus();
                        AgeCalculatoreActivity.this.l();
                    }
                } else if (AgeCalculatoreActivity.this.E.getText().hashCode() == editable.hashCode() && editable.length() == 4) {
                    ((InputMethodManager) AgeCalculatoreActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(AgeCalculatoreActivity.this.E.getWindowToken(), 0);
                    AgeCalculatoreActivity.this.l();
                }
            } else if (!AgeCalculatoreActivity.this.K.equals("yyyy-MM-dd")) {
            } else {
                if (AgeCalculatoreActivity.this.z.getText().hashCode() == editable.hashCode()) {
                    if (editable.length() == 4) {
                        AgeCalculatoreActivity.this.y.requestFocus();
                    }
                } else if (AgeCalculatoreActivity.this.y.getText().hashCode() == editable.hashCode()) {
                    try {
                        parseInt = Integer.parseInt(AgeCalculatoreActivity.this.y.getText().toString());
                        if (editable.length() == 1 && parseInt > 1) {
                            AgeCalculatoreActivity.this.y.setText("0" + AgeCalculatoreActivity.this.y.getText().toString().trim());
                        }
                        if (editable.length() == 2) {
                            AgeCalculatoreActivity.this.x.requestFocus();
                        }
                    } catch (Exception e5) {
                    }
                } else if (AgeCalculatoreActivity.this.x.getText().hashCode() == editable.hashCode()) {
                    if (editable.length() == 2) {
                        AgeCalculatoreActivity.this.E.requestFocus();
                    }
                } else if (AgeCalculatoreActivity.this.E.getText().hashCode() == editable.hashCode()) {
                    if (editable.length() == 4) {
                        AgeCalculatoreActivity.this.C.requestFocus();
                        AgeCalculatoreActivity.this.l();
                    }
                } else if (AgeCalculatoreActivity.this.C.getText().hashCode() == editable.hashCode()) {
                    try {
                        parseInt = Integer.parseInt(AgeCalculatoreActivity.this.C.getText().toString());
                        if (editable.length() == 1 && parseInt > 1) {
                            AgeCalculatoreActivity.this.C.setText("0" + AgeCalculatoreActivity.this.C.getText().toString().trim());
                        }
                        if (editable.length() == 2) {
                            AgeCalculatoreActivity.this.A.requestFocus();
                            AgeCalculatoreActivity.this.l();
                        }
                    } catch (Exception e6) {
                    }
                } else if (AgeCalculatoreActivity.this.A.getText().hashCode() == editable.hashCode() && editable.length() == 2) {
                    ((InputMethodManager) AgeCalculatoreActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(AgeCalculatoreActivity.this.E.getWindowToken(), 0);
                    AgeCalculatoreActivity.this.l();
                }
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            AgeCalculatoreActivity.this.k();
            AgeCalculatoreActivity.this.l();

        }
    };

//--------------------------

    private TextWatcher aG = new TextWatcher() {
        @SuppressLint("SetTextI18n")
        public void afterTextChanged(Editable editable) {
            int parseInt;
            if (AgeCalculatoreActivity.this.K.equals("dd-MM-yyyy")) {
                if (AgeCalculatoreActivity.this.B.getText().hashCode() == editable.hashCode()) {
                    if (editable.length() == 2) {
                        AgeCalculatoreActivity.this.D.requestFocus();
                    }
                } else if (AgeCalculatoreActivity.this.D.getText().hashCode() == editable.hashCode()) {
                    try {
                        parseInt = Integer.parseInt(AgeCalculatoreActivity.this.D.getText().toString());
                        if (editable.length() == 1 && parseInt > 1) {
                            AgeCalculatoreActivity.this.D.setText("0" + AgeCalculatoreActivity.this.D.getText().toString().trim());
                        }
                        if (editable.length() == 2) {
                            AgeCalculatoreActivity.this.F.requestFocus();
                        }
                    } catch (Exception e) {
                    }
                } else if (AgeCalculatoreActivity.this.F.getText().hashCode() == editable.hashCode() && editable.length() == 4) {
                    AgeCalculatoreActivity.this.G.requestFocus();
                }
            } else if (AgeCalculatoreActivity.this.K.equals("MM-dd-yyyy")) {
                if (AgeCalculatoreActivity.this.D.getText().hashCode() == editable.hashCode()) {
                    try {
                        parseInt = Integer.parseInt(AgeCalculatoreActivity.this.D.getText().toString());
                        if (editable.length() == 1 && parseInt > 1) {
                            AgeCalculatoreActivity.this.D.setText("0" + AgeCalculatoreActivity.this.D.getText().toString().trim());
                        }
                        if (editable.length() == 2) {
                            AgeCalculatoreActivity.this.B.requestFocus();
                        }
                    } catch (Exception e2) {
                    }
                } else if (AgeCalculatoreActivity.this.B.getText().hashCode() == editable.hashCode()) {
                    if (editable.length() == 2) {
                        AgeCalculatoreActivity.this.F.requestFocus();
                    }
                } else if (AgeCalculatoreActivity.this.F.getText().hashCode() == editable.hashCode() && editable.length() == 4) {
                    AgeCalculatoreActivity.this.G.requestFocus();
                }
            } else if (!AgeCalculatoreActivity.this.K.equals("yyyy-MM-dd")) {
            } else {
                if (AgeCalculatoreActivity.this.F.getText().hashCode() == editable.hashCode()) {
                    if (editable.length() == 4) {
                        AgeCalculatoreActivity.this.D.requestFocus();
                    }
                } else if (AgeCalculatoreActivity.this.D.getText().hashCode() == editable.hashCode()) {
                    try {
                        parseInt = Integer.parseInt(AgeCalculatoreActivity.this.D.getText().toString());
                        if (editable.length() == 1 && parseInt > 1) {
                            AgeCalculatoreActivity.this.D.setText("0" + AgeCalculatoreActivity.this.D.getText().toString().trim());
                        }
                        if (editable.length() == 2) {
                            AgeCalculatoreActivity.this.B.requestFocus();
                        }
                    } catch (Exception e3) {
                    }
                } else if (AgeCalculatoreActivity.this.B.getText().hashCode() == editable.hashCode() && editable.length() == 2) {
                    AgeCalculatoreActivity.this.G.requestFocus();
                }
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    };

    //--------------------------


    private DatePickerDialog.OnDateSetListener aH = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            AgeCalculatoreActivity.this.x.setText(AgeCalculatoreActivity.this.q.format((long) i3));
            AgeCalculatoreActivity.this.y.setText(AgeCalculatoreActivity.this.q.format((long) (i2 + 1)));
            AgeCalculatoreActivity.this.z.setText(AgeCalculatoreActivity.this.q.format((long) i));
        }
    };
    private DatePickerDialog.OnDateSetListener aI = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            AgeCalculatoreActivity.this.A.setText(AgeCalculatoreActivity.this.q.format((long) i3));
            AgeCalculatoreActivity.this.C.setText(AgeCalculatoreActivity.this.q.format((long) (i2 + 1)));
            AgeCalculatoreActivity.this.E.setText(AgeCalculatoreActivity.this.q.format((long) i));
        }
    };

    /////--------------------
    private Period a(Date date, Date date2, boolean z) {
        ReadableInstant readableInstant = null;
        ReadableInstant dateTime = date == null ? null : new DateTime((Object) date);
        if (date2 != null) {
            readableInstant = new DateTime((Object) date2);
        }
        Period period = new Period(dateTime, readableInstant);
        if (z) {
            assert readableInstant != null;
            assert dateTime != null;
            this.U.setText(String.valueOf(Years.yearsBetween(dateTime, readableInstant).getYears()));
            this.AA.setText(String.valueOf(Months.monthsBetween(dateTime, readableInstant).getMonths()));
            this.T.setText(String.valueOf(Weeks.weeksBetween(dateTime, readableInstant).getWeeks()));
            this.O.setText(String.valueOf(Days.daysBetween(dateTime, readableInstant).getDays()));
            this.P.setText(String.valueOf(Hours.hoursBetween(dateTime, readableInstant).getHours()));
            try {
                this.Q.setText(String.valueOf(Minutes.minutesBetween(dateTime, readableInstant).getMinutes()));
            } catch (Exception e) {
                this.Q.setText("NA");
            }
            try {
                this.S.setText(String.valueOf(Seconds.secondsBetween(dateTime, readableInstant).getSeconds()));
            } catch (Exception e2) {
                this.S.setText("NA");
            }
        }
        return period;
    }

    ////--------------------

    @SuppressLint("SimpleDateFormat")
    private void b(Date date) {
        this.M.setVisibility(View.VISIBLE);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < 10; i++) {
            try {
                Calendar instance = Calendar.getInstance();
                instance.setTime(date);
                instance.add(1, i);
                Date date2 = new Date(instance.getTimeInMillis());
                arrayList.add(new SimpleDateFormat("dd MMM yyyy").format(date2));
                arrayList2.add((String) DateFormat.format("EEEE", date2));
            } catch (Exception e) {
            }
        }
        this.ab.setText((CharSequence) arrayList.get(0));
        this.ad.setText((CharSequence) arrayList.get(1));
        this.ae.setText((CharSequence) arrayList.get(2));
        this.af.setText((CharSequence) arrayList.get(3));
        this.ag.setText((CharSequence) arrayList.get(4));
        this.ah.setText((CharSequence) arrayList.get(5));
        this.ai.setText((CharSequence) arrayList.get(6));
        this.aj.setText((CharSequence) arrayList.get(7));
        this.ak.setText((CharSequence) arrayList.get(8));
        this.ac.setText((CharSequence) arrayList.get(9));
        this.al.setText((CharSequence) arrayList2.get(0));
        this.an.setText((CharSequence) arrayList2.get(1));
        this.ao.setText((CharSequence) arrayList2.get(2));
        this.ap.setText((CharSequence) arrayList2.get(3));
        this.aq.setText((CharSequence) arrayList2.get(4));
        this.ar.setText((CharSequence) arrayList2.get(5));
        this.as.setText((CharSequence) arrayList2.get(6));
        this.at.setText((CharSequence) arrayList2.get(7));
        this.au.setText((CharSequence) arrayList2.get(8));
        this.am.setText((CharSequence) arrayList2.get(9));
    }

    private void k() {
    }

    private void l() {
    }

    private void m() {
        this.az.setText("00");
        this.aa.setText("00");
        this.Z.setText("00");
        this.W.setText("00");
        this.V.setText("00");
        this.U.setText("");
        this.AA.setText("");
        this.O.setText("");
        this.T.setText("");
        this.P.setText("");
        this.Q.setText("");
        this.S.setText("");
    }

    private void n() {
        this.A.setText("");
        this.C.setText("");
        this.E.setText("");
        this.az.setText("00");
        this.aa.setText("00");
        this.Z.setText("00");
        this.W.setText("00");
        this.V.setText("00");
        this.U.setText("0");
        this.AA.setText("0");
        this.O.setText("0");
        this.T.setText("0");
        this.P.setText("0");
        this.Q.setText("0");
        this.S.setText("0");
        Calendar instance = Calendar.getInstance();
        int i = instance.get(1);
        int i2 = instance.get(2);
        int i3 = instance.get(5);
        try {
            this.v.updateDate(i, i2, i3);
        } catch (Exception e) {
        }
        try {
            this.w.updateDate(i, i2, i3);
        } catch (Exception e2) {
        }
        this.x.setText(this.q.format((long) i3));
        this.y.setText(this.q.format((long) (i2 + 1)));
        this.z.setText(this.q.format((long) i));
        this.M.setVisibility(View.GONE);
        Toast.makeText(this, "resat", Toast.LENGTH_LONG).show();
    }

    //------------------------
    private void o() {
        try {
            int intValue = Integer.valueOf(this.x.getText().toString().trim()).intValue();
            int intValue2 = Integer.valueOf(this.y.getText().toString().trim()).intValue();
            int intValue3 = Integer.valueOf(this.z.getText().toString().trim()).intValue();
            int intValue4 = Integer.valueOf(this.A.getText().toString().trim()).intValue();
            int intValue5 = Integer.valueOf(this.C.getText().toString().trim()).intValue();
            int intValue6 = Integer.valueOf(this.E.getText().toString().trim()).intValue();
            String str = String.valueOf(intValue) + "-" + String.valueOf(intValue2) + "-" + String.valueOf(intValue3);
            String str2 = String.valueOf(intValue4) + "-" + String.valueOf(intValue5) + "-" + String.valueOf(intValue6);
            Date parse = this.o.parse(str);
            Date parse2 = this.o.parse(str2);
            String trim = this.x.getText().toString().trim();
            String trim2 = this.y.getText().toString().trim();
            String trim3 = this.z.getText().toString().trim();
            String trim4 = this.A.getText().toString().trim();
            String trim5 = this.C.getText().toString().trim();
            String trim6 = this.E.getText().toString().trim();
            trim = trim + trim2 + trim3;
            trim2 = trim4 + trim5 + trim6;
            Integer.parseInt(trim);
            Integer.parseInt(trim2);
            AlertDialog create;
            if (parse.getTime() > parse2.getTime()) {
                Period a = parse.compareTo(parse2) < 0 ? a(parse, parse2, true) : a(parse2, parse, true);
                intValue3 = a.getYears();
                intValue4 = a.getMonths();
                intValue = a.getDays() + (a.getWeeks() * 7);
                this.az.setText(this.q.format((long) intValue3));
                this.aa.setText(this.q.format((long) intValue4));
                this.Z.setText(this.q.format((long) intValue));
                a(parse2);
            } else if (parse.getTime() == parse2.getTime()) {
                create = new AlertDialog.Builder(this).create();
                create.setMessage(Html.fromHtml("<html><body><b>Birth date and Today date same !</b></body></html>"));
                create.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                create.show();
            } else {
                create = new AlertDialog.Builder(this).create();
                create.setMessage(Html.fromHtml("<html><body><b>Birth date bigger than Today date !</b></body></html>"));
                create.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                create.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///--------------
    public void a(Date date) {
        Date date2 = null;
        Date date3 = new Date();
        String format = this.p.format(date3);
        String format2 = this.p.format(date);
        Calendar instance = Calendar.getInstance();
        try {
            instance.setTime(this.p.parse(format));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar instance2 = Calendar.getInstance();
        try {
            instance2.setTime(this.p.parse(format2));
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        try {
            date2 = this.o.parse(this.o.format(date3));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            date3 = this.o.parse(String.valueOf(date.getDate()) + "-" + String.valueOf(date.getMonth() + 1) + "-" + String.valueOf(instance.after(instance2) ? Integer.parseInt(this.N.format(date3)) + 1 : Integer.parseInt(this.N.format(date3))));
            Period a = date2.compareTo(date3) < 0 ? a(date2, date3, false) : a(date3, date2, false);
            int weeks = (a.getWeeks() * 7) + a.getDays();
            this.W.setText(this.q.format((long) a.getMonths()));
            this.V.setText(this.q.format((long) weeks));
            b(date3);
        } catch (Exception e32) {
            e32.printStackTrace();
        }
    }




    //----------------

    public boolean a(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(str.trim());
            return true;
        } catch (ParseException e) {
            return false;
        }
    }






}