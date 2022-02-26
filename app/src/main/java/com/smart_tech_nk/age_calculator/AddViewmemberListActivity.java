package com.smart_tech_nk.age_calculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddViewmemberListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private FloatingActionButton addbutton;
    Dialog dialog,dialogdelete;
    DatePickerDialog datepikerdilaogforadd;
    DatePicker datepikerforadd;
    int currentdaysforadd,currentmonthforadd,currentyearforadd;
    String[] items12 =new String[]{"Birthay","Anniversary"};
    ArrayAdapter<String> adapter1;
    Spinner spinner;
    ExampleAdapter mAdapter;
    ArrayList<recyclerviewexdata>  examplelist=new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    private final int IMAGE_REQUEST_CODE=123;
    CircleImageView circleImageView;
    byte[] imagedata;
    EditText edit_txt_day,edit_txt_month,edit_txt_year;
    mydatabase localdatabase;
    String path="";

    ArrayList<String> imagespath=new ArrayList<>();

    private final int notificationId = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addviewmember_list);


        getSupportActionBar().setTitle("Add Member Date");

        addbutton=findViewById(R.id.flottingadicon);
        recyclerView=findViewById(R.id.recyclerView);
        dialog=new Dialog(AddViewmemberListActivity.this);
        dialogdelete=new Dialog(AddViewmemberListActivity.this);
        createnotificationchanel();

        //get permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {


            } else {


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            }
        }


        localdatabase = new mydatabase(this);
        SQLiteDatabase sqLiteDatabase=localdatabase.getWritableDatabase();

        //get data from database
        makerecyclerview();
        loaddatafromlocaldatabase();








        datepikerforadd=new DatePicker(AddViewmemberListActivity.this);
        currentdaysforadd=datepikerforadd.getDayOfMonth();
        currentmonthforadd=(datepikerforadd.getMonth())+1;
        currentyearforadd=datepikerforadd.getYear();

        addbutton.setOnClickListener(view -> showdilog());
    }
    public void showdilog(){
        dialog.setContentView(R.layout.dialog_add_member);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //diloag component declare

        EditText etname=dialog.findViewById(R.id.etName);

         edit_txt_day=dialog.findViewById(R.id.edit_text_day);
         edit_txt_month=dialog.findViewById(R.id.edit_text_month);
         edit_txt_year=dialog.findViewById(R.id.edit_text_year);

        textchangenext(edit_txt_day);
        textchangenext(edit_txt_month);

        Button btnSave=dialog.findViewById(R.id.btnSave);
        Button btnCancel=dialog.findViewById(R.id.btnCancel);
        ImageView ivclander1=dialog.findViewById(R.id.ivCalendar11);
        CardView cameraicon=dialog.findViewById(R.id.cardViewicon);
        spinner=dialog.findViewById(R.id.spinner1);
        circleImageView=dialog.findViewById(R.id.circleImageView);

        adapter1=new ArrayAdapter<>(AddViewmemberListActivity.this,R.layout.spinnerexdesign,
                R.id.txtshower,items12);
        spinner.setAdapter(adapter1);

        cameraicon.setOnClickListener(View ->{
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
           startActivityForResult(photoPickerIntent,IMAGE_REQUEST_CODE);

        });

        btnSave.setOnClickListener(view -> {
            String getname=etname.getText().toString();
            String d1=edit_txt_day.getText().toString();
            String m1=edit_txt_month.getText().toString();
            String y1=edit_txt_year.getText().toString();
            String eventt=spinner.getSelectedItem().toString();
            String decoreteddate=d1+"/"+m1+"/"+y1;



            if (!getname.isEmpty() && !d1.isEmpty() && !m1.isEmpty() && !y1.isEmpty() ){
                String getcurrentage=currentagecalculate(currentdaysforadd,currentmonthforadd,
                        currentyearforadd,Integer.parseInt(d1),Integer.parseInt(m1),Integer.parseInt(y1));

                String getnextage=currentagecalculatetwo(Integer.parseInt(d1),Integer.parseInt(m1),Integer.parseInt(y1)+1
                        ,currentdaysforadd,currentmonthforadd,currentyearforadd);

                //data add in adapter



                //set data in database



                try {
                    storedataindatabase(getname,eventt+":",decoreteddate,
                            getcurrentage,getnextage,"Next "+eventt+":",Integer.parseInt(m1),
                            Integer.parseInt(d1),eventt);

                    //////////////////////////////////////



                } catch (IOException e) {
                    e.printStackTrace();
                }



                dialog.dismiss();


            }else {

                Toast.makeText(AddViewmemberListActivity.this,
                        " fill all data",Toast.LENGTH_SHORT).show();


            }


        });

        ivclander1.setOnClickListener(view -> {

            //open clander

            datepikerdilaogforadd=new DatePickerDialog(AddViewmemberListActivity.this,
                    (datePicker, i, i1, i2) -> {
                        String day1=String.valueOf(i2);
                        String month1=String.valueOf(i1);
                        String year1=String.valueOf(i);
                        edit_txt_day.setText(day1);
                        edit_txt_month.setText(month1);
                        edit_txt_year.setText(year1);


                    },currentyearforadd,currentmonthforadd,currentdaysforadd);
            datepikerdilaogforadd.show();

        });


        btnCancel.setOnClickListener(view -> {

            dialog.dismiss();
        });

        dialog.show();
        
    }

    private void textchangenext(EditText editText1) {
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText1==edit_txt_day){
                    if (edit_txt_day.getText().toString().length()==2){
                        edit_txt_month.requestFocus();
                    }
                }else if (editText1==edit_txt_month){
                    if (edit_txt_month.getText().toString().length()==2){
                        if (Integer.parseInt(edit_txt_month.getText().toString())>12){
                            edit_txt_month.setText("12");
                            edit_txt_year.requestFocus();
                        }else {
                            edit_txt_year.requestFocus();
                        }
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void makerecyclerview(){
        //build recyclerview

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(AddViewmemberListActivity.this);
        mAdapter = new ExampleAdapter(examplelist);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(position -> {

            dialogdelete.setContentView(R.layout.dialog_delete_member);
            dialogdelete.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Button btncancel=dialogdelete.findViewById(R.id.btnCancel);
            Button btnconform=dialogdelete.findViewById(R.id.btnDelete);
            btncancel.setOnClickListener(view -> {
                dialogdelete.dismiss();
            });

            btnconform.setOnClickListener(view -> {

                //delete data

                String itmtyp= String.valueOf( examplelist.get(position).getDbid());
               int deleteres= localdatabase.deletedata(itmtyp);

                if (deleteres>0){
                    examplelist.remove(position);
                    mAdapter.notifyItemRemoved(position);
                    Toast.makeText(AddViewmemberListActivity.this,"Data Delete sucessful"
                    ,Toast.LENGTH_SHORT).show();
                }else {


                    Toast.makeText(AddViewmemberListActivity.this,"Data Delete unsucessful"
                            ,Toast.LENGTH_SHORT).show();
                }
                dialogdelete.dismiss();

            });

            dialogdelete.show();



        });







    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null){
              Uri imagedata9=data.getData();

            byte[] byteimage5= new byte[0];
            try {
               byteimage5 = readBytes(imagedata9);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap bitmapimg4=BitmapFactory.decodeByteArray(byteimage5, 0, byteimage5.length);
            Bitmap bitmap9=Bitmap.createScaledBitmap(bitmapimg4,120,120,false);

            circleImageView.setImageBitmap(bitmap9);


           ByteArrayOutputStream baos=new ByteArrayOutputStream();
           bitmap9.compress(Bitmap.CompressFormat.PNG,100, baos);
             imagedata= baos.toByteArray();







        }
    }


    public String currentagecalculate(int current_date, int current_month,
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
        return showage;
    }

    public void loaddatafromlocaldatabase(){

        //here we load data from database to recyclerview
        localdatabase.getReadableDatabase();
        Cursor cursor=localdatabase.displaydata();
        if (cursor.getCount()==0){
        }else {
            while (cursor.moveToNext()){
                String dbname=cursor.getString(1);
                String dbid=cursor.getString(0);
                String dbeventtype=cursor.getString(2);
                String dbdate=cursor.getString(3);

                String dbnextevent=cursor.getString(6);
                //get day,month,year separate from database date
                String[] datearray=dbdate.split("/");


                //get current date again
                //not essential but i declare again for better result
                DatePicker ndatepicker=new DatePicker(AddViewmemberListActivity.this);
                int cntday=ndatepicker.getDayOfMonth();
                int cntmonth=(ndatepicker.getMonth())+1;
                int cntyear=ndatepicker.getYear();
                ///////////////change there////////

                //get current age here
                String cage=currentagecalculate(cntday,cntmonth,
                        cntyear,Integer.parseInt(datearray[0]),Integer.parseInt(datearray[1]),Integer.parseInt(datearray[2]));
                //calculate next birthday here
                String nxtage=currentagecalculatetwo(Integer.parseInt(datearray[0]),Integer.parseInt(datearray[1]),Integer.parseInt(datearray[2])+1
                        ,cntday,cntmonth,cntyear);

                String imagefromdb = cursor.getString(7);
                byte[] imagebyte=Base64.decode(imagefromdb, Base64.DEFAULT);



               Bitmap bitmapimg=BitmapFactory.decodeByteArray(imagebyte, 0, imagebyte.length);


                  //  Uri uriimage=getImageUri(AddViewmemberListActivity.this,bitmapimg);
                   // Log.i("imagedata",path);
                    //add data in recycler view
                    examplelist.add(new recyclerviewexdata(bitmapimg,dbname,dbeventtype,dbdate,
                            cage,nxtage,dbnextevent,dbid));


            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private void storedataindatabase(String name, String eventype, String date, String nowage, String nexteventage,
                                     String nextevent, int month6, int day6, String eventg) throws IOException {
        if (imagedata==null){
            //if you need to change defould photo ,then change here
            Uri urii = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.default123);
            byte[] byteimage=readBytes(urii);
            Bitmap bitmapimg2=BitmapFactory.decodeByteArray(byteimage, 0, byteimage.length);
            String basestring2= Base64.encodeToString(byteimage, Base64.DEFAULT);
            long rowid= localdatabase.insertdata(name,eventype,date,
                    nowage,nexteventage,nextevent,basestring2);
            if (rowid==-1){
                Toast.makeText(AddViewmemberListActivity.this,"data insert usucessfull",Toast.LENGTH_SHORT).show();
            }else{
                Cursor cursor1=localdatabase.displaydata();
                if (cursor1.moveToLast()){
                    String gettid=cursor1.getString(0);
                    Toast.makeText(AddViewmemberListActivity.this,"data insert sucessfull",Toast.LENGTH_SHORT).show();
                    examplelist.add(new recyclerviewexdata(bitmapimg2,name,eventype,date,
                            nowage,nexteventage,nextevent,gettid));
                    alermset(eventg,month6,day6);
                    mAdapter.notifyDataSetChanged();
                }

                //     }else {
            }




        }else {


            Bitmap bitmapimg3=BitmapFactory.decodeByteArray(imagedata, 0, imagedata.length);
            String basestring= Base64.encodeToString(imagedata, Base64.DEFAULT);

            long rowid= localdatabase.insertdata(name,eventype,date,
                    nowage,nexteventage,nextevent,basestring);
            if (rowid==-1){
                Toast.makeText(AddViewmemberListActivity.this,"data insert usucessfull",Toast.LENGTH_SHORT).show();
            }else{
                Cursor cursor1=localdatabase.displaydata();
                if (cursor1.moveToLast()){
                    String gettid=cursor1.getString(0);
                    Toast.makeText(AddViewmemberListActivity.this,"data insert sucessfull",Toast.LENGTH_SHORT).show();
                    examplelist.add(new recyclerviewexdata(bitmapimg3,name,eventype,date,
                            nowage,nexteventage,nextevent,gettid));
                    alermset(eventg,month6,day6);
                    mAdapter.notifyDataSetChanged();
                    imagedata=null;
                }
            }
        }
    }
    public byte[] readBytes(Uri uri) throws IOException {
        // this dynamically extends to take the bytes you read
        InputStream inputStream = getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        // we need to know how may bytes were read to write them to the byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }
    public void createnotificationchanel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel("tanvirid","Today Event",importance);
            channel.setDescription("Check Today birthday/anniversary");

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void alermset(String Titleu,int monthl,int day1){


        Intent intent = new Intent(AddViewmemberListActivity.this, Alermreciver.class);

        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getBroadcast(
                AddViewmemberListActivity.this, 0, intent,0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);


        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.MONTH, monthl);
        startTime.set(Calendar.DAY_OF_MONTH, day1);
        startTime.set(Calendar.HOUR_OF_DAY, 23);
        startTime.set(Calendar.MINUTE, 10);



        alarmManager.set(AlarmManager.RTC_WAKEUP, startTime.getTimeInMillis(), pendingIntent);

        Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
    }

    public String currentagecalculatetwo(int current_date, int current_month,
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
        return calculated_month + " Months " +
                calculated_date + " Days";
    }







}