package com.smart_tech_nk.age_calculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class mydatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student.db";
    private static final String TABLE_NAME = "student_details";
    private static final String ID = "_id";
    private static final String NAME = "Name";
    private static final String EVENTTYPE = "eventype";

    private static final String DDATE = "ddate";
    private static final String NOWAGE = "nowage";
    private static final String NEXTEVENAGE = "nextevenage";
    private static final String NEXTEVEN = "nexteve";


    private static final int DATABASE_VERSION_NO = 1;
    private static final String KEY_IMAGE = "keyimage";
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR,"+EVENTTYPE+" VARCHAR,"+DDATE+" VARCHAR,"+NOWAGE+" VARCHAR,"+NEXTEVENAGE+" VARCHAR,"+NEXTEVEN+" VARCHAR,"+KEY_IMAGE+" VARCHAR);";
    private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String DISDTA="SELECT*FROM "+TABLE_NAME;

    private Context context;

    public mydatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_NO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL(CREATE_TABLE);
        }catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {

            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();
        }
    }
    public  long insertdata(String namee,String eventtype,String date,String nowage,
                            String nexeventage,String nextevent,String image){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,namee);
        contentValues.put(EVENTTYPE,eventtype);
        contentValues.put(DDATE,date);
        contentValues.put(NOWAGE,nowage);
        contentValues.put(NEXTEVENAGE,nexeventage);
        contentValues.put(NEXTEVEN,nextevent);
        contentValues.put(KEY_IMAGE,image);
        long rowidresult=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return  rowidresult;
    }
    public Cursor displaydata(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(DISDTA,null);
        return cursor;
    }
    public int deletedata(String id){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,ID+" = ?",new String[]{id});

    }

}
