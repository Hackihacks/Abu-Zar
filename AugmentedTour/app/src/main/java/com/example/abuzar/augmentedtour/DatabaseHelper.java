package com.example.abuzar.augmentedtour;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nexus computers on 2018-05-20.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DatabaseName="ALLRegLocations";
    public static final String TableName="LocationsDetail";
    public static final String column1="LocationName";
    public static final String column2="LocationLatitude";
    public static final String column3="LocationLongitude";
    public static final String column4="LocationAltitude";
    public static final String column5="LocationCategory";  //assiging values to variables which will use in creating database

    public DatabaseHelper(Context context) {
        super(context, DatabaseName, null, 1);
       // SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //creting table in DB
        sqLiteDatabase.execSQL("create table "+TableName+"("   +column1+  " Text,"    +column2+ " Text,"+ column3+ " Text,"+  column4+ " Text,"+ column5+  " Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TableName);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String name,String altitude,String latitude,String longitude,String cat) //Inserting Data into the table
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentvalues=new ContentValues();
        contentvalues.put(column1,name);
        contentvalues.put(column2,latitude);
        contentvalues.put(column3,longitude);
        contentvalues.put(column4,altitude);
        contentvalues.put(column5,cat);

        long insertionresult=sqLiteDatabase.insert(TableName,null,contentvalues);
        if(insertionresult==-1)
            return false;
        else
            return true;
    }
    public Boolean DeleteData(String s)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

       String whereClause="LocationName=?";


        String[] whereArgs = new String[]{ String.valueOf(s) };

    sqLiteDatabase.delete("LocationsDetail",whereClause,whereArgs);


       return true;


    }

public Cursor showdata( String condition){ //getting data for showing locations on camera preview
    SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    Cursor ref =sqLiteDatabase.rawQuery("select * from "+TableName+" where LocationCategory='"+condition+"'",null);

    return ref;


}

    public Cursor showdata2( ){  //getting data for displaying all registered locations
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor ref =sqLiteDatabase.rawQuery("select * from "+TableName,null);

        return ref;


    }
    public boolean updationData(String name,String altitude,String latitude,String longitude,String cat) //Inserting Data into the table
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentvalues=new ContentValues();
        contentvalues.put(column1,name);
        contentvalues.put(column2,latitude);
        contentvalues.put(column3,longitude);
        contentvalues.put(column4,altitude);
        contentvalues.put(column5,cat);
        String whereClause="LocationName=?";


        String[] whereArgs = new String[]{ String.valueOf(name) };


        long insertionresult=sqLiteDatabase.update("LocationsDetail",contentvalues,whereClause,whereArgs);
        if(insertionresult==-1)
            return false;
        else
            return true;
    }

}
